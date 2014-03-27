package concurso2014;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Collection;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;
import static concurso2014.ProblemaD.U.*;

/**
 * 
 * @author Chemaclass
 */
public class ProblemaD {

    Palabra[] palabras;

    public static void main(String[] args) {
        new ProblemaD().doMain(args);
    }

    public void doMain(String[] args) {
        try {
            d("Introduce el número de palabras: ");
            int c = Integer.parseInt(U.BR.readLine());
            palabras = new Palabra[c];
            for (int i = 0; i < c; i++) {
                d("Palabra[" + i + ']');

                String p = U.BR.readLine();

                Palabra palabra = new Palabra(p);
                palabras[i] = palabra;
            }
        } catch (IOException | NumberFormatException e) {
            de(e);
        }
        //Buscamos el que tenga más
        Palabra mayor = getPalabraMayor();
        System.out.println(mayor.PALABRA);
    }

    /**
     * Obtenemos la mayor palabra de nuestra lista
     *
     * @return Palabra
     */
    private Palabra getPalabraMayor() {
        Palabra mayor = new Palabra("");
        for (Palabra p : palabras) {
            if (mayor.getNumKaitas() < p.getNumKaitas()) {
                mayor = p;
            }
        }
        return mayor;
    }

    static class Palabra {

        private static final char[] CHARS_KAITAS = {'A', 'B', 'C', 'D', 'E', 'F', 'G', 'H', 'I', 'J', 'K'};
        public final String PALABRA;

        /**
         *
         * Tipo de caracter; Mayúscula o Minúscula
         */
        enum Tipo {

            MAYUS, MINUS
        }

        public Palabra(String s) {
            PALABRA = s;
        }

        /**
         *
         * @param s
         * @return
         */
        public long getNumKaitas() {
            long cMayus = getNumKaitas(Tipo.MAYUS);
            long cMinus = getNumKaitas(Tipo.MINUS);
            return (cMayus > cMinus) ? cMayus : cMinus;
        }

        /**
         * Obetener el número máximo de letras Kaitas sucesivas
         *
         * @param tipo Tipo por el que se comprobará
         * @return long
         */
        private int getNumKaitas(Tipo tipo) {
            char[] KAITAS = (tipo.equals(Tipo.MAYUS)) ? getChars(Tipo.MAYUS) : getChars(Tipo.MINUS);
            Set<Integer> posibles = new HashSet<>();
            int count = 0;
            label:
            for (char c : PALABRA.toCharArray()) {
                //primero comprobamos
                for (char k : KAITAS) {
                    if (c == k) {
                        count++;
                        posibles.add(count);
                        continue label;
                    }
                }
                if (isKaita(c)) {
                    count = 0;
                }
            }
            return getMax(posibles);
        }

        /**
         * Obtenemos el número máximo que tenemos en la collection
         *
         * @param posibles
         * @return
         */
        private int getMax(Collection<Integer> posibles) {
            int max = 0;
            Iterator<Integer> iter = posibles.iterator();
            while (iter.hasNext()) {
                int next = iter.next();
                if (max < next) {
                    max = next;
                }
            }
            return max;
        }

        /**
         *
         * @param s
         * @return
         */
        public boolean isKaita() {
            //recorremos
            for (char c : PALABRA.toLowerCase().toCharArray()) {
                for (char k : CHARS_KAITAS) {
                    if (c != k) {
                        return false;
                    }
                }
            }
            return true;
        }

        public boolean isKaita(char c) {
            //recorremos
            for (char k : CHARS_KAITAS) {
                if (c != k) {
                    return false;
                }
            }
            return true;
        }

        /**
         * Obtener un array de char con las letras que son kaitas
         *
         * @param tipo String Mayus|Minus
         * @return
         */
        public char[] getChars(Tipo tipo) {
            boolean isMayus = tipo.equals(Tipo.MAYUS);
            char[] a = new char[CHARS_KAITAS.length];
            for (int i = 0; i < CHARS_KAITAS.length; i++) {
                String s = String.valueOf(CHARS_KAITAS[i]);
                a[i] = (isMayus) ? s.toUpperCase().charAt(0) : s.toLowerCase().charAt(0);
            }
            return a;
        }
    }

    /**
     * Clase de utilidades
     *
     * @author Chemaclass
     */
    static class U {

        public static final BufferedReader BR = new BufferedReader(new InputStreamReader(System.in));

        private static final boolean flag = false, debug = flag, debugError = flag;

        public static String[] leer() throws IOException {
            return BR.readLine().split(" ");
        }

        /**
         * Imprime por pantalla un mensaje si estamos en modo debug
         *
         * @param o Object a imprimir
         */
        public static void d(Object o) {
            if (debug) {
                System.out.println(o);
            }
        }

        /**
         * Imprime por pantalla un mensaje si estamos en modo debugError
         *
         * @param o Object a imprimir
         */
        public static void de(Object o) {
            if (debugError) {
                System.err.println(o);
            }
        }

        /**
         * Comprobar si un Str es un número
         *
         * @param str
         * @return boolean
         */
        public static boolean isNumeric(String str) {
            try {
                double d = Double.parseDouble(str);
            } catch (NumberFormatException e) {
                return false;
            }
            return true;
        }

        /**
         * Transformar un array en un Str
         *
         * @param a Array<Object> array a convertir
         * @return Str Resultado final
         */
        public static String toStr(Object... a) {
            String str = "";
            if (debug) {
                str = "{";
                for (Object o : a) {
                    str += o + ", ";
                }
                str = "}";
            }
            return str;
        }
    }
}
