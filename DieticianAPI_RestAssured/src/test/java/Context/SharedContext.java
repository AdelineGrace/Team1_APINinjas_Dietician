package Context;

public class SharedContext {


    private static int patientIdMissingFile;
    private static int patientIdValid;

    public static int patientIdValid() {
        return patientIdValid;
    }

    public static void setPatientIdValid(int patientId) {
        patientIdValid = patientId;
    }
    public static int getPatientIdMissingFile() {
        return patientIdMissingFile;
    }

    public static void setPatientIdMissingFile(int patientId) {
        patientIdMissingFile = patientId;
    }
    
}