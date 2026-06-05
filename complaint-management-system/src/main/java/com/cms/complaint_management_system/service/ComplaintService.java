package com.cms.complaint_management_system.service;

import com.cms.complaint_management_system.dto.ComplaintRequest;
import com.cms.complaint_management_system.dto.DashboardDTO;
import com.cms.complaint_management_system.entity.Complaint;
import com.cms.complaint_management_system.entity.ComplaintStatus;
import com.cms.complaint_management_system.entity.Notification;
import com.cms.complaint_management_system.entity.User;
import com.cms.complaint_management_system.repository.ComplaintRepository;
import com.cms.complaint_management_system.repository.NotificationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.cms.complaint_management_system.repository.UserRepository;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class ComplaintService {


    @Autowired
    private ComplaintRepository complaintRepository;

    @Autowired
    private NotificationRepository notificationRepository;

    @Autowired
    private UserRepository userRepository;

    public List<Complaint> getAllComplaints() {
        return complaintRepository.findAll();
    }

    public String createComplaint(ComplaintRequest request) {

        Complaint complaint = new Complaint();

        complaint.setTitle(request.getTitle());
        complaint.setDescription(request.getDescription());
        complaint.setCategory(request.getCategory());
        complaint.setStatus(ComplaintStatus.OPEN);
        complaint.setAssignedTeam("NOT_ASSIGNED");
        complaint.setCreatedAt(LocalDateTime.now());

        // ← ADD THESE 3 LINES
        User user = userRepository.findById(request.getUserId())
                .orElseThrow(() -> new com.cms.complaint_management_system.exception.ResourceNotFoundException("User not found with id: " + request.getUserId()));
        complaint.setUser(user);

        complaintRepository.save(complaint);

        return "Complaint Raised Successfully";
    }
    public String assignComplaint(Long complaintId) {

        Complaint complaint =
                complaintRepository.findById(complaintId)
                        .orElseThrow(() -> new com.cms.complaint_management_system.exception.ResourceNotFoundException("Complaint not found with id: " + complaintId));

        complaint.setAssignedTeam("MAINTENANCE_TEAM");

        complaintRepository.save(complaint);

        return "Complaint Assigned";
    }

    public String updateStatus(Long id, ComplaintStatus status) {

        Complaint complaint =
                complaintRepository.findById(id)
                        .orElseThrow(() -> new com.cms.complaint_management_system.exception.ResourceNotFoundException("Complaint not found with id: " + id));

        complaint.setStatus(status);

        complaintRepository.save(complaint);

        if (status == ComplaintStatus.RESOLVED) {

            Notification notification = new Notification();

            notification.setMessage(
                    "Your complaint #" + complaint.getId()
                            + " has been resolved."
            );

            notification.setCreatedAt(LocalDateTime.now());

            notificationRepository.save(notification);
        }

        return "Status Updated";
    }public DashboardDTO getDashboard() {

        long total      = complaintRepository.count();
        long open       = complaintRepository.countByStatus(ComplaintStatus.OPEN);
        long resolved   = complaintRepository.countByStatus(ComplaintStatus.RESOLVED);
        long inProgress = complaintRepository.countByStatus(ComplaintStatus.IN_PROGRESS);

        return new DashboardDTO(total, open, resolved, inProgress);
    }
    public List<Complaint> getComplaintsByUser(Long userId) {
        return complaintRepository.findByUserId(userId);
    }
    // In ComplaintService.java
    public Complaint getComplaintById(Long id) {
        return complaintRepository.findById(id)
                .orElseThrow(() -> new com.cms.complaint_management_system.exception.ResourceNotFoundException(
                        "Complaint not found with id: " + id));
    }
}