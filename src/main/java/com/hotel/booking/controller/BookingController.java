package com.hotel.booking.controller;

import com.hotel.booking.model.Booking;
import com.hotel.booking.service.BookingService;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/bookings")
@CrossOrigin(origins = { "http://localhost:8080" })

public class BookingController {

    @Autowired
    private BookingService bookingService;

    // create booking
    @PostMapping
    public ResponseEntity<Booking> createBooking(@RequestBody Booking booking) {
        Booking created = bookingService.createBooking(booking);
        return new ResponseEntity<>(created, HttpStatus.CREATED);

    }

    // get all bookings
    @GetMapping("/all")
    public List<Booking> getAllBookings() {
        return bookingService.getAllBookings();
    }

    // get bookings by room id
    @GetMapping("/room/{roomId}")
    public List<Booking> getBookingsByRoomId(@PathVariable int roomId) {
        return bookingService.getBookingsById(roomId);
    }

    // get bookings by guest email
    @GetMapping("/guest/{guestEmail}")
    public List<Booking> getBookingsByGuestEmail(@PathVariable String guestEmail) {
        return bookingService.getBookingsByEmail(guestEmail);
    }

    // update booking
    @PutMapping("/{bookingId}")
    public ResponseEntity<Booking> updateBooking(@PathVariable String bookingId, @RequestBody Booking booking) {
        Booking updated = bookingService.updateBooking(bookingId, booking);
        if (updated != null) {
            return ResponseEntity.ok(updated);
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }

    // delete booking
    @DeleteMapping("/{bookingId}")
    public ResponseEntity<?> deleteBooking(@PathVariable String bookingId) {
        boolean deleted = bookingService.deleteBooking(bookingId);
        if (deleted) {
            return ResponseEntity.ok().body("Booking deleted");
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Booking not found");
        }
    }

    // update booking status
    @PutMapping("/{bookingId}/status")
    public ResponseEntity<Booking> updateBookingStatus(@PathVariable String bookingId,
            @RequestBody Map<String, String> body) {
        String status = body.get("status");
        Booking updated = bookingService.updateBookingStatus(bookingId, status);
        if (updated != null) {
            return ResponseEntity.ok(updated);
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }
}
