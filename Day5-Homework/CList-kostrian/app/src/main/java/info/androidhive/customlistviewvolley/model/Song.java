package info.androidhive.customlistviewvolley.model;

import java.util.ArrayList;

/**
 * Created by Kostrian on 2016-01-23.
 */
public class Song {
    private String title;
    private String artist;
    private int rank;
    private int last_rank;
    private String albumImgUrl;

    public Song() {

    }

    public Song(String title, String artist, int rank, int last_rank, String albumImgUrl) {
        this.title = title;
        this.artist = artist;
        this.rank = rank;
        this.last_rank = last_rank;
        this.albumImgUrl = albumImgUrl;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getArtist() {
        return artist;
    }

    public void setArtist(String artist) {
        this.artist = artist;
    }

    public int getRank() {
        return rank;
    }

    public void setRank(int rank) {
        this.rank = rank;
    }

    public int getLast_rank() {
        return last_rank;
    }

    public void setLast_rank(int last_rank) {
        this.last_rank = last_rank;
    }

    public String getAlbumImgUrl() {
        return albumImgUrl;
    }

    public void setAlbumImgUrl(String albumImgUrl) {
        this.albumImgUrl = albumImgUrl;
    }
}
