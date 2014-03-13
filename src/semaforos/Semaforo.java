package semaforos;

/**
 *
 * @author chemaclass
 */
public class Semaforo {

    /**
     * Estados de un semaforo
     */
    enum Estado {

        ABIERTO, CERRADO
    }

    private float distanciaAnterior;
    private float tiempoCerrado;
    private float tiempoAbierto;
    private Estado estadoActual;

    public Semaforo() {
        this.distanciaAnterior = 0;
        this.tiempoCerrado = 0;
        this.tiempoAbierto = 0;
        this.estadoActual = Estado.CERRADO;
    }

    public Semaforo(float distanciaAnterior, float tiempoCerrado, float tiempoAbierto) {
        this.distanciaAnterior = distanciaAnterior;
        this.tiempoCerrado = tiempoCerrado;
        this.tiempoAbierto = tiempoAbierto;
        this.estadoActual = Estado.CERRADO;
    }

    public float getTiempoCerrado() {
        return tiempoCerrado;
    }

    public void setTiempoCerrado(float tiempoCerrado) {
        this.tiempoCerrado = tiempoCerrado;
    }

    public float getTiempoAbierto() {
        return tiempoAbierto;
    }

    public void setTiempoAbierto(float tiempoAbierto) {
        this.tiempoAbierto = tiempoAbierto;
    }

    public Estado getEstadoActual() {
        return estadoActual;
    }

    public void setEstadoActual(Estado estadoActual) {
        this.estadoActual = estadoActual;
    }

    public float getDistanciaAnterior() {
        return distanciaAnterior;
    }

    public void setDistanciaAnterior(float distanciaAnterior) {
        this.distanciaAnterior = distanciaAnterior;
    }

    @Override
    public String toString() {
        return "Semaforo{" + "distanciaAnterior=" + distanciaAnterior + ", tiempoCerrado=" + tiempoCerrado + ", tiempoAbierto=" + tiempoAbierto + ", estadoActual=" + estadoActual + '}';
    }
}