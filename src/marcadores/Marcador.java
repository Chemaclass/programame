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
        String buffer;
        do {
            d("Introduce un número: ");
            buffer = BR.readLine();
            //Generamos una lista de chars
            lista = new Lista(buffer);
            if(buffer.equals("-1"))break;
            //guardamos esa lista en nuestras listas
            Listas.LISTAS.add(lista);
        } while (!buffer.equals("-1"));

        Listas.printResultado();
    }

}

class Listas {

    public static final List<Lista> LISTAS;

    static {
        LISTAS = new LinkedList<>();
    }

    public static void printResultado() {
        for (Lista l : LISTAS) {
            System.out.println(l.getNumCambios());
        }
    }
}

class Lista {

    //Lista con los caracteres
    private final List<Character> LISTA;
    
    public Lista(String s) {
        //Generamos una lista a partir de la cadena pasada
        LISTA = new LinkedList<>();
        for (char c : s.toCharArray()) {
            LISTA.add(c);
        }
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

}
