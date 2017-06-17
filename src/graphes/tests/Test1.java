/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package graphes.tests;

// Test de la classe Graph

import graphes.Graphe;
import graphes.uinterface.UI;

public class Test1 {
	public static void main(String[] args) throws Exception {
		System.out.println("Test 1 : test de la classe Graph");
		
		Graphe g;

		// petit graphe
		g = new Graphe(5, 5, 10);
		System.out.println(g);

		
	}
}
