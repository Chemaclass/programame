package barjavier;

import java.io.*;
import java.util.List;
import java.util.ArrayList;

/**
 * Start: jue 6 de mar 21:00h;
 *
 * Finish: jue 6 de mar 23:00h
 *
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
    private List<Semana> semanas;

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

class Resultado {

    private Dia max;
    private Dia min;
    private String isDomingoWin;

    public Resultado() {
    }

    public Resultado(Dia max, Dia min, String domin) {
        this.max = max;
        this.min = min;
        this.isDomingoWin = domin;
    }

    public Dia getMax() {
        return max;
    }

    public void setMax(Dia max) {
        this.max = max;
    }

    public Dia getMin() {
        return min;
    }

    public void setMin(Dia min) {
        this.min = min;
    }

    public String getDomingoWin() {
        return isDomingoWin;
    }

    public void setDomingoWin(String domingoWin) {
        this.isDomingoWin = domingoWin;
    }

    @Override
    public String toString() {
        return max.getDia().name() + " " + min.getDia().name() + " " + isDomingoWin;
    }

}

/**
 * Semana laboral
 */
class Semana {

    /**
     * Días laborales de la semana
     */
    private Dia[] dias;
    /**
     * Número días de la semana
     */
    private byte diaActual = 0;

    public Semana() {
        dias = new Dia[6];
        //Inicializamos los días de la semana laborales de la semana
        for (int i = 0, j = 2; i < 6; i++, j++) {
            dias[i] = new Dia(j);
        }
    }

    public Semana(int n) {
        dias = new Dia[n];
    }

    public Dia[] getDias() {
        return dias;
    }

    public void setDias(Dia[] dias) {
        this.dias = dias;
    }

    /**
     * Obtenemos el día n
     */
    public Dia getDia(int n) {
        return dias[n];
    }

    /**
     * Obtenemos el día n
     */
    public Dia getDia(DiaLaboral dl) {
        switch (dl) {
            case MARTES:
                return dias[0];
            case MIERCOLES:
                return dias[1];
            case JUEVES:
                return dias[2];
            case VIERNES:
                return dias[3];
            case SABADO:
                return dias[4];
            case DOMINGO:
                return dias[5];
            default:
                return null;
        }
    }

    public byte getDiaActual() {
        return diaActual;
    }

    public String getNombreDiaActual() {
        return dias[diaActual].getDia().name();
    }

    public void setDia(int n, float ganancia) {
        this.dias[n].setGanancia(ganancia);
    }

    /**
     *
     * @param ganancia
     */
    public void setDia(float ganancia) {
        this.dias[diaActual++].setGanancia(ganancia);
    }

    public String showDias() {
        String s = "\n";
        for (Dia d : dias) {
            s += d + ", ";
        }
        return s + "\n";
    }

    /**
     * Obtenemos la media de todos los días excepto el domingo
     *
     * @return
     */
    public float getMediaSemanaMenosDomingo() {
        float totalGanancias = 0, totalDias = 0;
        for (Dia d : dias) {
            //Si es domingo lo saltamos
            if (d.getDia().equals(DiaLaboral.DOMINGO)) {
                continue;
            }
            //Si ese día ha habido ganancias 
            if (d.getGanancia() > 0) {
                totalDias++;
            }
            //de lo contrario sumaremos el total de sus ganancias
            totalGanancias += d.getGanancia();

        }
        return totalGanancias / totalDias;
    }

    /**
     * Obtenemos el día de mayores ventas/beneficios
     *
     * @return Dia
     */
    public Dia getMaxVentas() {
        Dia diaMax = null;
        for (Dia d : dias) {
            if (diaMax == null) {
                diaMax = d;
            }
            //Si la ganancia del día actual es mayor que la del max
            if (d.getGanancia() > diaMax.getGanancia()) {
                diaMax = d;
            }
        }
        return diaMax;
    }

    /**
     * Obetenemos el día de menos beneficios/ventas
     *
     * @return Dia
     */
    public Dia getMinVentas() {
        Dia diaMin = null;
        for (Dia d : dias) {
            if (diaMin == null) {
                diaMin = d;
            }
            //Si la ganancia del día actual es mayor que la del max
            if (d.getGanancia() < diaMin.getGanancia()) {
                diaMin = d;
            }
        }
        return diaMin;
    }

    @Override
    public String toString() {
        return "Semana{" + showDias() + "}";
    }
}

/**
 * Días Laborales -> Martes..Domingo
 */
class Dia {

    private DiaLaboral dia;
    private float ganancia;

    public Dia(int n) {
        this.dia = makeDia(n);
    }

    public Dia(DiaLaboral dl) {
        this.dia = dl;
    }

    /**
     * Obtenemos el día laboral correspondiente [0..5]
     */
    private DiaLaboral makeDia(int n) {
        switch (n) {
            case 2:
                return DiaLaboral.MARTES;
            case 3:
                return DiaLaboral.MIERCOLES;
            case 4:
                return DiaLaboral.JUEVES;
            case 5:
                return DiaLaboral.VIERNES;
            case 6:
                return DiaLaboral.SABADO;
            case 7:
                return DiaLaboral.DOMINGO;
            default:
                return null;
        }

    }

    public void setGanancia(float ganancia) {
        this.ganancia = ganancia;
    }

    public float getGanancia() {
        return ganancia;
    }

    public DiaLaboral getDia() {
        return dia;
    }

    public void setDia(DiaLaboral dia) {
        this.dia = dia;
    }

    @Override
    public String toString() {
        return "Dia{" + dia + ", ganancia: " + ganancia + "}";
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
