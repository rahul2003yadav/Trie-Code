import java.util.* ;
import java.io.*; 


class Node{
    Node links[] = new Node[26];
    int cntEndWith = 0;
    int cntPrefix = 0;

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
    void increasePrefix(){
        cntPrefix++;
    }
    void increaseEnd(){
        cntEndWith++;
    }

    void reducePrefix(){
        cntPrefix--;
    }
    void deleteEnd(){
        cntEndWith--;
    }
    int getEnd(){
        return cntEndWith;
    }
    int getPrefix(){
        return cntPrefix;
    }
}


public class Trie {

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
            node.increasePrefix();
        }
        node.increaseEnd();
    }

    public int countWordsEqualTo(String word) {
        Node node = root;
        for(int i=0; i<word.length(); i++){
            if(!node.containsKey(word.charAt(i))){
                return 0;
            }
            node = node.get(word.charAt(i));
        }
        // if(node.isEnd()){
        //     return node.getEnd();
        // }
        //return 0;
        return node.getEnd();
    }

    public int countWordsStartingWith(String word) {
        Node node = root;
        for(int i=0; i<word.length(); i++){
            if(!node.containsKey(word.charAt(i))){
                return 0;
            }
            node = node.get(word.charAt(i));
        }
        return node.getPrefix();
    }

    public void erase(String word) {
        Node node = root;
        for(int i=0; i<word.length(); i++){
            if(!node.containsKey(word.charAt(i))){
                return;
            }
            node = node.get(word.charAt(i));
            node.reducePrefix();
        }
        node.deleteEnd();
    }

}
