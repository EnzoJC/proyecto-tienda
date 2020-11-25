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

    @Override
    public String toString() {
        return nombreMes;
    }

}
