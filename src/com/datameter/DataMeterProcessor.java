package com.datameter;

import java.io.*;
import java.util.*;

// This class processes data usage records and generates a report per mobile number
public class DataMeterProcessor {
    //Holds the rate card for cost calculation
    private RateCard rateCard;
    // Maps each mobile number to its usage aggregator
    private Map<String, MobileUsageAggregator> usageMap = new HashMap<>();
    // Counts the number of malformed lines encountered during processing
    private int malformedLines = 0;

    // Constructor initializes the processor with a given rate card
    public DataMeterProcessor(RateCard rateCard) {
        this.rateCard = rateCard;
    }

    // Processes a list of file paths containing data usage records
    public void processFiles(List<String> filePaths) {
        for (String filePath : filePaths) {
            try (BufferedReader br = new BufferedReader(new FileReader(filePath))) {
                String line;
                // Read each line from the file
                while ((line = br.readLine()) != null) {
                    try {
                        //Parse the line into a DataUsageRecord
                        DataUsageRecord record = DataUsageRecord.parse(line);
                        // Aggregate usage by mobile number
                        usageMap.computeIfAbsent(record.getMobileNumber(), MobileUsageAggregator::new)
                                .addUsage(record);
                    } catch (MalformedLineException e) {
                        // If the line is malformed, increment the counter
                        malformedLines++;
                    }
                }
            } catch (IOException e) {
                // Handle file reading errors
                System.err.println("Error reading file: " + filePath + " - " + e.getMessage());
            }
        }
    }

    //prints a summary report to the provided PrintStream
    public void printReport(PrintStream out) {
        // Print the report header
        out.println("Mobile Number|4G|5G|4G Roaming|5G Roaming|Cost");
        // Print usage and cost for each mobile number
        for (MobileUsageAggregator agg : usageMap.values()) {
            out.printf("%s|%d|%d|%d|%d|%d%n",
                    agg.getMobileNumber(),
                    agg.getData4G(),
                    agg.getData5G(),
                    agg.getData4GRoaming(),
                    agg.getData5GRoaming(),
                    (long) agg.calculateCost(rateCard));
        }
        // If any malformed lines were skipped, print a note
        if (malformedLines > 0) {
            out.println("Malformed lines skipped: " + malformedLines);
        }
    }
}