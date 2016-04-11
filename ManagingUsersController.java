// John Rogers

package ProcessSale;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JOptionPane;
import java.util.Random;

public class ManagingUsersController implements ActionListener {

    private ManagingUsers users;

    public ManagingUsersController(){}

    // method to add a model to this class
    public void addManageUsers(ManagingUsers m){
	this.users = m;
    }

    public void actionPerformed(ActionEvent ae){
	if(ae.getActionCommand().equals("Add Employee")){
	    String name = JOptionPane.showInputDialog("Enter employee's name");
	    String role = JOptionPane.showInputDialog("Enter employee's role (employee or manager)");
	    String password = JOptionPane.showInputDialog("Enter employees password");
	    Random rnd = new Random();
	    int intID = 10000 + rnd.nextInt(90000);
	    String id = Integer.toString(intID);
	    users.addUser(id, name, role, password);
	}
	else if(ae.getActionCommand().equals("Fire Employee")){
	    String id = JOptionPane.showInputDialog("Enter employee you wish to fire's ID");
	    users.deleteUser(id);
	}
        else if(ae.getActionCommand().equals("Start Register")){
            users.startRegister();
	}
	else if(ae.getActionCommand().equals("Cancel")){
	    
        }

    }


}