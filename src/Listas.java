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
        informe.append(String.format("%-20s %-10s %-15s %-15s %-15s %-15s\n",
                "NOMBRE", "SUELDO", "APORTE AL SEGURO", "IMPUESTO A LA RENTA", "FONDOS DE RESERVA", "SUELDO A RECIBIR"));

        for (Empleado e : empleados) {
            String nombre = e.getNombre();
            double sueldoMensual = e.getSueldoMensual();
            double aporteAlSeguro = e.calcularAporteAlSeguro();
            double impuestoRenta = e.calcularImpuestoRenta();
            double fondosDeReserva = e.calcularFondosDeReserva();
            double sueldoARecibir = e.calcularSueldoARecibir();

            informe.append(String.format("%-20s %-10.2f %-15.2f %-15.2f %-15.2f %-15.2f\n",
                    nombre, sueldoMensual, aporteAlSeguro, impuestoRenta, fondosDeReserva, sueldoARecibir));
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
