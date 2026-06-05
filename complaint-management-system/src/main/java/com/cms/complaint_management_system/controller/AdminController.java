package com.cms.complaint_management_system.controller;

import com.cms.complaint_management_system.dto.DashboardDTO;
import com.cms.complaint_management_system.service.ComplaintService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/admin")
public class AdminController {

    @Autowired
    private ComplaintService complaintService;

    @GetMapping("/dashboard")
    public DashboardDTO getDashboard() {
        return complaintService.getDashboard();
    }
}