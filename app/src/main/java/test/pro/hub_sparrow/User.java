package test.pro.hub_sparrow;

public class User {

    String username;
    String useremail;
    String userpassword;
    String userid;

    public User(String username, String useremail, String userpassword, String userid) {
        this.username = username;
        this.useremail = useremail;
        this.userpassword = userpassword;
        this.userid = userid;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getUseremail() {
        return useremail;
    }

    public void setUseremail(String useremail) {
        this.useremail = useremail;
    }

    public String getUserpassword() {
        return userpassword;
    }

    public void setUserpassword(String userpassword) {
        this.userpassword = userpassword;
    }

    public String getUserid() {
        return userid;
    }

    public void setUserid(String userid) {
        this.userid = userid;
    }

    public User() {

    }
}
