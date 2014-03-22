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
        for (String s1 : ss) {
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
        return true;
    }

    public int getNumCambios() {
        int numCambios = 0;
        for (Character c : LISTA) {

            numCambios += getNumByChar(c);
        }
        return numCambios;
    }

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
