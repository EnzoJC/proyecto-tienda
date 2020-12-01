package model;

public class Anio {

    private int anio;

    public Anio() {

    }

    public Anio(int anio) {
        this.anio = anio;
    }

    public int getAnio() {
	return anio;
    }

    public void setAnio(int anio) {
	this.anio = anio;
    }

    @Override
    public String toString() {
        return "" + anio;
    }

}
