package controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JDialog;
import javax.swing.JOptionPane;
import model.database.CRUD;
import model.database.Conexion;
import view.FrmSupervisor;
import view.PanelAgergarTrabajador;

public class ControllerSupervisor implements ActionListener {

    private FrmSupervisor vista;
    private Conexion conexion;

    public ControllerSupervisor() {
    }

    public ControllerSupervisor(FrmSupervisor vista, Conexion conexion) {
        this.vista = vista;
        this.conexion = conexion;
        vista.btnAgregarCajero.addActionListener(this);
        vista.btnTemaClaro.addActionListener(this);
        vista.btnTemaOscuro.addActionListener(this);
    }

//    public static void changeLaf(JFrame frame) {
//        try {
//             javax.swing.UIManager.setLookAndFeel(new com.formdev.flatlaf.FlatLightLaf());
//        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
//           JOptionPane.showMessageDialog(null, "Fallo al iniciar la libreria FlatLaf" + ex);
//        }
//        javax.swing.SwingUtilities.updateComponentTreeUI(frame);
//    }
    
    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == vista.btnAgregarCajero) {
            JDialog vac = new JDialog(vista, true);
            PanelAgergarTrabajador panel = new PanelAgergarTrabajador();
            
            CRUD crud = new CRUD();
            ControllerPanelAgregarCajero c = new ControllerPanelAgregarCajero(panel, crud);
            
            vac.add(panel);
            vac.setSize(470, 650);
            vac.setResizable(false);
            vac.setLocationRelativeTo(null);
            
            panel.cboDia.setModel(c.getListaFecha().get(2));
            panel.cboMes.setModel(c.getListaFecha().get(1));
            panel.cboAnio.setModel(c.getListaFecha().get(0));
            panel.cboDominios.setModel(c.getListaDominios());
            
            vac.setVisible(true);
        }
        if (e.getSource() == vista.btnTemaClaro) {
            try {
                javax.swing.UIManager.setLookAndFeel(new com.formdev.flatlaf.FlatLightLaf());
                javax.swing.SwingUtilities.updateComponentTreeUI(vista);
            } catch (javax.swing.UnsupportedLookAndFeelException ex) {
                JOptionPane.showMessageDialog(null, "Fallo al iniciar la libreria FlatLaf" + ex);
            }
        }
        
        if (e.getSource() == vista.btnTemaOscuro) {
            try {
                javax.swing.UIManager.setLookAndFeel(new com.formdev.flatlaf.FlatDarkLaf());
                javax.swing.SwingUtilities.updateComponentTreeUI(vista);
            } catch (javax.swing.UnsupportedLookAndFeelException ex) {
                JOptionPane.showMessageDialog(null, "Fallo al iniciar la libreria FlatLaf" + ex);
            }
        }
    }
}
