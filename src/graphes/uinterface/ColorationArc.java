/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package graphes.uinterface;

import java.awt.Color;


public class ColorationArc {
double x1, y1, x2, y2; // coordinates
	int f; // fatness
	Color color; // color
	
	ColorationArc(double x1, double y1, double x2, double y2, int f, Color color) {
		this.x1 = x1;
		this.y1 = y1;
		this.x2 = x2;
		this.y2 = y2;
		this.f = f;
		this.color = color;
	}
}
