/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package conexionbd;

import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author jellz
 */
public class Controlador {
    
    
    Operaciones op = new Operaciones();
    
    
    
    
    
    public void menu(int num) throws SQLException{
        
        if(num ==1){
            try {
                InsertarEmpleado IE = new InsertarEmpleado(op);
            } catch (SQLException ex) {
                Logger.getLogger(Controlador.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        else if(num ==2){
            EliminarEmpleado EE = new EliminarEmpleado(op);
        }
        else if(num==3){
            BuscarEmpleado BE = new BuscarEmpleado(op);
            
        }
        else if (num==4){
            ModificarEmpleado ME = new ModificarEmpleado(op);
            
        }
        else if(num==5){
            ListarEmpleados LE = new ListarEmpleados(op);
        }
        
    }
    
    
    
    
    
    
    public void obtenerConexion(){
        
        op.conectar();
    }
    
    public void agregar(String cedula, String nombre, String apellido, String telefono, String salario, int idContrato){
        
        boolean respuesta = op.agregarEmpleado(cedula,nombre,apellido,telefono,salario,idContrato);
        
        if(respuesta){
            
            System.out.println("agregado satisfactoriamente");
        }
        else{
            System.out.println("No se anadio existosamente");
        }
        
    }
    
    
    
}
