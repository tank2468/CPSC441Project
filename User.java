
public class User {

	public Auth auth;
	public LinkedList log;
	public LinkedList recipients;
	
	public void readLog()
	{
		if (auth.whoami().equals("ANONYMOUS")) return;
		log=util.readFileLL(auth.whoami()+".log");
	}
	
	public void writeLog()
	{
		if (auth.whoami().equals("ANONYMOUS")) return;
		util.LLtoFile(auth.whoami()+".log", log);
	}
}
