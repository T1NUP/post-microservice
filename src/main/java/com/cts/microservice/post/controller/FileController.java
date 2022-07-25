package com.cts.microservice.post.controller;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.cts.microservice.post.model.DBFile;
import com.cts.microservice.post.model.FileUploadResponse;
import com.cts.microservice.post.model.Post;
import com.cts.microservice.post.service.MediaService;
import com.cts.microservice.post.service.PostService;
//import com.cts.restfulwebservices.post.Post;

@RestController
@CrossOrigin
public class FileController {

	@Autowired
	private MediaService mediaService;
	
	@Autowired
	private PostService postService;
	
	/**
	*Upload post API 
	*@author Punit
	*@Note does Post entity upload with null description to be able to map url
	*/
    
    @PostMapping("/post/uploadPost/{username}")
    public FileUploadResponse uploadPost( @PathVariable String username, @RequestBody MultipartFile file) {
    	
        DBFile dbFile = mediaService.storeFile(file);
        
        Date timeStamp= new Date();
        
        Post picPost= new Post();
        picPost.setUsername(username);
        picPost.setTargetDate(timeStamp);

        String fileDownloadUri = ServletUriComponentsBuilder.fromCurrentContextPath()
                .path("/post/picpost/")
                .path(timeStamp.toString().replaceAll(" ", ""))
                .toUriString();
        System.out.println("This user: " + username);
        System.out.println("File Pic URL: " + fileDownloadUri);

//        DBFile toDelete =  dbFileRepository.findByFileURL(fileDownloadUri);
//
//        if(toDelete != null){
//            dbFileRepository.deleteById(toDelete.getId());
//        }

        dbFile.setFileURL(fileDownloadUri);

        mediaService.save(dbFile);
        System.out.println(dbFile);


//        Profile updated = jwtInMemoryUserDetailsService.assignAvatar(username, dbFile);
        picPost.setPostImage(dbFile.getFileURL());
        postService.savePost(picPost);


        return new FileUploadResponse(dbFile.getFileName(), fileDownloadUri,
                file.getContentType(), file.getSize());
    }
    
    /**
	*For sending src of image to ui
	*@author Punit
	*/
    
    @GetMapping("/post/picpost/{endpoint}")
    public ResponseEntity<Resource> downloadPostFile(@PathVariable String endpoint) {
        // Load file from database
//        DBFile dbFile = DBFileStorageService.getAvatarByProfile(username);
        
//        DBFile test = dbFileRepository.findByFileURL("http://localhost:8080/post/MonJun2722:21:41IST2022");
    	DBFile file= mediaService.getPostImage(endpoint);

        return ResponseEntity.ok()
                .contentType(MediaType.parseMediaType(file.getFileType()))
                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + file.getFileName() + "\"")
                .body(new ByteArrayResource(file.getData()));
    }
}