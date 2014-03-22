package marcadores;

import java.io.IOException;
import java.util.LinkedList;
import java.util.List;
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
        int numCambios = 0;
        //Str donde guardaremos el marcador que está visualizando actualmente
        String aux = "";
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
                numCambios += numByStr;
                d("aux: " + aux + " | " + flag + " | numByStr: " + numByStr
                        + " | numCambios: " + numCambios);
            }
            flag = true;
        } while (c++ < numLimite);
        return numCambios;
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
                return 6;
            case '1':
                return 2;
            case '2':
                return 5;
            case '3':
                return 5;
            case '4':
                return 4;
            case '5':
                return 5;
            case '6':
                return 6;
            case '7':
                return 3;
            case '8':
                return 7;
            case '9':
                return 6;
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
