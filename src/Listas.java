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

    public List<Empleado> getEmpleados() {
        return empleados;
    }

    public void setEmpleados(List<Empleado> empleados) {
        this.empleados = empleados;
    }
}
