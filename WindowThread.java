
public class WindowThread extends Thread {


private window f;
private int x;
private int y;
private TCPConnexion c;


public void setSize(int a, int b)
{
	x=a; y=b;
}

public void link(TCPConnexion d)
{
	c=d;
}
	@Override
	public void run() {
		f=new window(x,y, c);
	
		}
	public void start()
	{
		f.setVisible(clientAuthData.ok);
	}

     public void println(String in)
     {
    	 if (in.equals(TCPClient.OK)) return;
    	 f.addline(in+"\n");
     }
     
     public void kill()
     {
    	 f.dispose();
     }
}