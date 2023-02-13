package com.example.next_gen;

public class SetRecord {

    // private variables
    int _id;
    String _image, _image2, _image3, _image4;

    // Empty constructor
    public SetRecord() {

    }
    // constructor
    public SetRecord(int keyId, String image, String image2, String image3, String image4) {
        this._id = keyId;
        this._image = image;
        this._image2 = image2;
        this._image3 = image3;
        this._image4 = image4;

    }

    public SetRecord(String image, String image2, String image3, String image4) {
        this._image = image;
        this._image2 = image2;
        this._image3 = image3;
        this._image4 = image4;

    }

    public SetRecord(int keyId) {
        this._id = keyId;
    }

    // getting ID
    public int getID() {
        return this._id;
    }

    // setting id
    public void setID(int keyId) {
        this._id = keyId;
    }

    public String getImage() {
        return this._image;
    }

    // setting phone number
    public void setImage(String image) {
        this._image = image;
    }

    public String getImage2() {
        return this._image2;
    }

    // setting phone number
    public void setImage2(String image2) {
        this._image2 = image2;
    }

    public String getImage3() {
        return this._image3;
    }

    // setting phone number
    public void setImage3(String image3) {
        this._image3 = image3;
    }

    public String getImage4() {
        return this._image4;
    }

    // setting phone number
    public void setImage4(String image4) {
        this._image4 = image4;
    }
}
