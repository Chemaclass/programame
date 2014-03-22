package marcadores;

import static marcadores.Dig.P.*;

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