/*
 * A simple TCP client that sends messages to a server and display the message
   from the server. 
 * For use in CPSC 441 lectures
 * Instructor: Prof. Mea Wang
 */


import java.io.*; 
import java.net.*; 

class TCPClient { 
	
	@SuppressWarnings("unused") //set condition to true if you want ascii output
	
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
	
   public static final String EOM="EWFAKJWGAER";
   public static final String TERM="T$#T(QIGAK";
	
 public static   final String OK="EAKQRHWEQRGYWEQORIKJMN"; 

	public static void main(String args[]) throws Exception 
    {  



       
        if (args.length != 2)
        {
            System.out.println("Usage: TCPClient <Server IP> <Server Port>");
            System.exit(1);
        }
    	WindowThread out=new WindowThread();
    	out.setSize(300, 500);
    	
    	
    	TCPConnexion connexion=new TCPConnexion();
    	connexion.args=args;
    	connexion.run();
    	out.link(connexion);
    	out.run();

    	BufferedReader inFromUser = 
    	        new BufferedReader(new InputStreamReader(System.in)); 
    	        String line;
    	
       
    	// Get user input and send to the server
        // Display the echo meesage from the server
       // System.out.println("You are connected to the server!");
      //  System.out.println("If you do not have account, please enter 'newuser' ");
        

	
    	        
        // Get user input and send to the server
        // Display the echo meesage from the server
    	line=EOM;
        
    	clientAuthWindow A=new clientAuthWindow();
        
        while (!line.equals("logout"))
        {
            if (clientAuthData.ok);  // {  System.out.print("Please enter a message to be sent to the server ('logout' to terminate): "); }
            else
            {
            	while (!clientAuthData.ok)
            	{
            	while(!A.ready) Thread.sleep(500);
            	connexion.println(clientAuthData.user);
            	connexion.readLine();
            	connexion.println(clientAuthData.pass);
            	if (connexion.readLine().equals(OK)){ clientAuthData.ok=true; out.start();}
            	A.updateVisibility();
            	}
            	
            }
         
            
        
        
 
         
           while (!connexion.ready())Thread.sleep(500);
            
         
            // Getting response from the server
            
         
            while (true){ 
            	line=connexion.readLine();
            // if (line.equals(OK)) {ok=true; break;} //Auth okay.
			  
			 
		
             if (line.equals(EOM)||line.equals(TERM)) break;
            // System.out.println(line);
             out.println(line);
            }
         
			
			
			if (line.equals(TERM)) break;
			





		
        } //End of the while loop
        
        // Close the socket
      //  System.out.println("You are now disconnect from the server");
        out.kill();
        connexion.close();
	System.exit(0); }          
    
    
  
        
    }
    
 
