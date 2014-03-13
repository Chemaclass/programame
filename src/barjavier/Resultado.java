package barjavier;

/**
 *
 * @author chemaclass
 */
public class Resultado {

    private Dia max;
    private Dia min;
    private String isDomingoWin;

    public Resultado() {
    }

    public Resultado(Dia max, Dia min, String domin) {
        this.max = max;
        this.min = min;
        this.isDomingoWin = domin;
    }

    public Dia getMax() {
        return max;
    }

    public void setMax(Dia max) {
        this.max = max;
    }

    public Dia getMin() {
        return min;
    }

    public void setMin(Dia min) {
        this.min = min;
    }

    public String getDomingoWin() {
        return isDomingoWin;
    }

    public void setDomingoWin(String domingoWin) {
        this.isDomingoWin = domingoWin;
    }

    @Override
    public String toString() {
        return max.getDia().name() + " " + min.getDia().name() + " " + isDomingoWin;
    }

}
