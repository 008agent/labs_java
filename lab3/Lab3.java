//var 333
import java.io.IOException;
import java.util.Iterator;
import java.util.LinkedHashSet;

public class Lab3 
{
    
    public static void main(String[] args)
    {
        //variables declaration
        LinkedHashSet<Mark> lhs = new LinkedHashSet<Mark>();
        float argRadius;
        String unparsedRadius;
        byte[] buffer = new byte[128];
        Contour ctr;
        Mark m_tmp;
        Iterator<Mark> iterator;
        
        
        System.out.println("enter radius");
        
        try
        {
            //LinkedHashSet filling
            lhs.add(new Mark(5f, 3f));
            lhs.add(new Mark(2f, 2f));
            lhs.add(new Mark(4f, -5f));
            lhs.add(new Mark(-1f, 0f));
            lhs.add(new Mark(-3f, -4f));
            lhs.add(new Mark(1f, 1f));
            lhs.add(new Mark(-5f, 4f));  
            
            iterator = lhs.iterator();
            
            //reading and parsing [String] to [Float]
            System.in.read(buffer);
            unparsedRadius = new String(buffer);
            argRadius = Float.parseFloat(unparsedRadius);
            
            //creating contour with parsed radius
            ctr = new Contour(argRadius);

            //collection iteration
            do
            {
                m_tmp = iterator.next();
                if(ctr.is_hit(m_tmp)) 
                { 
                    System.out.println( m_tmp + " hit the target" ); 
                } 
            }
            while( iterator.hasNext() );
        }
        catch(NumberFormatException NFE)
        {
            System.err.println(NFE + NFE.toString());
            System.exit(-1);
        }
        catch(IOException IOE)
        {
            System.err.println(IOE + IOE.toString());
            System.exit(-1);
        }
        catch(Exception E)
        {
            System.err.println(E + E.toString());
            System.exit(-1);
        }
        
    }
}
