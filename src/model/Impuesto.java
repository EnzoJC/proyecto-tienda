package model;

public class Impuesto {
    private int id;
    private double porcentaje;

    public Impuesto() {
    }

    public Impuesto(int id, double porcentaje) {
	this.id = id;
	this.porcentaje = porcentaje;
    }

    public int getId() {
	return id;
    }

    public void setId(int id) {
	this.id = id;
    }

    public double getPorcentaje() {
	return porcentaje;
    }

    public void setPorcentaje(double porcentaje) {
	this.porcentaje = porcentaje;
    }

    @Override
    public String toString() {
	return "" + porcentaje;
    }
    
}
