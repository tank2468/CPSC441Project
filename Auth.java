import java.io.BufferedReader;
import java.io.FileReader;

// user authentication system
// TODO: create file for user/password database
public class Auth {

	//server message that user is authenticated
	public static final String OK="EAKQRHWEQRGYWEQORIKJMN"; 
	
	//list of users and passwords. Indexes are matched
	private static String users[]; 
	private static String passwords[];
	private static LinkedList loggedin;
	
	//control variables
	private boolean pass=false;  //parity control variable, input is to be tested as a password if true
	private String user="ANONYMOUS"; //current logged in user
	private boolean ok=false; //authentication state
	
	//removes newline characters Linux and Windows compatible
	public static String rmLine(String in)
	{
		try{
		in=in.split("\n")[0];
		return in.split("\r")[0];}
		catch (java.lang.ArrayIndexOutOfBoundsException fiw) {return "";}
	}
	
    public static String[] getLoggedIn()
    {
    	return loggedin.Traverse();
    }
    
    public static void logout(String user)
    {
    	loggedin.remove(user);
    }
	
	public Auth() // hardcoded identities until file db is implemented
	{
	/*
		users=new String [2];
		passwords=new String[2];
		users[0]="bob";
		passwords[0]="alice";
		users[1]="user";
		passwords[1]="password";
*/
		loggedin=new LinkedList();
	}

	public static void Init(String filename)
	{
		/* Initialise user database. first line of file is line count
		 * username and password on separate lines thereafter
		 */
	try {
		FileReader f=new FileReader(filename);
		BufferedReader b=new BufferedReader(f);
		int size=Integer.parseInt(b.readLine());
		size=size >> 1;
		System.out.println(size);
	    users=new String[size];
	    passwords=new String[size];
	    int i=0;
	    while (i<size)
	    {
	    	users[i]=b.readLine();
	    	passwords[i++]=b.readLine();
	    	
	    }
	} catch (java.lang.Exception k)
	{
		System.out.println("Error reading credentials from database");
		k.printStackTrace();
		System.exit(500);
	}
	}
	
	public boolean ok()  //is the user authenticated
	{
		return ok;
	}
	
	public String whoami()  
	{
		return user;
	}
	
	//receives a string from select server a candidate username or password depending on parity.
	public Boolean send(String in)
	{
		in=rmLine(in);
		if (pass==false)
		{
			pass=true;
			user=in;
			return false;
		}
		else
		{
			int index=findusr(user);
			if (index==-1) {ok=false; pass=false;  return false;}
			else 
			if (in.equals(passwords[index])) {ok=true; loggedin.append(user); return true;}
			else {ok=false; pass=false; return false;}
		}
		
	}
	
	private int findusr(String in)  //finds corresponding index for password
	{   
		for (int i=0; i<users.length; i++)
			if (users[i].equals(in)) return i;
		return -1;
	}
}
