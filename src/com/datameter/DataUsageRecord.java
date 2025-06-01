package com.datameter;

// Represents a single record of data usage for a mobile number
public class DataUsageRecord {
    private String mobileNumber;
    private String tower;
    private int data4G;
    private int data5G;
    private boolean isRoaming;

    // Constructor to initialize all fields
    public DataUsageRecord(String mobileNumber, String tower, int data4g, int data5g, boolean isRoaming) {
        this.mobileNumber = mobileNumber;
        this.tower = tower;
        this.data4G = data4g;
        this.data5G = data5g;
        this.isRoaming = isRoaming;
    }

    // Parses a line from the input file into a DataUsageRecord object
    public static DataUsageRecord parse(String line) throws MalformedLineException {
        String[] parts = line.split("\\|");
        if (parts.length != 5) {
            throw new MalformedLineException("Incorrect number of fields: " + line);
        }
        String mobileNumber = parts[0].trim();
        // Validate mobile number format (should be 10 digits)
        if (!mobileNumber.matches("\\d{10}")) {
            throw new MalformedLineException("Invalid mobile number: " + mobileNumber);
        }
        String tower = parts[1].trim();
        int data4g, data5g;
        try {
            // Parse data usage values
            data4g = Integer.parseInt(parts[2].trim());
            data5g = Integer.parseInt(parts[3].trim());
        } catch (NumberFormatException e) {
            throw new MalformedLineException("Invalid data usage: " + line);
        }
        String roamingStr = parts[4].trim();
        boolean isRoaming;
        // Parse roaming flag
        if (roamingStr.equalsIgnoreCase("Yes")) {
            isRoaming = true;
        } else if (roamingStr.equalsIgnoreCase("No")) {
            isRoaming = false;
        } else {
            throw new MalformedLineException("Invalid roaming flag: " + roamingStr);
        }
        return new DataUsageRecord(mobileNumber, tower, data4g, data5g, isRoaming);
    }

    // Getters for each field
    public String getMobileNumber() {
        return mobileNumber;
    }

    public int getData4G() {
        return data4G;
    }

    public int getData5G() {
        return data5G;
    }

    public boolean isRoaming() {
        return isRoaming;
    }
}