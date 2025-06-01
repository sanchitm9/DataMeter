package com.datameter;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

// Holds the pricing and threshold configuration for data usage
public class RateCard {
    // Rates for different data types (per byte)
    public double rate4G;
    public double rate5G;
    public double rate4GRoaming;
    public double rate5GRoaming;
    // Usage threshold for applying surcharge
    public int usageThreshold;
    // Surcharge percentage (e.g., 0.05 for 5%)
    public double surcharge;

    // Loads rate card configuration from a properties file
    public RateCard(String configPath) throws IOException {
        Properties props = new Properties();
        try (FileInputStream fis = new FileInputStream(configPath)) {
            props.load(fis);
        }
        // Parse rates from properties, with defaults
        rate4G = Double.parseDouble(props.getProperty("rate.4g", "0.01"));
        rate5G = Double.parseDouble(props.getProperty("rate.5g", "0.02"));
        // Roaming rates are calculated as a percentage above base rates
        rate4GRoaming = rate4G * 1.10;
        rate5GRoaming = rate5G * 1.15;
        usageThreshold = Integer.parseInt(props.getProperty("usage.threshold", "100000"));
        surcharge = Double.parseDouble(props.getProperty("surcharge", "0.05"));
    }
}