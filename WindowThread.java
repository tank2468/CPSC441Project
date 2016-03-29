
public class WindowThread extends Thread {


private window f;
private int x;
private int y;

public void setSize(int a, int b)
{
	x=a; y=b;
}
	@Override
	public void run() {
		f=new window(x,y);
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