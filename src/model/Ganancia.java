package model;

public class Ganancia {
    private int id;
    private double porcentaje; 

    public Ganancia() {
    }

    public Ganancia(int id, double porcentaje) {
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
	return "" + (int) (porcentaje * 100) + "%";
    }
}
