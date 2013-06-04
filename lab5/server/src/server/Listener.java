package server;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.sql.Time;
import java.text.DateFormat;
import java.util.Calendar;
import java.util.Date;


public class Listener implements Runnable
{
    ServerSocket sk;
    
    public Listener(int port) throws IOException
    {
        sk = new ServerSocket();
        sk.setReuseAddress(true);
        sk.bind(new InetSocketAddress(port));
        
    }

    @Override
    public void run()
    {
        while(true){
            try{
            Socket client = sk.accept();
            int CLID = client.getPort();
                System.out.println( time() + " accepted client id = " + CLID);
            client.getOutputStream().write("hello".getBytes());

            //closing connection
                client.close();
                System.out.println( time() + " closed client id = " + CLID);
            }
            catch(IOException ioe)
            {
                System.err.println(ioe + ioe.toString());
            }
        }
        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
    String time()
    {
        return DateFormat.getInstance().format(Calendar.getInstance().getTime());
    }
    
}
