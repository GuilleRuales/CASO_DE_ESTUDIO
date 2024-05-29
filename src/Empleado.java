public class Empleado {

    private String cedula;
    private String nombre;
    private Fecha fechaDeIngreso;
    private Double sueldoMensual;
    private Double aporteAlSeguro;
    private Double impuestoRenta;

    public Empleado() {
    }

    public Empleado(String cedula, String nombre, Fecha fechaDeIngreso, Double sueldoMensual, Double aporteAlSeguro, Double impuestoRenta) {
        this.cedula = cedula;
        this.nombre = nombre;
        this.fechaDeIngreso = fechaDeIngreso;
        this.sueldoMensual = sueldoMensual;
        this.aporteAlSeguro = aporteAlSeguro;
        this.impuestoRenta = impuestoRenta;
    }

    public Empleado(String cedula, String nombre, Fecha fechaDeIngreso, Double sueldoMensual) {
        this.cedula = cedula;
        this.nombre = nombre;
        this.fechaDeIngreso = fechaDeIngreso;
        this.sueldoMensual = sueldoMensual;
    }

    @Override
    public String toString() {
        return  "Cedula: "+cedula+
                "\n  Nombre: "+nombre+
                "\n  Sueldo Mensual: "+sueldoMensual+
                "\n  Fecha De Ingreso: "+fechaDeIngreso;
    }

    public String getCedula() {
        return cedula;
    }

    public void setCedula(String cedula) {
        this.cedula = cedula;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public Fecha getFechaDeIngreso() {
        return fechaDeIngreso;
    }

    public void setFechaDeIngreso(Fecha fechaDeIngreso) {
        this.fechaDeIngreso = fechaDeIngreso;
    }

    public Double getSueldoMensual() {
        return sueldoMensual;
    }

    public void setSueldoMensual(Double sueldoMensual) {
        this.sueldoMensual = sueldoMensual;
    }

    public Double getAporteAlSeguro() {
        return aporteAlSeguro;
    }

    public void setAporteAlSeguro(Double aporteAlSeguro) {
        this.aporteAlSeguro = aporteAlSeguro;
    }

    public Double getImpuestoRenta() {
        return impuestoRenta;
    }

    public void setImpuestoRenta(Double impuestoRenta) {
        this.impuestoRenta = impuestoRenta;
    }

    public int calcularAntiguedad() {
        Fecha fechaActual = new Fecha();
        fechaActual.inicializarHoy();

        int anios = fechaActual.getAnio() - this.fechaDeIngreso.getAnio();
        int meses = fechaActual.getMes() - this.fechaDeIngreso.getMes();
        int dias = fechaActual.getDia() - this.fechaDeIngreso.getDia();

        if (meses < 0 || (meses == 0 && dias < 0)) {
            anios--;
        }

        return anios;
    }

    public double calcularFondosDeReserva() {
        int antiguedad = calcularAntiguedad();
        if (antiguedad < 1) {
            return 0.0;
        }
        return this.sueldoMensual * antiguedad;
    }

    public double calcularAporteAlSeguro() {
        return this.sueldoMensual * 0.0945;
    }

    public double calcularImpuestoRenta() {
        double sueldoAnual = this.sueldoMensual * 12;
        double impuesto = 0.0;

        if (sueldoAnual <= 5000) {
            impuesto = 0.0;
        } else if (sueldoAnual <= 10000) {
            impuesto = (sueldoAnual - 5000) * 0.10;
        } else if (sueldoAnual <= 18000) {
            impuesto = (5000 * 0.10) + (sueldoAnual - 10000) * 0.20;
        } else {
            impuesto = (5000 * 0.10) + (8000 * 0.20) + (sueldoAnual - 18000) * 0.30;
        }

        return impuesto / 12;  // Importe mensual
    }

    public double calcularSueldoARecibir() {
        double aporteAlSeguro = calcularAporteAlSeguro();
        double impuestoRenta = calcularImpuestoRenta();
        double fondosDeReserva = calcularFondosDeReserva() / 12;  // Fondos de reserva mensual
        return this.sueldoMensual - aporteAlSeguro - impuestoRenta + fondosDeReserva;
    }

}
