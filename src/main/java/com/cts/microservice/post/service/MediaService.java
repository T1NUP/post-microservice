//package com.cts.microservice.post.service;
//
//import java.io.IOException;
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Service;
//import org.springframework.util.StringUtils;
//import org.springframework.web.multipart.MultipartFile;
//import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
//
//import com.cts.microservice.post.model.DBFile;
//import com.cts.microservice.post.repository.MediaRepository;
////import com.cts.restfulwebservices.Exception.FileStorageException;
////import com.cts.restfulwebservices.Exception.MyFileNotFoundException;
//
//
//@Service
//public class MediaService  {
//
//    @Autowired
//    private MediaRepository mRepository;
//    
//    public void save(DBFile file) {
//    	mRepository.save(file);
//    }
//
//    public DBFile storeFile(MultipartFile file) {
//        // Normalize file name
//        String fileName = StringUtils.cleanPath(file.getOriginalFilename());
//
//        try {
//            // Check if the file's name contains invalid characters
//            if(fileName.contains("..")) {
////                throw new FileStorageException("Sorry! Filename contains invalid path sequence " + fileName);
//            	System.out.println("Sorry! Filename contains invalid path sequence");return null;
//            }
//            //DBFile exists = dbFileRepository.findByUsername(dbfile.getUsername());
//            DBFile dbFile = new DBFile(fileName, file.getContentType(), file.getBytes());
////            if(exists == null) {
////                dbFile.setUsername(dbfile.getUsername());
////            }
//
//            return mRepository.save(dbFile);
//        } catch (IOException ex) {
////            throw new FileStorageException("Could not store file " + fileName + ". Please try again!", ex);
//        	System.out.println("Could not store file " + fileName + ". Please try again!");return null;
//        }
//    }
//    
//    /**
//	*Utility for retriving image 
//	*@author Punit
//	*image contains differentiator timestamp
//	*/
//    public DBFile getPostImage(String image) {
////    	String URI= "http://localhost:8080/picpost/"+image;
//    	String URI= ServletUriComponentsBuilder.fromCurrentContextPath()
//    			.path("/post/picpost/")
//    			.path(image)
//    			.toUriString();
//    	DBFile postFile= mRepository.findByFileURL(URI);
//    	return postFile;
//    }
//
//    public DBFile getAvatarByProfile(String name) {
////    	Profile profile = profileRepository.findByUsername(name);
//        String URI = ServletUriComponentsBuilder.fromCurrentContextPath()
//                .path("/avatar/")
//                .path(name)
//                .toUriString();
//        DBFile test = mRepository.findByFileURL(URI);
//
//        try {
//            System.out.println("--------------------------------------" + URI);
//    		return test;
//    	}
//    	catch(Exception e) {
//            System.out.println("------------------------------------------");
//            System.out.println();
//            System.out.println(e.toString());
//        }
//
//        return test;
//
//    }
//    
//    public DBFile getBackgroundByProfile(String name) {
////    	Profile profile = profileRepository.findByUsername(name);
//        String URI = ServletUriComponentsBuilder.fromCurrentContextPath()
//                .path("/background/")
//                .path(name)
//                .toUriString();
//        DBFile test = mRepository.findByFileURL(URI);
//    	try {
//    		return test;
//    	}
//    	catch(Exception e) {
//            System.out.println("------------------------------------------");
//            System.out.println(e.toString());
//    	}
//
//    	return test;
//    }
//    
//    public DBFile getFile(String fileId) {
//        return mRepository.findById(fileId).get();
////                .orElseThrow(() -> new MyFileNotFoundException("File not found with id " + fileId));
//    }
//}