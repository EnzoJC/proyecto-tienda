package controller;

import model.Trabajador;
import model.database.Conexion;
import view.FrmCajero;

public class ControllerCajero {

    private FrmCajero caja;
    private Trabajador cajero;
    private Conexion conexion;

    public ControllerCajero() {
    }

    public ControllerCajero(FrmCajero caja, Trabajador cajero, Conexion conexion) {
	this.caja = caja;
	this.cajero = cajero;
	this.conexion = conexion;
    }
    

}
