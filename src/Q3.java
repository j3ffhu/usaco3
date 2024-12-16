import java.util.*;
import java.util.regex.*;

public class Q3 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        // Read inputs
        int N = sc.nextInt();
        int F = sc.nextInt();
        String contest = sc.next();

        // Use regex to find moos in the original string
        Pattern pattern = Pattern.compile("(.)((?!\\1).)\\2");
        Map<String, Integer> mooCounts = new HashMap<>();

        // Step 1: Find "moos" appearing exactly once
        findMoos(contest, pattern, mooCounts);

        // Step 2: Simulate one-character corruption
        Set<String> possibleMoos = new HashSet<>(mooCounts.keySet());

        for (String moo : possibleMoos) {
            if ((F >= 2) && mooCounts.get(moo) == F - 1) {
                checkCorruption(contest, pattern, moo, mooCounts);
            }
        }

        if (F == 1) {
            checkCorruption(contest, pattern, mooCounts);
        }

        // Step 3: Collect and print results
        List<String> result = new ArrayList<>();
        for (Map.Entry<String, Integer> entry : mooCounts.entrySet()) {
            if (entry.getValue() >= F) {
                result.add(entry.getKey());
            }
        }

        Collections.sort(result);
        System.out.println(result.size());
        for (String moo : result) {
            System.out.println(moo);
        }
    }

    // Find moos using regex
    private static void findMoos(String str, Pattern pattern, Map<String, Integer> mooCounts) {
        Matcher matcher = pattern.matcher(str);
        while (matcher.find()) {
            String moo = matcher.group(1) + matcher.group(2) + matcher.group(2);
            mooCounts.put(moo, mooCounts.getOrDefault(moo, 0) + 1);
        }
    }

    // Check for possible corrupted: single
    private static void checkCorruption(String contest, Pattern pattern, Map<String, Integer> mooCounts) {
        int N = contest.length();

        for (int i = 0; i < N; i++) {
            for (char c = 'a'; c <= 'z'; c++) {
                if (contest.charAt(i) != c) {
                    String modifiedContest = contest.substring(0, i) + c + contest.substring(i + 1);
                    Map<String, Integer> tempCounts = new HashMap<>();
                    findMoos(modifiedContest, pattern, mooCounts);

                    Set<String> possibleMoos = new HashSet<>(tempCounts.keySet());

                    for (String moo : possibleMoos) {
                        mooCounts.put(moo, 1);

                    }

                }
            }
        }
    }

    // Check for possible corrupted moos
    private static void checkCorruption(String contest, Pattern pattern, String moo, Map<String, Integer> mooCounts) {
        int N = contest.length();

        for (int i = 0; i < N; i++) {
            for (char c = 'a'; c <= 'z'; c++) {
                if (contest.charAt(i) != c) {
                    String modifiedContest = contest.substring(0, i) + c + contest.substring(i + 1);
                    Map<String, Integer> tempCounts = new HashMap<>();
                    findMoos(modifiedContest, pattern, tempCounts);

                    if (tempCounts.containsKey(moo)) {
                        mooCounts.put(moo, Math.max(mooCounts.get(moo), tempCounts.get(moo)));
                    }
                }
            }
        }
    }
}
