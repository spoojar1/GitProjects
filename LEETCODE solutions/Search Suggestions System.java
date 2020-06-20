class Solution {
    public List<List<String>> suggestedProducts(String[] products, String searchWord){
        List<List<String>> result = new ArrayList<List<String>>();
        Arrays.sort(products);
        for(int i=0;i<searchWord.length();i++){
            String str = searchWord.substring(0, i+1);
            List<String> matches = new ArrayList<>();
            for(String product : products){
                if(product.startsWith(str))
                    matches.add(product);
            }
            if(matches.size()>3){
                result.add(matches.subList(0,3));
            }else{
                result.add(matches);
            }
        }
        return result;
    }
}