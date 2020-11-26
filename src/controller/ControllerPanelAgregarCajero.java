package controller;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.Year;
import java.util.ArrayList;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JOptionPane;
import model.Anio;
import model.Cajero;
import model.Dia;
import model.Dominio;
import model.Horario;
import model.Mes;
import model.database.CRUD;
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
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Error:\n" + ex);
        }
        return DCM;
    }

    public ArrayList<DefaultComboBoxModel> getListaFecha() {
        ArrayList<DefaultComboBoxModel> arregloDCM = new ArrayList(3);
        int year = Year.now().getValue();
        DefaultComboBoxModel auxAnio = new DefaultComboBoxModel();
        DefaultComboBoxModel auxMes = new DefaultComboBoxModel();
        DefaultComboBoxModel auxDia = new DefaultComboBoxModel();
        
        auxAnio.addElement("Año");
        
        for (int i = 1900; i <= year - 18; i++) {
            auxAnio.addElement(new Anio(i));
        }
        arregloDCM.add(auxAnio);
        
        auxMes.addElement("Mes");
        String[] meses = {"Enero", "Febrero", "Marzo", "Abril", "Mayo", "Junio", "Julio", "Agosto",
            "Septiembre", "Octubre", "Noviembre", "Diciembre"};

        for (int i = 0; i < 12; i++) {
            auxMes.addElement(new Mes(i + 1, meses[i]));
        }
        arregloDCM.add(auxMes);
        
        auxDia.addElement("Día");
        for (int i = 1; i <= 31; i++) {
            auxDia.addElement(new Dia(i));
        }
        arregloDCM.add(auxDia);
        
        return arregloDCM;
    }
}
