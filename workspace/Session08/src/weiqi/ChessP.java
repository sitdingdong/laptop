/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package weiqi;

import java.awt.Graphics;

/**
 *
 * @author 丁
 */
public abstract class ChessP {
    private int x, y;
	public ChessP(int x, int y) { this.x = x; this.y = y; }
	public abstract void paint(Graphics g);
	public int getX() { return x; }
	public int getY() { return y; }
    
}
