package lab5;

import java.util.*;

class Competition {

    private TreeMap<Integer, List<String>> scoreMap;

    public Competition() {
        scoreMap = new TreeMap<>(Comparator.reverseOrder());
    }

    public void addParticipant(String lastName, String firstName, int[] scores) {
        int totalScore = Arrays.stream(scores).sum();
        String fullName = lastName + " " + firstName;
        scoreMap.computeIfAbsent(totalScore, k -> new ArrayList<>()).add(fullName);
    }

    public List<String> getResults() {
        List<String> results = new ArrayList<>();
        int place = 1;

        for (Map.Entry<Integer, List<String>> entry : scoreMap.entrySet()) {
            int totalScore = entry.getKey();
            List<String> names = entry.getValue();

            for (String name : names) {
                results.add(name + " " + totalScore + " " + place);
            }

            place++;
        }

        return results;
    }
}
