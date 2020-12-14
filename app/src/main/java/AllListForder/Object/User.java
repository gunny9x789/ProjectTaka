package AllListForder.Object;

public class User {
    private int idUser;
    private String accountType, accountName, accountPass, userFistName, userLastName, userEmail, userPhone, sex, avatar;

    public User(int idUser, String accountType, String accountName, String accountPass, String userFistName, String userLastName, String userEmail, String userPhone, String sex, String avatar) {
        this.idUser = idUser;
        this.accountType = accountType;
        this.accountName = accountName;
        this.accountPass = accountPass;
        this.userFistName = userFistName;
        this.userLastName = userLastName;
        this.userEmail = userEmail;
        this.userPhone = userPhone;
        this.sex = sex;
        this.avatar = avatar;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public int getIdUser() {
        return idUser;
    }

    public void setIdUser(int idUser) {
        this.idUser = idUser;
    }

    public String getAccountType() {
        return accountType;
    }

    public void setAccountType(String accountType) {
        this.accountType = accountType;
    }

    public String getAccountName() {
        return accountName;
    }

    public void setAccountName(String accountName) {
        this.accountName = accountName;
    }

    public String getAccountPass() {
        return accountPass;
    }

    public void setAccountPass(String accountPass) {
        this.accountPass = accountPass;
    }

    public String getUserFistName() {
        return userFistName;
    }

    public void setUserFistName(String userFistName) {
        this.userFistName = userFistName;
    }

    public String getUserLastName() {
        return userLastName;
    }

    public void setUserLastName(String userLastName) {
        this.userLastName = userLastName;
    }

    public String getUserEmail() {
        return userEmail;
    }

    public void setUserEmail(String userEmail) {
        this.userEmail = userEmail;
    }

    public String getUserPhone() {
        return userPhone;
    }

    public void setUserPhone(String userPhone) {
        this.userPhone = userPhone;
    }

    public String getAvatar() {
        return avatar;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }
}
