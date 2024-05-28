import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class Listas {

    List<Empleado> empleados;

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

    public List<Empleado> editar( String cedula, String nuevoNombre,
                                 Fecha nuevaFechaDeIngreso, Double nuevoSueldoMensual) {
        for (Empleado e : empleados) {
            if (e.getCedula().equals(cedula)) {
                e.setNombre(nuevoNombre);
                e.setFechaDeIngreso(nuevaFechaDeIngreso);
                e.setSueldoMensual(nuevoSueldoMensual);
            }
        }
        return empleados;
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
