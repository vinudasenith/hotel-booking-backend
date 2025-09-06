package com.hotel.booking.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Document(collection = "rooms")

public class Room {

    @Id

    // room id
    private int roomId;

    // room details
    private String category;
    private int maxGuests;
    private double price = 0;
    private boolean available = true;
    private String specialDescription = "";

    // room images
    private List<String> imageUrls = new ArrayList<>();

}
