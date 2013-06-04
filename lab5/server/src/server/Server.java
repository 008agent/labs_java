/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package server;

import java.io.IOException;
import java.net.ServerSocket;
import sun.rmi.transport.tcp.TCPConnection;

/**
 *
 * @author s153335
 */
public class Server
{
    public static void main(String[] args) throws InterruptedException, IOException 
    {
        Listener ls = new Listener(1111);
        Thread main_listen_thread = new Thread(ls);
        main_listen_thread.start();
    }

}
