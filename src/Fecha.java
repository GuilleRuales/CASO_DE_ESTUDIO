import java.util.Calendar;
import java.util.GregorianCalendar;

public class Fecha {

    private int dia;
    private int mes;
    private int anio;

    public Fecha() {
    }

    public Fecha(int dia, int mes, int anio) {
        this.dia = dia;
        this.mes = mes;
        this.anio = anio;
    }

    public int getDia() {
        return dia;
    }

    public void setDia(int dia) {
        this.dia = dia;
    }

    public int getMes() {
        return mes;
    }

    public void setMes(int mes) {
        this.mes = mes;
    }

    public int getAnio() {
        return anio;
    }

    public void setAnio(int anio) {
        this.anio = anio;
    }

    public void inicializarHoy() {
        GregorianCalendar gc = new GregorianCalendar();

        this.dia = gc.get(Calendar.DAY_OF_MONTH);
        this.mes = gc.get(Calendar.MONTH) + 1;
        this.anio = gc.get(Calendar.YEAR);
    }

    public int calcularAntiguedad() {
        Fecha fechaActual = new Fecha();
        fechaActual.inicializarHoy();

        int anios = fechaActual.getAnio() - this.anio;
        int meses = fechaActual.getMes() - this.mes;
        int dias = fechaActual.getDia() - this.dia;

        if (meses < 0 || (meses == 0 && dias < 0)) {
            anios--;
        }

        return anios;
    }

    @Override
    public String toString() {
        return  "\nDia: "+dia+
                "\n  Mes: "+mes+
                "\n  Anio: "+anio;
    }
}
