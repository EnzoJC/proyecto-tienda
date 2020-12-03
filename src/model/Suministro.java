package model;

import java.sql.Date;

public class Suministro {
    private int id;
    private Proveedor proveedor;
    private int cantidad;
    private double precioUnidad;
    private double montoTotal = cantidad * precioUnidad;
    private Date fecha;
    private Kardex kardex;

    public Suministro() {
    }

    public Suministro(int id, Proveedor proveedor, int cantidad, double precioUnidad, Date fecha, Kardex kardex) {
	this.id = id;
	this.proveedor = proveedor;
	this.cantidad = cantidad;
	this.precioUnidad = precioUnidad;
	this.fecha = fecha;
	this.kardex = kardex;
    }

    public int getId() {
	return id;
    }

    public void setId(int id) {
	this.id = id;
    }

    public Proveedor getProveedor() {
	return proveedor;
    }

    public void setProveedor(Proveedor proveedor) {
	this.proveedor = proveedor;
    }

    public int getCantidad() {
	return cantidad;
    }

    public void setCantidad(int cantidad) {
	this.cantidad = cantidad;
    }

    public double getPrecioUnidad() {
	return precioUnidad;
    }

    public void setPrecioUnidad(double precioUnidad) {
	this.precioUnidad = precioUnidad;
    }

    public double getMontoTotal() {
	return montoTotal;
    }

    public Date getFecha() {
	return fecha;
    }

    public void setFecha(Date fecha) {
	this.fecha = fecha;
    }

    public Kardex getKardex() {
	return kardex;
    }

    public void setKardex(Kardex kardex) {
	this.kardex = kardex;
    }

    @Override
    public String toString() {
	return "" + id;
    }
}
