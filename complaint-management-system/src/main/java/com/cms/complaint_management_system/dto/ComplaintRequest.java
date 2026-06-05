package com.cms.complaint_management_system.dto;

public class ComplaintRequest {

    private String title;
    private String description;
    private String category;
    private Long userId;        // ← ADD THIS

    public ComplaintRequest() {
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public Long getUserId() {          // ← ADD THIS
        return userId;
    }

    public void setUserId(Long userId) { // ← ADD THIS
        this.userId = userId;
    }
}