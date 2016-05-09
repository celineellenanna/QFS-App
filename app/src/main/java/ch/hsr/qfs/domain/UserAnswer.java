package ch.hsr.qfs.domain;

public class UserAnswer {

    private String _id;
    private Answer _answer;
    private User _user;
    private int timeToAnswer;
    private boolean status;

    public boolean getStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

    public String get_id() {
        return _id;
    }

    public void set_id(String _id) {
        this._id = _id;
    }

    public Answer get_answer() {
        return _answer;
    }

    public void set_answer(Answer answer) {
        this._answer = answer;
    }

    public User get_user() {
        return _user;
    }

    public void set_user(User user) {
        this._user = user;
    }

    public int getTimeToAnswer() {
        return timeToAnswer;
    }

    public void setTimeToAnswer(int timeToAnswer) {
        this.timeToAnswer = timeToAnswer;
    }
}
