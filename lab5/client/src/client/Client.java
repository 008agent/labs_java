package client;

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
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.Locale;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JRadioButton;
import javax.swing.JSlider;
import javax.swing.JTextField;
import javax.swing.SwingUtilities;
import javax.swing.WindowConstants;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import var333.Contour;
import var333.Mark;

public class Client implements Runnable
{
    //глобальные переменные: радиус,контур,точка из прошлой лабы
    public static int               radius = 1,
                                    curr_x,
                                    curr_y;
    public static Contour           contour;
    public static Mark              mark;
    
    //элементы подключения к серверу
    public static JFrame            server_frame;
    public static JTextField        server_addr_text;
    public static JTextField        server_port_text;
    ActionListener                  serv_addr_changed;
    ActionListener                  serv_port_changed;
    
    //элементы управления
    public static JFrame            form;
    public static Panel             p_elements,p_canvas;
    public static Canvas            paint_area;
    public static JComboBox         x_coord;
    public static JRadioButton      y_1,y_2,y_3,y_4;
    public static JSlider           radius_slider;
    public static JLabel            label_coords;
    
    public static int               server_port;
    public static InetAddress       server_addr;
    
    //слушатели событий
    ActionListener                  x_changed;
    ActionListener                  y_changed;
    ChangeListener                  radius_changed;
    MouseListener                   mouse_click;
    
    //локализация
    ResourceBundle                  rb_def;
    

    //точка входа
    public static void main(String[] args) 
    {
        SwingUtilities.invokeLater(new client.Client());
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
        form.setTitle( rb_def.getString("radius") + " = " + String.valueOf(radius));
    }
    
    //инициализация элементов управления
    public void init_elements()
    {       
        //Locale.setDefault(Locale.);
        Locale loc = Locale.getDefault();
        
        //System.err.println(loc.getCountry());
        //System.err.println(loc.getLanguage());
        
        rb_def = ResourceBundle.getBundle("Labels",loc);
        
        server_frame = new JFrame( rb_def.getString("type_addr_port") );
            server_frame.getContentPane().setLayout(new GridLayout(1,2));
        server_addr_text = new JTextField("127.0.0.1");
        server_port_text = new JTextField("1111");
            server_frame.add(server_addr_text);
            server_frame.add(server_port_text);
        server_frame.pack();
        server_frame.setSize(600, 50);
        server_frame.setVisible(true);
        server_frame.setAlwaysOnTop(true);
            //server_frame.add()
        
      form = new JFrame( rb_def.getString("labwork_id") );
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
            p_elements.add(label_coords = new JLabel( rb_def.getString("coordinates") ));
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
                { //c\s
                    TCPConn tc = new TCPConn(server_port, server_addr);
                    boolean hit = tc.get_hit_result(curr_x, curr_y, radius);
                    painter.Painter.draw_animate_cursor(
                            paint_area,
                            25,
                            (int)Mark.Translate_reverse(mark, paint_area, 25).getX(),
                            (int)Mark.Translate_reverse(mark, paint_area, 25).getY(),
                            radius,
                            hit
                            );
                } catch (InterruptedException ex) 
                {
                    Logger.getLogger(client.Client.class.getName()).log(Level.SEVERE, null, ex);
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
                  //TCPConn tc = new TCPConn(curr_x, null)
                     TCPConn tc = new TCPConn(server_port, server_addr);
                    boolean hit = tc.get_hit_result(curr_x, curr_y, radius);
                    painter.Painter.draw_animate_cursor(
                            paint_area,
                            25,
                            (int)Mark.Translate_reverse(mark, paint_area, 25).getX(),
                            (int)Mark.Translate_reverse(mark, paint_area, 25).getY(),
                            radius,
                            hit
                            );
                } catch (InterruptedException ex) 
                {
                    Logger.getLogger(client.Client.class.getName()).log(Level.SEVERE, null, ex);
                }
                label_coords.setText(mark.toString());
            }
        };
        y_1.addActionListener(y_changed);
        y_2.addActionListener(y_changed);
        y_3.addActionListener(y_changed);
        y_4.addActionListener(y_changed);
        
        mouse_click     = new MouseListener() 
        {
            @Override
            public void mouseClicked(MouseEvent e) 
            {
                try 
                {
                   Mark m_tmp = Mark.Translate(paint_area, e.getX(), e.getY(), 25);
                   

                    TCPConn tc = new TCPConn(server_port, server_addr);
                    boolean hit = tc.get_hit_result(m_tmp.getX(), m_tmp.getY(), radius);
                    painter.Painter.draw_animate_cursor(
                            paint_area,
                            25,
                            e.getX(),
                            e.getY(),
                            //(int)Mark.Translate_reverse(mark, paint_area, 25).getX(),
                            //(int)Mark.Translate_reverse(mark, paint_area, 25).getY(),
                            radius,
                            hit
                            );
                    label_coords.setText(Mark.Translate(paint_area, e.getX(), e.getY(), 25).toString());
                } catch (InterruptedException ex) 
                {
                    Logger.getLogger(client.Client.class.getName()).log(Level.SEVERE, null, ex);
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
        
        //событие установки хоста сервера
        serv_addr_changed   = new ActionListener() 
        {
            @Override
            public void actionPerformed(ActionEvent e) 
            {
                try 
                {
                    server_addr = InetAddress.getByName(server_addr_text.getText());
                    //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
                } catch (UnknownHostException ex) 
                {
                    server_frame.setTitle(rb_def.getString("unknown_host") );
                    Logger.getLogger(Client.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        };
        server_addr_text.addActionListener(serv_addr_changed);
        
        serv_port_changed   = new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) 
            {
                try
                {
                    server_port = Integer.parseInt(server_port_text.getText());
                    
                }
                catch(NumberFormatException nfe)
                {
                    server_frame.setTitle( rb_def.getString("incorrect_id") );
                }
                //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
            }
              
        };
        server_port_text.addActionListener(serv_port_changed);
       
        
        form.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
    }
    
    @Override
    public void run() 
    {
        init_elements();
        init_events();
        init_globals();
    }
    

    
}
