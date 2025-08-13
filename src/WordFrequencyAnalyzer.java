import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;

public class WordFrequencyAnalyzer {

    public Map<String, Integer> countWords(String filePath) throws IOException {
        Map<String, Integer> wordFrequencies = new HashMap<>();

        try(BufferedReader reader = new BufferedReader(new FileReader(filePath))) {
            String line;
            while((line = reader.readLine()) != null){
                line = line.toLowerCase();
                line = line.replaceAll("[^a-z]", " ");
                String[] words = line.split("\\s+");
                for(String word : words){
                    if(!word.isEmpty()){
                        wordFrequencies.put(word, wordFrequencies.getOrDefault(word,0) + 1);
                    }
                }
            }
        }
        return wordFrequencies;
    }


    public List<Map.Entry<String, Integer>> getTopWords(Map<String,Integer> wordFrequencies , int limit) {


        PriorityQueue<Map.Entry<String, Integer>> maxHeap = new PriorityQueue<>((a, b)-> b.getValue() - a.getValue());
        maxHeap.addAll(wordFrequencies.entrySet());

        List<Map.Entry<String, Integer>> topWords = new ArrayList<>();
        for(int i = 0; i < limit && !maxHeap.isEmpty(); i++){
            topWords.add(maxHeap.poll());
        }
        return topWords;
    }

    public static void main(String[] args) throws IOException {

        if(args.length < 1) {
            System.out.println("Usage: java WordFrequencyAnalyzer <file-path>");
            return;
        }

        WordFrequencyAnalyzer analyzer = new WordFrequencyAnalyzer();
        String filePath = args[0];

        Map<String, Integer> wordFrequencies = analyzer.countWords(filePath);
        List<Map.Entry<String, Integer>> topWords = analyzer.getTopWords(wordFrequencies, 10);

        System.out.println("\nTop 10 Most Frequent Words:");
        for(Map.Entry<String, Integer> entry : topWords){
            System.out.println(entry.getKey() + ": " + entry.getValue());
        }

        System.out.println("\nWord Frequencies Bar Chart:");
        if(!topWords.isEmpty()){
            int maxCount = topWords.get(0).getValue();
            for(Map.Entry<String, Integer> entry : topWords){
                int barLength = (int) ((entry.getValue() / (double) maxCount) * 50);
                System.out.printf("%-10s | %s (%d)%n", entry.getKey(), "#".repeat(barLength), entry.getValue());
            }
        }


    }
}