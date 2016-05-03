package ch.hsr.qfs.domain;


import java.sql.Time;

public class UserAnswer {
    private Answer _answer;
    private User _user;
    private Time timeToAnswer;

    public Answer get_answer() {
        return _answer;
    }

    public void set_answer(Answer _answer) {
        this._answer = _answer;
    }

    public User get_user() {
        return _user;
    }

    public void set_user(User _user) {
        this._user = _user;
    }

    public Time getTimeToAnswer() {
        return timeToAnswer;
    }

    public void setTimeToAnswer(Time timeToAnswer) {
        this.timeToAnswer = timeToAnswer;
    }
}
