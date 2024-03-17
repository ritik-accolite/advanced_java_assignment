# MergeXmlFiles

This Java program merges two XML files containing license data based on specific keys and performs various operations on the merged data, including filtering valid and invalid licenses.

## Steps to Execute:

1. **Clone Repository:**
   - Clone this repository to your local machine using Git:
     ```
     git clone <https://github.com/ritik-accolite/advanced_java_assignment>
     ```

2. **Navigate to Source Directory:**
   - Open a terminal/command prompt and navigate to the directory containing the Java source files:


3. **Compile Java Code:**
   - Compile the Java source code using the Java compiler (javac):
     ```
     javac MergeXmlFiles.java
     ```

4. **Run the Program:**
   - Execute the compiled Java program:
     ```
     java MergeXmlFiles
     ```

5. **Check Output Files:**
   - After execution, check the following output files generated in the same directory:
     - `ValidLicenses.txt`: Contains details of valid licenses based on expiration date.
     - `InvalidLicenses.txt`: Contains details of invalid licenses based on expiration date.
     - `MergedFile.txt`: Contains merged data with appropriate license status.

## Additional Notes:

- Ensure that you have Java Development Kit (JDK) installed on your system.
- Make sure the input XML files (`License1.xml` and `License2.xml`) are present in the same directory as the Java source files.
- The program uses Java DOM (Document Object Model) API for XML parsing and manipulation.
- Modify the file paths and names as needed in the Java code and instructions above.
- Contact the repository owner for any issues or queries related to the code.
