package controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.ResultSet;
import java.sql.Statement;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JOptionPane;
import model.Cajero;
import model.Dominio;
import model.Horario;
import model.database.CRUD;
import model.database.Conexion;
import view.PanelAgergarCajero;

public class ControllerPanelAgregarCajero {
    
    private PanelAgergarCajero vista;
    private CRUD consulta;
    private Cajero cajero;
    private Horario horario;
    //public  ResultSet rs;
    private Statement stmt;
    //private Sesion sesion;

    public ControllerPanelAgregarCajero() {
    }

    public ControllerPanelAgregarCajero(PanelAgergarCajero vista, CRUD consulta) {
        this.vista = vista;
        this.consulta = consulta;
    }
    
    public DefaultComboBoxModel getListaDominios() {
        DefaultComboBoxModel DCM = new DefaultComboBoxModel();
        ResultSet rs = consulta.select("SELECT * FROM DOMINIOS");
        try {
            while (rs.next()) {
                DCM.addElement(new Dominio(rs.getInt(1), rs.getString(2)));
            }
        } catch (Exception ex) {
             JOptionPane.showMessageDialog(null, "Error:\n" + ex);
        }
        return DCM;
    }
}
