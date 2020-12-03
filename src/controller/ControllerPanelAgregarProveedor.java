package controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import model.Proveedor;
import model.database.CRUD;
import view.PanelAgregarProveedor;

public class ControllerPanelAgregarProveedor implements ActionListener {

    private PanelAgregarProveedor vista;
    private CRUD consulta;
    private Proveedor proveedor;

    public ControllerPanelAgregarProveedor() {
    }

    public ControllerPanelAgregarProveedor(PanelAgregarProveedor vista, CRUD consulta) {
	this.vista = vista;
	this.consulta = consulta;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
	if (e.getSource() == vista.btnAgregarProveedor) {
	    proveedor = new Proveedor();
	    proveedor.setRUC(vista.txtRuc.getText());
	    proveedor.setNombre(vista.txtNombre.getText());
	    proveedor.setCorreo(vista.txtCorreo.getText());
	    proveedor.setTelefono(vista.txtTelefono.getText());

	    consulta.insert("INSERT INTO PROVEEDORES VALUES("
		    + "'" + proveedor.getRUC() + "',"
		    + "'" + proveedor.getNombre() + "',"
		    + "'" + proveedor.getCorreo() + "',"
		    + "'" + proveedor.getTelefono() + "')"
	    );
	}
    }
}
