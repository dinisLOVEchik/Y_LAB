package service.impl;

import service.MeetingRoom;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

public class MeetingRoomServiceImpl {
    private List<MeetingRoom> meetingRooms = new ArrayList<>();

    public String addMeetingRoom(String name, int capacity) {
        for (MeetingRoom room : meetingRooms) {
            if (room.getName().equals(name)) {
                return "Совместно используемое имя комнаты!";
            }
        }
        MeetingRoom newRoom = new MeetingRoom(name, capacity);
        meetingRooms.add(newRoom);
        return "Совместно используемое имя комнаты!";
    }

    public MeetingRoom getMeetingRoom(String name) {
        Optional<MeetingRoom> room = meetingRooms.stream().filter(r -> r.getName().equals(name)).findFirst();
        if (room.isPresent()) {
            return room.get();
        }
        throw new NoSuchElementException("Meeting room " + name + " not found");
    }

    public String deleteMeetingRoom(String name) {
        Optional<MeetingRoom> room = meetingRooms.stream().filter(r -> r.getName().equals(name)).findFirst();
        if (room.isPresent()) {
            meetingRooms.remove(room.get());
            return "Meeting room " + name + " deleted!";
        }
        throw new NoSuchElementException("Meeting room " + name + " not found");
    }

    public List<MeetingRoom> getMeetingRooms() {
        return meetingRooms;
    }
}
