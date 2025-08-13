
# Word Frequency Analyzer

## 1. Option Chosen
Option: **Word Frequency Analyzer**  
A program that reads a text file, counts the frequency of each word, and displays the top most frequent words along with a bar chart.

---

## 2. How to Run the Program

### **Prerequisites**
- Java JDK 17 or later installed
- (Optional) IntelliJ IDEA for easy running
- JUnit 5 (included in the `lib` folder for running tests without IntelliJ)

---

### **Steps to Run**

#### **Option 1: Using Command Line (Recommended)**
*(Works on any system with Java installed — no IDE required)*

1. **Compile the program:**
```
javac src/WordFrequencyAnalyzer.java
````

2. **Run the program:**

```
java -cp src WordFrequencyAnalyzer <file-path>
```

Example:

```
java -cp src WordFrequencyAnalyzer sample.txt
```

---

#### **Option 2: Using IntelliJ IDEA (Optional)**

*(For users who have IntelliJ IDEA installed)*

1. Open the project in IntelliJ IDEA
2. Right-click on `WordFrequencyAnalyzer.java` → **Run 'WordFrequencyAnalyzer'**
3. In IntelliJ, go to **Run → Edit Configurations → Program arguments** to provide the file path

---

## 3. Language and Tools Used

* **Language:** Java (JDK 17)
* **Tools:**

  * IntelliJ IDEA 2025.1
  * JUnit 5 (for testing)
* **Build Tool:** None (plain Java project, no Maven/Gradle)

---

## 4. Features

### **Required Features**
- Accepts a path to a text file as input
- Ignores punctuation and letter case (e.g., "Apple" and "apple" are counted together)
- Displays the **top 10 most frequent words** with their counts

### **Bonus Features**
- Generates a simple **bar chart** showing the frequency of the top words
- Handles large files efficiently by reading them line by line
- Includes **JUnit 5 test coverage** for:
  - Word counting logic
  - Top words selection logic
  - Main method output verification
- Handles edge cases:
  - Empty files
  - Files containing only punctuation
  - Tie cases for top words

---

## 5. File and Folder Structure

```
WordFrequencyAnalyzer/
│
├── src/                           # Source code
│   ├── WordFrequencyAnalyzer.java
│
├── test/                          # Test code
│   ├── WordFrequencyAnalyzerTest.java
│
├── lib/                           # External libraries
│   ├── junit-platform-console-standalone-1.10.2.jar
│
├── sample.txt                     # Example input file
├── README.md                      # Project documentation
└── *.txt                          # Other example/edge case files
```

---

## 6. Running Tests (JUnit 5)

#### **Option 1: From Command Line**

*(Without IntelliJ)*

1. **Compile source and test files:**

```
javac -cp "lib/junit-platform-console-standalone-1.10.2.jar" src/*.java test/*.java
```

2. **Run tests:**

**Windows (PowerShell)**

```
java -jar lib/junit-platform-console-standalone-1.10.2.jar --class-path "src;test" --scan-class-path
```

**Linux/Mac**

```
java -jar lib/junit-platform-console-standalone-1.10.2.jar --class-path "src:test" --scan-class-path
```

---

#### **Option 2: From IntelliJ IDEA**

1. Open the project in IntelliJ IDEA
2. Right-click on `WordFrequencyAnalyzerTest.java` → **Run 'WordFrequencyAnalyzerTest'**
3. Or run all tests via:

```
Run → Run All Tests
```

---

## 7. Example Output

### Input (`sample.txt`):

```
Apple banana apple.
Banana orange apple!
Orange orange banana.
Apple, grape, grape.
The quick brown fox jumps over the lazy dog.
Hello World! Hello world.

```

### Output:

```
Top 10 Most Frequent Words:
apple: 4
banana: 3
orange: 3
grape: 2
hello: 2
world: 2
the: 2
quick: 1
brown: 1
fox: 1

Word Frequencies Bar Chart:
apple      | ################################################## (4)
banana     | ######################################## (3)
orange     | ######################################## (3)
grape      | ######################### (2)
hello      | ######################### (2)
world      | ######################### (2)
the        | ######################### (2)
quick      | ######### (1)
brown      | ######### (1)
fox        | ######### (1)

```




