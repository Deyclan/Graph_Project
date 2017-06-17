/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package graphes;

@SuppressWarnings("rawtypes")
// Noeud d'un graphe
public class Sommet implements Comparable {

  final int id; // identifiant
  final int val; // valeur

  // constructeur
  Sommet(int i, int v){
    id = i;
    val = v;
  }

  // fonction de comparaison
  public int compareTo(Object o1) {
    Sommet n = (Sommet) o1;
    if (this.val == n.val) return (this.id - n.id);
    return this.val - n.val;
  }
}
