import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

public class Ventana {
    private JPanel ventana;
    private JTextField textCedula;
    private JTextField textNombre;
    private JTextField textSueldo;
    private JTextField textAnio;
    private JTextField textMes;
    private JTextField textDia;
    private JButton agregarButton;
    private JButton modificarButton;
    private JButton calcularFReservaButton;
    private JList list1;
    private JButton reporteButton;
    private JTextArea textArea1;

    private Listas empleados = new Listas();
    DefaultListModel dlm = new DefaultListModel();
    DefaultListModel dlm2 = new DefaultListModel<>();

    public Ventana() {

        agregarButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    if (textCedula.getText().isEmpty() || textNombre.getText().isEmpty() ||
                            textDia.getText().isEmpty() || textMes.getText().isEmpty() ||
                            textAnio.getText().isEmpty() || textSueldo.getText().isEmpty()) {

                        JOptionPane.showMessageDialog(null, "Todos los campos deben ser llenados", "Error", JOptionPane.ERROR_MESSAGE);
                        return;
                    }else{
                        empleados.agregar(textCedula.getText(), textNombre.getText(), new Fecha(Integer.parseInt(textDia.getText()), Integer.parseInt(textMes.getText()),
                                Integer.parseInt(textAnio.getText())), Double.parseDouble(textSueldo.getText()));
                    }
                } catch (Exception ex) {
                    throw new RuntimeException(ex);
                }
                List<Empleado> lista = empleados.listarEmpleados();
                llenarJlist(lista, list1, dlm);
                setearCampos();
            }
        });

        modificarButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (list1.getSelectedIndex() != -1) {
                    Empleado empleadoSeleccionado = (Empleado) list1.getSelectedValue();
                    String cedula = empleadoSeleccionado.getCedula();

                    String nuevoNombre = textNombre.getText();
                    Fecha nuevaFecha = new Fecha(
                            Integer.parseInt(textDia.getText()),
                            Integer.parseInt(textMes.getText()),
                            Integer.parseInt(textAnio.getText())
                    );
                    double nuevoSueldo = Double.parseDouble(textSueldo.getText());

                    try {
                        empleados.editar(cedula, nuevoNombre, nuevaFecha, nuevoSueldo);
                        List<Empleado> lista = empleados.listarEmpleados();
                        llenarJlist(lista, list1, dlm);
                    } catch (Exception ex) {
                        JOptionPane.showMessageDialog(null, ex.getMessage());
                    }
                }
            }
        });

        list1.addListSelectionListener(new ListSelectionListener() {
            @Override
            public void valueChanged(ListSelectionEvent e) {
                if (!e.getValueIsAdjusting() && list1.getSelectedIndex() != -1) {
                    Empleado empleadoSeleccionado = (Empleado) list1.getSelectedValue();
                    textNombre.setText(empleadoSeleccionado.getNombre());
                    textDia.setText(String.valueOf(empleadoSeleccionado.getFechaDeIngreso().getDia()));
                    textMes.setText(String.valueOf(empleadoSeleccionado.getFechaDeIngreso().getMes()));
                    textAnio.setText(String.valueOf(empleadoSeleccionado.getFechaDeIngreso().getAnio()));
                    textSueldo.setText(String.valueOf(empleadoSeleccionado.getSueldoMensual()));
                }
            }
        });

        calcularFReservaButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (list1.getSelectedIndex() != -1) {
                    Empleado empleadoSeleccionado = (Empleado) list1.getSelectedValue();
                    int antiguedad = empleadoSeleccionado.obtenerAntiguedad(); // Llamamos a obtenerAntiguedad() en lugar de calcularAntiguedad()
                    double fondosDeReserva = empleadoSeleccionado.calcularFondosDeReserva();

                    JOptionPane.showMessageDialog(null,"Antigüedad: " + antiguedad + " años\n" +
                            "Fondos de Reserva: $" + fondosDeReserva);
                } else {
                    JOptionPane.showMessageDialog(null,"Seleccione un empleado de la lista");
                }
            }
        });

        reporteButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String informe = empleados.generarInforme();
                JTextArea textArea = new JTextArea(informe);
                textArea.setEditable(false);
                textArea1.setText(informe);
            }
        });
    }

    public void setearCampos(){
        textCedula.setText("");
        textNombre.setText("");
        textSueldo.setText("");
        textDia.setText("");
        textMes.setText("");
        textAnio.setText("");
    }

    public void llenarJlist(List<Empleado> lista, JList listaMostrar, DefaultListModel dl){
        dl.removeAllElements();
        for (Empleado e: lista)
            dl.addElement(e);
        listaMostrar.setModel(dl);
    }


    public static void main(String[] args) {
        JFrame frame = new JFrame("Ventana");
        frame.setContentPane(new Ventana().ventana);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);
    }

}