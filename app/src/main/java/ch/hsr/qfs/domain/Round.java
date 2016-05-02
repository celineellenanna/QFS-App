package ch.hsr.qfs.domain;

import java.util.ArrayList;

public class Round {
    private String _id;
    private Category _category;
    private ArrayList<RoundQuestion> _roundQuestions;
    private String createdAt;
    private String updatedAt;

    public String get_id() {
        return _id;
    }

    public void set_id(String _id) {
        this._id = _id;
    }

    public Category get_category() {
        return _category;
    }

    public void set_category(Category _category) {
        this._category = _category;
    }

    public ArrayList<RoundQuestion> get_roundQuestions() {
        return _roundQuestions;
    }

    public void set_roundQuestions(ArrayList<RoundQuestion> _roundQuestions) {
        this._roundQuestions = _roundQuestions;
    }

    public String getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(String createdAt) {
        this.createdAt = createdAt;
    }

    public String getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(String updatedAt) {
        this.updatedAt = updatedAt;
    }
}
