package com.cms.complaint_management_system.controller;

import com.cms.complaint_management_system.dto.ComplaintRequest;
import com.cms.complaint_management_system.entity.Complaint;
import com.cms.complaint_management_system.entity.ComplaintStatus;
import com.cms.complaint_management_system.service.ComplaintService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/complaints")
public class ComplaintController {

    @Autowired
    private ComplaintService complaintService;

    @PostMapping
    public String createComplaint(
            @RequestBody ComplaintRequest request) {

        return complaintService.createComplaint(request);
    }

    @GetMapping
    public List<Complaint> getAllComplaints() {
        return complaintService.getAllComplaints();
    }

    @PutMapping("/assign/{id}")
    public String assignComplaint(@PathVariable Long id) {
        return complaintService.assignComplaint(id);
    }

    @PutMapping("/{id}/status")
    public String updateStatus(
            @PathVariable Long id,
            @RequestParam ComplaintStatus status) {
        {

            return complaintService.updateStatus(id, status);
        }

    }
    @GetMapping("/user/{userId}")
    public List<Complaint> getComplaintsByUser(@PathVariable Long userId) {
        return complaintService.getComplaintsByUser(userId);
    }
    // In ComplaintController.java
    @GetMapping("/{id}")
    public Complaint getComplaintById(@PathVariable Long id) {
        return complaintService.getComplaintById(id);
    }
}