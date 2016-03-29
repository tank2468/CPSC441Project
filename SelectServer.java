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
                    	user.files=new LinkedList();
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
                        	if (result) 
                        		Ok();
                        	else 
                        		End();
                         }
                            
                         else{
                            System.out.println("TCP Client: " + TCPClient.toASCIIString(line));
                        
                            if (((User) key.attachment()).auth.whoami().equals("bob")) 
                            	Send("BOB");  //test string when sends BOB when bob is logged in
                            if (((User) key.attachment()).auth.whoami().equals("user")) 
                            	Send("user");  //test string when sends BOB when bob is logged in
                            
                            if (line.equals("terminate\n")||line.equals("terminate\r\n")){
                            	terminated = true; Send("T$#T(QIGAK");
                            	}
                            
                            boolean checkStr = line.contains("/");
								
                            
							if(checkStr == false)
							{
								Send(line);
								End();
								break;
							}
					
							if(checkStr == true && line.equals("/get\n")){
										System.out.println("You input was GET"); 
										Send("You input was GET");
										End();
					
									break;
							}
							
							if(checkStr == true && line.equals("/send\n")){
								System.out.println("Your input was GET"); 
								Send("Your input was GET");
								
								String fileToSend = "/home/ugb/ooajayi/aHash";

								Send("sendSignal");
								String[] readFile = util.readFile("fileToSend");
								if(!(readFile==null))
								for(int i=0; i<readFile.length; i++){
									Send(readFile[i]);
								}
								End(); //End send transmission
								
								Send("File has been sent");	//confirm file sent
								User user=((User) key.attachment());
								user.files.append(fileToSend);				//add to user records of file owned
								
								key.attach(user);
								
								break;
							}
							
															
							if(checkStr == true && line.equals("/list\n")){
								
								User user=((User) key.attachment());
								String[] fileList = user.files.Traverse();
								
								for(int i=0; i<fileList.length; i++){
									Send(fileList[i]);
								}

								End();
								break;
							}
						
							
							
						
						
						//INSERT YOUR FUCNTINOS HERE//
						
						
						
						
								else{
									Send(UNKNOWN+line); 
									End();
									break;
									/*if (checkStr == true){
										Send(UNKNOWN+line); 
										End();
									}
									
									else{
				
									Send(line);
									End();
								}*/
								
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
