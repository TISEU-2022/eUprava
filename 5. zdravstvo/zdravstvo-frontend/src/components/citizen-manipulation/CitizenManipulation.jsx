import AddBirthCertificate from "./AddBirthCertificate";
import RecordDeceasedCitizen from "./RecordDeceasedCitizen";

const CitizenManipulation = () => {
  return (
    <div className="citizen-manipulation-div">
      <div className="margin-lg">
        <AddBirthCertificate></AddBirthCertificate>
      </div>
      <div className="margin-lg">
        <RecordDeceasedCitizen></RecordDeceasedCitizen>
      </div>
    </div>
  );
};

export default CitizenManipulation;
