package info.androidhive.customlistviewvolley.model;

import java.io.Serializable;
import java.util.ArrayList;

/**
 * Created by JARVIS on 2016. 1. 23..
 */
public class Supporter implements Serializable{
    private String name, part, pickRate, winRate, thumbnailUrl;
    private ArrayList<String> isCC, isHeal;

    public Supporter(){ }
    public Supporter(String name, String pickRate, String winRate, ArrayList<String> isCC, ArrayList<String> isHeal, String part, String thumbnailUrl){
        this.name = name;
        this.pickRate = pickRate;
        this.winRate = winRate;;
        this.isCC = isCC;
        this.isHeal = isHeal;
        this.part = part;
        this.thumbnailUrl = thumbnailUrl;
    }

    public String getName(){ return name; }
    public void setName(String name){ this.name = name; }

    public String getPickRate(){ return pickRate; }
    public void setPickRate(String pickRate){ this.pickRate = pickRate; }

    public String getWinRate(){ return winRate; }
    public void setWinRate(String winRate){ this.winRate = winRate; }

    public ArrayList<String> getIsCC(){ return isCC; }
    public void setIsCC(ArrayList<String> isCC){ this.isCC = isCC; }

    public String getPart(){ return part; }
    public void setPart(String part){ this.part = part; }

    public String getThumbnailUrl(){ return thumbnailUrl; }
    public void setThumbnailUrl(String thumbnailUrl){ this.thumbnailUrl = thumbnailUrl; }

    public ArrayList<String> getIsHeal(){ return isHeal; }
    public void setIsHeal(ArrayList<String> isHeal){ this.isHeal = isHeal; }

}

