package service;

import java.time.LocalDateTime;

import java.time.LocalDateTime;

public class Booking {
    private User user;
    private Workspace workspace;
    private MeetingRoom room;
    private LocalDateTime startDate;
    private LocalDateTime endTime;

    public Booking(User user, Workspace workspace, MeetingRoom room, LocalDateTime startDate, LocalDateTime endTime) {
        this.user = user;
        this.workspace = workspace;
        this.room = room;
        this.startDate = startDate;
        this.endTime = endTime;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Workspace getWorkspace() {
        return workspace;
    }

    public void setWorkspace(Workspace workspace) {
        this.workspace = workspace;
    }

    public MeetingRoom getRoom() {
        return room;
    }

    public void setRoom(MeetingRoom room) {
        this.room = room;
    }

    public LocalDateTime getStartDate() {
        return startDate;
    }

    public void setStartDate(LocalDateTime startDate) {
        this.startDate = startDate;
    }

    public LocalDateTime getEndTime() {
        return endTime;
    }

    public void setEndTime(LocalDateTime endTime) {
        this.endTime = endTime;
    }
}