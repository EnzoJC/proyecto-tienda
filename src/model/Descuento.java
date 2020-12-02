package model;

public class Descuento {
    private int id;
    private String tipo;
    private Double porcentaje;

    public Descuento() {
    }

    public Descuento(int id, String tipo, Double porcentaje) {
	this.id = id;
	this.tipo = tipo;
	this.porcentaje = porcentaje;
    }

    public int getId() {
	return id;
    }

    public void setId(int id) {
	this.id = id;
    }

    public String getTipo() {
	return tipo;
    }

    public void setTipo(String tipo) {
	this.tipo = tipo;
    }

    public Double getPorcentaje() {
	return porcentaje;
    }

    public void setPorcentaje(Double porcentaje) {
	this.porcentaje = porcentaje;
    }

    @Override
    public String toString() {
	return "" + tipo;
    }
    
}
