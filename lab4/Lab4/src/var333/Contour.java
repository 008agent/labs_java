package var333;

public class Contour 
{
    //radius
    float R;
    //constructor
    public Contour(float _r)
    {
        this.R = (_r==0.0f) ? 0 : Math.abs(_r);
    }
    
    /** returns true if Mark m hit the target, else returns false */
    boolean is_hit(Mark m)
    {
        //first quarter
        if(m._x>0 && m._y>0)
        {
            if( m._y < (this.R-m._x) ) return true;
        }
        //third quarter
        else if(m._x<0 && m._y<0)
        {
            if( Math.abs(m._x)<(this.R/2) && Math.abs(m._y)<(this.R) ) return true;
        }
        //fourth quarter
        else if(m._x>0 && m._y<0)
        {
            if( (Math.pow(Math.abs(m._x),2)+Math.pow(Math.abs(m._y),2)) < Math.pow(this.R/2, 2) ) return true;
        }
        return false;
    }
    
}
