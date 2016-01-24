package info.androidhive.customlistviewvolley.model;

import java.io.Serializable;
import java.util.ArrayList;

/**
 * Created by JEEWOOYOON on 2016. 1. 23..
 */
public class Football implements Serializable {
    private String teamName, thumbnailUrl;
    private int points;
    private int position;
    private ArrayList<String> WDL;

    public Football() {
    }

    public Football(String teamName, String thumbnailUrl, int points, int position,
                 ArrayList<String> WDL) {
        this.teamName = teamName;
        this.thumbnailUrl = thumbnailUrl;
        this.points = points;
        this.position = position;
        this.WDL = WDL;

    }

    public String getTeamName() {
        return teamName;
    }

    public void setTeamName(String teamName) {
        this.teamName = teamName;
    }

    public void setPoints(int points) {
        this.points = points;
    }
    public int getPoints() {
        return points;
    }

    public int getPosition() {
        return position;
    }

    public void setPosition(int position) {
        this.position = position;
    }

    public ArrayList<String> getWDL() {
        return WDL;
    }

    public void setWDL(ArrayList<String> WDL) {
        this.WDL = WDL;
    }

    public String getThumbnailUrl() {
        return thumbnailUrl;
    }

    public void setThumbnailUrl(String thumbnailUrl) {
        this.thumbnailUrl = thumbnailUrl;
    }

}
