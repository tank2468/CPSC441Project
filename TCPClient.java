/*
 * A simple TCP client that sends messages to a server and display the message
   from the server. 
 * For use in CPSC 441 lectures
 * Instructor: Prof. Mea Wang
 */

//THIS IS A TEST

import java.io.*; 
import java.net.*; 

class TCPClient { 
	
	@SuppressWarnings("unused") //set condition to true if you want ascii output
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
	
 public static   final String OK="EAKQRHWEQRGYWEQORIKJMN"; 

	public static void main(String args[]) throws Exception 
    {  

    	final String EOM="EWFAKJWGAER";
       final String TERM="T$#T(QIGAK";
       final String SEND= "sendSignal";

       boolean ok=false;
        if (args.length != 2)
        {
            System.out.println("Usage: TCPClient <Server IP> <Server Port>");
            System.exit(1);
        }
    	WindowThread out=new WindowThread();
    	out.setSize(300, 500);
    	out.run();
    	TCPConnexion connexion=new TCPConnexion();
    	connexion.args=args;
    	connexion.run();

    	BufferedReader inFromUser = 
    	        new BufferedReader(new InputStreamReader(System.in)); 
    	        String line;
       
    	// Get user input and send to the server
        // Display the echo meesage from the server
        System.out.println("You are connected to the server!");
        System.out.println("If you do not have account, please enter 'newuser' ");
        
       // System.out.print("Enter ID: ");
       // String inputID = inFromUser.readLine(); 
       // System.out.print("Enter password: ");
	//String inputpassword = inFromUser.readLine(); 
        
        //System.out.println("Testing to print inputID from the user : " + inputID);
        //System.out.println("Testing to print inputpwd from the user : " + inputpassword);
        
        //line = inFromUser.readLine();         


	
    	        
        // Get user input and send to the server
        // Display the echo message from the server
    	line=EOM;
        boolean pass=false;
        
        
        while (!line.equals("logout"))
        {
            if (ok) 
            System.out.print("Please enter a message to be sent to the server ('logout' to terminate): ");
            else{if (pass)
            	System.out.print("Password=");
            else
            	System.out.print("Username=");
            }
            line = inFromUser.readLine();
            if (!ok){
            if (pass)
            	pass=false;
            else pass=true;
            }
            
            // Send to the server
            connexion.println(line); 
            
          
            
         
            // Getting response from the server
            
         
            while (true){ 
            
            	if (line.equals(OK)) {
            		ok=true; 
            		break;
            	} //Auth okay.
			  
            	line=connexion.readLine();
		
            	if (line.equals(EOM)||line.equals(TERM)) 
            		break;
            
            	out.println(toASCIIString(line));
            }
			
			if (line.equals(TERM)) break;

			if (line.equals(SEND)){
				//String[] file = util.readFile("fileToSend");
				LinkedList readFile = new LinkedList();
				while (!(line.equals(EOM))){
					readFile.append(line);
				}
				readFile.Traverse();
				
				//iterate thru readFile and write to file with filetoSend
				
				
				
				System.out.println("File received");	//confirm file sent
				
				break;
			}
				


		
        } //End of the while loop
        
        // Close the socket
        System.out.println("You are now disconnect from the server");
        out.kill();
        connexion.close();
	System.exit(0); }          
    
    
  
        
    }
    
 
