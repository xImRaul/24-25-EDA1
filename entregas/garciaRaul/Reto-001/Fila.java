import java.util.Random;

public class Fila {

    private Cliente[] clientes;
    private int posicionUltimo;
    private Random generador;

    public Fila() {
        clientes = new Cliente[200];
        posicionUltimo = 0;
        generador = new Random();
    }

    public void añadirCliente() {
        int numClientes = generador.nextInt(3) + 1;
        
        for (int i = 0; i < numClientes; i++) {
            if (posicionUltimo < clientes.length) {
                clientes[posicionUltimo] = new Cliente();
                posicionUltimo++;
            }
        }
    }

    public void atenderCliente() {
        if (hayClientes()) {
            moverIzquierda(clientes, 0, posicionUltimo);
            posicionUltimo--;
        }
    }

    private boolean hayClientes() {
        return clientes[0] != null;
    }

    private void moverIzquierda(Cliente[] clientes, int indice, int elementosOcupados) {
        assert indice >= 0 && indice < elementosOcupados;

        for (int i = indice; i < elementosOcupados; i++) {
            clientes[i] = clientes[i + 1];
        }

        clientes[elementosOcupados] = null;
    }

    public void clienteSeVa() {
        assert posicionUltimo > 0;
        moverIzquierda(clientes, generador.nextInt(posicionUltimo), posicionUltimo);
        posicionUltimo--;
    }

    public void recibirItemsExternos() {
        if (posicionUltimo > 0 && generador.nextInt(100) < 20) { 
            int clienteSeleccionado = generador.nextInt(posicionUltimo); 
            int itemsExtra = 2 + generador.nextInt(4);
            clientes[clienteSeleccionado].añadirItems(itemsExtra);
        }
    }

    public int getPosicionUltimo() {
        return posicionUltimo;
    }

    public void moverDerecha(int posicion) {
        assert posicion >= 0 && posicion < clientes.length; 
        assert posicionUltimo < clientes.length;
        
        for (int i = posicionUltimo; i >= posicion; i--) {
            clientes[i + 1] = clientes[i]; 
        }
        
        posicionUltimo++;
        clientes[posicion] = new Cliente();
    }
}
