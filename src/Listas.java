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

    public void editar(String cedula, String nuevoNombre, Fecha nuevaFechaDeIngreso, Double nuevoSueldoMensual) {
        Empleado empleado = buscarEmpleado(cedula);
        if (empleado != null) {
            empleado.setNombre(nuevoNombre);
            empleado.setFechaDeIngreso(nuevaFechaDeIngreso);
            empleado.setSueldoMensual(nuevoSueldoMensual);
        } else {
            throw new IllegalArgumentException("No se encontró ningún empleado con la cédula especificada: " + cedula);
        }
    }

    public List<Empleado> listarEmpleados(){
        List<Empleado> lista = new LinkedList<>();
        for (Empleado e: empleados)
            lista.add(e);
        return lista;
    }

    public int obtenerAntiguedadEmpleado(String cedula) {
        Empleado empleado = buscarEmpleado(cedula);
        if (empleado != null) {
            return empleado.calcularAntiguedad();
        }
        throw new IllegalArgumentException("No se encontró ningún empleado con la cédula especificada: " + cedula);
    }

    public double obtenerFondosDeReservaEmpleado(String cedula) {
        Empleado empleado = buscarEmpleado(cedula);
        if (empleado != null) {
            return empleado.calcularFondosDeReserva();
        }
        throw new IllegalArgumentException("No se encontró ningún empleado con la cédula especificada: " + cedula);
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

            informe.append("\tNombre: ").append(nombre).append(" ");
            informe.append("Sueldo: ").append(sueldoMensual).append(" ");
            informe.append("Aporte al seguro: ").append(aporteAlSeguro).append(" ");
            informe.append("Impuesto a la renta: ").append(impuestoRenta).append(" ");
            informe.append("Fondos de reserva: ").append(fondosDeReserva).append("\n");
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