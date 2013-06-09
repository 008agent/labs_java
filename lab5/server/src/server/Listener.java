package server;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.text.DateFormat;
import java.util.Calendar;
import java.util.logging.Level;
import java.util.logging.Logger;
import var333.Contour;
import var333.Mark;


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
        Socket client = null;
        int CLID = 0;
                //contains client message
                String tmp = "";
        while(true)
        {
            try
            {
            client = sk.accept();
            CLID = client.getPort();
                System.out.println( time() + " accepted client id = " + CLID);
            
                //reading from client
                    byte[] buff = new byte[128];
                    String data = "";
                    //while not EOF continue reading data
                    int r = client.getInputStream().read(buff);
                        data = new String(buff);
                        tmp += data;
                        data = "";

                 //end reading
                 
                    double X,Y,R;
                    int posXst,posYst,posRst,posXend,posYend,posRend;
                        posXst  = tmp.indexOf("X::");
                        posXend = tmp.indexOf("::Y");
                        posYst  = tmp.indexOf("Y::");
                        posYend = tmp.indexOf("::R");
                        posRst  = tmp.indexOf("R::");
                        posRend = tmp.indexOf("::END");
                     X = Double.parseDouble(tmp.substring(posXst+3,posXend));
                     Y = Double.parseDouble(tmp.substring(posYst+3,posYend));
                     R = Double.parseDouble(tmp.substring(posRst+3,posRend));
                     
                     
                     System.out.println(CLID + " message : " + tmp);
                     tmp = "";
                     System.out.println(CLID + " creating calculate context");
                        Contour c = new Contour((float)R);
                        boolean hit = c.is_hit(new Mark((float)X, (float)Y));
                     System.out.println(CLID + " context created. calculating response data : HIT = " + Boolean.toString(hit) );
                     System.out.println(CLID + " sending results to client"); 
                        String response="";
                        if(hit) 
                        {
                            response="true";
                        } 
                        else
                        {
                            response="false";        
                        }
                     client.getOutputStream().write(response.getBytes());
                     
                    //closing connection
                    client.close();
                    System.out.println( time() + " closed client id = " + CLID);
            }
            catch(IOException ioe)
            {
                System.err.println(ioe + ioe.toString());
                tmp = "";
            }
            //reason : incorrect client request
            catch(StringIndexOutOfBoundsException oub)
            {
                System.err.println(CLID + " incorrect client. closing");
                try 
                {
                    client.close();
                    tmp = "";
                } catch (IOException ex) 
                {
                    Logger.getLogger(Listener.class.getName()).log(Level.SEVERE, null, ex);
                }
                
            }
            //reason : unable to parse double
            catch(NumberFormatException nfe)
            {
                System.err.println(nfe + nfe.toString());
                try 
                {
                    client.close();
                    tmp = "";
                } catch (IOException ex) 
                {
                    Logger.getLogger(Listener.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }
        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
    String time()
    {
        return DateFormat.getInstance().format(Calendar.getInstance().getTime());
    }
    
}
