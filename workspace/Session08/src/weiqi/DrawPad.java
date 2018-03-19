/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package weiqi;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.util.ArrayList;
import javax.swing.JPanel;

/**
 *
 * @author 丁
 */
public class DrawPad extends JPanel implements MouseListener{
    private int x;
    private int y;
    private ArrayList<ChessP> chessp;
    
    
    public DrawPad(){
        setBackground(Color.yellow);
        //setLayout(null);
        setSize(880,880);
        //setVisible(true);
        addMouseListener(this);
        //chessp = new ArrayList<>(361);
       // Graphics g = getGraphics();
        //paint(g);
        
    }
    public void paint(Graphics g) {
        g.setColor(getBackground());
        g.fillRect(0, 0, getWidth(), getHeight());
        g.setColor(Color.black);
        for(int i = 80; i <= 800; i += 40) {
            g.drawLine(80, i, 800, i);
            g.drawLine(i, 80, i, 800);
        }
        
         //g.setColor(Color.blue);
    }

        public void mouseClicked(MouseEvent arg0) {
           
        }

       
        public void mousePressed(MouseEvent e) {
            boolean m = true;
            x = e.getX();
            y = e.getY();
            ChessPoint_B cb = new ChessPoint_B(x, y);
            ChessPoint_W cw = new ChessPoint_W(x, y);
            Graphics g = getGraphics();
            if(x < 80 || y < 80) {
                
            }else {
                if( m == true) {
                    cb.paint(g);
                    m = false;
                }
                else if (m == false) {
                    cw.paint(g);
                    m = true;
                    
                }
                
            
            }
           
            
        }

        public void mouseReleased(MouseEvent e) {
          
        }


        public void mouseEntered(MouseEvent e) {
          
        }

        public void mouseExited(MouseEvent e) {
           
        }


        public void mouseDragged(MouseEvent e) {
           
        }


        public void mouseMoved(MouseEvent e) {
           
        }
    }
    class ChessPoint_B extends ChessP /*implements MouseListener*/ {
       public ChessPoint_B(int x, int y){
           super(x,y);
       }
           
       public void paint(Graphics g) {
           g.setColor(Color.black);
           g.fillOval((getX() / 40 * 40) - 10, (getY() / 40 * 40) - 10, 20, 20);
           
           
       }

    }
       class ChessPoint_W extends ChessP /*implements MouseListener*/ {
       public ChessPoint_W(int x, int y){
           super(x,y);
       }
           
       public void paint(Graphics g) {
           g.setColor(Color.white);
           g.fillOval(((getX() + 20) / 40 * 40) - 10, ((getY() + 20) / 40 * 40) - 10, 20, 20);
           
           
       }
    }
    

   