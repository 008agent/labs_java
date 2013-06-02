package lab4;

import java.awt.BorderLayout;
import java.awt.Canvas;
import java.awt.Color;
import java.awt.GridLayout;
import java.awt.Panel;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JRadioButton;
import javax.swing.JSlider;
import javax.swing.SwingUtilities;

public class Lab4 implements Runnable
{
    //элементы управления
    public static JFrame            form;
    public static Panel             p_elements,p_canvas;
    public static Canvas            paint_area;
    public static JComboBox         x_coord;
    public static JRadioButton      y_1,y_2,y_3,y_4;
    public static JSlider           radius_slider;
    public static JLabel            label_coords;

    //точка входа
    public static void main(String[] args) 
    {
        SwingUtilities.invokeLater(new Lab4());
    }
    
    //инициализация элементов управления
    public void init_elements()
    {
      form = new JFrame("Lab4,var 333");
            form.getContentPane().setLayout(new BorderLayout());
        //панель с элементами управления
      p_elements = new Panel(new GridLayout(7,1));
            p_elements.setForeground(Color.WHITE);
            p_elements.add(x_coord = new JComboBox());
                x_coord.addItem("X=1");
                x_coord.addItem("X=2");
                x_coord.addItem("X=3");
                x_coord.addItem("X=4");
            p_elements.add(y_1 = new JRadioButton("Y=1"));
            p_elements.add(y_2 = new JRadioButton("Y=2"));
            p_elements.add(y_3 = new JRadioButton("Y=3"));
            p_elements.add(y_4 = new JRadioButton("Y=4"));
            p_elements.add(radius_slider = new JSlider(1, 10, 1));
            p_elements.add(label_coords = new JLabel("here will be coordinates"));
        //панель с областью рисования
      p_canvas   = new Panel(new GridLayout());
            paint_area = new Canvas();
            paint_area.setBackground(Color.GREEN);
            paint_area.setSize(400, 300);
            p_canvas.add(paint_area);
            
        form.add(p_elements,BorderLayout.WEST);
        form.add(p_canvas,BorderLayout.CENTER);
        form.pack();
        form.setVisible(true);
    }

    @Override
    public void run() 
    {
        init_elements();
    }
    
    
    
}
