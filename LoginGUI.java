package ProcessSale;

import java.util.Observable;
import java.util.Observer;
import java.awt.event.ActionListener;
import javax.swing.*;
import java.awt.*;

// GUI interface for the rental class
class LoginGUI implements Observer {
    JFrame f;
    JPanel p;
    FlowLayout fl;
    JLabel welcomeTF;
    JButton submit;
    JTextField username, password;
    
    // Constructor to initially set up the GUI
    public LoginGUI() {
        f = new JFrame("Welcome");
        p = new JPanel();
        fl = new FlowLayout(FlowLayout.CENTER);
        username = new JTextField(FlowLayout.CENTER);
        password = new JTextField(FlowLayout.CENTER);
        
        welcomeTF = new JLabel("Please Enter Your EmployeeID and Password: \n");
        welcomeTF.setPreferredSize( new Dimension( 350, 80 ) );
        
        
        
        username = new JTextField("EmployeeID");
        password = new JTextField("Password");
        
        submit = new JButton("Submit");
        
        
        p.setLayout(fl);
        p.add(welcomeTF);
        p.add(submit);
        p.add(username);
        p.add(password);
        
       
        
        f.setSize(400,400);
        f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        f.setContentPane(p);
        f.setVisible(true);
    }
   
    // Add a controller for this view
    public void addController(ActionListener controller) { 
        submit.addActionListener(controller);     
    }

    
    @Override
    public void update(Observable o, Object arg) {
        
            String userName = username.getText();
            String passWord = password.getText();
           
            LoginNoGUI login = new LoginNoGUI(userName, passWord);

            // Check validity
            if (!login.isValidUser()) {
                welcomeTF.setText("Please enter a valid username");
                System.exit(1);
            }
            welcomeTF.setText("Welcome " + username + "!");
        }
        
        
    }
   
    
    

