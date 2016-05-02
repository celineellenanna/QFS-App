package ch.hsr.qfs.domain;

import java.util.ArrayList;

public class Question {

    private enum Status {Created, Approved, Rejected, Deleted};

    private String _id;
    private String name;
    private ArrayList<Answer> answers;
    private Status status;

    public ArrayList<Answer> getAnswers() {
        return answers;
    }

    public void setAnswers(ArrayList<Answer> answers) {
        this.answers = answers;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

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
}
