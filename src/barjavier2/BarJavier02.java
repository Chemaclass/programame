package barjavier2;

import java.io.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * start: sab 8 de mar 16:40 finish: sab 8 de mar 19:00
 *
 * @author Chemaclass
 */
public class BarJavier02 {

    public static final BufferedReader BR = new BufferedReader(new InputStreamReader(System.in));
    public static final boolean isLog = false;
    private final List<Dia> dias;

    public BarJavier02() {
        dias = new ArrayList<>();
    }

    public static void main(String[] args) {
        new BarJavier02().doMain(args);
    }

    public void doMain(String[] args) {
        log("BEGIN");

        Dia dia = new Dia();
        String input;
        boolean isContinuamos = true;
        do {
            try {
                log("Introduce el codigo y los benefícios:");
                //Leemos la linea
                input = BR.readLine();
                //obtenemos las entradas
                String[] inputs = input.split(" ");

                Codigo codigo = Codigo.getCodigoById(inputs[0]);
                float ganancia = Float.parseFloat(inputs[1]);
                //Si introducimos 'N 0' cerramos el día y preparamos el siguiente
                if (codigo.equals(Codigo.INEXISTENTE) && ganancia == 0) {
                    dias.add(dia);
                    dia = new Dia();
                } //De lo contrario, comprobamos que el código esté entre
                // los códigos posibles a añadir y añadimos un nuevo 
                // codigo/ganancia al día
                else if (codigo.in()) {
                    dia.setCategoria(codigo, ganancia);
                }
            } catch (CodigoNotFoundException ex) {
                log(ex.getMessage());
                log("EXIT");
                isContinuamos = false;
            } catch (IOException | NumberFormatException ex) {
            }

            log("DIA ->" + dia);
            log("DIAS->" + dias);
        } while (isContinuamos);

        //Terminado
        try {
            Resultado[] resultados;
            resultados = getResultados();
            printResultados(resultados);
        } catch (Exception e) {
            log("Exception to try render to results ");
        }

        log("END");
    }

    private Resultado[] getResultados() throws Exception {
        Resultado[] resultados = new Resultado[dias.size()];
        for (int i = 0; i < dias.size(); i++) {
            Dia dia = dias.get(i);
            resultados[i] = new Resultado(
                    dia.getMax(),
                    dia.getMin(),
                    dia.isComidas()
            );
        }

        return resultados;
    }

    private void printResultados(Resultado[] resultados) throws Exception {
        for (Resultado r : resultados) {
            String s = r.max + "#" + r.min + "#" + r.isComidas;
            System.out.println(s);
        }
    }

    /**
     * Out
     *
     * @param s String
     */
    private void log(Object s) {
        if (isLog) {
            System.out.println(s);
        }
    }
}

class Resultado {

    public final String max;
    public final String min;
    public final String isComidas;

    public Resultado(String max, String min, String isComidas) {
        this.max = max.toUpperCase();
        this.min = min.toUpperCase();
        this.isComidas = isComidas.toUpperCase();
    }

}

/**
 * Día
 *
 * @author chemaclass
 */
class Dia {

    private final Map<Codigo, Float> categorias;

    /**
     * Inicializamos las categorías con los códigos permitidos
     */
    public Dia() {
        categorias = new HashMap<>();
        //inicializamos todos nuestros códigos con una ganancia de 0.0f
        for (Codigo c : Codigo.getCodigos()) {
            categorias.put(c, 0.0f);
        }
    }

    /**
     * Añadir una nueva categoría al día
     *
     * @param categoria Categoria
     */
    public void setCategoria(Codigo c, Float g) {
        categorias.put(c, g);
    }

    /**
     * Obtener la categoría del día con ganancia máxima
     *
     * @return Codigo con mayor beneficio
     */
    public String getMax() {
        Codigo codigoMax = Codigo.INEXISTENTE, codigo;
        float gananciaMax = 0f, ganancia;
        for (Map.Entry pairs : categorias.entrySet()) {
            codigo = (Codigo) pairs.getKey();
            ganancia = (Float) pairs.getValue();
            if (gananciaMax < ganancia) {
                gananciaMax = ganancia;
                //Reasignamos el nuevo código con mayor ganancia
                codigoMax = codigo;
            } else if (gananciaMax == ganancia) {
                codigoMax = Codigo.EMPATE;
            }
        }
        return codigoMax.valor;
    }

    /**
     * Obtener la media de las ganancias menos la de comidas
     *
     * @return float Media de las ganancias menos comidas
     */
    public float getMediaMenosComidas() {
        Codigo codigo;
        float gananciaTotal = 0f;
        for (Map.Entry pairs : categorias.entrySet()) {
            codigo = (Codigo) pairs.getKey();
            if (codigo.equals(Codigo.COMIDAS)) {
                continue;
            }
            gananciaTotal += (Float) pairs.getValue();

        }
        // La media se hace con las ganancias / número total de categorías
        // Si una categoría no está tiene como ganancias 0, y cuenta.
        return gananciaTotal / categorias.size();
    }

    /**
     * Comprobar si la media de las comidas es mayor que la media de todas las
     * demás ganancias
     *
     * @return SI o NO
     */
    public String isComidas() {

        if (categorias.containsKey(Codigo.COMIDAS)) {
            float gananciaComida = categorias.get(Codigo.COMIDAS);
            float gananciasMedia = getMediaMenosComidas();
            return (gananciaComida > gananciasMedia) ? "SI" : "NO";
        } else {
            return "NO";
        }
    }

    /**
     * Obtener la categoría del día con ganancia mínima
     *
     * @return Codigo con menor beneficio
     */
    public String getMin() {
        Codigo codigoMin = Codigo.INEXISTENTE, codigo;
        //No puede haber ganancia -1, por eso es para el primer código
        float gananciaMin = -1f, ganancia;
        for (Map.Entry pairs : categorias.entrySet()) {
            codigo = (Codigo) pairs.getKey();
            ganancia = (Float) pairs.getValue();
            if (ganancia < gananciaMin || gananciaMin == -1) {
                gananciaMin = ganancia;
                //Reasignamos el nuevo código con mayor ganancia
                codigoMin = codigo;
            } else if (gananciaMin == ganancia) {
                codigoMin = Codigo.EMPATE;
            }
        }
        return codigoMin.valor;
    }

    @Override
    public String toString() {
        return "Dia{" + "categorias=" + categorias + '}';
    }
}

/**
 * Código de la comida
 *
 * @author chemaclass
 */
enum Codigo {
    /* Códigos*/

    EMPATE("E", "EMPATE"),
    INEXISTENTE("N", "Inexistente"),
    DESAYUNOS("D", "Desayunos"),
    COMIDAS("A", "Comidas"),
    MERIENDAS("M", "Meriendas"),
    CENAS("I", "Cenas"),
    COPAS("C", "Copas");

    public final String id;
    public final String valor;

    private Codigo(String clave, String valor) {
        this.id = clave;
        this.valor = valor;
    }

    /**
     * Obtenemos todos los códigos de los distintos menus del bar
     *
     * @return
     */
    public static Codigo[] getCodigos() {
        Codigo[] codigos = {DESAYUNOS, COMIDAS, MERIENDAS, CENAS, COPAS};
        return codigos;
    }

    /**
     * Comprobamos que nuestro código tenga la id de uno de los códigos listados
     * en getCodigos()
     *
     * @return
     */
    public boolean in() {
        for (Codigo c : getCodigos()) {
            if (id.equals(c.id)) {
                return true;
            }
        }
        return false;
    }

    /**
     * Obtenemos el Código mediante su ID
     *
     * @param id
     * @return Codigo
     */
    public static Codigo getCodigoById(final String id) throws CodigoNotFoundException {
        if (id.equals(DESAYUNOS.id)) {
            return DESAYUNOS;
        } else if (id.equals(COMIDAS.id)) {
            return COMIDAS;
        } else if (id.equals(MERIENDAS.id)) {
            return MERIENDAS;
        } else if (id.equals(CENAS.id)) {
            return CENAS;
        } else if (id.equals(COPAS.id)) {
            return COPAS;
        } else if (id.equals(INEXISTENTE.id)) {
            return INEXISTENTE;
        } else if (id.equals(EMPATE.id)) {
            return EMPATE;
        } else {
            throw new CodigoNotFoundException();
        }

    }
}

/**
 * Exception que controla que el código exista
 *
 * @author chemaclass
 */
class CodigoNotFoundException extends Exception {

    public CodigoNotFoundException() {
        super("Código no existente");
    }
}
