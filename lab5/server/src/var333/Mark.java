package var333;

import java.awt.Canvas;


public class Mark
{
    float _x,_y;
    /** constructor */
    public Mark(float x,float y)
    {
        this._x=x;
        this._y=y;
    }
    public float getX()
    {
        return _x;
    }
    public float getY()
    {
        return _y;
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
    
    public static Mark Translate(Canvas _canvas,float X,float Y,int ppd)
    {
        float tmp_x,tmp_y;
        tmp_x = (X - _canvas.getWidth()/2)/ppd/2;
        tmp_y = -((Y - _canvas.getHeight()/2)/ppd/2);
        return new Mark(tmp_x, tmp_y);
    }
    public static Mark Translate_reverse(Mark m,Canvas _canvas,int ppd)
    {
        float tmp_x,tmp_y;
        tmp_x = ppd*m.getX() + _canvas.getWidth()/2;
        tmp_y = _canvas.getHeight()/2 - ppd*m.getY();
        return new Mark(tmp_x, tmp_y); 
    }
    
}
