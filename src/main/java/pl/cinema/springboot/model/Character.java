package pl.cinema.springboot.model;

public class Character {

    public int idCharacter;
    //public int idActor;
    //public int idMovie;
    public Actor actor;
    public Movie movie;
    public String name;
    public String surname;
    public String cPhotoUrl;

    @Override
    public String toString() {
        return "Character{" +
                "idCharacter=" + idCharacter +
                "idActor=" + actor.idActor +
                "idMovie=" + movie.idMovie +
                ", name='" + name + "\'" +
                ", surname='" + surname + "\'" +
                ", cPhotoUrl='" + cPhotoUrl + "\'" +
                "}";
    }

}
