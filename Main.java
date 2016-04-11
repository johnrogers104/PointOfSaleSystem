package ProcessSale;

import java.util.Scanner;

// Class to run the program
public class Main {

    public static void main(String[] args) {

        // Get username and password
        LoginNoGUI login = new LoginNoGUI();
        LoginController loginCont = new LoginController();

        loginCont.addLogin(login);

        LoginGUI loginGUI = new LoginGUI();
        loginGUI.addController(loginCont);
        login.addObserver(loginGUI);
        //login.storage.closeConnection();
    }
}
