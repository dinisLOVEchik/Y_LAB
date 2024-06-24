package service.test;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import service.Booking;
import service.MeetingRoom;
import service.User;
import service.Workspace;
import service.impl.BookingServiceImpl;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;

import static org.junit.jupiter.api.Assertions.*;

public class BookingServiceImplTest {

    BookingServiceImpl bookingService;
    @BeforeEach
    public void setUp() {
        bookingService = new BookingServiceImpl();
    }
    @Test
    public void testCreateBooking() throws Exception {
        User user = new User("SpiderMan", "Peter_Parker");
        Workspace workspace = new Workspace("Workspace1", 5); // Указываем емкость Workspace
        MeetingRoom room = new MeetingRoom("Room1", 10); // Указываем емкость MeetingRoom
        LocalDateTime startDate = LocalDateTime.of(2021, 10, 15, 9, 0);
        LocalDateTime endDate = LocalDateTime.of(2021, 10, 15, 10, 0);
        Booking newBooking = bookingService.createBooking(user, workspace, room, startDate, endDate);

        assertNotNull(newBooking);
        assertEquals(user, newBooking.getUser());
        assertEquals(workspace, newBooking.getWorkspace());
        assertEquals(room, newBooking.getRoom());
        assertEquals(startDate, newBooking.getStartDate());
        assertEquals(endDate, newBooking.getEndTime());
    }

    @Test
    public void testGetBooking() throws Exception {
        User user = new User("SpiderMan", "Peter_Parker");
        Workspace workspace = new Workspace("Workspace1", 5);
        MeetingRoom room = new MeetingRoom("Room1", 10);
        LocalDateTime startDate = LocalDateTime.of(2021, 10, 15, 9, 0);
        LocalDateTime endDate = LocalDateTime.of(2021, 10, 15, 10, 0);
        bookingService.createBooking(user, workspace, room, startDate, endDate);

        Booking foundBooking = bookingService.getBooking("SpiderMan", "Workspace1", "Room1", startDate);

        assertNotNull(foundBooking);
        assertEquals(user, foundBooking.getUser());
        assertEquals(workspace, foundBooking.getWorkspace());
        assertEquals(room, foundBooking.getRoom());
        assertEquals(startDate, foundBooking.getStartDate());
        assertEquals(endDate, foundBooking.getEndTime());
    }

    @Test
    public void testDeleteBooking() throws Exception {
        User user = new User("SpiderMan", "Peter_Parker");
        Workspace workspace = new Workspace("Workspace1", 5);
        MeetingRoom room = new MeetingRoom("Room1", 10);
        LocalDateTime startDate = LocalDateTime.of(2021, 10, 15, 9, 0);
        LocalDateTime endDate = LocalDateTime.of(2021, 10, 15, 10, 0);
        bookingService.createBooking(user, workspace, room, startDate, endDate);

        String result = bookingService.deleteBooking("SpiderMan", "Workspace1", "Room1", startDate);

        assertEquals("Бронирование отменено!", result);
        assertThrows(NoSuchElementException.class, () -> bookingService.getBooking("SpiderMan", "Workspace1", "Room1", startDate));
    }
    @Test
    public void testGetAllBookings() throws Exception {
        User user1 = new User("SpiderMan", "Peter_Parker");
        Workspace workspace = new Workspace("Workspace1", 5);
        MeetingRoom room = new MeetingRoom("Room1", 10);
        LocalDateTime startDate1 = LocalDateTime.of(2021, 10, 15, 9, 0);
        LocalDateTime endDate1 = LocalDateTime.of(2021, 10, 15, 10, 0);
        bookingService.createBooking(user1, workspace, room, startDate1, endDate1);

        User user2 = new User("SpiderGirl", "Mary_Jane");
        LocalDateTime startDate2 = LocalDateTime.of(2021, 10, 20, 13, 0);
        LocalDateTime endDate2 = LocalDateTime.of(2021, 10, 20, 14, 0);
        bookingService.createBooking(user2, workspace, room, startDate2, endDate2);

        List<Booking> allBookings = bookingService.getAllBookings();

        assertEquals(2, allBookings.size());
        assertTrue(allBookings.stream()
                .anyMatch(booking -> booking.getUser().getLogin().equals("SpiderMan")));
        assertTrue(allBookings.stream()
                .anyMatch(booking -> booking.getUser().getLogin().equals("SpiderGirl")));
    }
    @Test
    public void testGetBookingsByDate() {
        LocalDateTime date = LocalDateTime.now();
        Workspace workspace = new Workspace("Workspace1", 5);
        MeetingRoom room = new MeetingRoom("Room1", 10);

        Booking booking1 = new Booking(new User("SpiderMan", "Peter_Parker"), workspace, room, date, date.plusHours(1));
        Booking booking2 = new Booking(new User("SpiderGirl", "Mary_Jane"), workspace, room, date.minusDays(1), date.minusDays(1).plusHours(2));

        List<Booking> bookings = new ArrayList<>();
        bookings.add(booking1);
        bookings.add(booking2);

        bookingService.setBookings(bookings);

        List<Booking> result = bookingService.getBookingsByDate(date);

        assertEquals(1, result.size());
        assertEquals(booking1, result.get(0));
    }

    @Test
    public void testGetBookingsByResourceWorkspace() {
        Workspace workspace = new Workspace("Workspace1", 5);
        MeetingRoom room = new MeetingRoom("Room1", 10);
        LocalDateTime date = LocalDateTime.now();

        Booking booking1 = new Booking(new User("SpiderMan", "Peter_Parker"), workspace, room, date, date.plusHours(1));
        Booking booking2 = new Booking(new User("SpiderGirl", "Mary_Jane"), workspace, room, date.minusDays(1), date.minusDays(1).plusHours(2));

        List<Booking> bookings = new ArrayList<>();
        bookings.add(booking1);
        bookings.add(booking2);

        bookingService.setBookings(bookings);

        List<Booking> result = bookingService.getBookingsByResource(workspace);

        assertEquals(2, result.size());
    }

    @Test
    public void testGetBookingsByResourceMeetingRoom() {
        Workspace workspace = new Workspace("Workspace1", 5);
        MeetingRoom room = new MeetingRoom("Room1", 10);
        LocalDateTime date = LocalDateTime.now();

        Booking booking1 = new Booking(new User("SpiderMan", "Peter_Parker"), workspace, room, date, date.plusHours(1));
        Booking booking2 = new Booking(new User("SpiderGirl", "Mary_Jane"), workspace, room, date.minusDays(1), date.minusDays(1).plusHours(2));

        List<Booking> bookings = new ArrayList<>();
        bookings.add(booking1);
        bookings.add(booking2);

        bookingService.setBookings(bookings);

        List<Booking> result = bookingService.getBookingsByResource(room);

        assertEquals(2, result.size());
    }
}

