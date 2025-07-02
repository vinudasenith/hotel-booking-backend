package com.hotel.booking.repository;

import com.hotel.booking.model.Room;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

public interface RoomRepository extends MongoRepository<Room, String> {
    Room findTopByOrderByRoomIdDesc();

    Room findByRoomId(int roomId);

    List<Room> findByCategory(String category);

}
