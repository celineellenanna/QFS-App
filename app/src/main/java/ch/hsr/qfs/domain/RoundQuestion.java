package ch.hsr.qfs.domain;

import java.util.ArrayList;

public class RoundQuestion {

    private String _id;
    private int sequenceNo;
    private Question _question;
    private ArrayList<UserAnswer> _userAnswers;

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

    public Question get_question() {
        return _question;
    }

    public void set_question(Question question) {
        this._question = question;
    }

    public ArrayList<UserAnswer> get_userAnswers() {
        return _userAnswers;
    }

    public void set_userAnswers(ArrayList<UserAnswer> userAnswers) {
        this._userAnswers = userAnswers;
    }

}
