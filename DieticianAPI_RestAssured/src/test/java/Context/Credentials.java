package Context;

public class Credentials {

	
	    private String password;
	    private String userLoginEmail;


	    public Credentials() {
	    }

	    public Credentials(String password, String userLoginEmail) {
	        this.password = password;
	        this.userLoginEmail = userLoginEmail;
	    }


	    public String getPassword() {
	        return password;
	    }

	    public void setPassword(String password) {
	        this.password = password;
	    }

	    public String getUserLoginEmail() {
	        return userLoginEmail;
	    }

	    public void setUserLoginEmail(String userLoginEmail) {
	        this.userLoginEmail = userLoginEmail;
	    }

	}


