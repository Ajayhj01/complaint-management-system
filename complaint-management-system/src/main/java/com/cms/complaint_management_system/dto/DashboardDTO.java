package com.cms.complaint_management_system.dto;

public class DashboardDTO {

    private long totalComplaints;
    private long openComplaints;
    private long resolvedComplaints;
    private long inProgressComplaints;

    public DashboardDTO(long total, long open, long resolved, long inProgress) {
        this.totalComplaints = total;
        this.openComplaints = open;
        this.resolvedComplaints = resolved;
        this.inProgressComplaints = inProgress;
    }

    public long getTotalComplaints() { return totalComplaints; }
    public long getOpenComplaints() { return openComplaints; }
    public long getResolvedComplaints() { return resolvedComplaints; }
    public long getInProgressComplaints() { return inProgressComplaints; }
}