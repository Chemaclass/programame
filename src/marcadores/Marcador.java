package marcadores;

import java.io.IOException;
import java.util.LinkedList;
import java.util.List;
import utils.U;

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
                U.d("Introduce un número: ");
                buffer = U.BR.readLine();
                //Generamos una lista de chars
                lista = new Lista(buffer);
                if (buffer.equals("-1")) {
                    break;
                }
                //guardamos esa lista en nuestras listas
                Listas.LISTAS.add(lista);

                U.d(U.toStr(Listas.LISTAS));

            } catch (MalFormadoException ex) {
                U.de(ex);
            }
        } while (!buffer.equals("-1"));

        Listas.printResultado();
    }

    /**
     * Clase encargada de guardar todas las listas
     *
     * @author chemaclass
     */
    static class Listas {

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
}
