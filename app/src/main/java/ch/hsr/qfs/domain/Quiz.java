package ch.hsr.qfs.domain;

import java.util.ArrayList;
import java.util.Date;
import org.joda.time.*;
import org.joda.time.format.*;

public class Quiz {

    private enum Status {Open, Finished, Canceled, WaitingForChallenger, WaitingForOpponent}

    private String _id;
    private User _challenger;
    private User _opponent;
    private Status status;
    private String createdAt;
    private String updatedAt;
    private ArrayList<Round> _rounds;

    public ArrayList<Round> get_rounds() { return _rounds; }

    public void set_rounds(ArrayList<Round> _rounds) { this._rounds = _rounds; }

    public String getId() {
        return this._id;
    }

    public void setId(String id) {
        this._id = _id;
    }

    public User get_challenger() {
        return this._challenger;
    }

    public void set_challenger(User challenger) {
        this._challenger = challenger;
    }

    public User get_opponent() {
        return this._opponent;
    }

    public void set_opponent(User opponent) {
        this._opponent = opponent;
    }

    public String getStatus() {
        return this.status.toString();
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    public String getCreatedAt() {
        return this.createdAt;
    }

    public void setCreatedAt(String createdAt) {
        this.createdAt = createdAt;
    }

    public String getUpdatedAt() {
        return this.updatedAt;
    }

    public void setUpdatedAt(String updatedAt) {
        this.updatedAt = updatedAt;
    }

    public String getTimeElapsed() {
        DateTimeFormatter parser = ISODateTimeFormat.dateTime();
        LocalDateTime dtUpdateAt = parser.parseLocalDateTime(updatedAt);
        LocalDateTime dtNow = new LocalDateTime();

        Period period = new Period(dtUpdateAt, dtNow);

        PeriodFormatter formatter = new PeriodFormatterBuilder()
                .appendDays().appendSuffix(" T. ")
                .appendHours().appendSuffix(" Std. ")
                .appendMinutes().appendSuffix(" Min. ")
                .appendSeconds().appendSuffix(" Sek. ")
                .printZeroNever()
                .toFormatter();

        return formatter.print(period);
    }
}
