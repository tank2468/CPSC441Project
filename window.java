import java.awt.Button;
import java.awt.Frame;
import java.awt.TextArea;
import java.awt.TextField;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;



@SuppressWarnings("serial")
public class window extends Frame implements ActionListener{

	private TextArea out;
	private TextField in;
	private Button send;
	private TCPConnexion connexion;


	
	

	public window(int x, int y, TCPConnexion c)
	
	{	
		connexion=c;
		this.addWindowListener(new WindowAdapter(){
            public void windowClosing(WindowEvent we)
        {
                c.println("/logout");
             }
        });
		this.setTitle("CPSC 441 Messenger");
		 in=new TextField();
		 in.setBounds(10, (y-80), x, 40);
		 in.addActionListener(this);
		 this.add(in);
      out=new TextArea();
      out.setBounds(10, 0, x, (y-80) );
		setSize (x,y);
	
	 this.add(out);
	
	 send=new Button("Send:");
	 send.addActionListener(this);
	 this.add(send);
     addline("\n\n");

		
		}
	
	public void addline(String in)
	{
		out.append(in);
	}


	
	
	@SuppressWarnings("deprecation")
	@Override
	public void actionPerformed(ActionEvent e) {
		connexion.println(in.getText());
	in.disable();
	in.setText(null);
	in.enable();
	in.requestFocus();
	}
}
