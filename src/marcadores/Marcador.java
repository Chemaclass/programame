package marcadores;

import java.io.IOException;
import java.util.LinkedList;
import java.util.List;
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
