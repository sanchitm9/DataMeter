package com.datameter;

// Aggregates data usage for a single mobile number
public class MobileUsageAggregator {
    // The mobile number this aggregator is for
    private String mobileNumber;
    // Data usage in bytes for each category
    private long data4G;
    private long data5G;
    private long data4GRoaming;
    private long data5GRoaming;

    // Initialize aggregator for a specific mobile number
    public MobileUsageAggregator(String mobileNumber) {
        this.mobileNumber = mobileNumber;
    }

    // Add a usage record to the aggregator
    public void addUsage(DataUsageRecord record) {
        if (record.isRoaming()) {
            // If roaming, add to roaming counters
            data4GRoaming += record.getData4G();
            data5GRoaming += record.getData5G();
        } else {
            // Otherwise, add to regular counters
            data4G += record.getData4G();
            data5G += record.getData5G();
        }
    }

    // Calculate the total cost for this mobile number using the provided rate card
    public double calculateCost(RateCard rateCard) {
        double cost = 0;
        cost += data4G * rateCard.rate4G;
        cost += data5G * rateCard.rate5G;
        cost += data4GRoaming * rateCard.rate4GRoaming;
        cost += data5GRoaming * rateCard.rate5GRoaming;
        long totalUsage = data4G + data5G + data4GRoaming + data5GRoaming;
        // Apply surcharge if usage exceeds threshold
        if (totalUsage > rateCard.usageThreshold) {
            cost *= (1 + rateCard.surcharge);
        }
        return Math.round(cost); // as per sample output, round to nearest integer
    }

    // Getters for reporting
    public String getMobileNumber() {
        return mobileNumber;
    }

    public long getData4G() {
        return data4G;
    }

    public long getData5G() {
        return data5G;
    }

    public long getData4GRoaming() {
        return data4GRoaming;
    }

    public long getData5GRoaming() {
        return data5GRoaming;
    }
}