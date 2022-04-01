package com.revature.model;

import java.util.Objects;

public class ReimbursementStatus {

    private int statusId;
    private String Status;

    public ReimbursementStatus() {
    }

    public ReimbursementStatus(int statusId, String status) {
        this.statusId = statusId;
        Status = status;
    }

    public int getStatusId() {
        return statusId;
    }

    public void setStatusId(int statusId) {
        this.statusId = statusId;
    }

    public String getStatus() {
        return Status;
    }

    public void setStatus(String status) {
        Status = status;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ReimbursementStatus that = (ReimbursementStatus) o;
        return statusId == that.statusId && Objects.equals(Status, that.Status);
    }

    @Override
    public int hashCode() {
        return Objects.hash(statusId, Status);
    }

    @Override
    public String toString() {
        return "ReimbursementStatus{" +
                "statusId=" + statusId +
                ", Status='" + Status + '\'' +
                '}';
    }
}
