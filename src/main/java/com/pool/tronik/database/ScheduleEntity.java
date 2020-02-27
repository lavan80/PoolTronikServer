package com.pool.tronik.database;

import org.hibernate.annotations.Proxy;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "timing")
@Proxy(lazy = false) // FIX IT !!!!  lazyinitializationexception no session
public class ScheduleEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", columnDefinition = "int")
    private long id;
    private int relay;
    private int status;// off, on, remove
    @Column(name="startdate")
    private String startDate;
    @Column(name="nextdate")
    private String nextDate;
    private int duration;
    private int iteration;
    private int iterated;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public int getRelay() {
        return relay;
    }

    public void setRelay(int relay) {
        this.relay = relay;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public int getIteration() {
        return iteration;
    }

    public void setIteration(int iteration) {
        this.iteration = iteration;
    }

    public int getDuration() {
        return duration;
    }

    public void setDuration(int duration) {
        this.duration = duration;
    }

    public String getStartDate() {
        return startDate;
    }

    public void setStartDate(String startDate) {
        this.startDate = startDate;
    }

    public String getNextDate() {
        return nextDate;
    }

    public void setNextDate(String nextDate) {
        this.nextDate = nextDate;
    }

    public int getIterated() {
        return iterated;
    }

    public void setIterated(int iterated) {
        this.iterated = iterated;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ScheduleEntity that = (ScheduleEntity) o;
        return id == that.id &&
                relay == that.relay &&
                status == that.status &&
                duration == that.duration &&
                iteration == that.iteration &&
                iterated == that.iterated &&
                startDate.equals(that.startDate) &&
                nextDate.equals(that.nextDate);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, relay, status, startDate, nextDate, duration, iteration, iterated);
    }

    @Override
    public String toString() {
        return "ScheduleEntity{" +
                "id=" + id +
                ", relay=" + relay +
                ", status=" + status +
                ", startDate='" + startDate + '\'' +
                ", nextDate='" + nextDate + '\'' +
                ", duration=" + duration +
                ", iteration=" + iteration +
                '}';
    }
}
