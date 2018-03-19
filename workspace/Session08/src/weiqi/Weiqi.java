/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package weiqi;

import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import javax.swing.JFrame;

/**
 *
 * @author 丁
 */
public class Weiqi extends JFrame{
    private DrawPad weiqipad ;
    Weiqi(){
        Container c = getContentPane();
        weiqipad = new DrawPad();
        c.add(weiqipad, BorderLayout.CENTER);
        setVisible(true);
        //setLayout(null);
        //Graphics g = getGraphics();
        
        addWindowListener(new WindowAdapter(){
            public void windowClosing(WindowEvent e){
                System.exit(0);
            }
        });
        pack();
        setSize(600,550);
    }
    public static void main(String arg[]) {
        Weiqi wq = new Weiqi();
    }
}
