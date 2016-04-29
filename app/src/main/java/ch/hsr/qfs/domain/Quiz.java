package ch.hsr.qfs.domain;

import java.util.Date;
import org.joda.time.*;
import org.joda.time.format.*;

public class Quiz {

    private enum Status {Open, Finished, Canceled, WaitingForChallenger, WaitingForOpponent}

    private String _id;
    private User _challengerId;
    private User _opponentId;
    private Status status;
    private String createdAt;
    private String updatedAt;

    public String getId() {
        return this._id;
    }

    public void setId(String id) {
        this._id = _id;
    }

    public User get_challengerId() {
        return this._challengerId;
    }

    public void set_challengerId(String id) {
        this._challengerId = _challengerId;
    }

    public User get_opponentId() {
        return this._opponentId;
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
        LocalDateTime dtNow = new LocalDateTime().minusHours(2);

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
