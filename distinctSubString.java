
import java.util.ArrayList;


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
public class Solution 
{
	private static Node root;
	public static int countDistinctSubstrings(String s) 
	{
		//	  Write your code here.
		root = new Node();
		int count = 0;
		String word = s;
		
		for(int idx = 0; idx < word.length(); idx++){
			Node node = root;
			for(int i=idx; i<word.length(); i++){
				if(!node.containsKey(word.charAt(i))){
					node.put(word.charAt(i), new Node());
					count++;
				}
				node = node.get(word.charAt(i));
			}
		}
		return count+1;
	}
}
