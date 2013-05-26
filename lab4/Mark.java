public class Mark 
{
    float _x,_y;
    /** constructor */
    public Mark(float x,float y)
    {
        this._x=x;
        this._y=y;
    }

    @Override
    public String toString() 
    {
        StringBuffer sb = new StringBuffer();
        sb.append("<");
        sb.append(this._x);
        sb.append(",");
        sb.append(this._y);
        sb.append(">");        
        return sb.toString();
    }
    
}
