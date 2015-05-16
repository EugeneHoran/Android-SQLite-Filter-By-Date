package com.eugene.sqlitetesting;

public class Contact {
    //private variables
    private int _id;
    private String _name;
    private String _phone_number;
    private String _date;

    // Empty constructor
    public Contact() {
    }

    // constructor for database
    public Contact(int id, String name, String _phone_number, String _date) {
        this._id = id;
        this._name = name;
        this._phone_number = _phone_number;
        this._date = _date;
    }

    // constructor for activity
    public Contact(String name, String _phone_number, String _date) {
        this._name = name;
        this._phone_number = _phone_number;
        this._date = _date;
    }

    // getting ID
    public int getID() {
        return this._id;
    }

    // setting id
    public void setID(int id) {
        this._id = id;
    }

    // getting name
    public String getName() {
        return this._name;
    }

    // setting name
    public void setName(String name) {
        this._name = name;
    }

    // getting phone number
    public String getPhoneNumber() {
        return this._phone_number;
    }

    // setting phone number
    public void setPhoneNumber(String phone_number) {
        this._phone_number = phone_number;
    }

    // getting date
    public String getDate() {
        return this._date;
    }

    // setting date
    public void setDate(String date) {
        this._date = date;
    }
}
