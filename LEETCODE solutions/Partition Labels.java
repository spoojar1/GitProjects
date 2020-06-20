class Solution {
    public static List<Integer> partitionLabels(String S) {
        List<Integer> result = new ArrayList<>();
        int min = 0, max = 0;

        int track[] = new int[26];
        for(int i=0;i<S.length();i++){
            if(i > max){
                result.add(max - min + 1);
                min = i;
            }

            char c = S.charAt(i);
            if(track[c-'a'] == 0){
                track[c-'a']++;
                int pMax = S.lastIndexOf(c);
                if(pMax>max)
                    max = pMax;
            }
        }

        if(max == S.length()-1){
            result.add(max - min + 1);
        }

        return result;
    }
}