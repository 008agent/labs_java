package painter;

import java.awt.Canvas;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Panel;
import javax.swing.DebugGraphics;

public class Painter 
{
    public static void draw_grid_on_panel(Canvas _canvas,int radius)
    {
        int h = _canvas.getHeight();
        int w = _canvas.getWidth();
        Graphics g = _canvas.getGraphics();
        g.clearRect(0, 0, w, h);
        //horisontal_line
        g.drawLine(0, h/2,w,h/2);
        //vertical line
        g.drawLine(w/2,0,w/2,h);
        for(int i=0;i<10;i++)
        {
            
        }
    }
}
