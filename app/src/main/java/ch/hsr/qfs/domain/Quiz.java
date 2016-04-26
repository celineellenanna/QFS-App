package ch.hsr.qfs.domain;

public class Quiz {

    private enum Status {Started, Finished, Canceled, Waiting}

    private String _id;
    private String _challengerId;
    private String _opponentId;
    private Status status;

    public String getId() {
        return this._id;
    }

    public void setId(String id) {
        this._id = _id;
    }

    public String get_challengerId() {
        return this._id;
    }

    public void set_challengerId(String id) {
        this._challengerId = _challengerId;
    }

    public String get_opponentId() {
        return this._id;
    }

    public void set_opponentId(String id) {
        this._opponentId = _opponentId;
    }

    public String getStatus() {
        return this.status.toString();
    }

    public void setStatus(Status status) {
        this.status = status;
    }
}
