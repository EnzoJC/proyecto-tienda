package model;

import java.sql.Time;

public class Horario {

    private int id;
    private Time horaEntrada;
    private Time horaSalida;

    public Horario() {
    }

    public Horario(int id, Time horaEntrada, Time horaSalida) {
        this.id = id;
        this.horaEntrada = horaEntrada;
        this.horaSalida = horaSalida;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Time getHoraEntrada() {
        return horaEntrada;
    }

    public void setHoraEntrada(Time horaEntrada) {
        this.horaEntrada = horaEntrada;
    }

    public Time getHoraSalida() {
        return horaSalida;
    }

    public void setHoraSalida(Time horaSalida) {
        this.horaSalida = horaSalida;
    }

    @Override
    public String toString() {
        return "De: " + horaEntrada.toString().substring(0, 5) + " a " + horaSalida.toString().substring(0, 5);
    }
}
