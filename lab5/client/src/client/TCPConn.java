/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package client;

import java.io.IOException;
import java.lang.reflect.Field;
import java.net.InetAddress;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.net.SocketAddress;
import java.text.FieldPosition;
import java.text.NumberFormat;
import java.text.ParsePosition;

public class TCPConn 
{
    int         port;
    InetAddress addr;
    Socket      clientSock;
    
    public TCPConn(int _port,InetAddress _servAddr)
    {
        port        = _port;
        addr        = _servAddr;
        clientSock  = new Socket();
    }
    
    boolean get_hit_result(double x,double y,double radius)
    {
        try
        {
        SocketAddress sa = new InetSocketAddress(addr, port);
        clientSock.connect(sa, 5000);
            StringBuffer sbuf = new StringBuffer();
            sbuf.append("X::");
            sbuf.append(x);
            sbuf.append("::Y::");
            sbuf.append(y);
            sbuf.append("::R::");
            sbuf.append(radius);
            sbuf.append("::END");
            String outs = sbuf.toString();
           clientSock.getOutputStream().write(outs.getBytes());
            
           byte[] tmp = new byte[32];
           clientSock.getInputStream().read(tmp);
           String interchange = new String(tmp);
           // System.out.println(interchange);
        clientSock.close();
        if(interchange.contains("true"))
            return true;
        //return Boolean.parseBoolean(interchange);
        //clientSock.getOutputStream().write();
        }
        catch(IOException ioe)
        {
            System.err.println(ioe + ioe.toString());
        }
        return false;
    }
    
}
