package barjavier;

import java.io.*;
import java.util.List;
import java.util.ArrayList;

/**
 * Start: jue 6 de mar 21:00h;
 *
 * Finish: jue 6 de mar 23:00h
 *
 * @see https://www.aceptaelreto.com/problem/statement.php?id=105
 * @author chemaclass
 */
public class BarJavier {

    /**
     * Buffer de entrada
     */
    private final BufferedReader BR = new BufferedReader(new InputStreamReader(System.in));
    /**
     * Lista de semanas
     */
    private final List<Semana> semanas;

    //Para hacer debug
    private static final boolean log = false;

    public BarJavier() {
        this.semanas = new ArrayList<>();
    }

    public static void main(String[] args) throws IOException {
        new BarJavier().doMain(args);
    }

    private void doMain(String... args) throws IOException {
        // Semana con la que iremos trabajando
        Semana semana = new Semana();
        // Entrada de terminal
        String input;
        float ganancias;
        //Repetir mientras la entrada sea distinta de '-1'
        while (true) {
            try {
                if (log) {
                    System.out.print("Introduce las ganancias para el día '" + semana.getNombreDiaActual() + "'> ");
                }
                input = BR.readLine();
                if (input.equals("-1") && semana.getDiaActual() == 0) {
                    break;
                }
                ganancias = Float.parseFloat(input);

                semana.setDia(ganancias);

                if (log) {
                    System.out.println("----------");
                    System.out.println("Semana actual: " + semana);
                    System.out.println("Semanas: " + semanas);
                    System.out.println("----------");
                }
                //Cuando superemos los 6 días laborales [0..5] guardaremos la semana
                // y crearemos otra nueva.
                if (semana.getDiaActual() > 5) {
                    semanas.add(semana);
                    semana = new Semana();
                }
            } catch (java.lang.NumberFormatException e) {
                //System.out.println(e);
            }
        }
        // Añadimos la última semana 
        //semanas.add(semana);
        //Una vez terminado el programa pasaremos a obtener los resultados
        Resultado[] resultados = getMedia();

        for (Resultado r : resultados) {
            System.out.println(r);
        }
    }

    /**
     * Obtenemos los resultados de las semanas
     *
     * @return Resultados[]
     */
    private Resultado[] getMedia() {
        //Preparamos un array para almacenar los resultados
        Resultado[] resultados = new Resultado[semanas.size()];
        Resultado resultado;
        int count = 0;
        //Recorremos todas las semanas
        for (Semana s : semanas) {
            resultado = new Resultado();
            //obtenemos la media de las ganancias
            float media = s.getMediaSemanaMenosDomingo();
            float gananciaDomingo = s.getDia(DiaLaboral.DOMINGO).getGanancia();
            //Guardamos si la media es mayor que la ganancia del domingo
            resultado.setDomingoWin((gananciaDomingo > media) ? "SI" : (gananciaDomingo == media) ? "EMPATE" : "NO");
            //Guardamos las max y min ventas
            resultado.setMax(s.getMaxVentas());
            resultado.setMin(s.getMinVentas());
            //Guardamos el resultado en nuestra lista de resultados
            resultados[count++] = resultado;
        }
        return resultados;
    }

}

enum DiaLaboral {

    /**
     * 2
     */
    MARTES,
    /**
     * 3
     */
    MIERCOLES,
    /**
     * 4
     */
    JUEVES,
    /**
     * 5
     */
    VIERNES,
    /**
     * 6
     */
    SABADO,
    /**
     * 7
     */
    DOMINGO
}
