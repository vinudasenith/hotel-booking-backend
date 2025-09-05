package com.hotel.booking.service;

import com.hotel.booking.model.Booking;
import com.hotel.booking.repository.BookingRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BookingService {

    @Autowired
    private BookingRepository bookingRepository;

    // create booking method
    public Booking createBooking(Booking booking) {
        Booking lastBooking = bookingRepository.findTopByOrderByBookingIdDesc();

        String nextId = "BOOKING-001";

        if (lastBooking != null && lastBooking.getBookingId() != null) {
            String lastId = lastBooking.getBookingId();
            int num = Integer.parseInt(lastId.substring(8));
            num++;
            nextId = String.format("BOOKING-%03d", num);
        }
        booking.setBookingId(nextId);
        return bookingRepository.save(booking);
    }

    // get all bookings
    public List<Booking> getAllBookings() {
        return bookingRepository.findAll();
    }

    // delete booking
    public boolean deleteBooking(String bookingId) {
        if (bookingRepository.existsById(bookingId)) {
            bookingRepository.deleteById(bookingId);
            return true;
        } else {
            return false;
        }
    }

    // update booking
    public Booking updateBooking(String bookingId, Booking newBookinhg) {
        if (bookingRepository.existsById(bookingId)) {
            newBookinhg.setBookingId(bookingId);
            return bookingRepository.save(newBookinhg);
        } else {
            return null;
        }
    }

    // get bookings by room id
    public List<Booking> getBookingsById(int roomId) {
        return bookingRepository.findByRoomId(roomId);
    }

    // get bookings by guest email
    public List<Booking> getBookingsByEmail(String guestEmail) {
        return bookingRepository.findByGuestEmail(guestEmail);
    }

    // update booking status
    public Booking updateBookingStatus(String bookingId, String status) {
        Booking booking = bookingRepository.findById(bookingId).orElse(null);
        if (booking != null) {
            booking.setStatus(status);
            return bookingRepository.save(booking);
        }
        return null;
    }

}
