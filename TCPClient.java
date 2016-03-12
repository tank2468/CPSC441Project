/*
 * A simple TCP client that sends messages to a server and display the message
   from the server. 
 * For use in CPSC 441 lectures
 * Instructor: Prof. Mea Wang
 */


import java.io.*; 
import java.net.*; 

class TCPClient { 
	
	public static String toASCIIString(String in)
	{
		if (false){
		int size=in.length();
		int temp;
		String out="";
		for (int i=0; i<size; i++)
		{
			temp=(int) in.charAt(i);
			out+=Integer.toString(temp);
			out+=" ";
		}
		return out;} else return in;
	}
	
	public static boolean isEmpty(String in)
	{
		boolean outcome=false;
		int[] test=new int[in.length()];
		for (int i=0;i<in.length();i++)
		test[i]=Character.getNumericValue(in.charAt(i));
		for (int i=0;i<in.length();i++)
		{
			if ((test[i]>32) &&(test[i]<127))
			{outcome=true; break;}
		}
		return false;
	}
	
	public static String StringProcess(String in)
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

    public static void main(String args[]) throws Exception 
    {  final String EOM="EWFAKJWGAER";
       final String TERM="T$#T(QIGAK";
        if (args.length != 2)
        {
            System.out.println("Usage: TCPClient <Server IP> <Server Port>");
            System.exit(1);
        }

        // Initialize a client socket connection to the server
        Socket clientSocket = new Socket(args[0], Integer.parseInt(args[1])); 


		int clientPort = Integer.parseInt(args[1]); 
		
        // Initialize input and an output stream for the connection(s)
        PrintWriter outBuffer = 
          new PrintWriter(clientSocket.getOutputStream(), true); 
          
        BufferedReader inBuffer = 
          new BufferedReader(new
          InputStreamReader(clientSocket.getInputStream())); 

        // Initialize user input stream

        
		String line; 
        BufferedReader inFromUser = 
        new BufferedReader(new InputStreamReader(System.in)); 
        
        // Get user input and send to the server
        // Display the echo meesage from the server
        System.out.print("Please enter a message to be sent to the server ('logout' to terminate): ");
        line = inFromUser.readLine(); 
        
        
        while (!line.equals("logout"))
        {
            // Send to the server
            outBuffer.println(line); 
            
            
         
            // Getting response from the server
            
         
            while (923==923){ 
             
			 line = inBuffer.readLine(); 
			 line.trim();
		     line=StringProcess(line);
		
             if (line.equals(EOM)||line.equals(TERM)) break;
            
             System.out.println(toASCIIString(line));

            }
         
			
			
			if (line.equals(TERM)) break;
			
             
            System.out.print("Please enter a message to be sent to the server ('logout' to terminate): ");
            line = inFromUser.readLine();





		
        } //End of the while loop
        
        // Close the socket
        System.out.println("You are now disconnect from the server");

        clientSocket.close(); }          
    
    
    public static boolean checkList(String aFile){
		File location = new File("/home/ugd/ascho/Desktop/assignment1");
        File[] FilesInFolder = location.listFiles();
                
		boolean fileHere = false;
		
		for (File fileReq: FilesInFolder){
			if (fileReq.getName().equals(aFile))
						fileHere = true;
		}
		return fileHere;
	}
    
    public static void readFile(String file, int clientPort, long len){
    	BufferedReader br = null;
    	BufferedWriter bw = null;
    	
    	String newFile = file + "-" +clientPort;
    	
    	
    	
        try{	
            br = new BufferedReader(new FileReader(file));
            bw = new BufferedWriter(new FileWriter(newFile));	
            	
            String line = br.readLine();
            
            
            while (line != null) {
				bw.write(line);
            	System.out.println(line);
            	
            	bw.newLine();
            	
            	line = br.readLine();
            }
            
			System.out.println("File saved in " + file + "-" + clientPort + " (" + len + " bytes)");
            
 	    }catch (IOException e) {
            e.printStackTrace();
        }finally {
				try {
					br.close();
					bw.close();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
        }
    }
    
} 
