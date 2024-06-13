package conexionbd;

import java.awt.List;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.sql.ResultSet;
import javax.swing.JOptionPane;
import java.sql.SQLIntegrityConstraintViolationException;


public class Operaciones {

    private Connection conn;

    public void conectar() {
        try {
            conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/Universidad", "root", "Jjwm20020");
            System.out.println("Conexión exitosa");
        } catch (SQLException ex) {
            Logger.getLogger(Operaciones.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public boolean agregarContrato(String idContrato, String descripcion) {
        boolean state = false;
        PreparedStatement sentencia = null;

        try {
            sentencia = conn.prepareStatement("INSERT INTO TipoContrato VALUES (?,?)");
            sentencia.setString(1, idContrato);
            sentencia.setString(2, descripcion);

            int rowsAffected = sentencia.executeUpdate();

            if (rowsAffected > 0) {
                System.out.println("Contrato agregado exitosamente");
                state = true;
            } else {
                System.err.println("Error: No se pudo agregar el contrato");
            }
        } catch (SQLException ex) {
            Logger.getLogger(Operaciones.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            closeResources(sentencia);
        }

        return state;
    }

   public boolean agregarEmpleado(String cedula, String nombre, String apellido, String telefono, String salario, int idContrato) {
    boolean state = false;

    try {
        PreparedStatement sentencia = conn.prepareStatement("INSERT INTO Empleado VALUES (?,?,?,?,?,?)");

        sentencia.setString(1, cedula);
        sentencia.setString(2, nombre);
        sentencia.setString(3, apellido);
        sentencia.setString(4, telefono);
        sentencia.setString(5, salario);
        sentencia.setInt(6, idContrato);

        int rowsAffected = sentencia.executeUpdate();

        if (rowsAffected > 0) {
            JOptionPane.showMessageDialog(null,"Se ingreso un Empleado correctamente","INFO",JOptionPane.INFORMATION_MESSAGE);
            state = true;
        } else {
            JOptionPane.showMessageDialog(null,"NO Se ingreso un Empleado ERROR","ERROR",JOptionPane.ERROR);
        }

        sentencia.close();
    } catch (SQLIntegrityConstraintViolationException duplicateKeyException) {
        JOptionPane.showMessageDialog(null, "ERROR: Se ha intentado insertar un valor duplicado para la clave primaria.", "ERROR", JOptionPane.ERROR_MESSAGE);
        // Handle the exception as needed, log, or perform additional actions
        duplicateKeyException.printStackTrace();
    } catch (SQLException ex) {
        Logger.getLogger(Operaciones.class.getName()).log(Level.SEVERE, null, ex);
    }

    return state;
}




    private void closeResources(AutoCloseable... resources) {
        for (AutoCloseable resource : resources) {
            try {
                if (resource != null) {
                    resource.close();
                }
            } catch (Exception e) {
                // Handle or log the exception
            }
        }
    }
    
    /**
     *
     * @return
     */
    public ArrayList<String> obtenerContratos() throws SQLException {
    ArrayList<String> contratoList = new ArrayList<>();

    try {
        // Assuming your contrato table has a column named 'Descripcion'
        String query = "SELECT Descripcion FROM TipoContrato";
        PreparedStatement statement = conn.prepareStatement(query);
        ResultSet resultSet = statement.executeQuery();

        while (resultSet.next()) {
            String descripcion = resultSet.getString("Descripcion");
            contratoList.add(descripcion);
        }

        resultSet.close();
        statement.close();
    } catch (SQLException ex) {
        ex.printStackTrace();
        throw ex; // Rethrow the exception to handle it at the calling point
    }

    return contratoList;
}
    
    
    public boolean eliminarEmpleado(String cedula) {
    boolean state = false;

    try {
        String query = "DELETE FROM Empleado WHERE CEDULA = ?";
        try (PreparedStatement sentencia = conn.prepareStatement(query)) {
            sentencia.setString(1, cedula);

            int rowsAffected = sentencia.executeUpdate();

            if (rowsAffected > 0) {
                JOptionPane.showMessageDialog(null,"Empleado eliminado correctamente","INFO",JOptionPane.INFORMATION_MESSAGE);
                state = true;
            } else {
                JOptionPane.showMessageDialog(null,"Empleado no se pudo eliminar REVISAR LA CEDULA","ERROR",JOptionPane.ERROR_MESSAGE);
            }
        }
    } catch (SQLException ex) {
        ex.printStackTrace();
        // Handle the exception as needed
    }

    return state;
}
    
    public ArrayList<String> obtenerEmpleadoPorCedula(String cedula) {
    ArrayList<String> empleadoList = new ArrayList<>();

    try {
        String query = "SELECT * FROM Empleado WHERE Cedula = ?";
try (PreparedStatement statement = conn.prepareStatement(query)) {
    statement.setString(1, cedula);

    try (ResultSet resultSet = statement.executeQuery()) {
        while (resultSet.next()) {
            // Assuming your Empleado table has columns like 'Cedula', 'Nombre', 'Apellido', etc.
            String cedulaResult = resultSet.getString("Cedula");
            String nombreResult = resultSet.getString("Nombre");
            String apellidoResult = resultSet.getString("Apellido"); 
            String telefono = resultSet.getString("Telefono");
            String salario = resultSet.getString("Salario");
            int idTipoContrato = resultSet.getInt("idTipoContrato");

            // Convert idTipoContrato to string
            String idTipoContratoString = String.valueOf(idTipoContrato);

            // Add more columns as needed

            // Create a string representation of the employee details
            String empleadoDetails = "Cedula: " + cedulaResult + ", Nombre: " + nombreResult +
                ", Apellido: " + apellidoResult + ", Telefono: " + telefono +
                ", Salario: " + salario + ", idTipoContrato: " + idTipoContratoString;

            empleadoList.add(empleadoDetails);
        }
        
    
            }
        }
    } catch (SQLException ex) {
        ex.printStackTrace();
        // Handle the exception as needed
    }

    if(empleadoList.isEmpty()){
        JOptionPane.showMessageDialog(null,"No existe esa cedula en nuestra base de datos","ERROR",JOptionPane.ERROR_MESSAGE);
    }
    
    return empleadoList;
}
    
    public ArrayList<String> obtenerTodosLosEmpleados() {
    ArrayList<String> empleadoList = new ArrayList<>();

    try {
        String query = "SELECT * FROM Empleado";
        try (PreparedStatement statement = conn.prepareStatement(query);
             ResultSet resultSet = statement.executeQuery()) {

            while (resultSet.next()) {
                // Assuming your Empleado table has columns like 'Cedula', 'Nombre', 'Apellido', etc.
                String cedulaResult = resultSet.getString("Cedula");
                String nombreResult = resultSet.getString("Nombre");
                String apellidoResult = resultSet.getString("Apellido"); 
                String telefono = resultSet.getString("Telefono");
                String salario = resultSet.getString("Salario");
                int idTipoContrato = resultSet.getInt("idTipoContrato");

                // Convert idTipoContrato to string
                String idTipoContratoString = String.valueOf(idTipoContrato);

                // Create a string representation of the employee details
                String empleadoDetails = "Cedula: " + cedulaResult + ", Nombre: " + nombreResult +
                    ", Apellido: " + apellidoResult + ", Telefono: " + telefono +
                    ", Salario: " + salario + ", idTipoContrato: " + idTipoContratoString;

                empleadoList.add(empleadoDetails);
            }
        }
    } catch (SQLException ex) {
        ex.printStackTrace();
        // Handle the exception as needed
    }

    return empleadoList;
    }
    
    
   public ArrayList<String> obtenerDescripcionesTipoContrato() {
    ArrayList<String> listaContratos = new ArrayList<>();

    try {
        String query = "SELECT Descripcion FROM TipoContrato";
        try (PreparedStatement statement = conn.prepareStatement(query);
             ResultSet resultSet = statement.executeQuery()) {

            while (resultSet.next()) {
                // Retrieve the "Descripcion" column from the result set
                String contratoDescripcion = resultSet.getString("Descripcion");

                // Add the description to the list
                listaContratos.add(contratoDescripcion);
                System.out.println(listaContratos);
            }
        }
    } catch (SQLException ex) {
        ex.printStackTrace();
        // Handle the exception as needed
    }

    return listaContratos;
}



   
   public boolean modificarEmpleado(String cedula, String nombre, String apellido, String telefono, String salario, int idContrato) {
    
    boolean state = false; 
    

  
    try {
            PreparedStatement sentencia = conn.prepareStatement("UPDATE Empleado SET Nombre = ?, Apellido = ?, Telefono = ?, Salario = ?, idTipoContrato = ? WHERE Cedula = ?");
            
            


            
            sentencia.setString(1, nombre);
            sentencia.setString(2, apellido);
            sentencia.setString(3, telefono);
            sentencia.setString(4, salario);
            sentencia.setInt(5, idContrato);
            sentencia.setString(6, cedula);

            int rowsAffected = sentencia.executeUpdate();

            if (rowsAffected > 0) {
                JOptionPane.showMessageDialog(null,"Se ha MODIFICADO el empleado exitosamente","INFO",JOptionPane.INFORMATION_MESSAGE);
                state = true; 
            } else {
                JOptionPane.showMessageDialog(null,"ERROR: NO Se ha MODIFICADO el empleado ","ERROR",JOptionPane.ERROR_MESSAGE);
            }

            sentencia.close();
        } catch (SQLException ex) {
            Logger.getLogger(Operaciones.class.getName()).log(Level.SEVERE, null, ex);
        }

        return state;
    }    
}
   
   
   