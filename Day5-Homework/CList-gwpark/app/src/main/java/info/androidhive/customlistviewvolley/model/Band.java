package info.androidhive.customlistviewvolley.model;

import java.io.Serializable;
import java.util.ArrayList;

public class Band implements Serializable {
	private String title, thumbnailUrl;
	private int year;
	private ArrayList<String> genre;

	public Band() {
	}

	public Band(String name, String thumbnailUrl, int year, ArrayList<String> genre) {
		this.title = name;
		this.thumbnailUrl = thumbnailUrl;
		this.year = year;
		this.genre = genre;
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

	public int getYear() {
		return year;
	}

	public void setYear(int year) {
		this.year = year;
	}

	public ArrayList<String> getGenre() {
		return genre;
	}

	public void setGenre(ArrayList<String> genre) {
		this.genre = genre;
	}

}
