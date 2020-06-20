class Solution {
    public static int[] topKFrequent(int[] nums, int k) {
        Map<Integer, Integer> map = new HashMap<>();
        for(int n : nums){
            map.put(n, map.getOrDefault(n, 0) + 1);
        }

        PriorityQueue<Integer> q = new PriorityQueue<>((n1, n2) -> map.get(n1) - map.get(n2));

        for(int n : map.keySet()){
            q.add(n);
            if (q.size() > k)
                q.poll();
        }

        int result[] = new int[k];
        for(int i=k-1;i >=0;i--){
            result[i] = q.poll();
        }

        return result;
    }
}