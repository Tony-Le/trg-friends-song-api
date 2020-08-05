package com.herokuapp.TRGFriendsSongs;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Arrays;
import java.util.Objects;

//database is connected through application.properties
//https://stackoverflow.com/questions/58537519/mongo-date-custom-converter-not-being-called-when-save-method-of-mongo-repositor
//Saving to repository currently saves class, look to above link to how to change that
@Document(collection = "trgFriendsSongs") //to know what collection this maps to instead of the default of the class name
public class Song {
    @Id
    public  String _id;
    public  String id;
    public  String title;
    public  String[] tags;

    public Song() {}

    public Song(String _id, String id, String title, int searchable, String[] tags) {
        this._id = _id;
        this.id = id;
        this.title = title;
        this.tags = tags;
    }

    public String get_id() {
        return _id;
    }

    public String getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public String[] getTags() {
        return tags;
    }

    public void set_id(String _id) {
        this._id = _id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setTags(String[] tags) {
        this.tags = tags;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Song song = (Song) o;
        return Objects.equals(_id, song._id) &&
                Objects.equals(id, song.id) &&
                Objects.equals(title, song.title) &&
                Arrays.equals(tags, song.tags);
    }

    @Override
    public int hashCode() {
        int result = Objects.hash(_id, id, title);
        result = 31 * result + Arrays.hashCode(tags);
        return result;
    }

    @Override
    public String toString() {
        return "Song{" +
                "_id='" + _id + '\'' +
                ", id='" + id + '\'' +
                ", title='" + title + '\'' +
                ", tags=" + Arrays.toString(tags) +
                '}';
    }
}
