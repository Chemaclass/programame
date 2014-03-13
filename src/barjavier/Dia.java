package barjavier;

/**
 *
 * Días Laborales -> Martes..Domingo
 *
 * @author chemaclass
 */
class Dia {

    private DiaLaboral dia;
    private float ganancia;

    public Dia(int n) {
        this.dia = makeDia(n);
    }

    public Dia(DiaLaboral dl) {
        this.dia = dl;
    }

    /**
     * Obtenemos el día laboral correspondiente [0..5]
     */
    private DiaLaboral makeDia(int n) {
        switch (n) {
            case 2:
                return DiaLaboral.MARTES;
            case 3:
                return DiaLaboral.MIERCOLES;
            case 4:
                return DiaLaboral.JUEVES;
            case 5:
                return DiaLaboral.VIERNES;
            case 6:
                return DiaLaboral.SABADO;
            case 7:
                return DiaLaboral.DOMINGO;
            default:
                return null;
        }

    }

    public void setGanancia(float ganancia) {
        this.ganancia = ganancia;
    }

    public float getGanancia() {
        return ganancia;
    }

    public DiaLaboral getDia() {
        return dia;
    }

    public void setDia(DiaLaboral dia) {
        this.dia = dia;
    }

    @Override
    public String toString() {
        return "Dia{" + dia + ", ganancia: " + ganancia + "}";
    }

}
