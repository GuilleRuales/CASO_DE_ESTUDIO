import java.util.*;

public class Listas {

    List<Empleado> empleados;
    Fecha fecha = new Fecha();

    public Listas() {
        empleados = new ArrayList<>();
    }

    public Empleado buscarEmpleado(String cedula) {
        for (Empleado e : empleados)
            if (e.getCedula().equals(cedula))
                return e;
        return null;
    }

    public void agregar(String cedula, String nombre, Fecha fecha, Double sueldoMensual) throws Exception {
        if (buscarEmpleado(cedula) == null)
            empleados.add(new Empleado(cedula, nombre, fecha, sueldoMensual));
        else
            throw new Exception("La cedula ya esta registrada");
    }

    public void editar(String cedula, String nuevoNombre, Fecha nuevaFechaDeIngreso, Double nuevoSueldoMensual) throws Exception{

        Empleado empleado = buscarEmpleado(cedula);
        if (empleado != null) {
            empleado.setNombre(nuevoNombre);
            empleado.setFechaDeIngreso(nuevaFechaDeIngreso);
            empleado.setSueldoMensual(nuevoSueldoMensual);

        } else {
            throw new Exception("No se encontró ningún empleado con la cédula especificada: " + cedula);
        }
    }

    public List<Empleado> listarEmpleados(){
        List<Empleado> lista = new LinkedList<>();
        for (Empleado e: empleados)
            lista.add(e);
        return lista;
    }

    public String generarInforme() {
        StringBuilder informe = new StringBuilder();
        informe.append("INFORME DE EMPLEADOS\n\n");

        for (Empleado e : empleados) {
            String nombre = e.getNombre();
            double sueldoMensual = e.getSueldoMensual();
            double aporteAlSeguro = e.calcularAporteAlSeguro();
            double impuestoRenta = e.calcularImpuestoRenta();
            double fondosDeReserva = e.calcularFondosDeReserva();
            int antiguedad = e.getFechaDeIngreso().calcularAntiguedad(); // Llamada al método calcularAntiguedad de Fecha

            informe.append("\tNombre: ").append(nombre).append(" ");
            informe.append("Sueldo: ").append(String.format("%.2f", sueldoMensual)).append(" ");
            informe.append("Aporte al seguro: ").append(String.format("%.2f", aporteAlSeguro)).append(" ");
            informe.append("Impuesto a la renta: ").append(String.format("%.2f", impuestoRenta)).append(" ");
            informe.append("Fondos de reserva: ").append(String.format("%.2f", fondosDeReserva)).append(" ");
            informe.append("Antigüedad: ").append(antiguedad).append(" años\n");
        }

        return informe.toString();
    }

    public List<Empleado> getEmpleados() {
        return empleados;
    }

    public void setEmpleados(List<Empleado> empleados) {
        this.empleados = empleados;
    }
}
