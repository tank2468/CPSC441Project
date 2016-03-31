
public class TCPMonitor extends Thread {
	
	private boolean go;
	private TCPConnexion in;
	private WindowThread out;
	
	public TCPMonitor(TCPConnexion iNn, WindowThread oUt)
	{
		in=iNn;
		out=oUt;
			}
	
	public void run()
	{ 
		go=true;
		while (go)
		{
			if (in.ready()) out.println(in.readLine());
			
		}
				
		
	}

	public void halt()
	{
		go=false;
	}
}
