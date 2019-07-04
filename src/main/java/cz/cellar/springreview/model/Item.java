package cz.cellar.springreview.model;

import com.fasterxml.jackson.annotation.JsonProperty;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.NotBlank;

@Entity
public class Item {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private long id;

    private String name;

    private double year;
    private String type;

    private String genre;
    private String textShort;
    private String textLong;

    public Item(){

    }

    public Item(@JsonProperty("name")String name, @JsonProperty("year")double year, @JsonProperty("type")String type, @JsonProperty("genre")String genre, @JsonProperty("textShort")String textShort, @JsonProperty("textLong")String textLong) {
        this.name = name;
        this.year = year;
        this.type = type;
        this.genre = genre;
        this.textShort = textShort;
        this.textLong = textLong;
    }

    public long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public double getYear() {
        return year;
    }

    public String getType() {
        return type;
    }

    public String getGenre() {
        return genre;
    }

    public String getTextShort() {
        return textShort;
    }

    public String getTextLong() {
        return textLong;
    }
}
