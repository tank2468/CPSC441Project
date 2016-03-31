import java.io.BufferedReader;
import java.io.FileReader;
import java.nio.channels.SelectionKey;

public class Auth {

	private static SelectionKey[] keys;
	
	public static SelectionKey getKey(String username) throws NullPointerException
	{
		for (int i=0; i<MAX; i++)
			if (((User) keys[i].attachment()).auth.whoami().equals(username)) return keys[i];
		return null;
	}
	
	
   private static int MAX;

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
	
	
    public static String[] getLoggedIn()
    {
    	return loggedin.Traverse();
    }
    
    private static void tk()
    {
    	for (int i=0; i<MAX; i++)
    		{if (keys[i]==null) System.out.println("null");
    		else System.out.println(((User) keys[i].attachment()).auth.whoami());}
    }
    
    public static void register(SelectionKey key)
    {
    	//System.out.println(key.toString());
    	for(int i=0; i<MAX; i++)
    		if (keys[i]==null) {keys[i]= key; break;}
    	tk();
    }
    
    public static void logout(String user)
    {
    	loggedin.remove(user);
    	for (int i=0; i<MAX; i++)
    		{ if (keys[i]!=null)
    		if (((User) keys[i].attachment()).auth.whoami().equals(user)) keys[i]=null;
    		}
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
		
	}

	public static void Init(String filename)
	{
		/* Initialise user database. first line of file is line count
		 * username and password on separate lines thereafter
		 */
		loggedin=new LinkedList();
	try {
		FileReader f=new FileReader(filename);
		BufferedReader b=new BufferedReader(f);
		 MAX=Integer.parseInt(b.readLine());
		MAX=MAX >> 1;
		System.out.println(MAX);
	    users=new String[MAX];
	    passwords=new String[MAX];
	    int i=0;
	    while (i<MAX)
	    {
	    	users[i]=b.readLine();
	    	passwords[i++]=b.readLine();
	    	
	    }
	    b.close();
	    keys=new SelectionKey[MAX];
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
		in=util.rmLine(in);
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
