package com.example.next_gen;

public class ClothRecord {

    int _id;
    String _name,_colour,_category,_heatlvl;
    String _image;

    public ClothRecord() {

    }
    public ClothRecord(int keyId, String name, String image, String colour, String category, String heatlvl) {
        this._id = keyId;
        this._name = name;
        this._image = image;
        this._colour = colour;
        this._category = category;
        this._heatlvl = heatlvl;

    }

    public ClothRecord(String name, String image, String colour, String category, String heatlvl) {
        this._name = name;
        this._image = image;
        this._colour = colour;
        this._category = category;
        this._heatlvl = heatlvl;

    }

    public ClothRecord(int keyId) {
        this._id = keyId;
    }

    public int getID() {
        return this._id;
    }

    public void setID(int keyId) {
        this._id = keyId;
    }

    public String getName() {
        return this._name;
    }

    public void setName(String name) {
        this._name = name;
    }


    public String getHeatlvl() { return this._heatlvl;}

    public void setHeatlvl(String heatlvl) {this._heatlvl = heatlvl; }

    public String getColour() {return this._colour;    }

    public void setColour(String colour) {     this._colour = colour;}

    public String getCategory() { return this._category;    }

    public void setCategory(String category) {this._category = category;}

    public String getImage() {
        return this._image;
    }

    public void setImage(String image) {
        this._image = image;
    }


    }
