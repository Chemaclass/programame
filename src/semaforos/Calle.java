package semaforos;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import utils.U;

/**
 *
 * @author chemaclass
 */
public class Calle {

    private static final int IMPOSIBLE = -1;
    private static final String IMPOSIBLE_STR = "IMPOSIBLE";

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
        try {
            for (int i = 0, j = 0; i < inputs.length; i += 3, j++) {
                float distanciaAnterior = Float.parseFloat(inputs[i]);
                float tiempoCerrado = Float.parseFloat(inputs[i + 1]);
                float tiempoAbierto = Float.parseFloat(inputs[i + 2]);
                //Creamos una nueva instancia Semaforo
                Semaforo semaforo = new Semaforo(distanciaAnterior, tiempoCerrado, tiempoAbierto);
                //Y la añadimos a la calle
                setSemaforo(j, semaforo);
            }
        } catch (Exception ex) {
            U.de(ex);
        }
    }

    /**
     * Establecer un nuevo semáforo en la calle
     *
     * @param i int Posición a colocar
     * @param semaforo Semaforo
     */
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
    public float getVelMed() {
        float d, t, r;
        Semaforo s;
        float velocidades[] = new float[semaforos.length];

        for (int i = 0; i < semaforos.length; i++) {
            try {
                s = semaforos[i];
                d = s.getDistanciaAnterior();
                //Según el semáforo cogeremos su tiempo de abierto o cerrado
                if (i % 2 == 0) {
                    t = s.getTiempoCerrado();
                } else {
                    t = s.getTiempoAbierto();
                }
                r = d / t;
                //Si es mayor que la velocidad máxima, debemos buscar una a una
                // hasta dar con alguna
                if (r > velMax) {
                    U.d("r > velMax == false");
                    for (int j = (int) t + 1; j < d; j++) {
                        U.d(d + " % " + j + " ? ");
                        if (d % j == 0) {
                            U.d(d + " % " + j + " == 0");
                            //Obtenemos el nuevo tiempo
                            r = d / j;
                            U.d("r => " + r);
                            break;
                        }
                    }
                    r = (r > velMax) ? IMPOSIBLE : r;
                } else if (r < 0.1f) {
                    r = IMPOSIBLE;
                }
                velocidades[i] = r;
                U.d("velocidad[" + i + "]=" + r + " m/s");
            } catch (NullPointerException ex) {
                U.de("NullPointerException: " + ex);
                velocidades[i] = IMPOSIBLE;
            }
        }

        return getMedia(velocidades);
    }

    /**
     * Obtenemos el tiempo que tarda el coche en segundos, teniendo en cuenta la
     * velocidad media a la que tiene que ir
     *
     * @return String
     */
    public String getTiempoTarda() {

        int velMed = (int) getVelMed();

        return (velMed == IMPOSIBLE) ? IMPOSIBLE_STR : String.valueOf(getDistanciaTotal() / velMed);
    }

    /**
     * Obtenemos la distancia todal que tendrá que recorrer, a partir de las
     * distancias que existen entre los semáforos de la calle
     *
     * @return int
     */
    private int getDistanciaTotal() {
        int t = 0;
        for (Semaforo s : semaforos) {
            t += s.getDistanciaAnterior();
        }
        return t;
    }

    /**
     * Obtenemos la media de las velocidades
     *
     * @param velocidades float[] Array de velocidades
     * @return float La media de toda las velocidades pasadas en el array
     */
    private float getMedia(float[] velocidades) {
        float total = 0, cont = 0;
        for (float v : velocidades) {
            //Con que haya una sola velocidad imposible bastará por dar por imposible
            // dicha calle
            if (v == IMPOSIBLE) {
                return IMPOSIBLE;
            }
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