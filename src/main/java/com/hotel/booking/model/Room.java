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
    private int roomId;
    private String category;
    private int maxGuests;
    private double price;
    private boolean available = true;
    private String specialDescription = "";
    private List<String> imageUrls = new ArrayList<>();

}
