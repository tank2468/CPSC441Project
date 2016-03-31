import java.awt.Button;
import java.awt.Frame;
import java.awt.TextField;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

@SuppressWarnings("serial")
public class clientAuthWindow extends Frame implements ActionListener{

	public boolean ready=false;
	private TextField user;
	private TextField pass;
public clientAuthWindow()
{
	setSize(320,240);
	this.setTitle("CPSC 441 Messenger Login");
	this.addWindowListener(new WindowAdapter(){
        public void windowClosing(WindowEvent we)
    {
            System.exit(0);
         }
    });
	user=new TextField();
	user.setBounds(150,100, 100, 20);
	TextField USER=new TextField("Username");
	USER.setBounds(47, 100, 100, 20);
	USER.setEditable(false);
	this.add(USER);
	TextField PASS=new TextField("Password");
	PASS.setBounds(47, 150, 100, 20);
	PASS.setEditable(false);
	this.add(PASS);
	this.add(user);
	pass=new TextField();
	pass.setBounds(150, 150, 100, 20);
	pass.setEchoChar('*');
	pass.addActionListener(this);
	this.add(pass);
    Button send=new Button("Click to Login");
    send.addActionListener(this);
    this.add(send);
    updateVisibility();
}

@Override
public void actionPerformed(ActionEvent arg0) {
	clientAuthData.user=user.getText();
	clientAuthData.pass=pass.getText();
	ready=true;
	user.setEnabled(false);
	user.setText(null);
	user.setEnabled(true);
	pass.setEnabled(false);
	pass.setText(null);
	pass.setEnabled(true);

}

public void updateVisibility()
{
	this.setVisible(!clientAuthData.ok);
}
	


}
