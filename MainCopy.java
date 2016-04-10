    package ProcessSale;

    import java.util.Scanner;
    import java.util.Observable;
    import java.util.Observer;
    import java.awt.event.ActionListener;
    import javax.swing.*;
    import java.awt.*;
    // Class to run the program
    public class MainCopy extends Observable {

        public static void main(String[] args) {

            // Get username and password
            LoginNoGUI login = new LoginNoGUI();
            LoginController loginCont = new LoginController();
            
            loginCont.addLogin(login);

            LoginGUI loginGUI = new LoginGUI();
            loginGUI.addController(loginCont);
            login.addObserver(loginGUI);
            
            /*
            
            // Perform manager methods
            // manager(input, username, password);

            // Set up initial register GUI and
            Register register = new Register();
            RegisterController regCont = new RegisterController();

            regCont.addRegister(register);

            RegisterGUI regGUI = new RegisterGUI();
            regGUI.addController(regCont);
            register.addObserver(regGUI);
            
            */

        }

        
        public static void manager(String username, String password) {
            Scanner input = new Scanner(System.in);
            ManagingUsers userRole = new ManagingUsers(username);
            String role = userRole.getRoles(username, password);
            System.out.println("Role: " + role);

            boolean proceed = true;
            while (proceed == true) {
                if (role.trim().equals("MANAGER")) { //trim() removes extra whitespace

                    System.out.println("Would you like to ~Add User~ ~Delete User~ ~Transaction~");
                    String managerAnswer = input.nextLine().toUpperCase();

                    if (managerAnswer.equals("ADD USER")) {
                        ManagingUsers addUser = new ManagingUsers(username);
                        System.out.println("What will be the User's new Employee_ID?");
                        String newID = input.nextLine();
                        System.out.println("What will be the User's new Employee Name?");
                        String newName = input.nextLine();
                        System.out.println("What will be the User's new Role?");
                        String newRole = input.nextLine();
                        System.out.println("What will be the User's new Password?");
                        String newPassword = input.nextLine();

                        addUser.addUser(newID, newName, newRole, newPassword);
                        System.out.println("~Succesfully Added User~");
                    } else if (managerAnswer.equals("DELETE USER")) {
                        ManagingUsers deleteUser = new ManagingUsers(username);
                        System.out.println("Enter the user's password for confirmation: ");
                        String deleteUserPassword = input.nextLine();
                        deleteUser.deleteUser(deleteUserPassword);
                        System.out.println("~Succesfully Deleted User~");
                    } else if (managerAnswer.equals("TRANSACTION")) {
                        System.out.println("~Continue Transaction~");
                        proceed = false;
                    } else {
                        System.out.println("Error in Manager Answer");
                    }
                }
            }
        }
    }

        

        

