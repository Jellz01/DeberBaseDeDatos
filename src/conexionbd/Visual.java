/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package conexionbd;

import java.awt.Color;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import static javax.swing.WindowConstants.EXIT_ON_CLOSE;

/**
 *
 * @author jellz
 */
public class Visual implements ActionListener{
    
    
    private JPanel panelTop;
    
    private JPanel pBotones;
    
    
    private JFrame frame;
   
    private JButton anadir;
    
    private JButton borrar;
    
    private JButton buscar;
    
    private JButton modificar;
    
    private JButton listar;
    
    private Controlador controlador;
     private Image img;
    
    
    
    
    
    
    
    
    
    
    
    public Visual(Controlador controlador){
        
        
        this.controlador = controlador;
        
      ImageIcon image = new ImageIcon("/home/jellz/NetBeansProjects/ConexionBD/src/conexionbd/imagen1jpg");
         img = image.getImage();
         
         JLabel label = new JLabel();
        label.setIcon(image);


        panelTop = new JPanel();
        panelTop.setBackground(Color.black);
        panelTop.setSize(1000,700);
        panelTop.add(label);
        

        frame = new JFrame();
        frame.setSize(1000, 700);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setResizable(false);
        frame.setLocationRelativeTo(null);
        frame.setTitle("DEBER BASE DE DATOS");
        
         frame.setVisible(true);
        

        // Add the panel to the frame
       
            
            
            
            anadir = new JButton("Anadir");
            anadir.setSize(100,20);
            anadir.addActionListener(this);
            
            borrar = new JButton("Eliminar");
            borrar.setSize(100,20);
            borrar.addActionListener(this);
            
            buscar = new JButton("Buscar");
            buscar.setSize(100,20);
            buscar.addActionListener(this);
            
            modificar = new JButton("Modificar");
            modificar.setSize(100,20);
            modificar.addActionListener(this);
            
            
            listar = new JButton("Listar");
            listar.setSize(100,20);
            listar.addActionListener(this);
            
            
            pBotones = new JPanel();
            pBotones.setSize(500,35);
            pBotones.setBackground(Color.BLACK);
            pBotones.setLocation(250,20);
            
            
            
            pBotones.add(anadir);
            pBotones.add(borrar);
            pBotones.add(modificar);
            pBotones.add(listar);
            pBotones.add(buscar);
            
            
            
            frame.add(pBotones);
             frame.add(panelTop);
           
            

    }
    
    
    
    
    
    
    
    
    

    @Override
    public void actionPerformed(ActionEvent e) {
        
        if(e.getSource() == anadir){
            
            try {
                controlador.menu(1);
            } catch (SQLException ex) {
                Logger.getLogger(Visual.class.getName()).log(Level.SEVERE, null, ex);
            }
            
        }
        else if(e.getSource() == borrar){
            
            try {
                controlador.menu(2);
            } catch (SQLException ex) {
                Logger.getLogger(Visual.class.getName()).log(Level.SEVERE, null, ex);
            }
            
        }
        else if(e.getSource() == buscar){
            
            try {
                controlador.menu(3);
            } catch (SQLException ex) {
                Logger.getLogger(Visual.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        else if(e.getSource() == modificar){
            
            try {
                controlador.menu(4);
            } catch (SQLException ex) {
                Logger.getLogger(Visual.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        else if(e.getSource() == listar){
            
            try {
                controlador.menu(5);
            } catch (SQLException ex) {
                Logger.getLogger(Visual.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        
        
    }
    
}
