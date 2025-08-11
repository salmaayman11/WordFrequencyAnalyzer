import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class WordFrequencyAnalyzer {
    public static void main(String[] args) throws IOException {

        if(args.length < 1) {
            System.out.println("Usage: java WordFrequencyAnalyzer <file-path>");
            return;
        }

        String filePath = args[0];

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
    }
}