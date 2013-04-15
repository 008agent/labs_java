/** Our basic class */
public class HelloWorld implements Iprintable
{
    /** Protected field with greeting */
    protected String msg = "hello world";
    
    public static void main(String[] args)
    {
        HelloWorld hw = new HelloWorld();
        hw.flush();                         //this won't happen. flush is deprecated
        hw.print();
        
        
        Extender ext = new Extender("hello,eternal world");
        ext.print();
        String extracted_field = ext.get_field();   /* we don't realy need that. but non-familiar classes coudn't get access to msg like on the next string */
        System.out.println(ext.msg);
        ext.flush();
        ext.print();

    }

    @Override
    public void print() 
    {
        System.out.println( this.msg );
    }

    @Deprecated
    @Override
    public void flush() 
    {
        this.msg = "";
    }

    @Override
    public String get_field() 
    {
        return this.msg;
    }

    @Override
    public void set_field(String arg) 
    {
        this.msg = arg;
    }
}



