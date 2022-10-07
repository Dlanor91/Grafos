package cola;

public class ColaImp<T> implements Cola<T> {

    private NodoCola<T> inicio;
    private NodoCola<T> fin;
    private int largo;

    public ColaImp() {
    }

    @Override
    public void encolar(T dato){
        if(this.inicio == null){
            //inicio = fin = new NodoCola<T>(dato); //Alternativa
            inicio = new NodoCola<T>(dato);
            fin = inicio;
        }else{
            fin.setSiguiente(new NodoCola<T>(dato));
            fin = fin.getSiguiente();
        }
        this.largo++;
    }

    @Override
    public T desencolar() {
        return null;
    }

    @Override
    public boolean esVacia() {
        return false;
    }
}
