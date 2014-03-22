package marcadores;

import java.io.IOException;
import java.util.LinkedList;
import java.util.List;
import static marcadores.Dig.P.*;
import static utilidades.Utils.*;

/**
 *
 * @author chemaclass
 */
public class Marcador {

    public static void main(String[] args) throws IOException {
        new Marcador().doMain(args);
    }

    private void doMain(String... args) throws IOException {
        Lista lista;
        String buffer = "";
        do {
            try {
                d("Introduce un número: ");
                buffer = BR.readLine();
                //Generamos una lista de chars
                lista = new Lista(buffer);
                if (buffer.equals("-1")) {
                    break;
                }
                //guardamos esa lista en nuestras listas
                Listas.LISTAS.add(lista);

                arrToStr(Listas.LISTAS);

            } catch (MalFormadoException ex) {
                de(ex);
            }
        } while (!buffer.equals("-1"));

        Listas.printResultado();
    }

}

/**
 * Clase encargada de guardar todas las listas
 *
 * @author chemaclass
 */
class Listas {

    public static final List<Lista> LISTAS;

    static {
        LISTAS = new LinkedList<>();
    }

    private Listas() {
    }

    public static void printResultado() {
        for (Lista l : LISTAS) {
            System.out.println(l.getNumCambios());
        }
    }
}

/**
 * Guarda una lista de números que representan un marcador
 *
 * @author chemaclass
 */
class Lista {

    //Lista con los caracteres
    private final List<Character> LISTA;

    public Lista(String s) throws MalFormadoException {
        //Generamos una lista a partir de la cadena pasada
        LISTA = new LinkedList<>();
        //Dividimos por espacios
        String[] ss = s.split(" ");
        if (!esValido(ss)) {
            throw new MalFormadoException();
        }
        //Each todos los elementos
        String s1;//El último elemento será -1 (Por eso no lo incluimos)
        for (int i = 0; i < ss.length - 1; i++) {
            s1 = ss[i];
            Character c = s1.charAt(0);
            LISTA.add(c);
        }
    }

    /**
     * Comprobamos si el número dado está entre 9 y 0
     *
     * @param ss String []
     * @return boolean
     */
    private boolean esValido(String[] ss) {
        //Para que sea válido no deben tener más de un char
        String s;
        //Si sólo tiene espacios en blanco
        if (ss.length < 1) {
            return false;
        }
        for (int i = 0; i < ss.length; i++) {
            s = ss[i];
            //Si esun char enmedio y no es el final
            if (s.length() > 1 && i != ss.length - 1) {
                return false;
            } //Si es el último y es distinto de -1
            else if (i == ss.length - 1 && !s.equals("-1")) {
                return false;
            } //Si no es un número
            else if (!isNumeric(s)) {
                return false;
            }
        }
        arrToStr(ss);
        return true;
    }

    /**
     * Devuelve el número de cambios de la lista
     *
     * @return int Número de cambios del marcador
     */
    public int getNumCambios() {
        int numEncendidos = 0, numApagados = 0;
        //Str donde guardaremos el marcador que está visualizando actualmente
        String aux = "", aux2 = "";
        //Bandera que indica si se llegó a la mitad
        boolean flag = false;
        //Contador 
        byte c = 0, numLimite = 2;
        do {
            for (Character CHAR : LISTA) {
                //Si no se llegó a la mitad
                if (!flag) {
                    Character ch = CHAR;
                    aux += ch;
                } else {
                    try {
                        aux = aux.substring(1, aux.length());
                    } catch (StringIndexOutOfBoundsException e) {
                        de(e);
                        break;
                    }
                }
                int numByStr = getNumByString(aux);
                numEncendidos += numByStr;
                d("aux: " + aux + " | " + flag + " | numByStr: " + numByStr
                        + " | numEncendidos: " + numEncendidos
                        + " | numApagados: " + numApagados);
            }
            flag = true;
        } while (c++ < numLimite);
        return numEncendidos + numApagados;
    }

    /**
     * Obtenemos el número de cambios que tiene un str
     *
     * @param s String
     * @return int numCambios
     */
    private int getNumByString(String s) {
        int numCambios = 0;
        for (Character c : s.toCharArray()) {
            int numCambiosChar = getNumByChar(c);
            numCambios += numCambiosChar;
            d(" || numCambiosChar: " + numCambiosChar
                    + " | numCambios: " + numCambios);
        }
        return numCambios;
    }

    /**
     * Obtenemos el número de cambios que tiene un char
     *
     * @param c Character
     * @return int posiciones que ocupa
     */
    private int getNumByChar(Character c) {
        switch (c) {
            case '0':
                return Dig.CERO.POS.length;
            case '1':
                return Dig.UNO.POS.length;
            case '2':
                return Dig.DOS.POS.length;
            case '3':
                return Dig.TRES.POS.length;
            case '4':
                return Dig.CUATRO.POS.length;
            case '5':
                return Dig.CINCO.POS.length;
            case '6':
                return Dig.SEIS.POS.length;
            case '7':
                return Dig.SIETE.POS.length;
            case '8':
                return Dig.OCHO.POS.length;
            case '9':
                return Dig.NUEVE.POS.length;
            default:
                return 0;
        }
    }

    @Override
    public String toString() {
        String s = "Lista{ ";
        for (Character l : LISTA) {
            s += l + ",";
        }
        return s + "}";
    }

}

/**
 * Representa un dígito numérico
 *
 * @author chemaclass
 */
enum Dig {

    CERO(new P[]{AR, AB, I_AR, I_AB, D_AR, D_AB}),
    UNO(new P[]{D_AR, D_AB}),
    DOS(new P[]{AR, D_AR, CE, I_AB, AB}),
    TRES(new P[]{AR, AB, D_AR, D_AB, CE}),
    CUATRO(new P[]{I_AR, CE, D_AR, D_AB}),
    CINCO(new P[]{AR, I_AR, CE, D_AB, AB}),
    SEIS(new P[]{AR, AB, I_AR, I_AB, D_AB, CE}),
    SIETE(new P[]{AR, D_AR, D_AB}),
    OCHO(new P[]{AR, AB, I_AR, I_AB, D_AR, D_AB, CE}),
    NUEVE(new P[]{AR, AB, I_AR, D_AR, D_AB, CE});

    /**
     * Posiciones led posibles
     */
    enum P {

        /**
         * ARRIBA
         */
        AR,
        /**
         * ABAJO
         */
        AB,
        /**
         * CENTRO
         */
        CE,
        /**
         * IZQUIERDA ARRIBA
         */
        I_AR,
        /**
         * IZQUIERDA ABAJO
         */
        I_AB,
        /**
         * DERECHA ARRIBA
         */
        D_AR,
        /**
         * DERECHA ABAJO
         */
        D_AB;
    }

    public final P[] POS;

    private Dig(P[] pos) {
        this.POS = pos;
    }

    /**
     * Devuelve el número de dígitos comunes que tienen dos números (led)
     *
     * @param num Num
     * @return int número de dígitos comunes
     */
    public int getComun(Dig num) {
        int comun = 0;
        for (P p : POS) {
            for (P p2 : num.POS) {
                if (p.equals(p2)) {
                    comun++;
                }
            }
        }
        return comun;
    }
}

/**
 * Excepción para controlar una entrada mal formada. Las entradas deben estar
 * dadas por número de entre 0 y 9, separadas por un espacio.
 *
 * @author chemaclass
 */
class MalFormadoException extends Exception {

    private static final String ms = "Entrada mal formada.";

    public MalFormadoException() {
        super(ms);
    }
}
