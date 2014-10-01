package com.github.ngyewch.aws.billing;

import java.math.BigInteger;
import java.util.Date;
import java.util.Map;

public class LineItem {
    private String invoiceId;
    private Long payerAccountId;
    private Long linkedAccountId;
    private String recordType;
    private BigInteger recordId;
    private String productName;
    private Long rateId;
    private Long subscriptionId;
    private Long pricingPlanId;
    private String usageType;
    private String operation;
    private String availabilityZone;
    private Boolean reservedInstance;
    private String itemDescription;
    private Date usageStartDate;
    private Date usageEndDate;
    private Double usageQuantity;
    private Double blendedRate;
    private Double blendedCost;
    private Double unBlendedRate;
    private Double unBlendedCost;
    private String resourceId;
    private Map<String, String> tags;

    public String getInvoiceId() {
        return invoiceId;
    }

    public void setInvoiceId(String invoiceId) {
        this.invoiceId = invoiceId;
    }

    public Long getPayerAccountId() {
        return payerAccountId;
    }

    public void setPayerAccountId(Long payerAccountId) {
        this.payerAccountId = payerAccountId;
    }

    public Long getLinkedAccountId() {
        return linkedAccountId;
    }

    public void setLinkedAccountId(Long linkedAccountId) {
        this.linkedAccountId = linkedAccountId;
    }

    public String getRecordType() {
        return recordType;
    }

    public void setRecordType(String recordType) {
        this.recordType = recordType;
    }

    public BigInteger getRecordId() {
        return recordId;
    }

    public void setRecordId(BigInteger recordId) {
        this.recordId = recordId;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public Long getRateId() {
        return rateId;
    }

    public void setRateId(Long rateId) {
        this.rateId = rateId;
    }

    public Long getSubscriptionId() {
        return subscriptionId;
    }

    public void setSubscriptionId(Long subscriptionId) {
        this.subscriptionId = subscriptionId;
    }

    public Long getPricingPlanId() {
        return pricingPlanId;
    }

    public void setPricingPlanId(Long pricingPlanId) {
        this.pricingPlanId = pricingPlanId;
    }

    public String getUsageType() {
        return usageType;
    }

    public void setUsageType(String usageType) {
        this.usageType = usageType;
    }

    public String getOperation() {
        return operation;
    }

    public void setOperation(String operation) {
        this.operation = operation;
    }

    public String getAvailabilityZone() {
        return availabilityZone;
    }

    public void setAvailabilityZone(String availabilityZone) {
        this.availabilityZone = availabilityZone;
    }

    public Boolean getReservedInstance() {
        return reservedInstance;
    }

    public void setReservedInstance(Boolean reservedInstance) {
        this.reservedInstance = reservedInstance;
    }

    public String getItemDescription() {
        return itemDescription;
    }

    public void setItemDescription(String itemDescription) {
        this.itemDescription = itemDescription;
    }

    public Date getUsageStartDate() {
        return usageStartDate;
    }

    public void setUsageStartDate(Date usageStartDate) {
        this.usageStartDate = usageStartDate;
    }

    public Date getUsageEndDate() {
        return usageEndDate;
    }

    public void setUsageEndDate(Date usageEndDate) {
        this.usageEndDate = usageEndDate;
    }

    public Double getUsageQuantity() {
        return usageQuantity;
    }

    public void setUsageQuantity(Double usageQuantity) {
        this.usageQuantity = usageQuantity;
    }

    public Double getBlendedRate() {
        return blendedRate;
    }

    public void setBlendedRate(Double blendedRate) {
        this.blendedRate = blendedRate;
    }

    public Double getBlendedCost() {
        return blendedCost;
    }

    public void setBlendedCost(Double blendedCost) {
        this.blendedCost = blendedCost;
    }

    public Double getUnBlendedRate() {
        return unBlendedRate;
    }

    public void setUnBlendedRate(Double unBlendedRate) {
        this.unBlendedRate = unBlendedRate;
    }

    public Double getUnBlendedCost() {
        return unBlendedCost;
    }

    public void setUnBlendedCost(Double unBlendedCost) {
        this.unBlendedCost = unBlendedCost;
    }

    public String getResourceId() {
        return resourceId;
    }

    public void setResourceId(String resourceId) {
        this.resourceId = resourceId;
    }

    public Map<String, String> getTags() {
        return tags;
    }

    public void setTags(Map<String, String> tags) {
        this.tags = tags;
    }
}
