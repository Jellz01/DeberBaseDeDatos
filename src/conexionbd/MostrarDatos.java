/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package conexionbd;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.util.ArrayList;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;

public class MostrarDatos extends JFrame implements ActionListener {
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

    MostrarDatos(Operaciones op, ArrayList<String> empleadoDetailsList ,String cedula) throws SQLException {
        this.op = op;
        this.empleadoDetailsList = empleadoDetailsList;
        ArrayList<String> listaContratos = op.obtenerContratos();
        
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
        txtNombre.setEditable(false);
        this.add(txtNombre);

        JLabel labApellido = new JLabel("Apellido:");
        labApellido.setBounds(10, 65, 90, 25);
        this.add(labApellido);
        txtApellido = new JTextField();
        txtApellido.setBounds(100, 65, 150, 25);
        txtApellido.setText(apellido);
        txtApellido.setEditable(false);
        this.add(txtApellido);

        JLabel labDireccion = new JLabel("Telefono:");
        labDireccion.setBounds(10, 95, 90, 25);
        this.add(labDireccion);
        txtDireccion = new JTextField();
        txtDireccion.setBounds(100, 95, 200, 25);
        txtDireccion.setText(telefono);
        txtDireccion.setEditable(false);
        this.add(txtDireccion);

        JLabel labSalario = new JLabel("Salario:");
        labSalario.setBounds(10, 125, 90, 25);
        this.add(labSalario);
        txtSalario = new JTextField();
        txtSalario.setBounds(100, 125, 200, 25);
        txtSalario.setText(salario);
        txtSalario.setEditable(false);
        this.add(txtSalario);

        JLabel labContratos = new JLabel("Contratos:");
        labContratos.setBounds(10, 155, 90, 25);
        
        this.add(labContratos);
           
        contratos = new JComboBox<>();
        contratos.setSize(225, 30);
        contratos.setLocation(100, 155);

        if (idTipoContrato.equals("1")) {
            String res = listaContratos.get(0);
            contratos.addItem(res);
            contratos.setSelectedItem(res);
            contratos.setEditable(false);
        }
        else if (idTipoContrato.equals("2")) {
            String res = listaContratos.get(1);
            contratos.addItem(res);
            contratos.setSelectedItem(res);
            contratos.setEditable(false);
        }
        else if (idTipoContrato.equals("3")) {
            String res = listaContratos.get(2);
            contratos.addItem(res);
            contratos.setSelectedItem(res);
            contratos.setEditable(false);
        }
        else if (idTipoContrato.equals("4")) {
            String res = listaContratos.get(3);
            contratos.addItem(res);
            contratos.setSelectedItem(res);
            contratos.setEditable(false);
        }
        else if (idTipoContrato.equals("5")) {
            String res = listaContratos.get(4);
            contratos.addItem(res);
            contratos.setSelectedItem(res);
            contratos.setEditable(false);
        }

        
            


        
        
        contratos.setSelectedItem(idTipoContrato);
        contratos.setEditable(false);

        

        this.add(contratos);

        botGrabar = new JButton("OK");
        botGrabar.setBounds(40, 210, 100, 25);
        botGrabar.addActionListener(this);
        this.add(botGrabar);

        

        super.setTitle("Empleados");
        super.setSize(400, 250);
        super.setLocationRelativeTo(null);
        super.setResizable(false);
        super.setVisible(true);
    }

 
    
@Override
public void actionPerformed(ActionEvent e) {
    if (e.getSource() == botGrabar) {
        this.setVisible(false);
    } else if (e.getSource() == botCancelar) {
        // Handle cancel action if needed
    }
}
}
