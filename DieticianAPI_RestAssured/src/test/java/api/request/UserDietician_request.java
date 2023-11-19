package api.request;


public class UserDietician_request {
	public int patientId;
	public String fileId;

    public UserDietician_request(int PatiendId,String FileId)
    {
        this.patientId = PatiendId;
        this.fileId = FileId;
              
    }

}
