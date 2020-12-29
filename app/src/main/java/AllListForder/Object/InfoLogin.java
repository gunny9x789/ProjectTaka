package AllListForder.Object;

public class InfoLogin {
    private int idCheck, idUserLogin;
    private String infoLogin;

    public InfoLogin(int idCheck, int idUserLogin, String infoLogin) {
        this.idCheck = idCheck;
        this.idUserLogin = idUserLogin;
        this.infoLogin = infoLogin;
    }

    public int getIdCheck() {
        return idCheck;
    }

    public void setIdCheck(int idCheck) {
        this.idCheck = idCheck;
    }

    public int getIdUserLogin() {
        return idUserLogin;
    }

    public void setIdUserLogin(int idUserLogin) {
        this.idUserLogin = idUserLogin;
    }

    public String getInfoLogin() {
        return infoLogin;
    }

    public void setInfoLogin(String infoLogin) {
        this.infoLogin = infoLogin;
    }
}
