# Data Meter Project

This project processes telecom data usage logs from multiple files, aggregates usage per mobile number, and generates a categorized report with cost calculation.

## Features

- Aggregates 4G/5G and Roaming/Non-Roaming data per mobile number
- Configurable rate card and usage threshold (see `config.properties`)
- Handles large files efficiently
- Robust error handling for malformed lines and file I/O

## Prerequisites

- **Java JDK 21** (required)

## Project Structure

```text
src/com/datameter/         # Java source files
config.properties          # Configuration for rates and threshold
```

## How to Build and Run

1. **Clone the repository**

   ```sh
   git clone [<your-repo-url>](https://github.com/sanchitm9/DataMeter.git)
   cd DataMeter
   ```

2. **Compile the Java source files**

   ```sh
   javac --release 21 -d out src/com/datameter/*.java
   ```

3. **Prepare your input files**

   - Place your data log files (plain text, pipe-separated) in the project directory or specify their paths.

4. **Run the program**

   ```sh
   java -cp out com.datameter.Main config.properties inputfile1.txt inputfile2.txt ...
   ```

   - Replace `inputfile1.txt inputfile2.txt ...` with your actual data files.

5. **Output**

   - The report will be printed to the console in the format:

     ```text
     Mobile Number|4G|5G|4G Roaming|5G Roaming|Cost
     9000600600|0|13456|0|0|123
     9000600601|0|0|1345|0|345
     ```

## Configuration

Edit `config.properties` to adjust rates, threshold, and surcharge:

```properties
rate.4g=0.01
rate.5g=0.02
usage.threshold=100000
surcharge=0.05
```

## Notes

- Malformed lines in input files are skipped and counted in the output.
- All code is in plain Java 21, no external dependencies required.

---
