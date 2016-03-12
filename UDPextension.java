import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.SocketException;
import java.net.UnknownHostException;

public class UDPextension extends Thread {
	private DatagramSocket UDPclientSocket;
    private int port;
	public  UDPextension(int port)
	{
		this.port=port;
        try {
			UDPclientSocket = new DatagramSocket(port);
		} catch (SocketException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
      
	}
	
	public void kill()
	{
		try {
			DatagramSocket socket=new DatagramSocket();
		
		byte[] fwe=new byte[3];
		
			DatagramPacket packet=new DatagramPacket(fwe, 3, InetAddress.getByName("127.0.0.1"),port);
			socket.send(packet);
			socket.close();
		} catch (java.lang.Exception e) {}
	
		
	}
	
	public void run()
	
	{
   String line="";
    try {
        // As long as we receive data, echo that data back to the client.
        while (!line.equals("terminate")) {
           byte[] UDPinBuffer = new byte[32];

            // Initlize a datagram packet for the receive operation
            DatagramPacket myPacket = new DatagramPacket(UDPinBuffer, UDPinBuffer.length);
            
            // Receive data into myPacket from the socket
            UDPclientSocket.receive(myPacket);
            
            // Convert the packet to a string
            line = new String(myPacket.getData());
            
            // Trim the buffer data and get the actual received data
            line = line.substring(0, myPacket.getLength());
            System.out.println("UDP Client: "+ line);
            
             // Echo the packet back to the client
            UDPclientSocket.send(myPacket);
        }
        
        // Close the socket
        UDPclientSocket.close();
    }   
    catch (IOException e) {
        System.out.println(e);
    }
	}
   

}
