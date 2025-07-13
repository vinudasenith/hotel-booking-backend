package com.hotel.booking.controller;

import com.hotel.booking.model.Room;
import com.hotel.booking.service.UserService;
import com.hotel.booking.service.RoomService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;

import java.util.List;

@RestController
@RequestMapping("/api/rooms")
@CrossOrigin(origins = "http://localhost:8080")

public class RoomController {

    private static final String UPLOAD_DIR = "uploads/";

    @Autowired
    private RoomService roomService;

    @Autowired
    private UserService userService;

    @PostMapping("/upload-image")
    public ResponseEntity<String> uploadRoomImage(@RequestParam("file") MultipartFile file,
            @RequestHeader("email") String email) {
        if (!userService.isAdmin(email)) {
            return ResponseEntity.status(HttpStatus.FORBIDDEN).body("Forbidden");
        }
        try {
            File uploadDir = new File(UPLOAD_DIR);
            if (!uploadDir.exists())
                uploadDir.mkdirs();

            String fileName = System.currentTimeMillis() + "_" + file.getOriginalFilename();
            Path filePath = Paths.get(UPLOAD_DIR + fileName);
            Files.copy(file.getInputStream(), filePath, StandardCopyOption.REPLACE_EXISTING);

            String publicUrl = "/uploads/" + fileName;
            return ResponseEntity.ok(publicUrl);
        } catch (IOException e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Upload failed");
        }
    }

    @PostMapping
    public ResponseEntity<?> createRoom(@RequestBody Room room, @RequestHeader("email") String email) {
        if (!userService.isAdmin(email)) {
            return ResponseEntity.status(HttpStatus.FORBIDDEN).body("Forbidden");
        }
        try {
            Room createdRoom = roomService.createRoom(room);
            return ResponseEntity.ok(createdRoom);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Room creation failed");
        }
    }

    @DeleteMapping("/{roomId}")
    public ResponseEntity<?> deleteRoom(@PathVariable int roomId, @RequestHeader("email") String email) {
        if (!userService.isAdmin(email)) {
            return ResponseEntity.status(HttpStatus.FORBIDDEN).body("Forbidden");
        }

        boolean deleted = roomService.deleteRoom(roomId);
        if (deleted) {
            return ResponseEntity.ok("Room deleted successfully");
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Room not found");
        }
    }

    @GetMapping("/{roomId}")
    public ResponseEntity<?> getRoomById(@PathVariable int roomId) {
        Room room = roomService.getRoomById(roomId);
        if (room != null) {
            return ResponseEntity.ok(room);
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Room not found");
        }
    }

    @GetMapping("/all")
    public ResponseEntity<?> getAllRooms() {
        List<Room> rooms = roomService.getAllRooms();
        return ResponseEntity.ok(rooms);
    }

    @PutMapping("/{roomId}")
    public ResponseEntity<?> updateRoom(@PathVariable int roomId, @RequestBody Room newData,
            @RequestHeader("email") String email) {
        if (!userService.isAdmin(email)) {
            return ResponseEntity.status(HttpStatus.FORBIDDEN).body("Forbidden");
        }
        Room updatedRoom = roomService.updateRoom(roomId, newData);
        if (updatedRoom != null) {
            return ResponseEntity.ok(updatedRoom);
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Room not found");
        }
    }

    @GetMapping("/category/{category}")
    public ResponseEntity<?> getRoomsByCategory(@PathVariable String category) {
        List<Room> rooms = roomService.getRoomsByCategory(category);
        return ResponseEntity.ok(rooms);
    }

}
