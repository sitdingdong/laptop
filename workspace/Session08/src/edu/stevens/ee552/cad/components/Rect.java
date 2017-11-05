/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.stevens.ee552.cad.components;

import java.awt.Graphics;

/**
 *
 * @author 83590
 */
public class Rect extends Shape{
        private int width,height;
	
	public Rect(int x, int y, int width, int height) {
		super(x,y);
		this.width = width; this.height = height;
	}
	public void draw(Graphics g) {
		g.fillRect(getX(), getY(), width, height);
	}
        public void setPoint2(int x2, int y2) {
            width = x2 -getX() ;
            height = y2 - getY() ;
        }
    
}
