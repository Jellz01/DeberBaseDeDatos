/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package conexionbd;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;

/**
 *
 * @author jellz
 */
public class ModificarEmpleado extends JFrame implements ActionListener {

    private Operaciones op;
    private JTextField txtCedula;
    private JButton mostrar;
    private JButton cancelar;

    public ModificarEmpleado(Operaciones op) {
        super.setLayout(null);
        this.op = op;
        op.conectar();

        JLabel labCedula = new JLabel("Cedula:");
        labCedula.setSize(100, 25);
        labCedula.setLocation(60, 20);
        this.add(labCedula);
        txtCedula = new JTextField();
        txtCedula.setSize(100, 25);
        txtCedula.setLocation(120, 20);
        this.add(txtCedula);

        mostrar = new JButton("Mostrar Datos");
        mostrar.setSize(160, 25);
        mostrar.setLocation(90, 60);
        mostrar.addActionListener(this);
        this.add(mostrar);

        cancelar = new JButton("Cancelar");
        cancelar.setSize(100, 25);
        cancelar.setLocation(260, 60);
        cancelar.addActionListener(this);
        this.add(cancelar);

        super.setTitle("BUSCAR");
        super.setSize(400, 250);
        super.setLocationRelativeTo(null);
        super.setResizable(false);
        super.setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == mostrar) {
            String cedula = txtCedula.getText();

            try {
                // Retrieve employee data
                ArrayList<String> empleadoDetailsList = op.obtenerEmpleadoPorCedula(cedula);
                

                // Create and show MostrarDatos window with employee data
                if (!empleadoDetailsList.isEmpty()) {
                    EditarDatos ED = new EditarDatos(op, empleadoDetailsList,cedula);
                    this.setVisible(false);
                }
            } catch (SQLException ex) {
                Logger.getLogger(BuscarEmpleado.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
}
 
    

