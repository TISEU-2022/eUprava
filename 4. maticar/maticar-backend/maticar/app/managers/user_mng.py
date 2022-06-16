from maticar.app import models as m_mng
from datetime import datetime as dt


class UserManager(object):
    def __init__(self):
        self.db = next(m_mng.get_db())

    async def add_birth_certificate(self, birth_certificate):
        birth_certificate = birth_certificate.__dict__
        exists = self.db.query(
            m_mng.UserBirthRegister.identification_number
        ).filter(
            m_mng.UserBirthRegister.identification_number == birth_certificate['identification_number']
        ).first()
        if exists:
            return False
        user = m_mng.UserBirthRegister(**birth_certificate)
        self.db.add(user)
        self.db.commit()
        return True

    async def mark_user_as_deceased(self, identification_number):
        user = self.db.query(
            m_mng.UserBirthRegister
        ).filter(
            m_mng.UserBirthRegister.identification_number == identification_number,
            m_mng.UserBirthRegister.deceased_at.is_(None)
        ).first()
        if user:
            user.deceased_at = dt.now()
            self.db.commit()
            return {"status": "Success"}
        return None

    async def get_user_info(self, identification_number):
        birth_certificate = self.db.query(
            m_mng.UserBirthRegister.identification_number,
            m_mng.UserBirthRegister.first_name,
            m_mng.UserBirthRegister.last_name,
            m_mng.UserBirthRegister.gender,
            m_mng.UserBirthRegister.date_of_birth,
            m_mng.UserBirthRegister.deceased_at,
            m_mng.UserBirthRegister.country_of_birth,
            m_mng.UserBirthRegister.citizenship
        ).filter(
            m_mng.UserBirthRegister.identification_number == identification_number
        ).first()

        if birth_certificate:
            return_object = {}
            return_object['birth_certificate'] = birth_certificate
            birth_certificate = birth_certificate._asdict()
            parents = self.db.query(
                m_mng.UserRelation.parent_id
            ).filter(
                m_mng.UserRelation.child_id == birth_certificate['identification_number']
            ).all()
            if parents:
                parents = [k._asdict()['parent_id'] for k in parents]
                return_object['parents'] = parents
            children = self.db.query(
                m_mng.UserRelation.child_id
            ).filter(
                m_mng.UserRelation.parent_id == birth_certificate['identification_number']
            ).all()
            if children:
                children = [k._asdict()['child_id'] for k in children]
                return_object['children'] = children
            return return_object
        return None

    async def add_parents(self, parents, identification_number):
        parents = parents.__dict__
        child = self.db.query(
            m_mng.UserBirthRegister.identification_number
        ).filter(
            m_mng.UserBirthRegister.identification_number == identification_number
        ).first()

        if not child:
            return "Child identification number does not exist!"

        if parents['parent_1_iden_number'] == parents['parent_2_iden_number']:
            return "Two different identification numbers required!"

        if child.identification_number in parents:
            return "Values must be unique. Child cannot be its own parent."

        parents = self.db.query(
            m_mng.UserBirthRegister.identification_number
        ).filter(
            m_mng.UserBirthRegister.identification_number.in_(parents.values())
        ).all()

        if not parents:
            return "One of the parents doesen't have a birth certificate!"

        already_is_child = self.db.query(
            m_mng.UserRelation.child_id,
            m_mng.UserRelation.parent_id
        ).filter(
            m_mng.UserRelation.child_id == identification_number
        ).all()

        if already_is_child:
            if len(list(already_is_child)) >= 2:
                return "Child already has parents!"

        is_related = self.db.query(
            m_mng.UserRelation.child_id
        ).filter(
            m_mng.UserRelation.parent_id == identification_number
        ).all()

        if is_related:
            for parent in is_related:
                if parent in parents:
                    return "Cannot have cross relations!"

        parents = [k.identification_number for k in parents]
        for parent_id in parents:
            relation = m_mng.UserRelation(
                child_id=identification_number,
                parent_id=parent_id
            )
            self.db.add(relation)
        self.db.commit()
        return True
