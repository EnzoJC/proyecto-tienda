package controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JOptionPane;
import model.Cargo;
import model.database.CRUD;
import view.FrmAdmin;

public class ControllerAdmin implements ActionListener {

    private FrmAdmin vista;
    private CRUD consulta;

    public ControllerAdmin() {
    }

    public ControllerAdmin(FrmAdmin vista, CRUD consulta) {
	this.vista = vista;
	this.consulta = consulta;
	this.vista.cboCargo.setModel(this.getListaCargos());
	ControllerPanelAgregarTrabajdor c = new ControllerPanelAgregarTrabajdor(vista.panelAgergarTrabajador, consulta);

	vista.panelAgergarTrabajador.cboDia.setModel(c.getListaFecha().get(2));
	vista.panelAgergarTrabajador.cboMes.setModel(c.getListaFecha().get(1));
	vista.panelAgergarTrabajador.cboAnio.setModel(c.getListaFecha().get(0));
	vista.panelAgergarTrabajador.cboDominios.setModel(c.getListaDominios());
	
	vista.cboCargo.setSelectedItem(null);
//	vista.cboCargo.addActionListener(new ActionListener() {
//	    @Override
//	    public void actionPerformed(ActionEvent e) {
//		if (e.getSource() == vista.cboCargo) {
//		    if (vista.cboCargo.getSelectedItem().toString().compareTo("Cajero") == 0) {
//			vista.cboSupervisor.setVisible(true);
//			vista.lblSupervisor.setVisible(true);
//		    } else {
//			vista.cboSupervisor.setVisible(false);
//			vista.lblSupervisor.setVisible(false);
//		    }
//		}
//	    }
//	});
	vista.cboCargo.addActionListener(this);
	vista.cboSupervisor.setVisible(false);
	vista.lblSupervisor.setVisible(false);
    }

    private DefaultComboBoxModel getListaCargos() {
	DefaultComboBoxModel DCM = new DefaultComboBoxModel();
	ResultSet rs = consulta.select("SELECT * FROM CARGOS");
	try {
	    while (rs.next()) {
		DCM.addElement(new Cargo(rs.getInt(1), rs.getString(2)));
	    }
	} catch (SQLException ex) {
	    JOptionPane.showMessageDialog(null, "Error:\n" + ex);
	}
	return DCM;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
	if (e.getSource() == vista.cboCargo) {
	    if (vista.cboCargo.getSelectedItem().toString().compareTo("Cajero") == 0) {
		vista.cboSupervisor.setVisible(true);
		vista.lblSupervisor.setVisible(true);
	    } else {
		vista.cboSupervisor.setVisible(false);
		vista.lblSupervisor.setVisible(false);
	    }
	}
    }
}
