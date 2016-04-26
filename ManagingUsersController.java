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
	    EmployeeForm empForm = new EmployeeForm(users);
	}
	else if(ae.getActionCommand().equals("Fire Employee")){
	    EmployeeFireForm fireForm = new EmployeeFireForm(users);
	}
        else if(ae.getActionCommand().equals("Start Register")){
            users.startRegister();
	}
	else if(ae.getActionCommand().equals("Cancel")){
	    System.exit(0);
        }

    }


}