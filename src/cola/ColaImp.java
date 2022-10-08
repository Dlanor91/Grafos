package cola;

public class ColaImp<T> implements Cola<T> {

    private NodoCola<T> inicio;
    private NodoCola<T> fin;
    private int cantElementos;

    public ColaImp() {

    }

    public NodoCola<T> getInicio() {
        return inicio;
    }

    public void setInicio(NodoCola<T> inicio) {
        this.inicio = inicio;
    }

    public NodoCola<T> getFin() {
        return fin;
    }

    public void setFin(NodoCola<T> fin) {
        this.fin = fin;
    }

    public int getCantElementos() {
        return cantElementos;
    }

    public void setCantElementos(int cantElementos) {
        this.cantElementos = cantElementos;
    }

    @Override
    public boolean esVacia() {
        return this.cantElementos == 0;
    }

    @Override
    public void encolar(T dato){
        if(this.inicio == null){
            inicio = new NodoCola<T>(dato);
            fin = inicio;
        }else{
            fin.setSiguiente(new NodoCola<T>(dato));
            fin = fin.getSiguiente();
        }
        this.cantElementos++;
    }

    @Override
    public T desencolar() {
        T dato = this.inicio.getDato();
        inicio = inicio.getSiguiente();
        this.cantElementos--;
        if(this.inicio == null){
            fin = null;
        }

        return dato;
    }


}
