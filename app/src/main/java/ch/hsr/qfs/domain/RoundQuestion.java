package ch.hsr.qfs.domain;

import java.util.ArrayList;

public class RoundQuestion {

    private String _id;
    private int sequenceNo;
    private Question question;
    private ArrayList<UserAnswer> userAnswers;

    public String get_id() {
        return _id;
    }

    public void set_id(String _id) {
        this._id = _id;
    }

    public int getSequenceNo() {
        return sequenceNo;
    }

    public void setSequenceNo(int sequenceNo) {
        this.sequenceNo = sequenceNo;
    }

    public Question getQuestion() {
        return question;
    }

    public void setQuestion(Question question) {
        this.question = question;
    }

    public ArrayList<UserAnswer> getUserAnswers() {
        return userAnswers;
    }

    public void setUserAnswers(ArrayList<UserAnswer> userAnswers) {
        this.userAnswers = userAnswers;
    }

}
