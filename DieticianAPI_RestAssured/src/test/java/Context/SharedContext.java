package Context;

public class SharedContext {

    private static int patientIdMissingFile;
    private static int patientIdValid;
    private static String existingPhoneNumber = null;
    private static int fileId = 0;
    private static String existingEmail = null;

    public int getPatientIdValid() {
        return patientIdValid;
    }

    public static void setPatientIdValid(int patientId) {
        patientIdValid = patientId;
    }

    public int getPatientIdMissingFile() {
        return patientIdMissingFile;
    }

    public static void setPatientIdMissingFile(int patientId) {
        patientIdMissingFile = patientId;
    }

    public static String getExistingPhoneNumber() {
        return existingPhoneNumber;
    }

    public void setExistingPhoneNumber(String phoneNumber) {
        existingPhoneNumber = phoneNumber;
    }

    public int getFileId() {
        return fileId;
    }

    public void setFileId(int fileIdValue) {
        fileId = fileIdValue;
    }

    public static String getExistingEmail() {
        return existingEmail;
    }

    public void setExistingEmail(String email) {
        existingEmail = email;
    }
}
