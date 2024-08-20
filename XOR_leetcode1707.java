class Node {
    Node links[] = new Node[2];
    public Node(){
        
    }
    boolean containsKey(int ind){
        return (links[ind] != null);
    }
    Node get(int ind){
        return links[ind];
    }
    void put(int ind, Node node){
        links[ind] = node;
    }
}
class Trie {
    private static Node root;

    Trie() {
        root = new Node();
    }

    public static void insert(int num){
        Node node = root;
        for(int i=31; i>=0; i--){
            int bit = (num >> i) & 1;
            if(!node.containsKey(bit)){
                node.put(bit, new Node());
            }
            node = node.get(bit);
        }
    }

    public int getMax(int num){
        Node node = root;
        int maxNum = 0;
        for(int i=31; i>=0; i--){
            int bit = (num >> i) & 1;
            if(node.containsKey(1 - bit)){
                maxNum = maxNum | (1 << i);
                node = node.get(1-bit);
            } else {
                node = node.get(bit);
            }
        }
        return maxNum;
    }
}

class Solution {
    public int[] maximizeXor(int[] nums, int[][] queries) {
        Trie trie = new Trie();
        int ans[] = new int[queries.length];

        int newQueries[][] = new int[queries.length][3];
        for(int i=0; i<queries.length; i++){
            newQueries[i][0] = queries[i][0];
            newQueries[i][1] = queries[i][1];
            newQueries[i][2] = i;
        }
        Arrays.sort(nums);
        Arrays.sort(newQueries, Comparator.comparingDouble(o -> o[1]));
        int i=0;
        int numIdx = 0;
        while( numIdx < queries.length) {
            while(i < nums.length && nums[i] <= newQueries[numIdx][1]){
                trie.insert(nums[i]);
                i++;
            }
            if(i == 0){
                    ans[newQueries[numIdx][2]] = -1;
                    numIdx++;
                    continue;
            }
            ans[newQueries[numIdx][2]] = trie.getMax(newQueries[numIdx][0]);
            numIdx++;
        }
        
        return ans;
    
    }
    
}
