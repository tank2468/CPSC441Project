/*
 * A simple TCP select server that accepts multiple connections and echo message back to the clients
 * For use in CPSC 441 lectures
 * Instructor: Prof. Mea Wang
 */

import java.io.*;
import java.net.*;
import java.nio.*;
import java.nio.channels.*;
import java.nio.charset.*;
import java.util.*;

public class SelectServer {
	
	private static byte[] makeEmpty()
	{
		final int ZERO=0;
		byte[] out=new byte[BUFFERSIZE];
		for (int i=0; i< BUFFERSIZE; i++)
			out[i]=(byte)ZERO;
		return out;
	}
		private static ByteBuffer inBuffer=null;
		
		private static void End()
		{
			Send("EWFAKJWGAER");
		}
		
		private static void Ok()
		{
			Send(Auth.OK);
		}
	
		
		private static void transmit(String[] data, String username)
		{
			try{
			SelectionKey f=Auth.getKey(username);
		   SocketChannel fchannel=(SocketChannel)f.channel();
			
		   SocketChannel dchannel=cchannel;
		   cchannel=fchannel;
		   for (int i=0; i<data.length; i++)
			   {Send(data[i]); ((User) f.attachment()).log.append(data[i]);}
		   End();
		   cchannel=dchannel;
		   
		} catch (java.lang.NullPointerException d) { Send("Error user offline: " + username); End();}
	
		}
		
		

		
		private static void Send(String in)
		{   in+=(char)10;
		char[] array=in.toCharArray();
			 
             CharBuffer outBuffer;
           
           inBuffer.clear();
           inBuffer.put(makeEmpty());
           inBuffer.clear();
           //outBuffer=inBuffer.asCharBuffer();
           //outBuffer.clear();
        
           //outBuffer.put(outt);
           for (int i=0;i<array.length;i++)
        	   inBuffer.putChar(array[i]);
           inBuffer.flip();
           try {
			cchannel.write(inBuffer);
		} catch (IOException e) {
			
		}
        
		}
		
		private static void removeLineFromFile(String file, String lineToRemove) {
 
    try {
 
      File inFile = new File(file);
      
      if (!inFile.isFile()) {
        System.out.println("Parameter is not an existing file");
        
        return;
      }
       
      //Construct the new file that will later be renamed to the original filename. 
      File tempFile = new File(inFile.getAbsolutePath() + ".tmp");
      
      BufferedReader br = new BufferedReader(new FileReader(file));
      PrintWriter pw = new PrintWriter(new FileWriter(tempFile));
      
      String line = null;
 
      //Read from the original file and write to the new 
      //unless content matches data to be removed.
      
							
      int friendcheckcounter = 0;
      
      while ((line = br.readLine()) != null) {
        
        if (!line.trim().equals(lineToRemove)) {
			
          pw.println(line);
          pw.flush();
          
         
        }
        else{
			friendcheckcounter++;
			Send("delete successful");
		//	End();
		}
        
      }
      
      while(friendcheckcounter < 1){
		  Send("no Friends");
		  break;
		 
		
	  }
	  
      
      pw.close();
      br.close();
      
      //Delete the original file
      if (!inFile.delete()) {
        System.out.println("Could not delete file");
        return;
      } 
      
      //Rename the new file to the filename the original file had.
      if (!tempFile.renameTo(inFile))
        System.out.println("Could not rename file");
      
    }
    catch (FileNotFoundException ex) {
      ex.printStackTrace();
    }
    catch (IOException ex) {
      ex.printStackTrace();
    }
  }
  
  
  
  private static void duplicateCheckForAdd(String file, String lineToRemove) {
 
		try {
 
		  File inFile = new File(file);
		  
		  if (!inFile.isFile()) {
			System.out.println("Parameter is not an existing file");
			
			return;
		  }
		   
		  //Construct the new file that will later be renamed to the original filename. 
		  File tempFile = new File(inFile.getAbsolutePath() + ".tmp");
		  
		  BufferedReader br = new BufferedReader(new FileReader(file));
		  PrintWriter pw = new PrintWriter(new FileWriter(tempFile));
		  
		  String line = null;
	 
		  //Read from the original file and write to the new 
		  //unless content matches data to be removed.
		  
								
		  int friendcheckcounter = 0;
		  
		  while ((line = br.readLine()) != null) {
			
			if (!line.trim().equals(lineToRemove)) {
				
			  pw.println(line);
			  pw.flush();
			  
			 
			}
			else{
				friendcheckcounter++;
				Send("You already added request user!");
			//	End();
			}
			
		  }
		  String filename1 = file;
		  String adduserName = lineToRemove;
		  
		  while(friendcheckcounter == 0){
			  addFriend(filename1, lineToRemove);
			  Send("You added succesfully");
			  break;
			 
			
		  }
	  
      
		 pw.close();
		  br.close();
		  /*
		  //Delete the original file
		  if (!inFile.delete()) {
			System.out.println("Could not delete file");
			return;
		  } 
		  
		  //Rename the new file to the filename the original file had.
		  if (!tempFile.renameTo(inFile))
			System.out.println("Could not rename file");
		  */
		}
		catch (FileNotFoundException ex) {
		  ex.printStackTrace();
		}
		catch (IOException ex) {
		  ex.printStackTrace();
		}
	  }	
	
  
  
  private static void addFriend(String file, String lineToAdd) {

		
      try{
			String filename= file;
			FileWriter fw = new FileWriter(filename,true); //the true will append the new data
			fw.write(lineToAdd+"\n");//appends the string to the file
			fw.close();
		}
	catch(IOException ioe){
			System.err.println("IOException: " + ioe.getMessage());
		}
	  }	
		
  
  
  
  
  
  
		
		
		
		
		
		
		private static  SocketChannel cchannel;
    public static int BUFFERSIZE = 1500;

	@SuppressWarnings("deprecation")
	public static void main(String args[]) throws Exception 
    {
        if (args.length != 1)
        {
            System.out.println("Usage: SelectServer <Listening Port>");
            System.exit(1);
        }
        
        final int PORT=Integer.parseInt(args[0]);
        UDPextension UDPServer=new UDPextension(PORT);
        UDPServer.start();
        Auth.Init("users.db");
        
        
        // Initialize buffers and coders for channel receive and send
        System.out.println(args[0]);
        String line = "";
        Charset charset = Charset.forName( "us-ascii" );  
        CharsetDecoder decoder = charset.newDecoder();  
        CharsetEncoder encoder = charset.newEncoder();
   
        CharBuffer cBuffer = null;
        int bytesSent, bytesRecv;     // number of bytes sent or received
        Socket clientSocket = null;
        
        // Initialize the selector
        Selector selector = Selector.open();

        // Create a server channel and make it non-blocking
        ServerSocketChannel channel = ServerSocketChannel.open();
        channel.configureBlocking(false);
        
     

        // Get the port number and bind the socket
        InetSocketAddress isa = new InetSocketAddress(PORT);
        channel.socket().bind(isa);

        // Register that the server selector is interested in connection requests
        channel.register(selector, SelectionKey.OP_ACCEPT);
		
		
		
		
        // Wait for something happen among all registered sockets
        try {
            boolean terminated = false;
            while (!terminated) 
            {
                if (selector.select(500) < 0)
                {
                    System.out.println("select() failed");
                    System.exit(1);
                }
                
                // Get set of ready sockets
                Set readyKeys = selector.selectedKeys();
                Iterator readyItor = readyKeys.iterator();
              //  final String GET="get ";
              //  final String LIST="list";
                final String UNKNOWN="Unknown Command: ";
                
                String out="";
                String test="";

                
                boolean ok=false;
                // Walk through the ready set
                while (readyItor.hasNext()) 
                {
                    // Get key from set
                    SelectionKey key = (SelectionKey)readyItor.next();
                    if (key.attachment()==null)
                    {
                    	User user=new User();
                    	user.auth=new Auth();
                    	user.log=new LinkedList();
                    	key.attach(user);
                    }
                    // Remove current entry
                    readyItor.remove();
            
                    // Accept new connections, if any
                    if (key.isAcceptable())
                    {
                        
                         cchannel = ((ServerSocketChannel)key.channel()).accept();
                        cchannel.configureBlocking(false);
                        System.out.println("Accept conncection from " + cchannel.socket().toString());
                    
                        
                
                        // Register the new connection for read operation
                        cchannel.register(selector, SelectionKey.OP_READ);
                                  
                    } 
                    else 
                    {
                        cchannel = (SocketChannel)key.channel();
                        if (key.isReadable())
                        {
     
                            Socket socket = cchannel.socket();
                        
                            // Open input and output streams
                            inBuffer = ByteBuffer.allocateDirect(BUFFERSIZE);
                            cBuffer = CharBuffer.allocate(BUFFERSIZE);
                             
                            // Read from socket
                            bytesRecv = cchannel.read(inBuffer);
                            
                            
                            
                            if (bytesRecv <= 0)
                            {
                                System.out.println("read() error, or connection closed");
                                key.cancel();  // deregister the socket
                                continue;
                            }
                             
                            inBuffer.flip();      // make buffer available  
                            decoder.decode(inBuffer, cBuffer, false);
                            cBuffer.flip();
                            line = cBuffer.toString();
                           
                         if (((User) key.attachment()).auth.ok()==false)  //Checks if there is no user associated with this session
                         {
                        	User user=((User) key.attachment());
                        	boolean result=user.auth.send(line);
                        	key.attach(user);
                        	if (result) {Ok(); Auth.register(key); user.readLog(); user.recipients=new LinkedList();}
                        	else End();
                         }
                            
                         else{
                            System.out.println("TCP Client: " + line);
                        
                                               
                           
                          
                            
                            if (line.equals("terminate\n")||line.equals("terminate\r\n"))
                            {terminated = true; Send("T$#T(QIGAK");}
               
                           
                            /*
                            ok=false;
                            test="";
                            try{
								
                            for (int i=0; i<4; i++)   
                             	test=test+line.charAt(i);
                            }  
                            
                            catch (java.lang.StringIndexOutOfBoundsException i){}	
                            
                            */
                            
                            
                            
                            boolean checkStr = (line.charAt(0)=='/');
								
                            
							if(checkStr == false)
							{
								User user=((User) key.attachment());
								Send(line);
								String[] t=new String[2];
								t[0]="From: "+((User) key.attachment()).auth.whoami();
								t[1]=line;
								String[] to=user.recipients.Traverse();
								for (int i=0;i <to.length; i++)
									transmit(t,to[i]);
								user.log.append(line);
								End();
								break;
							}
							else line=util.rmLine(line);
								
					        if(checkStr ==true && line.contains("/add "))
					        {
					        	String[] users=line.split(" ");
					        	for (int i=1; i<users.length; i++)
					        	((User) key.attachment()).recipients.append(users[i]);
					        	End();
					        	break;
					        						        	
					        }
					        if (checkStr == true && line.contains("/remove "))
					        {
					        	String[] users=line.split(" ");
					        	for (int i=1; i<users.length; i++)
					        	((User) key.attachment()).recipients.remove(users[i]);
					        	End();
					        	break;
					        }
					        
					        if (checkStr == true && line.equals("/to"))
					        {
					        	String[] to=((User) key.attachment()).recipients.Traverse();
					        	Send("Recipients:");
					        	for (int i=0; i<to.length; i++)
					        		Send(to[i]);
					        	End();
					        	break;
					        	
					        }
					        	
								
								
							if(checkStr == true && line.equals("/help")){
								Send("/get");
								Send("/filelist");
								Send("/listfriend");
								break;
							
							}
								
								
								
								
							if(checkStr == true && line.equals("/get")){
                            
									
									
									
										System.out.println("You input was GET"); 
										Send("You input was GET");
										End();
					
									break;
							
							}
							
										
							if (checkStr == true && line.equals("/loggedin"))
							{
								String[] loggedin=Auth.getLoggedIn();
								Send("Loggedin Users:");
								for (int i=0; i<loggedin.length; i++)
									Send(loggedin[i]);
								End(); break;
							}
							
							if (checkStr == true && line.equals("/clear"))
							{
								((User) key.attachment()).log=new LinkedList();
								util.delete(((User) key.attachment()).auth.whoami()+".log");
								End();
								break;
							}
							
							if (checkStr == true && line.equals("/logout"))
							{
								Auth.logout(((User) key.attachment()).auth.whoami());
								Send("T$#T(QIGAK"); break;
							}
							if(checkStr ==true && line.equals("/log"))
							{
								String[] log=((User) key.attachment()).log.Traverse();
								Send("Chat Log:");
								for (int i=0; i<log.length; i++)
									Send(log[i]);
								End(); break;
							}
							if(checkStr == true && line.equals("/list")){
										/*test="";
										try{
											for (int i=0; i<4; i++)   
												test=test+line.charAt(i);
											} catch (java.lang.StringIndexOutOfBoundsException i){}	
											* 
											*/
											
									// SECOND IF 
									
										/* {  File location = new File(".");
										File[] FilesInFolder=location.listFiles();
										for (int i=0; i<FilesInFolder.length; i++)
										{	if (FilesInFolder[i].isFile())
											out="File " + FilesInFolder[i].getName()+"\n";
											else out="Directory "+FilesInFolder[i].getName()+"\n";*/
										
											for (int i=0;  i<10; i++){
											Send("Hello World!");
											}
                               
										End();
									
	
						
						
						
						//INSERT YOUR FUCNTINOS HERE//
										break;
										

								}
								
									
							if(checkStr == true && line.equals("/viewFriends")){
								
										System.out.println("Your FriendList:");
										Send("----Your FriendList:-----");
										String username = (((User) key.attachment()).auth.whoami());
										String readfriend_db = username + ".db";
										
										
										//System.out.println("Reading userfile name");
										
										System.out.println(readfriend_db);
										
										FileReader friendfile=new FileReader(readfriend_db);
										BufferedReader b_2 =new BufferedReader(friendfile);
										
										
										String friendLine;
										while((friendLine = b_2.readLine()) != null)
										{
											Send(friendLine);
										
										}
										b_2.close();
										
								
										
									End();
									break;
									
									
							
								}
								
								
								
								
							boolean checkDel = line.contains("/delFriends");
								
							if(checkStr == true && checkDel == true){
								
								
								
									String delusername = line.substring(12); //remove "/delFriends"

										delusername = delusername.trim(); //remove nextline from substring
										
								
										String requestUsername = (((User) key.attachment()).auth.whoami());
										String readfriend_db = requestUsername + ".db";
										
										
										removeLineFromFile(readfriend_db, delusername);
								
										
										End();
										break;

							}
								
								
							boolean checkAdd = line.contains("/addFriends");
								
							if(checkStr == true && checkAdd == true){
								
									String addusername = line.substring(12); //remove "/delFriends"

									addusername = addusername.trim(); //remove nextline from substring
									
									//CHECK CONDITION REQUIRED IF USER EXSISTS OR NOT
										
								
									String requestUsername = (((User) key.attachment()).auth.whoami());
									String readfriend_db = requestUsername + ".db";
									
									duplicateCheckForAdd(readfriend_db, addusername);
									
									End();
									break;
									}
									
								
							
							
							
							
							
							
								
								
								
								
								
								
								
								
								
								
								
								
								
								
								
								
						
						
						
									if (checkStr == true){
										Send(UNKNOWN+line); 
										End();
									}
									
									else{
				
									Send(line);
									End();
								}
								
							
							}//end of else case
						
						
                         }
                    }
                } // end of while (readyItor.hasNext()) 
            } // end of while (!terminated)
        }
        catch (IOException e) {
            System.out.println(e);
        }
 
        // close all connections
        Set keys = selector.keys();
        Iterator itr = keys.iterator();
         
        while (itr.hasNext()) 
        {
        
            SelectionKey key = (SelectionKey)itr.next();
            //clientSocket.close();
            //itr.remove();
            if (key.isAcceptable())
                ((ServerSocketChannel)key.channel()).socket().close();
            else if (key.isValid())
                ((SocketChannel)key.channel()).socket().close();

        }
    UDPServer.stop();
	UDPServer.kill();

      
    }
}
