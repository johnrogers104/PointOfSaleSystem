package ProcessSale;

import java.util.Observable;
import java.util.Observer;
import java.awt.event.ActionListener;
import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.concurrent.TimeUnit;

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
        
        username.setPreferredSize( new Dimension( 100, 20 ) );
        password.setPreferredSize( new Dimension( 100, 20 ) );
        
        // Clear the text after clicking on the box
        username.addMouseListener(new MouseAdapter(){
            @Override
            public void mouseClicked(MouseEvent e){
                username.setText("");
            }
        });
        password.addMouseListener(new MouseAdapter(){
            @Override
            public void mouseClicked(MouseEvent e){
                password.setText("");
            }
        });
        
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
    public void update(Observable subject, Object subjectChange) {
        
        // Check if user is valid
        if (subjectChange instanceof LoginNoGUI) {
            LoginNoGUI login = (LoginNoGUI) subjectChange;
            login.setID(username.getText());
            login.setPassword(password.getText());

            // Check validity
            if (!login.isValidUser()) {
                welcomeTF.setText("Please enter a valid username");
            } else {
                welcomeTF.setText("Welcome " + login.getID() + "!");
                
                // Make this GUI invisible and set up the next one
                f.setVisible(false);
                
                // Open up the manager GUI if they are a manager
                if (login.isManager()) {
                    ManagingUsers manager = new ManagingUsers();
                    ManagingUsersController managerCont = new ManagingUsersController();
                    
                    managerCont.addManageUsers(manager);

                    ManagingUsersGUI manGUI = new ManagingUsersGUI();
                    manGUI.addController(managerCont);
                    manager.addObserver(manGUI);
                    
                } else {
                    // Set up initial register GUI and its controller
                    Register register = new Register(new Sale());
                    RegisterController regCont = new RegisterController();

                    regCont.addRegister(register);

                    RegisterGUI regGUI = new RegisterGUI();
                    regGUI.addController(regCont);
                    register.addObserver(regGUI);
                }
            }
        }   
    }
        
        
}
   
    
    

