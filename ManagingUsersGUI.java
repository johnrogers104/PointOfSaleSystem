// John Rogers

package ProcessSale;

import java.util.Observable;
import java.util.Observer;
import java.awt.event.ActionListener;
import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.concurrent.TimeUnit;

public class ManagingUsersGUI implements Observer {
    JFrame f;
    JPanel p;
    FlowLayout fl;
    JButton addEmployee, fireEmployee, cancel;

    public ManagingUsersGUI(){
	f = new JFrame("Managing Users");
        p = new JPanel();
        fl = new FlowLayout();

	addEmployee = new JButton("Add Employee");
	fireEmployee = new JButton("Fire Employee");
	cancel = new JButton("Cancel");

	p.setLayout(fl);
	p.add(addEmployee);
	p.add(fireEmployee);
	p.add(cancel);

	f.setSize(400,400);
        f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        f.setContentPane(p);
        f.setVisible(true);
    }

    public void update(Observable subject, Object subjectChange) { 
	
    }

    public void addController(ActionListener controller){
	addEmployee.addActionListener(controller);
	fireEmployee.addActionListener(controller);
	cancel.addActionListener(controller);
    }
}