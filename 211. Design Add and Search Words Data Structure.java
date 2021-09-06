/*

Design a data structure that supports adding new words and finding if a string matches any previously added string.

Implement the WordDictionary class:

WordDictionary() Initializes the object.
void addWord(word) Adds word to the data structure, it can be matched later.
bool search(word) Returns true if there is any string in the data structure that matches word or false otherwise. word may contain dots '.' where dots can be matched with any letter.


Example:

Input
["WordDictionary","addWord","addWord","addWord","search","search","search","search"]
[[],["bad"],["dad"],["mad"],["pad"],["bad"],[".ad"],["b.."]]
Output
[null,null,null,null,false,true,true,true]

Explanation
WordDictionary wordDictionary = new WordDictionary();
wordDictionary.addWord("bad");
wordDictionary.addWord("dad");
wordDictionary.addWord("mad");
wordDictionary.search("pad"); // return False
wordDictionary.search("bad"); // return True
wordDictionary.search(".ad"); // return True
wordDictionary.search("b.."); // return True


Constraints:

1 <= word.length <= 500
word in addWord consists lower-case English letters.
word in search consist of  '.' or lower-case English letters.
At most 50000 calls will be made to addWord and search.
 */

class WordDictionary {
    Node head;
    /** Initialize your data structure here. */
    public WordDictionary() {
        head = new Node();
    }

    public void addWord(String word) {
        Node curr = head;
        for (int i = 0; i < word.length(); i++) {
            int index = word.charAt(i) - 'a';
            if (curr.nodes[index] == null) {
                curr.nodes[index] = new Node();
            }
            curr = curr.nodes[index];
        }
        curr.isWord = true;
    }

    public boolean search(String word) {
        Node curr = head;
        return search(word, curr, 0);
    }


    boolean search(String word, Node curr, int index) {
        if (curr == null) return false;
        if (index == word.length()) {
            return curr.isWord;
        }

        char c = word.charAt(index);
        if (c != '.') {
            if (curr.nodes[c-'a'] == null) {
                return false;
            }
            return search(word, curr.nodes[c-'a'], index + 1);
        } else {
            for (int i = 0; i < 26; i++) {
                if (search(word, curr.nodes[i], index + 1)) {
                    return true;
                }
            }
            return false;
        }


    }




    class Node {
        Node[] nodes;
        boolean isWord;
        public Node() {
            nodes = new Node[26];
        }
    }

}

/**
 * Your WordDictionary object will be instantiated and called as such:
 * WordDictionary obj = new WordDictionary();
 * obj.addWord(word);
 * boolean param_2 = obj.search(word);
 */