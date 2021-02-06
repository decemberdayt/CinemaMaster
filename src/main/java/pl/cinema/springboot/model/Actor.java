package pl.cinema.springboot.model;

import java.util.List;

public class Actor {
    public int idActor;
    public String name;
    public String surname;
    public String country;
    public String aPhotoUrl;

    @Override
    public String toString() {
        return "Actor{" +
                "idActor=" + idActor +
                ", name='" + name + "\'" +
                ", surname='" + surname + "\'" +
                ", country='" + country + "\'" +
                ", aPhotoUrl='" + aPhotoUrl + "\'" +
                "}";
    }

    public Actor(int idActor, String name, String surname, String country, String aPhotoUrl) {
        this.idActor = idActor;
        this.name = name;
        this.surname = surname;
        this.country = country;
        this.aPhotoUrl = aPhotoUrl;
    }

    public int getIdActor() {
        return idActor;
    }

    public void setIdActor(int idActor) {
        this.idActor = idActor;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getaPhotoUrl() {
        return aPhotoUrl;
    }

    public void setaPhotoUrl(String aPhotoUrl) {
        this.aPhotoUrl = aPhotoUrl;
    }
}
