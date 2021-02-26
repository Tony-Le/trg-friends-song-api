package com.herokuapp.TRGFriendsSongs;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;
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
    public  LocalDateTime publish_date;

    public Song() {}

    public Song(String _id, String id, String title, String[] tags, LocalDateTime publish_date) {
        this._id = _id;
        this.id = id;
        this.title = title;
        this.tags = tags;
        this.publish_date = publish_date;
    }

    public String get_id() {
        return _id;
    }

    public void set_id(String _id) {
        this._id = _id;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String[] getTags() {
        return tags;
    }

    public void setTags(String[] tags) {
        this.tags = tags;
    }

    public LocalDateTime getPublish_date() {
        return publish_date;
    }

    public void setPublish_date(LocalDateTime publish_date) {
        this.publish_date = publish_date;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Song song = (Song) o;
        return Objects.equals(id, song.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
