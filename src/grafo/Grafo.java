package grafo;

import cola.Cola;
import cola.ColaImp;
import lista.*;

public class Grafo {

    //Definir atributos
    private int tope;
    private int cantidad;
    private String[] vertices;
    private Arista[][] matAdyacentes; //matriz de adyacentes

    public Grafo(int unTope, boolean esDirigido) {
        this.tope = unTope;
        this.cantidad = 0;
        this.vertices = new String[unTope];
        this.matAdyacentes = new Arista[unTope][unTope];

        if (esDirigido) {
            for (int i = 0; i < this.tope; i++) {
                for (int j = 0; j < this.tope; j++) {
                    this.matAdyacentes[i][j] = new Arista(); //no se hace mas en otro lugar
                }
            }
        } else {
            for (int i = 0; i < this.tope; i++) {
                for (int j = i; j < this.tope; j++) {
                    this.matAdyacentes[i][j] = new Arista();
                    this.matAdyacentes[j][i] = this.matAdyacentes[i][j];

                }
            }
        }
    }

    public boolean esLleno() {
        return this.cantidad == this.tope;
    }

    public boolean esVacio() {
        return this.cantidad == 0;
    }

    // PRE: !esLleno()
    private int obtenerPosLibre() {
        for (int i = 0; i < this.tope; i++) {
            if (this.vertices[i] == null) {
                return i;
            }
        }
        return -1;
    }

    private int obtenerPos(String vert) {
        for (int i = 0; i < this.tope; i++) {
            if (this.vertices[i] != null) {
                if (this.vertices[i].equals(vert)) {
                    return i;
                }
            }
        }
        return -1;
    }

    // PRE: !esLleno && !existeVertice
    public void agregarVertice(String vert) {
        int pos = obtenerPosLibre();
        this.vertices[pos] = vert;
        this.cantidad++;
    }

    // PRE: existeVertice
    public void borrarVertice(String vert) {
        int pos = obtenerPos(vert);
        for (int k = 0; k < tope; k++) {
            this.matAdyacentes[pos][k].setExiste(false);//aqui borro los adyacentes
            this.matAdyacentes[k][pos].setExiste(false);
        }
        this.vertices[pos] = null;
        this.cantidad--;
    }

    public boolean existeVertice(String vert) {
        return obtenerPos(vert) != -1;
    }

    // existeVertice(origen) && existeVertice(destino) && !existeArista
    public void agregarArista(String origen, String destino, int peso) {
        int posOrigen = obtenerPos(origen);
        int posDestino = obtenerPos(destino);
        matAdyacentes[posOrigen][posDestino].setExiste(true);
        matAdyacentes[posOrigen][posDestino].setPeso(peso);
    }

    // existeVertice(origen) && existeVertice(destino)
    public boolean existerArista(String origen, String destino) {
        int posOrigen = obtenerPos(origen);
        int posDestino = obtenerPos(destino);
        return matAdyacentes[posOrigen][posDestino].isExiste();
    }

    // existeVertice(origen) && existeVertice(destino) && existeArista
    public void borrarArista(String origen, String destino) {
        int posOrigen = obtenerPos(origen);
        int posDestino = obtenerPos(destino);
        matAdyacentes[posOrigen][posDestino].setExiste(false);
    }

    public Lista<String> verticesAdyacentes(String vert) {
        Lista<String> retorno = new ListaImp<>();
        int pos = obtenerPos(vert);
        for (int i = 0; i < tope; i++) {
            if (this.matAdyacentes[pos][i].isExiste()) {
                retorno.insertar(vertices[i]);
            }
        }
        return retorno;
    }

    // Pre: existeVertice(vert)
    public Lista<String> verticesIncidentes(String vert) {
        Lista<String> retorno = new ListaImp<>();
        int pos = obtenerPos(vert);
        for (int i = 0; i < tope; i++) {
            if (this.matAdyacentes[i][pos].isExiste()) {
                retorno.insertar(vertices[i]);
            }
        }
        return retorno;
    }

    //Pre: existeVertice(vert)
    public void dfs(String vert) {
        boolean[] visitados = new boolean[tope]; // Inicia todo en false
        int pos = obtenerPos(vert);
        dfsRec(pos, visitados);

    }

    private void dfsRec(int pos, boolean[] visitados) {
        //Los pasos son
        //1ero imprimir
        //2do visitar
        //3ero por cada adyacente  no visitado llamar recursivo
        System.out.println(vertices[pos]);
        visitados[pos] = true;
        for (int i = 0; i < tope; i++) {
            if (this.matAdyacentes[pos][i].isExiste() && !visitados[i]) {
                dfsRec(i, visitados);
            }
        }

    }

    //visitando cuando desencolo puedo encolar elementos repetidos
    //Pre: existeVertice(vert)
    public void bfs(String vert) {
        boolean[] visitados = new boolean[tope];
        int inicio = obtenerPos(vert);
        Cola<Integer> cola = new ColaImp<>();
        cola.encolar(inicio);
        while (!cola.esVacia()) {
            //desencolar
            //si no esta visitado
            int pos = cola.desencolar();
            if (!visitados[pos]) {
                visitados[pos] = true;
                System.out.println(vertices[pos]);
                for (int i = 0; i < tope; i++) {
                    if (this.matAdyacentes[pos][i].isExiste() && !visitados[i]) {
                        cola.encolar(i);
                    }
                }
            }//si no esta visitado

        }
    }

    //Marcando como visitado al encolar no encolo elementos repetidos
    public void bfs2(String vert) {
        boolean[] visitados = new boolean[tope];
        int inicio = obtenerPos(vert);
        Cola<Integer> cola = new ColaImp<>();
        cola.encolar(inicio);
        visitados[inicio] = true;
        while (!cola.esVacia()) {
            int pos = cola.desencolar();
            System.out.println(vertices[pos]);
            for (int i = 0; i < tope; i++) {
                if (this.matAdyacentes[pos][i].isExiste() && !visitados[i]) {
                    cola.encolar(i);
                    visitados[i] = true;
                }
            }
        }
    }

    public int dijkstra(String vOrigen, String vDestino){
        int posOrigen = obtenerPos(vOrigen);
        int posDestino = obtenerPos(vDestino);

        boolean[] visitados = new boolean[this.tope];
        int[] costos = new int[this.tope];
        String[] anterior = new String[this.tope];
        for(int i = 0; i<tope; i++){
            costos[i] = Integer.MAX_VALUE;
            anterior[i] = "-";
        }
        costos[posOrigen] = 0; // Marcar el origen con distancia cero

        for(int i = 0; i<tope; i++){ //Loop (cantidad de vertices)
            //Obtener el vertice no visitado de menor costo(si hay varios cualquiera)
            int pos= obtenerSiguienteVerticeNoVisitadoDeMenorDistancia(costos,visitados);
            //2 Visitarlo
            visitados[pos] = true;
            //3 Evaluar sitengo que actualizar el costo de los adyacentes NO VISITADOS
            for(int j =0; j<tope;j++){
                if(matAdyacentes[pos][j].isExiste() && !visitados[j]){
                    int distanciaNueva = costos[pos] + matAdyacentes[pos][j].getPeso();
                    if(distanciaNueva < costos[j]) {
                        costos[j] = distanciaNueva;
                        anterior[j] = vertices[pos];
                    }
                }
            }
        }

        return costos[posDestino];
    }

    private int obtenerSiguienteVerticeNoVisitadoDeMenorDistancia(int [] costos, boolean[] visitados){
        int posMin = -1;
        int min = Integer.MAX_VALUE;
        for(int i = 0; i<tope; i++){
            if(!visitados[i] && costos[i]< min){
                min = costos[i];
                posMin = i;
            }
        }
        return posMin;
    }
}
