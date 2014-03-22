package marcadores;

import java.io.IOException;
import java.util.LinkedList;
import java.util.List;
import static marcadores.Dig.P.*;
import utilidades.Utils;

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
                Utils.d("Introduce un número: ");
                buffer = Utils.BR.readLine();
                //Generamos una lista de chars
                lista = new Lista(buffer);
                if (buffer.equals("-1")) {
                    break;
                }
                //guardamos esa lista en nuestras listas
                Listas.LISTAS.add(lista);

                Utils.d(Utils.toStr(Listas.LISTAS));

            } catch (MalFormadoException ex) {
                Utils.de(ex);
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
            else if (!Utils.isNumeric(s)) {
                return false;
            }
        }
        return true;
    }

    /**
     * Devuelve el número de cambios de la lista
     *
     * @return int Número de cambios del marcador
     */
    public int getNumCambios() {
        int nCambios = 0;
        //Str donde guardaremos el marcador que está visualizando actualmente
        String actual = "", aux = "";
        //Bandera que indica si se llegó a la mitad
        boolean flag = false;
        //Contador 
        byte c = 0, numLimite = 2;
        do {
            for (final Character CHAR : LISTA) {
                //Si no se llegó a la mitad
                if (!flag) {
                    Character ch = CHAR;
                    actual += ch;
                } else {
                    try {
                        actual = actual.substring(1, actual.length());
                    } catch (StringIndexOutOfBoundsException e) {
                        Utils.de("StringIndexOutOfBoundsException. break;");
                        break;
                    }
                }
                nCambios += getNumByString(actual, aux);

                Utils.d("actual: " + actual + " - aux: " + aux + " | " + flag
                        + " || nCambios: " + nCambios);
                //Guardamos la imagen actual en una auxiliar
                aux = actual;
            }
            flag = true;
        } while (c++ < numLimite);
        //Multiplicamos por 2 porque nos da la mitad del resultado
        return nCambios * 2;
    }

    /**
     * Obtenemos el número de cambios que existen entre dos Str
     *
     * @param s String primera imagen
     * @param s2 String segunda imagen
     * @return int Número de cambios
     */
    private int getNumByString(String s, String s2) {
        int nCambios = 0;
        Dig d1 = Dig.VACIO, d2;
        char[] carray = s.toCharArray();
        String saux = Utils.lpad(s2, s.length());
        char[] carray2 = saux.toCharArray();
        Utils.d("--- carray: " + Utils.toStr(carray) + " | carray2: " + Utils.toStr(carray2));
        Utils.d(" ||| s: " + s + " | saux: " + saux);

        for (int i = 0; i < carray.length; i++) {
            try {
                d1 = Dig.getByChar(carray[i]);
                d2 = Dig.getByChar(carray2[i]);

                nCambios += d1.getDif(d2);

                Utils.d(" >|| d1: " + d1.name() + " || d2: " + d2.name()
                        + " | nCambios: " + nCambios);

            } catch (ArrayIndexOutOfBoundsException e) {
                //de("> a > ArrayIndexOutOfBoundsException. ");
                nCambios += d1.POS.length;
                Utils.d("nCambios=> " + nCambios);
            } catch (NullPointerException e) {
                Utils.de("> a > NullPointerException. ");
            }
        }
        return nCambios;
    }

    /**
     * Obtenemos el número de cambios que tiene un char
     *
     * @param c Character
     * @return int posiciones que ocupa
     *
     * private int getNumByChar(Character c) { return
     * Dig.getByChar(c).POS.length; }
     */
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

    VACIO(new P[]{}),
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
    private int getComun(Dig num) {
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

    /**
     * Devuelve el número de dígitos diferentes que tienen dos dígitos led. Es
     * el resultado de el número de dígitos totales - los que tienen en común
     *
     * @param num Num
     * @return int número de dígitos diferentes
     */
    public int getDif(Dig num) {
        return POS.length - getComun(num);
    }

    public static Dig getByChar(Character c) {
        switch (c) {
            case '0':
                return Dig.CERO;
            case '1':
                return Dig.UNO;
            case '2':
                return Dig.DOS;
            case '3':
                return Dig.TRES;
            case '4':
                return Dig.CUATRO;
            case '5':
                return Dig.CINCO;
            case '6':
                return Dig.SEIS;
            case '7':
                return Dig.SIETE;
            case '8':
                return Dig.OCHO;
            case '9':
                return Dig.NUEVE;
            default:
                return Dig.VACIO;
        }
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
