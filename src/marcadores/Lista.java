package marcadores;

import java.util.LinkedList;
import java.util.List;
import utilidades.Utils;

/**
 * Guarda una lista de números que representan un marcador
 *
 * @author chemaclass
 */
public class Lista {

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
