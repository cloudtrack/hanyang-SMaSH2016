package info.androidhive.customlistviewvolley.model;

import java.util.ArrayList;

/**
 * Created by sangwonjo on 2016. 1. 23..
 */
public class Music {
    private int rating;
    private String title, thumbnailUrl, album;
    private ArrayList<String> artist;

    public Music() {
    }

    public Music(String name, String thumbnailUrl, int rating,
                 ArrayList<String> artist, String album) {
        this.title = name;
        this.thumbnailUrl = thumbnailUrl;
        this.album = album;
        this.rating = rating;
        this.artist = artist;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String name) {
        this.title = name;
    }

    public String getThumbnailUrl() {
        return thumbnailUrl;
    }

    public void setThumbnailUrl(String thumbnailUrl) {
        this.thumbnailUrl = thumbnailUrl;
    }

    public String getAlbum() {
        return album;
    }

    public void setAlbum(String album) {
        this.album = album;
    }

    public int getRating() {
        return rating;
    }

    public void setRating(int rating) {
        this.rating = rating;
    }

    public ArrayList<String> getArtist() {
        return artist;
    }

    public void setArtist(ArrayList<String> artist) {
        this.artist = artist;
    }

}
