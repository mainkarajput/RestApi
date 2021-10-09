package com.mainka.restapi.Entity;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "model")
public class Model {

    public int getKey() {
        return key;
    }

    public void setKey(int key) {
        this.key = key;
    }

    @PrimaryKey(autoGenerate = true)
    private int key;

    @ColumnInfo(name = "name")
    private String name;

    @ColumnInfo(name = "agency")
    private String agency;

    @ColumnInfo(name="image")
    private String image;

    @ColumnInfo(name = "wikipedia")
    private String wikipedia;

    @ColumnInfo(name = "status")
    private String status;

    public Model(String name, String agency, String image, String wikipedia, String status) {
        this.name = name;
        this.agency = agency;
        this.image = image;
        this.wikipedia = wikipedia;
        this.status = status;
    }
// Getter Methods

    public String getName() {
        return name;
    }

    public String getAgency() {
        return agency;
    }

    public String getImage() {
        return image;
    }

    public String getWikipedia() {
        return wikipedia;
    }

    public String getStatus() {
        return status;
    }

    // Setter Methods

    public void setName(String name) {
        this.name = name;
    }

    public void setAgency(String agency) {
        this.agency = agency;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public void setWikipedia(String wikipedia) {
        this.wikipedia = wikipedia;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public void deleteAll() {deleteAll();}
}