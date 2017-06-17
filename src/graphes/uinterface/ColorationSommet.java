/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package graphes.uinterface;

import java.awt.Color;


public class ColorationSommet {
	double x, y; // coordinates
	int r; // radius
	Color color; // color
	
	ColorationSommet(double x, double y, int r, Color color) {
		this.x = x;
		this.y = y;
		this.r = r;
		this.color = color;
	}
}
