package com.revature.model;

import java.io.InputStream;
import java.sql.Timestamp;
import java.util.Date;
import java.util.Objects;

public class Reimbursement {

    private int reImbID;
    private double reAmount;
    private InputStream reReceipt;
    private String reDescription;
    private Timestamp reSubmittedDate;
    private Timestamp reResolvedDate;
    private int reAuthorID;
    private String reAuthor;
    private int reResolverID;
    private String reResolver;
    private int reStatusId;
    private String reStatus;
    private int reTypeId;
    private String reType;
    private String receiptURL;

    public Reimbursement() {
    }

    public Reimbursement(int reImbID, double reAmount, InputStream reReceipt, String reDescription, Timestamp reSubmittedDate, Timestamp reResolvedDate, int reAuthorID, String reAuthor, int reResolverID, String reResolver, int reStatusId, String reStatus, int reTypeId, String reType, String receiptURL) {
        this.reImbID = reImbID;
        this.reAmount = reAmount;
        this.reReceipt = reReceipt;
        this.reDescription = reDescription;
        this.reSubmittedDate = reSubmittedDate;
        this.reResolvedDate = reResolvedDate;
        this.reAuthorID = reAuthorID;
        this.reAuthor = reAuthor;
        this.reResolverID = reResolverID;
        this.reResolver = reResolver;
        this.reStatusId = reStatusId;
        this.reStatus = reStatus;
        this.reTypeId = reTypeId;
        this.reType = reType;
        this.receiptURL = receiptURL;
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

    public InputStream getReReceipt() {
        return reReceipt;
    }

    public void setReReceipt(InputStream reReceipt) {
        this.reReceipt = reReceipt;
    }

    public String getReDescription() {
        return reDescription;
    }

    public void setReDescription(String reDescription) {
        this.reDescription = reDescription;
    }

    public Timestamp getReSubmittedDate() {
        return reSubmittedDate;
    }

    public void setReSubmittedDate(Timestamp reSubmittedDate) {
        this.reSubmittedDate = reSubmittedDate;
    }

    public Timestamp getReResolvedDate() {
        return reResolvedDate;
    }

    public void setReResolvedDate(Timestamp reResolvedDate) {
        this.reResolvedDate = reResolvedDate;
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

    public int getReResolverID() {
        return reResolverID;
    }

    public void setReResolverID(int reResolverID) {
        this.reResolverID = reResolverID;
    }

    public String getReResolver() {
        return reResolver;
    }

    public void setReResolver(String reResolver) {
        this.reResolver = reResolver;
    }

    public int getReStatusId() {
        return reStatusId;
    }

    public void setReStatusId(int reStatusId) {
        this.reStatusId = reStatusId;
    }

    public String getReStatus() {
        return reStatus;
    }

    public void setReStatus(String reStatus) {
        this.reStatus = reStatus;
    }

    public int getReTypeId() {
        return reTypeId;
    }

    public void setReTypeId(int reTypeId) {
        this.reTypeId = reTypeId;
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Reimbursement that = (Reimbursement) o;
        return reImbID == that.reImbID && Double.compare(that.reAmount, reAmount) == 0 && reAuthorID == that.reAuthorID && reResolverID == that.reResolverID && reStatusId == that.reStatusId && reTypeId == that.reTypeId && Objects.equals(reReceipt, that.reReceipt) && Objects.equals(reDescription, that.reDescription) && Objects.equals(reSubmittedDate, that.reSubmittedDate) && Objects.equals(reResolvedDate, that.reResolvedDate) && Objects.equals(reAuthor, that.reAuthor) && Objects.equals(reResolver, that.reResolver) && Objects.equals(reStatus, that.reStatus) && Objects.equals(reType, that.reType) && Objects.equals(receiptURL, that.receiptURL);
    }

    @Override
    public int hashCode() {
        return Objects.hash(reImbID, reAmount, reReceipt, reDescription, reSubmittedDate, reResolvedDate, reAuthorID, reAuthor, reResolverID, reResolver, reStatusId, reStatus, reTypeId, reType, receiptURL);
    }

    @Override
    public String toString() {
        return "Reimbursement{" +
                "reImbID=" + reImbID +
                ", reAmount=" + reAmount +
                ", reReceipt=" + reReceipt +
                ", reDescription='" + reDescription + '\'' +
                ", reSubmittedDate=" + reSubmittedDate +
                ", reResolvedDate=" + reResolvedDate +
                ", reAuthorID=" + reAuthorID +
                ", reAuthor='" + reAuthor + '\'' +
                ", reResolverID=" + reResolverID +
                ", reResolver='" + reResolver + '\'' +
                ", reStatusId=" + reStatusId +
                ", reStatus='" + reStatus + '\'' +
                ", reTypeId=" + reTypeId +
                ", reType='" + reType + '\'' +
                ", receiptURL='" + receiptURL + '\'' +
                '}';
    }
}
