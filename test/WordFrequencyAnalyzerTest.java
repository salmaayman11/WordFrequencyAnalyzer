import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

import java.io.*;
import java.util.List;
import java.util.Map;

public class WordFrequencyAnalyzerTest {

    @Test
    public void testCountWordsNormalCase() throws IOException {
        File tmp = File.createTempFile("wf-test", ".txt");
        try (PrintWriter out = new PrintWriter(tmp)) {
            out.println("Apple apple banana orange!");
        }
        WordFrequencyAnalyzer analyzer = new WordFrequencyAnalyzer();
        Map<String, Integer> result = analyzer.countWords(tmp.getAbsolutePath());

        assertEquals(2, result.get("apple"));
        assertEquals(1, result.get("banana"));
        assertEquals(1, result.get("orange"));
    }

    @Test
    public void testCountWordsEmptyFile() throws IOException {
        File tmp = File.createTempFile("wf-empty", ".txt");
        WordFrequencyAnalyzer analyzer = new WordFrequencyAnalyzer();
        Map<String, Integer> result = analyzer.countWords(tmp.getAbsolutePath());

        assertTrue(result.isEmpty());
    }

    @Test
    public void testCountWordsOnlyPunctuation() throws IOException {
        File tmp = File.createTempFile("wf-punct", ".txt");
        try (PrintWriter out = new PrintWriter(tmp)) {
            out.println("!!! ??? ...");
        }
        WordFrequencyAnalyzer analyzer = new WordFrequencyAnalyzer();
        Map<String, Integer> result = analyzer.countWords(tmp.getAbsolutePath());

        assertTrue(result.isEmpty());
    }

    @Test
    public void testGetTopWordsWithTie() {
        Map<String, Integer> counts = Map.of(
                "apple", 3,
                "banana", 3,
                "grape", 1
        );

        WordFrequencyAnalyzer analyzer = new WordFrequencyAnalyzer();
        List<Map.Entry<String, Integer>> topWords = analyzer.getTopWords(counts, 2);

        assertEquals(2, topWords.size());
        assertEquals(3, topWords.get(0).getValue());
        assertEquals(3, topWords.get(1).getValue());
    }

    @Test
    public void testGetTopWordsFewerThanLimit() {
        Map<String, Integer> counts = Map.of("apple", 1);

        WordFrequencyAnalyzer analyzer = new WordFrequencyAnalyzer();
        List<Map.Entry<String, Integer>> topWords = analyzer.getTopWords(counts, 10);

        assertEquals(1, topWords.size());
    }

    @Test
    public void testRegexRemovesNumbersAndApostrophes() throws IOException {
        File tmp = File.createTempFile("wf-regex", ".txt");
        try (PrintWriter out = new PrintWriter(tmp)) {
            out.println("Grape's 123 grape");
        }
        WordFrequencyAnalyzer analyzer = new WordFrequencyAnalyzer();
        Map<String, Integer> result = analyzer.countWords(tmp.getAbsolutePath());

        // "Grape's" becomes "grape s" → grape appears twice, s appears once
        assertEquals(2, result.get("grape"));
        assertEquals(1, result.get("s"));
        assertFalse(result.containsKey("123"));
    }

    @Test
    public void testMainWithNoArgs() {
        ByteArrayOutputStream outContent = new ByteArrayOutputStream();
        PrintStream originalOut = System.out;
        System.setOut(new PrintStream(outContent));

        String[] args = {};
        assertDoesNotThrow(() -> WordFrequencyAnalyzer.main(args));

        System.setOut(originalOut);
        String output = outContent.toString().trim();
        assertTrue(output.contains("Usage: java WordFrequencyAnalyzer <file-path>"));
    }


    @Test
    public void testMainOutputContainsExpectedWords() throws IOException {
        File tmp = File.createTempFile("wf-main", ".txt");
        try (FileWriter fw = new FileWriter(tmp)) {
            fw.write("apple banana banana grape");
        }

        ByteArrayOutputStream outContent = new ByteArrayOutputStream();
        PrintStream originalOut = System.out;
        System.setOut(new PrintStream(outContent));

        String[] args = { tmp.getAbsolutePath() };
        WordFrequencyAnalyzer.main(args);

        System.setOut(originalOut);

        String output = outContent.toString();
        assertTrue(output.contains("banana: 2"));
        assertTrue(output.contains("apple: 1") || output.contains("apple: 2"));
    }
}
