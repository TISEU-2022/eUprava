from maticar.app import models as m_mng


class UserManager(object):
    def __init__(self):
        self.db = next(m_mng.get_db())

    def get_users(self):
        user1 = m_mng.UserBirthRegister(
            identification_number='0312999800094',
            first_name="Ivan",
            last_name="Djuraki",
            status="ALIVE",
            )

        user1 = self.db.add(user1)
        self.db.commit()
        print(user1)
