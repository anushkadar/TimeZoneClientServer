package org.me.dtd.entity;

import com.fasterxml.jackson.annotation.JsonFormat;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.OffsetDateTime;
import java.time.ZonedDateTime;
import java.util.Date;

@Entity
@Table(name = "date_tb")
public class Data implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @Column(name = "date_str")
    private String dateStr;
    @Column(name = "date")
    private Date date;
    @Column(name = "local_time")
    private LocalTime localTime;
    @Column(name = "local_Date")
    private LocalDate localDate;
    //forcing jackson to include timezone
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd'T'HH:mm:ss.SSS'Z'")
    @Column(name = "local_datetime_dt")
    private LocalDateTime localDateTimeDt;
    @Column(name = "local_datetime_ts")
    private LocalDateTime localDateTimeTs;
    @Column(name = "offset_datetime")
    private OffsetDateTime offsetDateTime;
    @Column(name = "zoned_datetime")
    private ZonedDateTime zonedDateTime;

    public Data() {
    }

    public Data(String dateStr) {
        this.dateStr = dateStr;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getDateStr() {
        return dateStr;
    }

    public void setDateStr(String dateStr) {
        this.dateStr = dateStr;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public LocalTime getLocalTime() {
        return localTime;
    }

    public void setLocalTime(LocalTime localTime) {
        this.localTime = localTime;
    }

    public LocalDate getLocalDate() {
        return localDate;
    }

    public void setLocalDate(LocalDate localDate) {
        this.localDate = localDate;
    }

    public LocalDateTime getLocalDateTimeDt() {
        return localDateTimeDt;
    }

    public void setLocalDateTimeDt(LocalDateTime localDateTimeDt) {
        this.localDateTimeDt = localDateTimeDt;
    }

    public LocalDateTime getLocalDateTimeTs() {
        return localDateTimeTs;
    }

    public void setLocalDateTimeTs(LocalDateTime localDateTimeTs) {
        this.localDateTimeTs = localDateTimeTs;
    }

    public OffsetDateTime getOffsetDateTime() {
        return offsetDateTime;
    }

    public void setOffsetDateTime(OffsetDateTime offsetDateTime) {
        this.offsetDateTime = offsetDateTime;
    }

    public ZonedDateTime getZonedDateTime() {
        return zonedDateTime;
    }

    public void setZonedDateTime(ZonedDateTime zonedDateTime) {
        this.zonedDateTime = zonedDateTime;
    }

    @Override
    public String toString() {
        return "\n id\t\t\t\t" + id + '\n' + " dateStr\t\t" + dateStr + '\n' + " date\t\t\t" + date + '\n'
                + " localTime\t\t" + localTime + '\n' + " localDate\t\t" + localDate + '\n' + " localDateTimeDt\t"
                + localDateTimeDt + '\n' + " localDateTimeTs\t" + localDateTimeTs + '\n' + " offsetDateTime\t"
                + offsetDateTime + '\n' + " zonedDateTime\t" + zonedDateTime;
    }
}
