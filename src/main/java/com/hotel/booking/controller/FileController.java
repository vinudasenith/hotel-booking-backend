package com.hotel.booking.controller;

import com.hotel.booking.service.StorageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/files")
@CrossOrigin(origins = { "http://localhost:8080", "hotel-booking-frontend-omega.vercel.app",
        "hotel-booking-frontend-git-master-vinudas-projects.vercel.app",
        "hotel-booking-frontend-dhs6wk2gy-vinudas-projects.vercel.app" })
public class FileController {

    @Autowired
    private StorageService storageService;

    @GetMapping("/uploads/{filename:.+}")
    public ResponseEntity<Resource> serveFile(@PathVariable String filename) {
        Resource file = storageService.loadAsResource(filename);
        return ResponseEntity.ok()
                .header(HttpHeaders.CONTENT_DISPOSITION, "inline; filename=\"" + file.getFilename() + "\"")
                .body(file);
    }
}
