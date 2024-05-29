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




}
