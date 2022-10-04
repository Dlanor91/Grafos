package inicio;

import grafo.*;
import lista.Lista;

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

		for(String vert : vertices) {
			System.out.println(vert);
		}

   
	}

}
