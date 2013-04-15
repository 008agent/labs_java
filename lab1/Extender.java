class Extender extends HelloWorld implements Iprintable
{
    public Extender( String _ms )
    {
        this.msg = _ms;
    }

    @Override
    public String toString() 
    {
        return this.msg;
    }

    @Override
    public void print() 
    {
        System.out.println( this.toString() );
    }
    
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