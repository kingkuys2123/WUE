package com.appdev.wue.service;

import java.util.List;
import java.util.NoSuchElementException;

import com.appdev.wue.entity.UserEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.appdev.wue.entity.OrganizerEntity;
import com.appdev.wue.repository.OrganizerRepository;
import com.appdev.wue.repository.UserRepository;

@Service
public class OrganizerService {

    @Autowired
    private OrganizerRepository oRepo;

    @Autowired
    private UserRepository userRepo;

    // Create Organizer
    public OrganizerEntity createOrganizer(OrganizerEntity organizer) {
        return oRepo.save(organizer);
    }

    public OrganizerEntity createOrganizerWithUserId(OrganizerEntity organizer, int userid){
        organizer.setUser(userRepo.findById(userid).get());
        return oRepo.save(organizer);
    }

    // Get All Organizers
    public List<OrganizerEntity> getAllOrganizers() {
        return oRepo.findAll();
    }

    // Get Organizer by Id
    public OrganizerEntity getOrganizer(int id) {
        return oRepo.findById(id).orElseThrow(() -> new NoSuchElementException("Organizer with ID " + id + " not found!"));
    }

    // Update Organizer (PUT)
    public OrganizerEntity updateOrganizer(int id, OrganizerEntity newOrganizer) {
        UserEntity user = userRepo.findById(id).orElseThrow(() -> new NoSuchElementException("User with ID " + id + " not found!"));
        OrganizerEntity existingOrganizer;
        try {
            existingOrganizer = oRepo.findById(id).get();
            user.setAccountType("organizer");
            existingOrganizer.setApprovalStatus(newOrganizer.getApprovalStatus());
            existingOrganizer.setDateTimeApproved(newOrganizer.getDateTimeApproved());

            userRepo.save(user);
            return oRepo.save(existingOrganizer);
        } catch (Exception e) {
            throw new RuntimeException("Error updating organizer with ID " + id + ": " + e.getMessage(), e);
        }
    }

    // Delete Organizer
    public String deleteOrganizer(int id) {
        String msg;
        try {
            if (oRepo.existsById(id)) {
                oRepo.deleteById(id);
                userRepo.deleteById(id);
                msg = "Organizer deleted successfully";
            } else {
                msg = "Organizer with ID " + id + " not found.";
            }
        } catch (Exception e) {
            msg = "Error occurred while deleting organizer with ID " + id + ": " + e.getMessage();
        }
        return msg;
    }

    //Add Organizer
    public OrganizerEntity addOrganizer(OrganizerEntity organizer) {
        if (organizer.getApprovalStatus() == null || organizer.getApprovalStatus().isEmpty()) {
            organizer.setApprovalStatus("pending"); // Default status
        }
        
        if (organizer.getUser() == null) {
            throw new IllegalArgumentException("User cannot be null for an organizer.");
        }
    
        return oRepo.save(organizer);
    }

    public OrganizerEntity getOrganizerWithUser(int id) {
        try {
            return oRepo.findOrganizerWithUserByUserId(id);
        } catch (Exception e) {
            throw new RuntimeException("Error finding organizer with user ID " + id + ": " + e.getMessage(), e);
        }
    }

    public OrganizerEntity applyForOrganizer(OrganizerEntity organizer, int userId) {
        try {
            UserEntity user = userRepo.findById(userId).orElseThrow(() -> new NoSuchElementException("User with ID " + userId + " not found!"));

            organizer.setUser(user);
            organizer.setOrganizerId(userId);

            organizer.setApprovalStatus("Pending");

            return oRepo.save(organizer);
        } catch (Exception e) {
            throw new RuntimeException("Error applying for organizer: " + e.getMessage(), e);
        }
    }

    public List<OrganizerEntity> findApprovedOrganizers() {
        return oRepo.findApprovedOrganizers();
    }


}

