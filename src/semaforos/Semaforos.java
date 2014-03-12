package semaforos;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import utilidades.Utils;

/**
 * https://www.aceptaelreto.com/problem/statement.php?id=113
 *
 * @author chemaclass
 */
public class Semaforos {

    /**
     * Buffer de entrada
     */
    public static final BufferedReader BR = new BufferedReader(new InputStreamReader(System.in));

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
        input = BR.readLine();
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
                Utils.d("Introduce 2 números(El número de semáforos y la velocidad máxima):");
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

                Utils.d("Introduce 3 números(dist,tiem_cerr,tiem_abie), por cada semáforo:");
                // Obtenemos los datos de los semáforos, 3 por semáforo
                inputs = leer(input);
                //Montamos la calle con los parámetros introducidos por consola
                calle.setSemaforos(inputs);
                //Añadimos la nueva calle a nuestras calles
                calles.add(calle);

                Utils.d(calle);

            } catch (IOException ex) {
                Utils.de("IOException: " + ex);
            } catch (NumberFormatException ex) {
                Utils.de("NumberFormatException: " + ex);
            } catch (ArrayIndexOutOfBoundsException ex) {
                Utils.de("ArrayIndexOutOfBoundsException: " + ex);
            }
        }

        Utils.d("Programa finalizado... obteniendo resultados por calle...");
        //Obtenemos los resultados y los pintamos
        printResultados();
        Utils.d("END");
    }

    /**
     * Pinta los resultados
     */
    protected void printResultados() {
        for (Calle calle : calles) {
            System.out.println(calle.getVelMed());
        }
    }
}

