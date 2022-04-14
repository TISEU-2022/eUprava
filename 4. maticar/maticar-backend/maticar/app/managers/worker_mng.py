from maticar.app import models as m_mng
from sqlalchemy.sql import or_
import logging
from datetime import datetime as dt
from sqlalchemy.sql import func

logger = logging.getLogger(__name__)


class WorkerManager(object):
    def __init__(self):
        self.db = next(m_mng.get_db())

    def all_unique(lst):
        return len(lst) == len(set(lst))

    async def get_users(self):
        users = self.db.query(
            m_mng.UserBirthRegister
        ).all()
        return users

    async def get_marriages(self):
        # user_1 = m_mng.UserBirthRegister(
        #     identification_number="0312999800094",
        #     first_name="Ivan",
        #     last_name="Djuraki",
        #     gender="Male",
        #     date_of_birth=dt.now(),
        #     deceased_at=None,
        #     country_of_birth="Serbia",
        #     citizenship="Serbian"
        # )
        # user_2 = m_mng.UserBirthRegister(
        #     identification_number="0312999800093",
        #     first_name="Brajko",
        #     last_name="Radic",
        #     gender="Male",
        #     date_of_birth=dt.now(),
        #     deceased_at=None,
        #     country_of_birth="Serbia",
        #     citizenship="Serbian"
        # )
        # user_3 = m_mng.UserBirthRegister(
        #     identification_number="0312999800091",
        #     first_name="Zunja",
        #     last_name="Marin",
        #     gender="Male",
        #     date_of_birth=dt.now(),
        #     deceased_at=None,
        #     country_of_birth="Serbia",
        #     citizenship="Serbian"
        # )
        # user_4 = m_mng.UserBirthRegister(
        #     identification_number="0312999800092",
        #     first_name="Gepard",
        #     last_name="Orovicki",
        #     gender="Male",
        #     date_of_birth=dt.now(),
        #     deceased_at=None,
        #     country_of_birth="Serbia",
        #     citizenship="Serbian"
        # )
        # self.db.add(user_1)
        # self.db.add(user_2)
        # self.db.add(user_3)
        # self.db.add(user_4)
        # self.db.commit()
        marriages = self.db.query(
            m_mng.UserMarriageRegister.id,
            m_mng.UserMarriageRegister.party_1_id,
            m_mng.UserMarriageRegister.party_2_id,
            m_mng.UserMarriageRegister.witness_1_id,
            m_mng.UserMarriageRegister.witness_2_id,
            m_mng.UserMarriageRegister.created_at,
            m_mng.UserMarriageRegister.divorced_at
        ).all()
        return marriages

    async def divorce_marriage(self, marriage_id):
        marriage = self.db.query(
            m_mng.UserMarriageRegister
        ).filter(
            m_mng.UserMarriageRegister.id == marriage_id,
            m_mng.UserMarriageRegister.divorced_at.is_(None)
        ).first()
        if marriage:
            marriage.divorced_at = func.now()
            self.db.commit()
            return True
        return False

    async def add_marriage(self, marriage):
        try:

            marriage = marriage.__dict__
            iden_number_list = list(marriage.values())

            # STEP 1 check for unique IDS
            if len(set(iden_number_list)) != 4:
                return "User IDs not unique!"

            # STEP 2 check if anyone is dead
            iden_number_check = self.db.query(
                m_mng.UserBirthRegister.identification_number,
            ).filter(
                m_mng.UserBirthRegister.deceased_at.is_(None),
            ).all()
            if iden_number_check:
                iden_number_check = [x[0] for x in iden_number_check]
                if not all(item in iden_number_check for item in iden_number_list):
                    return "Some of the parties are deceased or the identifcation numbers are invalid."

                # STEP 3 check if anyone is married
                marriage_check = self.db.query(
                    m_mng.UserMarriageRegister.party_1_id,
                    m_mng.UserMarriageRegister.party_2_id
                ).filter(
                    or_(
                        m_mng.UserMarriageRegister.party_1_id == marriage['party_1_id'],
                        m_mng.UserMarriageRegister.party_2_id == marriage['party_2_id']
                    ),
                    m_mng.UserMarriageRegister.divorced_at.is_(None)
                ).all()
                if marriage_check:
                    return "Some of the parties are already married."

                # STEP 4 finally make the marriage
                new_marriage = m_mng.UserMarriageRegister(
                    created_at=dt.now(),
                    party_1_id=marriage['party_1_id'],
                    party_2_id=marriage['party_2_id'],
                    witness_1_id=marriage['witness_1_id'],
                    witness_2_id=marriage['witness_2_id']
                )
                self.db.add(new_marriage)
                self.db.commit()
                return "Successfully created the marriage."
        except Exception as e:
            logger.error("Error ocured getting workers. Error {}".format(str(e)))
        return "IDs not found"
