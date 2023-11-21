package Context;

public class SharedContext {

<<<<<<< HEAD
    private static int patientIdMissingFile;
    private static int patientIdValid;
    private static String existingPhoneNumber = null;
    private static String existingDateOfBirth = null;
    private static int fileId = 0;
    private static String existingEmail = null;

    public int getPatientIdValid() {
=======

    private static int patientIdMissingFile;
    private static int patientIdValid;

    public static int patientIdValid() {
>>>>>>> origin/Dhivyabranch
        return patientIdValid;
    }

    public static void setPatientIdValid(int patientId) {
        patientIdValid = patientId;
    }
<<<<<<< HEAD

    public int getPatientIdMissingFile() {
=======
    public static int getPatientIdMissingFile() {
>>>>>>> origin/Dhivyabranch
        return patientIdMissingFile;
    }

    public static void setPatientIdMissingFile(int patientId) {
        patientIdMissingFile = patientId;
    }
<<<<<<< HEAD

    public static String getExistingPhoneNumber() {
        return existingPhoneNumber;
    }

    public  static void setExistingPhoneNumber(String phoneNumber) {
        existingPhoneNumber = phoneNumber;
    }
    public static String getDateOfBirth() {
        return existingDateOfBirth;
    }

    public  static void setDateOfBirth(String dateOfBirth) {
        existingDateOfBirth = dateOfBirth;
    }

    public int getFileId() {
        return fileId;
    }

    public  static void setFileId(int fileIdValue) {
        fileId = fileIdValue;
    }

    public static String getExistingEmail() {
        return existingEmail;
    }

    public static void setExistingEmail(String email) {
        existingEmail = email;
    }
}
=======
    
}
>>>>>>> origin/Dhivyabranch
