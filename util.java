import java.io.BufferedReader;
import java.io.FileReader;

public class util {
	

	
	public static String rmLine(String in)
	{
		try{
		in=in.split("\n")[0];
		return in.split("\r")[0];}
		catch (java.lang.ArrayIndexOutOfBoundsException fiw) {return "";}
	}
	
	public static String toASCIIString(String in)
	{
		
		int size=in.length();
		int temp;
		String out="";
		for (int i=0; i<size; i++)
		{
			temp=(int) in.charAt(i);
			out+=Integer.toString(temp);
			out+=" ";
		}
		return out;
	}
	
	public static String[] readFile(String filename)
	{
		/* Initialise user database. first line of file is line count
		 * username and password on separate lines thereafter
		 */
		LinkedList g=new LinkedList();
	try {
		FileReader f=new FileReader(filename);
		BufferedReader b=new BufferedReader(f);
		String s;
		while (true)
		{
			s=b.readLine();
			if (s==null) break;
			else
			g.append(s);
		}
		b.close();
		return g.Traverse();
	    
	} catch (java.lang.Exception k)
	{return null;}
	}
	


}

