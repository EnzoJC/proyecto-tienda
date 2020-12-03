package model;

public class Kardex {
    private Producto id;
    private int cantidad;
    private double precioUnidad;
    private double valorTotal = cantidad * precioUnidad;

    public Kardex() {
    }

    public Kardex(Producto id, int cantidad, double precioUnidad) {
	this.id = id;
	this.cantidad = cantidad;
	this.precioUnidad = precioUnidad;
    }

    public Producto getId() {
	return id;
    }

    public void setId(Producto id) {
	this.id = id;
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

    public double getValorTotal() {
	return valorTotal;
    }

    @Override
    public String toString() {
	return "" + id.getId();
    }
}
