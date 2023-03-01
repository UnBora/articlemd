package com.example.ariclemanagement002.controller;

import com.example.ariclemanagement002.model.DBFile;
import com.example.ariclemanagement002.service.response.UploadFileResponse;
import com.example.ariclemanagement002.service.DBFileStorageService;

import org.springframework.core.io.ByteArrayResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;

import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.io.IOException;

@RestController
public class FileController {

    private final DBFileStorageService dbFileStorageService;

    public FileController(DBFileStorageService dbFileStorageService) {
        this.dbFileStorageService = dbFileStorageService;
    }

    @PostMapping("/uploadFile")
    public UploadFileResponse uploadFile(@RequestParam("file") MultipartFile file) {
        DBFile dbFile = dbFileStorageService.storeFile(file);

        String fileDownloadUri = ServletUriComponentsBuilder.fromCurrentContextPath()
                .path("/downloadFile/")
                .path(dbFile.getId())
                .toUriString();
        return new UploadFileResponse(dbFile.getFileName(), fileDownloadUri,
                file.getContentType(), file.getSize());
    }
    @PostMapping("/updateFile/{fileId}")
    public  DBFile updateFile(@RequestParam("file") MultipartFile file, @RequestParam String fileId) throws IOException {
        return dbFileStorageService.updateFile(fileId, file);
    }
    @GetMapping("/downloadFile/{fileId}")
    public ResponseEntity<byte[]> downloadFile(@PathVariable String fileId){
        DBFile dbFile = dbFileStorageService.getFile(fileId);

        return ResponseEntity.ok()
                .contentType(MediaType.parseMediaType(dbFile.getFileType()))
                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\""+ dbFile.getFileName()+ "\"")
                .body( new ByteArrayResource(dbFile.getData()).getByteArray());
    }

    @GetMapping("/viewImageById/{fileId}")
    public  ResponseEntity<byte[]> viewImage(@PathVariable String fileId){
        DBFile dbFile = dbFileStorageService.getFile(fileId);

        return ResponseEntity.ok()
                .contentType(MediaType.parseMediaType(dbFile.getFileType()))
                .header(HttpHeaders.ACCEPT, "attachment; filename=\""+ dbFile.getFileName()+ "\"")
                .body( dbFile.getData());
    }
}
