package com.datameter;

import java.util.*;

// Entry point for the Data Meter application
public class Main {
    public static void main(String[] args) {
        // Check if the required arguments are provided
        if (args.length < 2) {
            System.err.println(
                    "Usage: java -cp . com.datameter.Main <config.properties> <inputfile1> [<inputfile2> ...]");
            System.exit(1);
        }
        // First argument is the path to the config file
        String configPath = args[0];
        // Remaining arguments are input data files
        List<String> inputFiles = Arrays.asList(Arrays.copyOfRange(args, 1, args.length));
        try {
            // Load rate card configuration
            RateCard rateCard = new RateCard(configPath);
            // Create processor with the loaded rate card
            DataMeterProcessor processor = new DataMeterProcessor(rateCard);
            // Process all input files
            processor.processFiles(inputFiles);
            // Print the final report to standard output
            processor.printReport(System.out);
        } catch (Exception e) {
            // Handle any errors during processing
            System.err.println("Error: " + e.getMessage());
            System.exit(2);
        }
    }
}