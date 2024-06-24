package service.impl;

import service.Booking;
import service.MeetingRoom;
import service.User;
import service.Workspace;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;
import java.util.stream.Collectors;

public class BookingServiceImpl {
    private List<Booking> bookings = new ArrayList<>();

    public Booking createBooking(User user, Workspace workspace, MeetingRoom room, LocalDateTime startDate, LocalDateTime endTime) throws Exception {
        for (Booking booking : bookings) {
            if (booking.getUser().getLogin().equals(user.getLogin()) && booking.getWorkspace().getName().equals(workspace.getName()) && booking.getRoom().getName().equals(room.getName()) && booking.getStartDate().equals(startDate) && booking.getEndTime().equals(endTime)) {
                throw new Exception("Эта бронь уже существует!");
            }
        }
        Booking newBooking = new Booking(user, workspace, room, startDate, endTime);
        bookings.add(newBooking);
        return newBooking;
    }

    public Booking getBooking(String login, String workspaceName, String roomName, LocalDateTime startDate) {
        Optional<Booking> booking = bookings.stream().filter(b -> b.getUser().getLogin().equals(login) && b.getWorkspace().getName().equals(workspaceName) && b.getRoom().getName().equals(roomName) && b.getStartDate().equals(startDate)).findFirst();
        if (booking.isPresent()) {
            return booking.get();
        }
        throw new NoSuchElementException("Бронирование не найдено!");
    }

    public String deleteBooking(String login, String workspaceName, String roomName, LocalDateTime startDate) {
        Optional<Booking> booking = bookings.stream().filter(b -> b.getUser().getLogin().equals(login) && b.getWorkspace().getName().equals(workspaceName) && b.getRoom().getName().equals(roomName) && b.getStartDate().equals(startDate)).findFirst();
        if (booking.isPresent()) {
            bookings.remove(booking.get());
            return "Бронирование отменено!";
        }
        throw new NoSuchElementException("Бронирование не найдено!");
    }

    public List<Booking> getAllBookings() {
        return bookings;
    }
    public List<Booking> getBookingsByUser(String login) {
        List<Booking> bookingList = new ArrayList<>();
        for (Booking booking : bookings){
            if (booking.getUser().getLogin().equals(login)){
                bookingList.add(booking);
            }
        }
        return bookingList;
    }
    public List<Booking> getBookingsByDate(LocalDateTime date) {
        return bookings.stream()
                .filter(booking -> booking.getStartDate().equals(date) || booking.getEndTime().equals(date))
                .collect(Collectors.toList());
    }
    public List<Booking> getBookingsByResource(Workspace workspace) {
        return bookings.stream()
                .filter(booking -> booking.getWorkspace().equals(workspace))
                .collect(Collectors.toList());
    }
    public List<Booking> getBookingsByResource(MeetingRoom meetingRoom) {
        return bookings.stream()
                .filter(booking -> booking.getRoom().equals(meetingRoom))
                .collect(Collectors.toList());
    }

    public void setBookings(List<Booking> bookings) {
        this.bookings = bookings;
    }
}
