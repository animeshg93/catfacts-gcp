package com.example.purrfacts.cat.model;

public class Cartoon {
    private String abbreviation;
    private String name;
    private int year;

    // Constructors
    public Cartoon() {
    }

    public Cartoon(String abbreviation, String name, int year) {
        this.abbreviation = abbreviation;
        this.name = name;
        this.year = year;
    }

    // Getters
    public String getName() {
        return name;
    }

    public int getYear() {
        return year;
    }

    // Setters
    public void setName(String name) {
        this.name = name;
    }

    public void setYear(int year) {
        this.year = year;
    }

    @Override
    public String toString() {
        return "Cartoon{" +
                "name='" + name + '\'' +
                ", year=" + year +
                '}';
    }

    public String getAbbreviation() {
        return abbreviation;
    }

    public void setAbbreviation(String abbreviation) {
        this.abbreviation = abbreviation;
    }
}
