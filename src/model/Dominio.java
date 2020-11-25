package model;

public class Dominio {
    private int id;
    private String dominio;

    public Dominio() {
    }

    public Dominio(int id, String dominio) {
        this.id = id;
        this.dominio = dominio;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getDominio() {
        return dominio;
    }

    public void setDominio(String dominio) {
        this.dominio = dominio;
    }

    @Override
    public String toString() {
        return dominio;
    }
}
