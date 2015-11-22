package NF.authlab.security;


public class Access {

    private String userName;
    private String[] accessLevels;

    public Access(String userName, String[] accessLevels){
        this.userName = userName;
        this.accessLevels = accessLevels;
    }

    public String getUserName(){
        return userName;
    }

    public void setUserName(String userName){
        this.userName = userName;
    }

    public String[] getAccessLevels(){
        return accessLevels;
    }

    public void setAccessLevels(String[] accessLevels){
        this.accessLevels = accessLevels;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("Access: UserName: ");
        sb.append(this.userName);
        sb.append("Access levels: ");
        for(int i = 0; i < accessLevels.length; i++){
            sb.append(accessLevels[i]);
            sb.append(", ");
        }
        return sb.toString();
    }
}
