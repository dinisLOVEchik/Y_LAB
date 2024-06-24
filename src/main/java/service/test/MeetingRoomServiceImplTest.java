package service.test;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import service.MeetingRoom;
import service.impl.MeetingRoomServiceImpl;

import java.util.List;
import java.util.NoSuchElementException;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class MeetingRoomServiceImplTest {
    MeetingRoomServiceImpl meetingRoomService;
    @BeforeEach
    public void setUp() {
        meetingRoomService = new MeetingRoomServiceImpl();
    }
    @Test
    public void testAddMeetingRoom() {
        assertEquals("Комната успешно добавлена!", meetingRoomService.addMeetingRoom("Комната A", 10));

        List<MeetingRoom> meetingRooms = meetingRoomService.getMeetingRooms();
        assertEquals(1, meetingRooms.size());
        assertEquals("Комната A", meetingRooms.get(0).getName());
        assertEquals(10, meetingRooms.get(0).getCapacity());
    }
    @Test
    public void testGetMeetingRoom() {
        meetingRoomService.addMeetingRoom("Комната B", 15);

        MeetingRoom room = meetingRoomService.getMeetingRoom("Комната B");

        assertEquals("Комната B", room.getName());
        assertEquals(15, room.getCapacity());
    }
    @Test
    public void testGetMeetingRoomNotFound() {
        assertThrows(NoSuchElementException.class, () -> meetingRoomService.getMeetingRoom("Несуществующая комната"));
    }
    @Test
    public void testDeleteMeetingRoom() {
        meetingRoomService.addMeetingRoom("Комната C", 20);

        assertEquals("Комната удалена!", meetingRoomService.deleteMeetingRoom("Комната C"));

        List<MeetingRoom> meetingRooms = meetingRoomService.getMeetingRooms();
        assertEquals(0, meetingRooms.size());
    }
    @Test
    public void testDeleteMeetingRoomNotFound() {
        assertThrows(NoSuchElementException.class, () -> meetingRoomService.deleteMeetingRoom("Несуществующая комната"));
    }
}

