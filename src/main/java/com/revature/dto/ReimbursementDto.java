package com.revature.dto;

import java.io.InputStream;
import java.sql.Timestamp;
import java.util.Objects;

public class ReimbursementDto {
    private int reImbID;
    private double reAmount;
    private String reDescription;
    private int reAuthorID;
    private String reAuthor;
    private String reResolver;
    private String reStatus;
    private String reType;
    private String receiptURL;
    private String submittedDate;
    private String resolvedDate;
    public ReimbursementDto() {
    }

    public ReimbursementDto(int reImbID, double reAmount, String reDescription, int reAuthorID, String reAuthor, String reResolver, String reStatus, String reType, String receiptURL, String submittedDate, String resolvedDate) {
        this.reImbID = reImbID;
        this.reAmount = reAmount;
        this.reDescription = reDescription;
        this.reAuthorID = reAuthorID;
        this.reAuthor = reAuthor;
        this.reResolver = reResolver;
        this.reStatus = reStatus;
        this.reType = reType;
        this.receiptURL = receiptURL;
        this.submittedDate = submittedDate;
        this.resolvedDate = resolvedDate;
    }

    public int getReImbID() {
        return reImbID;
    }

    public void setReImbID(int reImbID) {
        this.reImbID = reImbID;
    }

    public double getReAmount() {
        return reAmount;
    }

    public void setReAmount(double reAmount) {
        this.reAmount = reAmount;
    }

    public String getReDescription() {
        return reDescription;
    }

    public void setReDescription(String reDescription) {
        this.reDescription = reDescription;
    }

    public int getReAuthorID() {
        return reAuthorID;
    }

    public void setReAuthorID(int reAuthorID) {
        this.reAuthorID = reAuthorID;
    }

    public String getReAuthor() {
        return reAuthor;
    }

    public void setReAuthor(String reAuthor) {
        this.reAuthor = reAuthor;
    }

    public String getReResolver() {
        return reResolver;
    }

    public void setReResolver(String reResolver) {
        this.reResolver = reResolver;
    }

    public String getReStatus() {
        return reStatus;
    }

    public void setReStatus(String reStatus) {
        this.reStatus = reStatus;
    }

    public String getReType() {
        return reType;
    }

    public void setReType(String reType) {
        this.reType = reType;
    }

    public String getReceiptURL() {
        return receiptURL;
    }

    public void setReceiptURL(String receiptURL) {
        this.receiptURL = receiptURL;
    }

    public String getSubmittedDate() {
        return submittedDate;
    }

    public void setSubmittedDate(String submittedDate) {
        this.submittedDate = submittedDate;
    }

    public String getResolvedDate() {
        return resolvedDate;
    }

    public void setResolvedDate(String resolvedDate) {
        this.resolvedDate = resolvedDate;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ReimbursementDto that = (ReimbursementDto) o;
        return reImbID == that.reImbID && Double.compare(that.reAmount, reAmount) == 0 && reAuthorID == that.reAuthorID && Objects.equals(reDescription, that.reDescription) && Objects.equals(reAuthor, that.reAuthor) && Objects.equals(reResolver, that.reResolver) && Objects.equals(reStatus, that.reStatus) && Objects.equals(reType, that.reType) && Objects.equals(receiptURL, that.receiptURL) && Objects.equals(submittedDate, that.submittedDate) && Objects.equals(resolvedDate, that.resolvedDate);
    }

    @Override
    public int hashCode() {
        return Objects.hash(reImbID, reAmount, reDescription, reAuthorID, reAuthor, reResolver, reStatus, reType, receiptURL, submittedDate, resolvedDate);
    }

    @Override
    public String toString() {
        return "ReimbursementDto{" +
                "reImbID=" + reImbID +
                ", reAmount=" + reAmount +
                ", reDescription='" + reDescription + '\'' +
                ", reAuthorID=" + reAuthorID +
                ", reAuthor='" + reAuthor + '\'' +
                ", reResolver='" + reResolver + '\'' +
                ", reStatus='" + reStatus + '\'' +
                ", reType='" + reType + '\'' +
                ", receiptURL='" + receiptURL + '\'' +
                ", submittedDate='" + submittedDate + '\'' +
                ", resolvedDate='" + resolvedDate + '\'' +
                '}';
    }
}
