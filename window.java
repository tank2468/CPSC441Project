import java.awt.Frame;
import java.awt.TextArea;


@SuppressWarnings("serial")
public class window extends Frame{

	private TextArea out;
	
	@SuppressWarnings("deprecation")
	public window(int x, int y)
	
	{
      out=new TextArea();
		setSize (x,y);
	 this.add(out);


		this.show();
		}
	
	public void addline(String in)
	{
		out.append(in);
		
	}
}
