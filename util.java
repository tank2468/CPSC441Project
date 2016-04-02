import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.io.File;

public class util {
	
    public static void delete(String filename)
    {
    	File target=new File(filename);
    	target.delete();
    }
	
	public static String rmLine(String in)
	{
		try{
		in=in.split("\n")[0];
		return in.split("\r")[0];}
		catch (java.lang.ArrayIndexOutOfBoundsException fiw) {return "";}
	}
	
	public static void LLtoFile(String name, LinkedList LL)
	{
		String[] data=LL.Traverse();
		PrintWriter writer;
		try {
			writer = new PrintWriter(name, "UTF-8");
			for (int i=0; i< data.length; i++)
			writer.println(data[i]);
			writer.close();
		} catch (FileNotFoundException | UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		
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
	{return readFileLL(filename).Traverse();}

	public static LinkedList readFileLL(String filename){
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
		return g;
	    
	} catch (java.lang.Exception k)
	{return new LinkedList();}
	}
	
	}




