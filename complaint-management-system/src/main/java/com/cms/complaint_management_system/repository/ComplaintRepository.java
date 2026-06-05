package com.cms.complaint_management_system.repository;

import com.cms.complaint_management_system.entity.Complaint;
import com.cms.complaint_management_system.entity.ComplaintStatus;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ComplaintRepository extends JpaRepository<Complaint, Long> {
    long countByStatus(ComplaintStatus status);
    List<Complaint> findByUserId(Long userId);
}