package server;

import java.io.IOException;

public class Server
{
    public static void main(String[] args) throws InterruptedException, IOException 
    {
        if(args.length==0) 
        {
            System.err.println("\nUsage : Server [port]");
            System.exit(-1);
        }
        Listener ls = new Listener( Integer.parseInt(args[0]) );
        System.err.println("\nServer launched on port " + args[0] );
        Thread main_listen_thread = new Thread(ls);
        main_listen_thread.start();
        
    }

}
