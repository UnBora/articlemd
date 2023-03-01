package com.example.ariclemanagement002.service;

import com.example.ariclemanagement002.exception.FileStorageException;
import com.example.ariclemanagement002.exception.MyFileNotFoundException;
import com.example.ariclemanagement002.model.DBFile;
import com.example.ariclemanagement002.repository.DBFileRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.util.StringUtils;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;

@Service
public class DBFileStorageService {
    @Autowired
    private DBFileRepository dbFileRepository;

    public DBFile storeFile(MultipartFile file){

        String fileName = StringUtils.cleanPath(file.getOriginalFilename());
        try {
            // Check if the file's name contains invalid characters
            if (fileName.contains("..")){
                throw new FileStorageException("Sorry! Filename contains invalid path sequence " + fileName);
            }
            DBFile dbFile = new DBFile(fileName, file.getContentType(), file.getBytes());
            return dbFileRepository.save(dbFile);
        }catch (IOException ex){
            throw new FileStorageException("Could not store file " + fileName + ". Please try again!", ex);
        }
    }

    public DBFile getFile(String fileId){
        return  dbFileRepository.findById(fileId)
                .orElseThrow(() -> new MyFileNotFoundException("File not found with ID:: "+ fileId));
    }
    public Object deleteFileById(String id) {
        if (dbFileRepository.existsById(id)) {
            this.dbFileRepository.deleteById(id);
            return "The ID::" + id + " Delete Successful!";
        } else {
            return "The ID:: " + id + " Not found!";
        }
    }

    public DBFile updateFile(String id, MultipartFile file) throws IOException {
        DBFile newFile = dbFileRepository.findById(id).orElseThrow(null);
        if (dbFileRepository.existsById(id)){
            newFile.setData(file.getBytes());
            newFile.setFileType(newFile.getFileType());
            newFile.setFileName(file.getOriginalFilename());
            return dbFileRepository.save(newFile);
        }else {
            return new DBFile();
        }
    }

}
