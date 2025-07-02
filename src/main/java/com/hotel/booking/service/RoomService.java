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

    public Room createRoom(Room room) {
        Room lastRoom = roomRepository.findTopByOrderByRoomIdDesc();
        int nextId = (lastRoom != null) ? lastRoom.getRoomId() + 1 : 1;
        room.setRoomId(nextId);
        return roomRepository.save(room);
    }

    public boolean deleteRoom(int roomId) {
        Room room = roomRepository.findByRoomId(roomId);
        if (room != null) {
            roomRepository.delete(room);
            return true;
        }
        return false;
    }

    public Room getRoomById(int roomId) {
        return roomRepository.findByRoomId(roomId);
    }

    public List<Room> getAllRooms() {
        return roomRepository.findAll();
    }

    public Room updateRoom(int roomId, Room newData) {
        Room existing = roomRepository.findByRoomId(roomId);
        if (existing != null) {
            newData.setId(existing.getId());
            newData.setRoomId(roomId);
            return roomRepository.save(newData);
        }
        return null;
    }

    public List<Room> getRoomsByCategory(String category) {
        return roomRepository.findByCategory(category);
    }

}
