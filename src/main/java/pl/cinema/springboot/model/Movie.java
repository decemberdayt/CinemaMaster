package pl.cinema.springboot.model;

import java.util.List;

public class Movie {

    public int idMovie;
    public String title;
    public String subTitle;
    public float rating;
    public String creator;
    public int durationMin;
    public String mPhotoUrl;

    @Override
    public String toString() {
        return "Movie{" +
                "idMovie=" + idMovie +
                ", title='" + title + "\'" +
                ", subTitle='" + subTitle + "\'" +
                "rating=" + rating +
                ", creator='" + creator + "\'" +
                "durationMin=" + durationMin +
                ", mPhotoUrl='" + mPhotoUrl + "\'" +
                "}";
    }
}
