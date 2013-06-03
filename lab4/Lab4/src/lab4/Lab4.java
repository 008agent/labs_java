package lab4;

import java.awt.BorderLayout;
import java.awt.Canvas;
import java.awt.Color;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.Panel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JRadioButton;
import javax.swing.JSlider;
import javax.swing.SwingUtilities;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import var333.Contour;
import var333.Mark;

public class Lab4 implements Runnable
{
    //глобальные переменные: радиус,контур,точка из прошлой лабы
    public static int               radius = 1,
                                    curr_x,
                                    curr_y;
    public static Contour           contour;
    public static Mark              mark;
    
    //элементы управления
    public static JFrame            form;
    public static Panel             p_elements,p_canvas;
    public static Canvas            paint_area;
    public static JComboBox         x_coord;
    public static JRadioButton      y_1,y_2,y_3,y_4;
    public static JSlider           radius_slider;
    public static JLabel            label_coords;
    
    //слушатели событий
    ActionListener                  x_changed;
    ActionListener                  y_changed;
    ChangeListener                  radius_changed;
    MouseListener                   mouse_click;

    //точка входа
    public static void main(String[] args) 
    {
        SwingUtilities.invokeLater(new Lab4());
    }
    
    public void paint_all()
    {
        paint_area.getGraphics().clearRect(0, 0, paint_area.getWidth(), paint_area.getHeight());
        painter.Painter.draw_var333_figure(paint_area, 25, radius);
        painter.Painter.draw_grid_on_panel(paint_area,25);
    }
    
    //инициализация глобальных переменных
    public void init_globals()
    {
        mark    = new Mark(curr_x, curr_y);
        contour = new Contour(radius);
        form.setTitle("radius = " + String.valueOf(radius));
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
                label_coords.setFont(new Font(Font.MONOSPACED, Font.BOLD, 20));
        //панель с областью рисования
      p_canvas   = new Panel(new GridLayout());
            paint_area = new Canvas();
            paint_area.setBackground(new Color(0,250,0));
            paint_area.setSize(400, 300);
            p_canvas.add(paint_area);
            
        form.add(p_elements,BorderLayout.WEST);
        form.add(p_canvas,BorderLayout.CENTER);
        form.pack();
        form.setVisible(true);
    }
       
    //инициализация событий
    public void init_events()
    {
        //обработчик события смены радиуса
        radius_changed = new ChangeListener() {

            @Override
            public void stateChanged(ChangeEvent e) 
            {
                 //поменять радиус
                 radius = radius_slider.getValue();
                 radius_slider.setToolTipText( String.valueOf(radius_slider.getValue()) );
                 //пересчитать контур
                 init_globals();
                 paint_all();
            }
        };
        radius_slider.addChangeListener(radius_changed);
        
        //обработчик события смены координаты X
        x_changed       = new ActionListener() 
        {
            @Override
            public void actionPerformed(ActionEvent e) 
            {
                curr_x = x_coord.getSelectedIndex()+1;
                init_globals();
                label_coords.setText(mark.toString());
                try 
                {
                    painter.Painter.draw_animate_cursor(paint_area, 25,(int)Mark.Translate_reverse(mark, paint_area, 25).getX(), (int)Mark.Translate_reverse(mark, paint_area, 25).getY(), radius);
                } catch (InterruptedException ex) 
                {
                    Logger.getLogger(Lab4.class.getName()).log(Level.SEVERE, null, ex);
                }
                
            }
        };
        x_coord.addActionListener(x_changed);
                
        //обработчик события смены координаты Y
        y_changed       = new ActionListener() 
        {
            @Override
            public void actionPerformed(ActionEvent e) 
            {
                 //some indian code
                if(y_1.isSelected())
                {
                    y_2.setSelected(false);
                    y_3.setSelected(false);
                    y_4.setSelected(false);
                    curr_y = 1;
                }
                if(y_2.isSelected())
                {
                    y_1.setSelected(false);
                    y_3.setSelected(false);
                    y_4.setSelected(false);
                    curr_y = 2;
                }
                if(y_3.isSelected())
                {
                    y_1.setSelected(false);
                    y_2.setSelected(false);
                    y_4.setSelected(false);
                    curr_y = 3;
                }
                if(y_4.isSelected())
                {
                    y_1.setSelected(false);
                    y_2.setSelected(false);
                    y_3.setSelected(false);
                    curr_y = 4;
                }
                init_globals();
                try 
                {
                    painter.Painter.draw_animate_cursor(paint_area, 25,(int)Mark.Translate_reverse(mark, paint_area, 25).getX(), (int)Mark.Translate_reverse(mark, paint_area, 25).getY(), radius);
                } catch (InterruptedException ex) 
                {
                    Logger.getLogger(Lab4.class.getName()).log(Level.SEVERE, null, ex);
                }
                label_coords.setText(mark.toString());
            }
        };
        y_1.addActionListener(y_changed);
        y_2.addActionListener(y_changed);
        y_3.addActionListener(y_changed);
        y_4.addActionListener(y_changed);
        
        mouse_click     = new MouseListener() {

            @Override
            public void mouseClicked(MouseEvent e) {
                label_coords.setText("Clicked at " + e.getX() + ":" + e.getY());
                try 
                {
                    painter.Painter.draw_animate_cursor(paint_area, 25,e.getX(), e.getY(), radius);
                    label_coords.setText(Mark.Translate(paint_area, e.getX(), e.getY(), 25).toString());
                } catch (InterruptedException ex) 
                {
                    Logger.getLogger(Lab4.class.getName()).log(Level.SEVERE, null, ex);
                }

            }

            @Override
            public void mousePressed(MouseEvent e) {
                //label_coords.setText("Pressed");
                //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
            }

            @Override
            public void mouseReleased(MouseEvent e) {
                //label_coords.setText("Released");
                //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
            }

            @Override
            public void mouseEntered(MouseEvent e) {
                //label_coords.setText("Entered at ");
               // throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
            }

            @Override
            public void mouseExited(MouseEvent e) {
                //label_coords.setText("Exited");
               // throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
            }
        };
        paint_area.addMouseListener(mouse_click);

        
    }
    
    @Override
    public void run() 
    {
        init_elements();
        init_events();
        init_globals();
    }
    

    
}
