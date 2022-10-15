package inicio;

import grafo.*;
import lista.Lista;

import java.sql.SQLOutput;

public class Main {

	public static void main(String[] args) {
		
		Grafo g = new Grafo(7,true);

		
		g.agregarVertice("1");
		g.agregarVertice("2");
		g.agregarVertice("3");
		g.agregarVertice("4");
		g.agregarVertice("5");
		g.agregarVertice("6");
		g.agregarVertice("7");
	
		g.agregarArista("4", "2", 1);
		g.agregarArista("4", "6", 1);
		g.agregarArista("2", "1", 1);
		g.agregarArista("2", "3", 1);
		g.agregarArista("6", "5", 1);
		g.agregarArista("6", "7", 1);
		g.agregarArista("1", "4", 1);

		Lista<String> vertices = g.verticesAdyacentes("6");
		System.out.println("--Vertivces Adyacentes--");
		for(String vert : vertices) {
			System.out.println(vert);
		}

		System.out.println("--Vertivces Incidentes--");
		Lista<String> verticesIncidentes = g.verticesIncidentes("4");
		for(String vert : verticesIncidentes) {
			System.out.println(vert);
		}

		g.borrarArista("1","4");
		System.out.println("--Vertivces Incidentes--");
		Lista<String> verticesIncidentes2 = g.verticesIncidentes("4");
		for(String vert : verticesIncidentes2) {
			System.out.println(vert);
		}

		System.out.println("--Recorrida por Profundidad--");
		g.dfs("4");
		g.borrarVertice("3");
		System.out.println(g.existeVertice("3"));
		System.out.println("--Recorrida por Amplitud--");
		g.bfs("4");

		System.out.println("Dijsktra");
		Grafo g2 = new Grafo(8,false);
		g2.agregarVertice("A");
		g2.agregarVertice("B");
		g2.agregarVertice("C");
		g2.agregarVertice("D");
		g2.agregarVertice("E");
		g2.agregarVertice("F");
		g2.agregarVertice("G");
		g2.agregarVertice("H");

		g2.agregarArista("A","C",2);
		g2.agregarArista("A","B",2);
		g2.agregarArista("B","E",4);
		g2.agregarArista("B","H",1);
		g2.agregarArista("C","F",4);
		g2.agregarArista("D","F",9);
		g2.agregarArista("D","H",8);
		g2.agregarArista("E","G",2);
		g2.agregarArista("F","G",3);
		g2.agregarArista("G","H",7);

		System.out.println(g2.dijkstra("D","E"));

	}

}
