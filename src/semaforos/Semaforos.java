package semaforos;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import utils.U;

/**
 * https://www.aceptaelreto.com/problem/statement.php?id=113
 *
 * @author chemaclass
 */
public class Semaforos {


    private static final String[] SALIDA = {"0", "0"};
    /**
     * Calles con semáforos
     */
    private List<Calle> calles;

    public Semaforos() {
        calles = new ArrayList<>();
    }

    public static void main(String[] args) {
        new Semaforos().doMain(args);
    }

    private String[] leer(String input) throws IOException {
        input = U.BR.readLine();
        return input.split(" ");
    }

    /**
     * Comprueba que que se haya introducido "0 0" para salir de la app
     *
     * @param inputs String[]
     * @return boolean
     */
    private boolean fin(String[] inputs) {
        return (inputs.length == 2
                && inputs[0].equals(SALIDA[0])
                && inputs[1].equals(SALIDA[1]));
    }

    public void doMain(String... args) {
        Calle calle;
        String inputs[] = {}, input = "";

        while (!fin(inputs)) {
            try {
                U.d("Introduce 2 números(El número de semáforos y la velocidad máxima):");
                inputs = leer(input);
                if (fin(inputs)) {
                    break;
                }
                // Obtenemos los valores, el primero es el número de semáforos
                // y el segundo la velocidad (m/s) máxima
                byte numSemaforos = Byte.parseByte(inputs[0]);
                float velMax = Float.parseFloat(inputs[1]);
                //Creamos una nueva calle
                calle = new Calle(numSemaforos, velMax);

                U.d("Introduce 3 números(dist,tiem_cerr,tiem_abie), por cada semáforo:");
                // Obtenemos los datos de los semáforos, 3 por semáforo
                inputs = leer(input);
                //Montamos la calle con los parámetros introducidos por consola
                calle.setSemaforos(inputs);
                //Añadimos la nueva calle a nuestras calles
                calles.add(calle);

                U.d(calle);

            } catch (IOException ex) {
                U.de("IOException: " + ex);
            } catch (NumberFormatException ex) {
                U.de("NumberFormatException: " + ex);
            } catch (ArrayIndexOutOfBoundsException ex) {
                U.de("ArrayIndexOutOfBoundsException: " + ex);
            } catch (Exception ex) {
                U.de("Exception: " + ex);
            }
        }

        U.d("Programa finalizado... obteniendo resultados por calle...");
        //Obtenemos los resultados y los pintamos
        printResultados();
        U.d("END");
    }

    /**
     * Pinta los resultados
     */
    protected void printResultados() {
        for (Calle calle : calles) {
            try {
                System.out.println(calle.getTiempoTarda());
            } catch (ArithmeticException ex) {
                U.de("ArithmeticException: " + ex);
            }
        }
    }
}
