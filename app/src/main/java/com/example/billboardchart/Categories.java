package com.example.billboardchart;


public class Categories {

    private String name;
    private CharSequence description;

    //array that defines the different categories that will be in the list
    public static final Categories[] categories = {
            new Categories("Hot 100", " "),
            new Categories("Billboard 200"," "),
            new Categories("Artist 100", " "),

    };

    //constructor for categories. Each category has a description and a name
    public Categories(String name, String description){
        this.setDescription(description);
        this.setName(name);
    }

    //methods to get and set the name and the description
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public CharSequence getDescription() {
        return description;
    }

    public CharSequence setDescription(CharSequence description) {
        this.description = description;
        return this.description;
    }

    public static Categories[] getWorkouts() {
        return categories;
    }
}