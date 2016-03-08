import java.io.*;
import java.sql.*;
import java.util.Scanner;

public class Login216 {
   public static void main (String[] args) throws SQLException, IOException, java.lang.ClassNotFoundException {
         //Code taken from JayDeeBeeSea.java for connection establishment and exception throwing
       
     // Code for connecting to 241 database. Prompts for my password  
     Scanner scanner = new Scanner(System.in); //Declare Scanner
     String input;
     Connection connect = null; 
     Class.forName("oracle.jdbc.driver.OracleDriver");  
     
     boolean passwordF = true;
     do {
      try {      
          String password = scanner.nextLine();
          connect = DriverManager.getConnection (
          "jdbc:oracle:thin:@edgar1.cse.lehigh.edu:1521:cse241","jeg416CSE216", password); //password = jtees216
          System.out.println("~Successful Connection~\n");
          passwordF = false;
     } catch (SQLException e) 
        {     
          System.err.println("Wrong Password. Try Again");
       } 
     } while (passwordF == true);
   
    Statement statement = connect.createStatement();
    int sid;
    String query;
    ResultSet result;
    storeMainMenu();
   }
   
   //This method is the main menu
     public static void storeMainMenu() {
     System.out.println("          !Welcomw to Holy Shirts and Pants!");
     System.out.println("Are you an -Employee- -Manager- -Quit-");
     Scanner scanner = new Scanner(System.in);
     String input = scanner.nextLine().toUpperCase();
     if (input.equals("EMPLOYEE")) {
          //Do something
     } else if (input.equals("MANAGER")) {
          //Do something
     } else if (input.equals("QUIT")) {
          System.out.println("Thank you and have a nice day!");
          System.exit(1);
     }
   }
}
   
   
   
