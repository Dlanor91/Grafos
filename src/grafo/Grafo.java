package grafo;

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

        if(esDirigido){
            for(int i = 0; i < this.tope; i++){
                for (int j=0; j< this.tope; j++){
                    this.matAdyacentes[i][j] = new Arista(); //no se hace mas en otro lugar
                }
            }
        }else{
            for(int i = 0; i < this.tope; i++){
                for (int j=i; j< this.tope; j++){
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
        for(int i =0; i < this.tope; i++){
            if(this.vertices[i] == null){
                return i;
            }
        }
        return -1;
    }

    private int obtenerPos(String vert) {
        for(int i =0; i < this.tope; i++){
            if(this.vertices[i].equals(vert)){
                return i;
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
        Lista<String> retorno = new ListaImp<>();
        int pos = obtenerPos(vert);
        this.vertices[pos] = null;
        for(int k=0; k < tope ; k++){
            this.matAdyacentes[pos][k].setExiste(false);//aqui borro los adyacentes
            this.matAdyacentes[k][pos].setExiste(false);
        }
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
        for(int i=0; i < tope ; i++){
            if(this.matAdyacentes[pos][i].isExiste()){
                retorno.insertar(vertices[i]);
            }
        }
        return retorno;
    }

    // Pre: existeVertice(vert)
    public Lista<String> verticesIncidentes(String vert) {
        Lista<String> retorno = new ListaImp<>();
        int pos = obtenerPos(vert);
        for(int i=0; i < tope ; i++){
            if(this.matAdyacentes[pos][i].isExiste()){
                retorno.insertar(vertices[i]);
            }
        }
        return retorno;
    }

    //Pre: existeVertice(vert)
    public void dfs(String vert){
        boolean[] visitados = new boolean[tope]; // Inicia todo en false
        Lista<String> retorno = new ListaImp<>();
        int pos = obtenerPos(vert);
        dfsRec(pos,visitados);

    }

    private void dfsRec(int pos, boolean[] visitados){
      //Los pasos son
      //1ero imprimir
      //2do visitar
      //3ero por cada adyacente  no visitado llamar recursivo
        System.out.println(vertices[pos]);
        visitados[pos]=true;
        for (int i = 0; i < tope; i++) {
            if (this.matAdyacentes[pos][i].isExiste() && !visitados[i]) {
                dfsRec(i,visitados);
            }
        }

    }

}