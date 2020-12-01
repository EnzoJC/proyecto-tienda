package model;

public class Mes {

    private int mes;
    private String nombreMes;

    public Mes() {

    }

    public Mes(int mes, String nombreMes) {
        this.mes = mes;
        this.nombreMes = nombreMes;
    }

    public int getMes() {
	return mes;
    }

    public void setMes(int mes) {
	this.mes = mes;
    }

    public String getNombreMes() {
	return nombreMes;
    }

    public void setNombreMes(String nombreMes) {
	this.nombreMes = nombreMes;
    }
    
    @Override
    public String toString() {
        return nombreMes;
    }

}
