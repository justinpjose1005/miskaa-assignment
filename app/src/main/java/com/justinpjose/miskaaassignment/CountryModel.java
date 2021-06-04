package com.justinpjose.miskaaassignment;
//
//public class CountryModel {
//    String name;
//    String capital;
//    String flag;
//    String region;
//    String subregion;
//    String population;
//    String borders;
//    String languages;
//
//    public CountryModel(String name, String capital, String flag, String region, String subregion, String population, String borders, String languages) {
//        this.name = name;
//        this.capital = capital;
//        this.flag = flag;
//        this.region = region;
//        this.subregion = subregion;
//        this.population = population;
//        this.borders = borders;
//        this.languages = languages;
//    }
//
//    public String getName() {
//        return name;
//    }
//
//    public void setName(String name) {
//        this.name = name;
//    }
//
//    public String getCapital() {
//        return capital;
//    }
//
//    public void setCapital(String capital) {
//        this.capital = capital;
//    }
//
//    public String getFlag() {
//        return flag;
//    }
//
//    public void setFlag(String flag) {
//        this.flag = flag;
//    }
//
//    public String getRegion() {
//        return region;
//    }
//
//    public void setRegion(String region) {
//        this.region = region;
//    }
//
//    public String getSubregion() {
//        return subregion;
//    }
//
//    public void setSubregion(String subregion) {
//        this.subregion = subregion;
//    }
//
//    public String getPopulation() {
//        return population;
//    }
//
//    public void setPopulation(String population) {
//        this.population = population;
//    }
//
//    public String getBorders() {
//        return borders;
//    }
//
//    public void setBorders(String borders) {
//        this.borders = borders;
//    }
//
//    public String getLanguages() {
//        return languages;
//    }
//
//    public void setLanguages(String languages) {
//        this.languages = languages;
//    }
//}

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

import java.io.Serializable;

//Define table name
@Entity(tableName = "countries")
public class CountryModel implements Serializable {

    public CountryModel(String name, String capital, String flag, String region, String subregion, String population, String borders, String languages) {
        this.name = name;
        this.capital = capital;
        this.flag = flag;
        this.region = region;
        this.subregion = subregion;
        this.population = population;
        this.borders = borders;
        this.languages = languages;
    }

    //id of file
    @PrimaryKey(autoGenerate = true)
    private int ID;

    @ColumnInfo(name = "name")
    private String name;

    @ColumnInfo(name = "capital")
    private String capital;

    @ColumnInfo(name = "flag")
    private String flag;

    @ColumnInfo(name = "region")
    private String region;

    @ColumnInfo(name = "subregion")
    private String subregion;

    @ColumnInfo(name = "population")
    private String population;

    @ColumnInfo(name = "borders")
    private String borders;

    @ColumnInfo(name = "languages")
    private String languages;

    public int getID() {
        return ID;
    }

    public void setID(int ID) {
        this.ID = ID;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCapital() {
        return capital;
    }

    public void setCapital(String capital) {
        this.capital = capital;
    }

    public String getFlag() {
        return flag;
    }

    public void setFlag(String flag) {
        this.flag = flag;
    }

    public String getRegion() {
        return region;
    }

    public void setRegion(String region) {
        this.region = region;
    }

    public String getSubregion() {
        return subregion;
    }

    public void setSubregion(String subregion) {
        this.subregion = subregion;
    }

    public String getPopulation() {
        return population;
    }

    public void setPopulation(String population) {
        this.population = population;
    }

    public String getBorders() {
        return borders;
    }

    public void setBorders(String borders) {
        this.borders = borders;
    }

    public String getLanguages() {
        return languages;
    }

    public void setLanguages(String languages) {
        this.languages = languages;
    }
}