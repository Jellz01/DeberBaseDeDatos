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
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;

/**
 *
 * @author jellz
 */
public class EditarDatos extends JFrame implements ActionListener {
    private JButton botGrabar;
    private JButton botCancelar;

    JTextField txtCedula;
    JTextField txtNombre;
    JTextField txtApellido;
    JTextField txtDireccion;
    JTextField txtSalario;

    private Operaciones op;
    private JComboBox<String> contratos;
    private ArrayList<String> empleadoDetailsList;

    EditarDatos(Operaciones op, ArrayList<String> empleadoDetailsList ,String cedula) throws SQLException {
        this.op = op;
        this.empleadoDetailsList = empleadoDetailsList;
        
        op.conectar();
        
        
        String empleadoDetailsString = empleadoDetailsList.get(0);

        String[] empleadoDetails = empleadoDetailsString.split(", ");

        String cedulaa = empleadoDetails[0].split(": ")[1];
        String nombre = empleadoDetails[1].split(": ")[1];
        String apellido = empleadoDetails[2].split(": ")[1];
        String telefono = empleadoDetails[3].split(": ")[1];
        String salario = empleadoDetails[4].split(": ")[1];
        String idTipoContrato = empleadoDetails[5].split(": ")[1];

        super.setLayout(null);

        JLabel labCedula = new JLabel("Cédula:");
        labCedula.setBounds(10, 5, 90, 25);
        this.add(labCedula);
        txtCedula = new JTextField();
        txtCedula.setBounds(100, 5, 80, 25);
        txtCedula.setText(cedulaa);
        txtCedula.setEditable(false);
        this.add(txtCedula);

        JLabel labNombre = new JLabel("Nombre:");
        labNombre.setBounds(10, 35, 90, 25);
        this.add(labNombre);
        txtNombre = new JTextField();
        txtNombre.setBounds(100, 35, 150, 25);
        txtNombre.setText(nombre);
        txtNombre.setEditable(true);
        this.add(txtNombre);

        JLabel labApellido = new JLabel("Apellido:");
        labApellido.setBounds(10, 65, 90, 25);
        this.add(labApellido);
        txtApellido = new JTextField();
        txtApellido.setBounds(100, 65, 150, 25);
        txtApellido.setText(apellido);
        txtApellido.setEditable(true);
        this.add(txtApellido);

        JLabel labDireccion = new JLabel("Telefono:");
        labDireccion.setBounds(10, 95, 90, 25);
        this.add(labDireccion);
        txtDireccion = new JTextField();
        txtDireccion.setBounds(100, 95, 200, 25);
        txtDireccion.setText(telefono);
        txtDireccion.setEditable(true);
        this.add(txtDireccion);

        JLabel labSalario = new JLabel("Salario:");
        labSalario.setBounds(10, 125, 90, 25);
        this.add(labSalario);
        txtSalario = new JTextField();
        txtSalario.setBounds(100, 125, 200, 25);
        txtSalario.setText(salario);
        txtSalario.setEditable(true);
        this.add(txtSalario);

        JLabel labContratos = new JLabel("Contratos:");
        labContratos.setBounds(10, 155, 90, 25);
        
        this.add(labContratos);

        contratos = new JComboBox<>();
        contratos.setSize(225, 30);
        contratos.setLocation(100, 155);
        ArrayList<String> contratoList = op.obtenerContratos();
        for (String contrato : contratoList) {
            contratos.addItem(contrato);
}

        
        
        contratos.setSelectedItem(idTipoContrato);
        contratos.setEditable(true);

        

        this.add(contratos);

        botGrabar = new JButton("Modificar");
        botGrabar.setBounds(40, 210, 100, 25);
        botGrabar.addActionListener(this);
        this.add(botGrabar);

        botCancelar = new JButton("Cancelar");
        botCancelar.setBounds(150, 210, 100, 25);
        botCancelar.addActionListener(this);
        this.add(botCancelar);

        super.setTitle("Empleados");
        super.setSize(400, 250);
        super.setLocationRelativeTo(null);
        super.setResizable(false);
        super.setVisible(true);
    }

 
    
@Override
public void actionPerformed(ActionEvent e) {
    if (e.getSource() == botGrabar) {
        
         String cedula = txtCedula.getText();
            String nombre = txtNombre.getText();
            String apellido = txtApellido.getText();
            String telefono = txtDireccion.getText();
            String salario = txtSalario.getText();
            int idContratos = contratos.getSelectedIndex();
            int idContrato = idContratos+1;
            
            System.out.println("contrato: "+idContrato);

            
            
            op.modificarEmpleado(cedula, nombre, apellido, telefono, salario,idContrato);
            this.setVisible(false);
         
      
    } else if (e.getSource() == botCancelar) {
        // Handle cancel action if needed
    }
}
}
