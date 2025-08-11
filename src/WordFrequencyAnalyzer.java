import java.util.Map;
import java.util.TreeMap;

public class WordFrequencyAnalyzer {
    public static void main(String[] args) {

        if(args.length < 1) {
            System.out.println("Usage: java WordFrequencyAnalyzer <file-path>");
            return;
        }

        String filePath = args[0];

        Map<String, Integer> wordFrequencies = new TreeMap<String, Integer>();
    }
}