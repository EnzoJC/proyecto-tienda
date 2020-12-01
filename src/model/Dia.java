package model;

public class Dia {

    private int dia;

    public Dia() {
    }

    public Dia(int dia) {
        this.dia = dia;
    }

    public int getDia() {
	return dia;
    }

    public void setDia(int dia) {
	this.dia = dia;
    }

    @Override
    public String toString() {
        return "" + dia;
    }

}
