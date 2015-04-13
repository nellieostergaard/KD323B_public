package com.example.k3lara.lecture_3_examples.xmlparser;

/**
 * Created by K3LARA on 10/04/2015.
 */
public class ScheduleItem {
    private String startTime;
    private String endTime;
    private String date;
    private String course;
    private String moment;
    private String room;
    private String teacher;

    public String getDate() {
        return date;
    }
    public void setDate(String date) {
        this.date = date;
    }
    public String getMoment() {
        return moment;
    }
    public void setMoment(String moment) {
        this.moment = moment;
    }
    public String getStartTime() {
        return startTime;
    }
    public void setStartTime(String startTime) {
        this.startTime = startTime;
    }
    public String getEndTime() {
        return endTime;
    }
    public void setEndTime(String endTime) {
        this.endTime = endTime;
    }
    public String getCourse() {
        return course;
    }
    public void setCourse(String course) {
        this.course = course;
    }
    public String getRoom() {
        return room;
    }
    public void setRoom(String room) {
        this.room = room;
    }
    public void setTeacher(String teacher) {
        this.teacher = teacher;
    }
    public String getTeacher() {
        return teacher;
    }
}
