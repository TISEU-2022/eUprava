from maticar.app import models as m_mng


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

    async def add_parents(self, parents, identification_number):
        parents = parents.__dict__
        child = self.db.query(
            m_mng.UserBirthRegister.identification_number
        ).filter(
            m_mng.UserBirthRegister.identification_number == identification_number
        ).first()
        if not child:
            return {
                    "detail": [
                        {
                            "loc": [
                                "body",
                                "identification_number"
                            ],
                            "msg": "Child identification number does not exist!",
                            "type": "value_error.identification_number"
                        }
                    ]
                }
        if parents['parent_1_iden_number'] == parents['parent_2_iden_number']:
            return {
                    "detail": [
                        {
                            "loc": [
                                "body",
                                "parent_1_iden_number",
                                "parent_2_iden_number"
                            ],
                            "msg": "Two different identification numbers required!",
                            "type": "value_error.unique"
                        }
                    ]
                }
        parents = self.db.query(
            m_mng.UserBirthRegister.identification_number
        ).filter(
            m_mng.UserBirthRegister.identification_number.in_(parents.values())
        ).all()
        if not parents:
            return {
                    "detail": [
                        {
                            "loc": [
                                "body",
                                "parent_1_iden_number",
                                "parent_2_iden_number"
                            ],
                            "msg": "One of the parents doesen't have a birth certificate!",
                            "type": "value_error.unique"
                        }
                    ]
                }
        already_is_child = self.db.query(
            m_mng.UserRelation
        ).filter(
            m_mng.UserRelation.child_id == identification_number
        ).all()
        if already_is_child:
            if list(already_is_child) == 2:
                return {
                        "detail": [
                            {
                                "loc": [
                                    "body",
                                    "parent_1_iden_number",
                                    "parent_2_iden_number"
                                ],
                                "msg": "Child already has parents!",
                                "type": "value_error.unique"
                            }
                        ]
                    }
        parents = [k.identification_number for k in parents]
        for parent_id in parents:
            relation = m_mng.UserRelation(
                child_id=identification_number,
                parent_id=parent_id
            )
            self.db.add(relation)
        self.db.commit()
        return True
