package com.hotel.booking.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Document(collection = "bookings")

public class Booking {

    @Id
    private String bookingId;

    // room info
    private int roomId;
    private String checkInDate;
    private String checkOutDate;

    // guest info
    private String guestName;
    private String guestEmail;
    private String guestPhone;

    // booking info
    private double totalAmount = 0;

    // booking info
    private String status = "pending";
    private String notes = "";

}
