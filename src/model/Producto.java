package model;

public class Producto {
    private int id;
    private String nombre;
    private int stock;
    private double precioVenta;
    private Ganancia ganancia;
    private Categoria categoria;
    private Descuento descuento;
    private Genero genero;
    private Marca marca;
    private Impuesto impuesto;
    private Talla talla;

    public Producto() {
    }

    public Producto(int id, String nombre, int stock, double precioVenta, Ganancia ganancia, Categoria categoria, Descuento descuento, Genero genero, Marca marca, Impuesto impuesto, Talla talla) {
	this.id = id;
	this.nombre = nombre;
	this.stock = stock;
	this.precioVenta = precioVenta;
	this.ganancia = ganancia;
	this.categoria = categoria;
	this.descuento = descuento;
	this.genero = genero;
	this.marca = marca;
	this.impuesto = impuesto;
	this.talla = talla;
    }

    public int getId() {
	return id;
    }

    public void setId(int id) {
	this.id = id;
    }

    public String getNombre() {
	return nombre;
    }

    public void setNombre(String nombre) {
	this.nombre = nombre;
    }

    public int getStock() {
	return stock;
    }

    public void setStock(int stock) {
	this.stock = stock;
    }

    public double getPrecioVenta() {
	return precioVenta;
    }

    public void setPrecioVenta(double precioVenta) {
	this.precioVenta = precioVenta;
    }

    public Ganancia getGanancia() {
	return ganancia;
    }

    public void setGanancia(Ganancia ganancia) {
	this.ganancia = ganancia;
    }

    public Categoria getCategoria() {
	return categoria;
    }

    public void setCategoria(Categoria categoria) {
	this.categoria = categoria;
    }

    public Descuento getDescuento() {
	return descuento;
    }

    public void setDescuento(Descuento descuento) {
	this.descuento = descuento;
    }

    public Genero getGenero() {
	return genero;
    }

    public void setGenero(Genero genero) {
	this.genero = genero;
    }

    public Marca getMarca() {
	return marca;
    }

    public void setMarca(Marca marca) {
	this.marca = marca;
    }

    public Impuesto getImpuesto() {
	return impuesto;
    }

    public void setImpuesto(Impuesto impuesto) {
	this.impuesto = impuesto;
    }

    public Talla getTalla() {
	return talla;
    }

    public void setTalla(Talla talla) {
	this.talla = talla;
    }

    @Override
    public String toString() {
	return nombre + " - " + id;
    }
}
