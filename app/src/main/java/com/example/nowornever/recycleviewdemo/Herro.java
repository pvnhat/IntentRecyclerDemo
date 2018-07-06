package com.example.nowornever.recycleviewdemo;

import java.io.Serializable;

public class Herro implements Serializable {
    private String herroId;
    private String herroName;
    private String description;
    private int heroImage;

    public Herro(String herroId, String herroName, String description, int heroImage) {
        this.herroId = herroId;
        this.herroName = herroName;
        this.description = description;
        this.heroImage = heroImage;
    }

    public Herro(String herroId, String herroName, String description) {
        this.herroId = herroId;
        this.herroName = herroName;
        this.description = description;
    }

    public String getHerroId() {
        return herroId;
    }

    public void setHerroId(String herroId) {
        this.herroId = herroId;
    }

    public String getHerroName() {
        return herroName;
    }

    public void setHerroName(String herroName) {
        this.herroName = herroName;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getHeroImage() {
        return heroImage;
    }

    public void setHeroImage(int heroImage) {
        this.heroImage = heroImage;
    }
}
