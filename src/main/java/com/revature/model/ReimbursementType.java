package com.revature.model;

import java.util.Objects;

public class ReimbursementType {
    private int typeId;
    private String reimbursementType;

    public ReimbursementType() {
    }

    public ReimbursementType(int typeId, String reimbursementType) {
        this.typeId = typeId;
        this.reimbursementType = reimbursementType;
    }

    public int getTypeId() {
        return typeId;
    }

    public void setTypeId(int typeId) {
        this.typeId = typeId;
    }

    public String getReimbursementType() {
        return reimbursementType;
    }

    public void setReimbursementType(String reimbursementType) {
        this.reimbursementType = reimbursementType;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ReimbursementType that = (ReimbursementType) o;
        return typeId == that.typeId && Objects.equals(reimbursementType, that.reimbursementType);
    }

    @Override
    public int hashCode() {
        return Objects.hash(typeId, reimbursementType);
    }

    @Override
    public String toString() {
        return "ReimbursementType{" +
                "typeId=" + typeId +
                ", reimbursementType='" + reimbursementType + '\'' +
                '}';
    }
}
