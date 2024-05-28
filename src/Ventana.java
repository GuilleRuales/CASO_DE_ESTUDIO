import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Collections;
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
    private JButton button1;
    private JList list1;
    private JList list2;

    private Listas empleados = new Listas();
    DefaultListModel dlm = new DefaultListModel();

    public Ventana() {

        agregarButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    empleados.agregar(textCedula.getText(), textNombre.getText(), new Fecha(Integer.parseInt(textDia.getText()), Integer.parseInt(textMes.getText()),
                                    Integer.parseInt(textAnio.getText())), Double.parseDouble(textSueldo.getText()));
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

                if(list1.getSelectedIndex()!=-1){
                    int indice = list1.getSelectedIndex();
                    Empleado em = empleados.getEmpleados().get(indice);
                    Fecha fe = empleados.getEmpleados().get(indice).getFechaDeIngreso();
                    textNombre.setText(em.getNombre());
                    textSueldo.setText(String.valueOf(em.getSueldoMensual()));
                    textDia.setText(String.valueOf(fe.getDia()));
                    textMes.setText(String.valueOf(fe.getMes()));
                    textAnio.setText(String.valueOf(fe.getAnio()));

                    empleados.editar(textCedula.getText(), textNombre.getText(), new Fecha(Integer.parseInt(textDia.getText()),
                            Integer.parseInt(textMes.getText()), Integer.parseInt(textAnio.getText())),
                            Double.parseDouble(textSueldo.getText()));

                    List<Empleado> lista = empleados.listarEmpleados();
                    llenarJlist(lista, list1, dlm);

                }

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
