package util;

public class Area {
    Double Radius;
    public Area(Double R){
        this.Radius = (R<=0) ? 1.0 : R;
    }
    public boolean Hit(Double X,Double Y)
    {
        double absX = Math.abs(X);
        double absY = Math.abs(Y);
        //first
        if(X>0 && Y>0)
        {
            if(absX<=this.Radius && absY<=this.Radius)
            {
                return true;
            }
        }
        //second
        if(X<0 && Y>0)
        {
            if(absY<=Math.sqrt(this.Radius*this.Radius/4-absX*absX))
                return true;
        }
        //third
        if(X<0 && Y<0)
        {
            if(absY<=this.Radius-absX*2)
            {
                return true;
            }
        }
        return false;
    }
}
