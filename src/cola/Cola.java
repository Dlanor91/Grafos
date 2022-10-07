package cola;

public interface Cola<T> {
    void encolar(T dato);

    //Pre: !esVacia()
    T desencolar();
    boolean esVacia();
}
