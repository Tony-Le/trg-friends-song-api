package io.github.trgfriendscovers;

import java.util.Objects;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

//database is connected through application.properties

//https://stackoverflow.com/questions/58537519/mongo-date-custom-converter-not-being-called-when-save-method-of-mongo-repositor
//Saving to repository currently saves class, look to above link to how to change that
@Document(collection = "trgFriendsCovers") //to know what collection this maps to instead of the default of the class name
public class Cover {
    @Id
    public  String id;
    public  String artist;
    public  String origin;
    public  String theme;
    public  String featuring;
    public  String link;

    public Cover() {}

    public Cover(String artist, String origin, String theme, String featuring, String link) {
        this.artist = artist;
        this.origin = origin;
        this.theme = theme;
        this.featuring = featuring;
        this.link = link;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getArtist() {
        return artist;
    }

    public void setArtist(String artist) {
        this.artist = artist;
    }

    public String getOrigin() {
        return origin;
    }

    public void setOrigin(String origin) {
        this.origin = origin;
    }

    public String getTheme() {
        return theme;
    }

    public void setTheme(String theme) {
        this.theme = theme;
    }

    public String getFeaturing() {
        return featuring;
    }

    public void setFeaturing(String featuring) {
        this.featuring = featuring;
    }

    public String getLink() {
        return link;
    }

    public void setLink(String link) {
        this.link = link;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Cover cover = (Cover) o;
        return Objects.equals(this.id, cover.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(this.id, this.artist, this.origin, this.theme, this.featuring, this.link);
    }

    @Override
    public String toString() {
        return "Cover{" +
                "id=" + id +
                ", artist='" + artist + '\'' +
                ", origin='" + origin + '\'' +
                ", theme='" + theme + '\'' +
                ", featuring='" + featuring + '\'' +
                ", link='" + link + '\'' +
                '}';
    }
}
