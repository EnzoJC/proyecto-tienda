package model;

public class Fecha {
    private Dia dia;
    private Mes mes;
    private Anio anio;

    public Fecha() {
    }

    public Fecha(Dia dia, Mes mes, Anio anio) {
	this.dia = dia;
	this.mes = mes;
	this.anio = anio;
    }

    public Dia getDia() {
	return dia;
    }

    public void setDia(Dia dia) {
	this.dia = dia;
    }

    public Mes getMes() {
	return mes;
    }

    public void setMes(Mes mes) {
	this.mes = mes;
    }

    public Anio getAnio() {
	return anio;
    }

    public void setAnio(Anio anio) {
	this.anio = anio;
    }
    
    public boolean isBisiesto() {
	String stringAnio = anio.toString();

	String auxIzquierda = Character.toString(stringAnio.charAt(0)) + Character.toString(stringAnio.charAt(1));
	int numIzquierda = Integer.parseInt(auxIzquierda);

	String auxDerecha = Character.toString(stringAnio.charAt(2)) + Character.toString(stringAnio.charAt(3));
	int numDerecha = Integer.parseInt(auxDerecha);

	if (numDerecha % 4 == 0 && numDerecha != 0) {
	    return true;
	} else if (numIzquierda % 4 == 0) {
	    return false;
	} else {
	    return false;
	}
    }

    public boolean validarFecha(int dia, int mes, int anio) {
	int meses[] = {1, 3, 5, 7, 8, 10, 12};
	int mesT[] = {4, 6, 9, 11};
	boolean isCorrecto = false;
	if (isBisiesto()) {
	    for (int i = 0; i < 7; i++) {
		if (mes == meses[i]) {
		    if (dia > 0 && dia <= 31) {
			isCorrecto = true;
		    }
		    break;
		} else if (mes == mesT[i] && i <= 3) {
		    if (dia > 0 && dia <= 30) {
			isCorrecto = true;
		    }
		    break;
		} else if (mes == 2) {
		    if (dia > 0 && dia <= 29) {
			isCorrecto = true;
		    }
		    break;
		} else {
		    isCorrecto = false;
		}
	    }
	} else {
	    for (int i = 0; i < 7; i++) {
		if (mes == meses[i]) {
		    if (dia > 0 && dia <= 31) {
			isCorrecto = true;
		    }
		    break;
		} else if (mes == mesT[i] && i <= 3) {
		    if (dia > 0 && dia <= 30) {
			isCorrecto = true;
		    }
		    break;
		} else if (mes == 2) {
		    if (dia > 0 && dia <= 28) {
			isCorrecto = true;
		    }
		    break;
		} else {
		    isCorrecto = false;
		}
	    }
	}
	return isCorrecto;
    }

    @Override
    public String toString() {
	return anio.getAnio() + "-" + mes.getMes() + "-" + dia.getDia();
    }
}
