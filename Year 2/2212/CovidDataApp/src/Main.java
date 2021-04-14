public class Main {

    public static void main(String[] args) {

        IDandPass idandPass = new IDandPass();

        try {
            Login login = new Login(idandPass.getLoginInfo());
        } catch (Exception e) {
            System.out.println("Login Error");
        }

    }

}
