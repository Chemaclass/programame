package semaforos;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import utilidades.Utils;

/**
 *
 * @author chemaclass
 */
public class Calle {

    private Semaforo[] semaforos;
    private float velMax;

    public Calle() {
    }

    public Calle(byte numSemaforos, float velMax) {
        this.semaforos = new Semaforo[numSemaforos];
        this.velMax = velMax;
    }

    public List<Semaforo> getListSemaforos() {
        List<Semaforo> l = new ArrayList();
        l.addAll(Arrays.asList(semaforos));
        return l;
    }

    public Semaforo[] getSemaforos() {
        return semaforos;
    }

    public void setSemaforos(Semaforo[] semaforos) {
        this.semaforos = semaforos;
    }

    /**
     * Montamos los semáforos de la calle a partir del array de String que
     * introducimos por consola
     *
     * @param inputs String[]
     */
    public void setSemaforos(String[] inputs) {
        for (int i = 0, j = 0; i < inputs.length; i += 3, j++) {
            float distanciaAnterior = Float.parseFloat(inputs[i]);
            float tiempoCerrado = Float.parseFloat(inputs[i + 1]);
            float tiempoAbierto = Float.parseFloat(inputs[i + 2]);
            //Creamos una nueva instancia Semaforo
            Semaforo semaforo = new Semaforo(distanciaAnterior, tiempoCerrado, tiempoAbierto);
            //Y la añadimos a la calle
            setSemaforo(j, semaforo);
        }
    }

    public void setSemaforo(int i, Semaforo semaforo) {
        semaforos[i] = semaforo;
    }

    public float getVelMax() {
        return velMax;
    }

    public void setVelMax(float velMax) {
        this.velMax = velMax;
    }

    /**
     * Obtenemos la velocidad media
     *
     * @return String velocidad media o IMPOSIBLE si no se puede
     */
    public String getVelMed() {
       
        String velMed = "IMPOSIBLE";
       
        return velMed;
    }

    private float getMedia(float[] velocidad) {
        float total = 0, cont = 0;
        for (float v : velocidad) {
            total += v;
            cont++;
        }
        return total / cont;
    }

    @Override
    public String toString() {
        return "Calle{" + "semaforos=" + getListSemaforos() + ", velMax=" + velMax + '}';
    }
}
