package barjavier;

/**
 * Semana laboral
 *
 * @author chemaclass
 */
class Semana {

    /**
     * Días laborales de la semana
     */
    private Dia[] dias;
    /**
     * Número días de la semana
     */
    private byte diaActual = 0;

    public Semana() {
        dias = new Dia[6];
        //Inicializamos los días de la semana laborales de la semana
        for (int i = 0, j = 2; i < 6; i++, j++) {
            dias[i] = new Dia(j);
        }
    }

    public Semana(int n) {
        dias = new Dia[n];
    }

    public Dia[] getDias() {
        return dias;
    }

    public void setDias(Dia[] dias) {
        this.dias = dias;
    }

    /**
     * Obtenemos el día n
     */
    public Dia getDia(int n) {
        return dias[n];
    }

    /**
     * Obtenemos el día n
     */
    public Dia getDia(DiaLaboral dl) {
        switch (dl) {
            case MARTES:
                return dias[0];
            case MIERCOLES:
                return dias[1];
            case JUEVES:
                return dias[2];
            case VIERNES:
                return dias[3];
            case SABADO:
                return dias[4];
            case DOMINGO:
                return dias[5];
            default:
                return null;
        }
    }

    public byte getDiaActual() {
        return diaActual;
    }

    public String getNombreDiaActual() {
        return dias[diaActual].getDia().name();
    }

    public void setDia(int n, float ganancia) {
        this.dias[n].setGanancia(ganancia);
    }

    /**
     *
     * @param ganancia
     */
    public void setDia(float ganancia) {
        this.dias[diaActual++].setGanancia(ganancia);
    }

    public String showDias() {
        String s = "\n";
        for (Dia d : dias) {
            s += d + ", ";
        }
        return s + "\n";
    }

    /**
     * Obtenemos la media de todos los días excepto el domingo
     *
     * @return
     */
    public float getMediaSemanaMenosDomingo() {
        float totalGanancias = 0, totalDias = 0;
        for (Dia d : dias) {
            //Si es domingo lo saltamos
            if (d.getDia().equals(DiaLaboral.DOMINGO)) {
                continue;
            }
            //Si ese día ha habido ganancias 
            if (d.getGanancia() > 0) {
                totalDias++;
            }
            //de lo contrario sumaremos el total de sus ganancias
            totalGanancias += d.getGanancia();

        }
        return totalGanancias / totalDias;
    }

    /**
     * Obtenemos el día de mayores ventas/beneficios
     *
     * @return Dia
     */
    public Dia getMaxVentas() {
        Dia diaMax = null;
        for (Dia d : dias) {
            if (diaMax == null) {
                diaMax = d;
            }
            //Si la ganancia del día actual es mayor que la del max
            if (d.getGanancia() > diaMax.getGanancia()) {
                diaMax = d;
            }
        }
        return diaMax;
    }

    /**
     * Obetenemos el día de menos beneficios/ventas
     *
     * @return Dia
     */
    public Dia getMinVentas() {
        Dia diaMin = null;
        for (Dia d : dias) {
            if (diaMin == null) {
                diaMin = d;
            }
            //Si la ganancia del día actual es mayor que la del max
            if (d.getGanancia() < diaMin.getGanancia()) {
                diaMin = d;
            }
        }
        return diaMin;
    }

    @Override
    public String toString() {
        return "Semana{" + showDias() + "}";
    }
}
