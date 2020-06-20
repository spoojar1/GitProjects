class Solution {
    public List<String> topKFrequent(String[] words, int k) {
        Map<String, Integer> map = new HashMap<>();
        for(String word: words){
            map.put(word, map.getOrDefault(word, 0) + 1);
        }

        List<String> result = new ArrayList<>(map.keySet());
        Collections.sort(result, (w1, w2) -> {
            int diff = map.get(w2) - map.get(w1);
            return diff == 0 ? w1.compareTo(w2) : diff;
        });
        return result.subList(0, k);
    }
}