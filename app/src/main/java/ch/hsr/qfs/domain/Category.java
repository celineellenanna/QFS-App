package ch.hsr.qfs.domain;

import java.util.ArrayList;

public class Category {

    private String _id;
    private String name;
    private ArrayList<Question> _questions;

    public String get_id() {
        return _id;
    }

    public void set_id(String _id) {
        this._id = _id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public ArrayList<Question> get_questions() {
        return _questions;
    }

    public void set_questions(ArrayList<Question> _questions) {
        this._questions = _questions;
    }

}
