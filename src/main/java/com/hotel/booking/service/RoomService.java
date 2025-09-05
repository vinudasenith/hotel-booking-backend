package com.hotel.booking.service;

import com.hotel.booking.model.Room;
import com.hotel.booking.repository.RoomRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RoomService {

    @Autowired
    private RoomRepository roomRepository;

    // create room
    public Room createRoom(Room room) {
        return roomRepository.save(room);
    }

    // delete room
    public boolean deleteRoom(int roomId) {
        Room room = roomRepository.findByRoomId(roomId);
        if (room != null) {
            roomRepository.delete(room);
            return true;
        }
        return false;
    }

    // get room by id
    public Room getRoomById(int roomId) {
        return roomRepository.findByRoomId(roomId);
    }

    // get all rooms
    public List<Room> getAllRooms() {
        return roomRepository.findAll();
    }

    // update room
    public Room updateRoom(int roomId, Room newData) {
        Room existing = roomRepository.findByRoomId(roomId);
        if (existing != null) {
            newData.setRoomId(existing.getRoomId());
            newData.setRoomId(roomId);
            return roomRepository.save(newData);
        }
        return null;
    }

    // get rooms by category
    public List<Room> getRoomsByCategory(String category) {
        return roomRepository.findByCategory(category);
    }

}
