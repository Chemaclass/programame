package pruebadel9;

import java.io.IOException;
import static utilidades.Utils.*;

/**
 *
 * @author chemaclass
 */
public class PruebaDel9 {

    Casos casos;

    public static void main(String[] args) {
        new PruebaDel9().doMain(args);
    }

    private void doMain(String... args) {
        d("Introduce el número de casos de prueba");
        try {
            int num = Integer.parseInt(BR.readLine());
            casos = new Casos(num);
            //rellenamos todos los casos de prueba
            for (int i = 0; i < num; i++) {
                try {
                    String input = BR.readLine();
                    String[] inputs = input.split(" ");
                    //Si se pasaron !=5 param, salta exception y no cuenta i
                    if (inputs.length != 5) {
                        throw new Exception("Número de parámetros incorrecto. Deben ser 5");
                    }
                    
                    
                } catch (Exception e) {
                    de(e);
                    i--; //restamos por el error
                }
            }

        } catch (NumberFormatException | IOException e) {
            de(e);
        }
        d(casos.getNumCasos());
    }
}

class Casos {

    public final Division[] CASOS_PRUEBA;

    public Casos(int num) {
        CASOS_PRUEBA = new Division[num];
    }

    public int getNumCasos() {
        return CASOS_PRUEBA.length;
    }
}

class Division {

}
