import java.util.* ;
import java.io.*; 


class Node{
    Node links[] = new Node[26];
    boolean flag = false;

    public Node(){

    }

    boolean containsKey(char ch){
        return (links[ch-'a'] != null);
    }
    Node get(char ch){
        return links[ch-'a'];
    }
    void put(char ch, Node node){
        links[ch-'a'] = node;
    }
    void setEnd(){
        flag = true;
    }
    boolean isEnd(){
        return flag;
    }
}

class Trie {

    private static Node root;

    public Trie() {
        root = new Node();
    }
    
    public void insert(String word) {
        Node node = root;
        for(int i=0; i<word.length(); i++){
            if(!node.containsKey(word.charAt(i))){
                node.put(word.charAt(i), new Node());
            }
            node = node.get(word.charAt(i));
        }
        node.setEnd();
        
    }
    
    public int search(String word) {
        Node node = root;
        int max = 0;
        for(int i=0; i<word.length(); i++){
            node = node.get(word.charAt(i));
            if(!node.flag){
              return 0;
            }
            max++;
        }
        return max;
        
    }
}
class Solution {

public static String findSmall(String s1, String s2){
  int n = s1.length();
  for(int i=0; i<n; i++){
    char ch1 = s1.charAt(i);
    char ch2 = s2.charAt(i);
    int diff = (int)ch2-(int)ch1;
    if(diff > 0){
      return s1;
    } else if(diff < 0){
      return s2;
    }

  }
  return s1;
}
  public static String completeString(int n, String[] a) {
    // Write your code here.

    Trie trie = new Trie();
    for(int i=0; i<n; i++){
      trie.insert(a[i]);
    }
    int max = 0;
    String ans = "";
    for(int i=0; i<n; i++){
      int curr = trie.search(a[i]);
      if(curr > max){
        max = curr;
        ans = a[i];
      } else if(curr == max){
        ans = findSmall(ans,a[i]);
      }
    }
    if(max == 0){
      return "None";
    }
    return ans;

    



  }
}
