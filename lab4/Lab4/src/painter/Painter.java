package painter;

import java.awt.Canvas;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Panel;
import java.awt.Polygon;
import java.awt.Shape;
import java.awt.geom.Ellipse2D;
import java.util.Random;
import javax.swing.DebugGraphics;
import var333.Mark;

public class Painter implements Runnable
{
    public static void draw_grid_on_panel(Canvas _canvas,int ppd)
    {
        int h = _canvas.getHeight();
        int w = _canvas.getWidth();
        Graphics g = _canvas.getGraphics();
        //g.clearRect(0, 0, w, h);
        
        //horisontal_line
        g.drawLine(0, h/2,w,h/2);
        //vertical line
        g.drawLine(w/2,0,w/2,h);
        
        for(int i=0;i<w/ppd/2;i++)
        {
            //horosontal gaps
            g.drawLine(w/2 + i*ppd, h/2-4,w/2 + i*ppd, h/2+4);
            g.drawLine(w/2 - i*ppd, h/2-4,w/2 - i*ppd, h/2+4);
            //vertical gaps      
            g.drawLine(w/2-4, h/2 - i*ppd ,w/2+4, h/2 - i*ppd );
            g.drawLine(w/2-4, h/2 + i*ppd ,w/2+4, h/2 + i*ppd ); 
        }
    }
    
    public static void draw_var333_figure(Canvas _canvas,int ppd,int radius)
    {
        int h = _canvas.getHeight();
        int w = _canvas.getWidth();
        Graphics g = _canvas.getGraphics();
        
        //нарисуем окружность
        g.setColor(new Color(0,180,0));
        g.fillOval(w/2 - radius*ppd, h/2 - radius*ppd, radius*ppd*2 , radius*ppd*2);
        
        //оставим только 4 четверть окружности
        g.setColor(new Color(0,250,0));
        g.fillRect(0, 0, w/2, h/2);
        
        //нарисуем треугольник
        g.setColor(new Color(0,180,0));
        Polygon p = new Polygon();
            p.addPoint(w/2, h/2);
            p.addPoint(w/2+radius*ppd*2, h/2);
            p.addPoint(w/2 , h/2 - radius*ppd*2 );
            p.addPoint(w/2, h/2);
        g.fillPolygon(p);
        
        g.fillRect(w/2 - radius*ppd , h/2, radius*ppd, radius*ppd*2);
    }
    
    public static void draw_animate_cursor(Canvas _canvas,int ppd,int X,int Y,int radius) throws InterruptedException 
    {
        int h = _canvas.getHeight();
        int w = _canvas.getWidth();
        Graphics g = _canvas.getGraphics();
        g.setXORMode(g.getColor());
        
        g.setColor(Color.red);
        float r;

        for(r=radius/5.f;r>radius/10.f;r-=radius/20.f)
        {
            g.fillOval((int)(X-Math.round(r*2)), (int)(Y-Math.round(r*2)), (int)(ppd*r), (int)(ppd*r) );
            Thread.sleep(50);
            g.fillOval((int)(X-Math.round(r*2)), (int)(Y-Math.round(r*2)), (int)(ppd*r), (int)(ppd*r) );    
        }
        if(lab4.Lab4.contour.is_hit(Mark.Translate(_canvas, X, Y, ppd)))
            g.setColor(Color.BLUE);
        
        g.fillOval((int)(X-Math.round(r*4)), (int)(Y-Math.round(r*2)), (int)(ppd*r), (int)(ppd*r) );
        
    }


    @Override
    public void run() 
    {
       
        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

}
