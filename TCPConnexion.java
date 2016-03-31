import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;


public class TCPConnexion extends Thread{
	
	private Socket clientSocket;
	private PrintWriter outBuffer;
	private BufferedReader inBuffer;
	public String[] args;
	public TCPConnexion(){};
	public void run()
	{
		try{
		 // Initialize a client socket connection to the server
         clientSocket = new Socket(args[0], Integer.parseInt(args[1])); 


		// Initialize input and an output stream for the connection(s)
         outBuffer = 
          new PrintWriter(clientSocket.getOutputStream(), true); 
          
         inBuffer = 
          new BufferedReader(new
          InputStreamReader(clientSocket.getInputStream())); 

        // Initialize user input stream

       
        
	} catch (java.lang.Exception e){e.printStackTrace();}


}
	public boolean ready()
	{
		try {
			return inBuffer.ready();
		} catch (IOException e) {return false;}
	}
	
	public String readLine()
	{
		try {
			return StringProcess(inBuffer.readLine());
		} catch (IOException e) {
			e.printStackTrace(); return null;
		}
	}
	
	public void println(String in)
	{
			outBuffer.println(in);
	}
	
	public void close()
	{
		try {
			clientSocket.close();
		} catch (IOException e) {
			
		}
	}
	
	
	private String StringProcess(String in)
	{  if (true)
	{
	   in.trim();
	   String theString="";
	   int j=-1;
	   for (int i=1;i<in.length();i+=2)
	   { if (in.charAt(i)=='\n') break; else
		   theString+=in.charAt(i);}
	   String out="";
	   try{
	   while (theString.charAt(++j)!=(char) 0)
		   out+=theString.charAt(j);}
	   catch (java.lang.StringIndexOutOfBoundsException afui){}
	   
	      
	   return out;} else return in;
	   
	}

	
}
