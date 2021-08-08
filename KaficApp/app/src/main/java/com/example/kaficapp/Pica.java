package com.example.kaficapp;

public class Pica {

    String id;
    String naziv;
    String cena;

    public Pica(String id, String naziv, String cena){
        super();
        this.id = id;
        this.naziv = naziv;
        this.cena = cena;
    }

    public Pica(){};

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getNaziv() {
        return naziv;
    }

    public void setNaziv(String naziv) {
        this.naziv = naziv;
    }

    public String getCena() {
        return cena;
    }

    public void setCena(String cena) {
        this.cena = cena;
    }
}
