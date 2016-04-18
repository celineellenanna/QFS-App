package ch.hsr.qfs.domain;

public class Quiz {

    private enum Status {Started, Finished, Canceled, Waiting}

    private String challengerId;
    private String opponentId;
    private Status status;

}
