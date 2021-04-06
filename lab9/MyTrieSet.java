import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class MyTrieSet implements TrieSet61B{
    private Node root;

    private static class Node {
        private boolean isKey;
        private HashMap<Character, Node> map;

        private Node(boolean b) {
            isKey= b;
            map = new HashMap<>();
        }
    }

    public MyTrieSet() {
        root = new Node(false);
    }

    /** Clears all items out of Trie */
    @Override
    public void clear() {
        root.map.clear();
    }

    /** Returns true if the Trie contains KEY, false otherwise */
    @Override
    public boolean contains(String key) {
        if (key == null || key.length() < 1) {
            return false;
        }
        Node curr = root;
        for (int i = 0, n = key.length(); i < n; i++) {
            char c = key.charAt(i);
            if (!curr.map.containsKey(c)) {
                return false;
            }
            curr = curr.map.get(c);
        }
        if (curr.isKey) {
            return true;
        }
        return false;
    }

    /** Inserts string KEY into Trie */
    @Override
    public void add(String key) {
        if (key == null || key.length() < 1) {
            return;
        }
        Node curr = root;
        for (int i = 0, n = key.length(); i < n; i++) {
            char c = key.charAt(i);
            if (!curr.map.containsKey(c)) {
                curr.map.put(c, new Node(false));
            }
            curr = curr.map.get(c);
        }
        curr.isKey = true;
    }

    /** Returns a list of all words that start with PREFIX */
    @Override
    public List<String> keysWithPrefix(String prefix) {
        List<String> x = new ArrayList<>();
        if (prefix == null || prefix.length() < 1) {
            return collect();
        }
        Node curr = root;
        for (int i = 0, n = prefix.length(); i < n; i++) {
            char c = prefix.charAt(i);
            if (!curr.map.containsKey(c)) {
                return x;
            }
            curr = curr.map.get(c);
        }
        x = collect(prefix, curr);
        if (curr.isKey) {
            x.add(prefix);
        }
        return x;
    }

    public List<String> collect() {
        return collect("", root);
    }

    private List<String> collect(String prefix, Node n) {
        List<String> x = new ArrayList<>();
        for (char c : n.map.keySet()) {
            colHelp(prefix + c, x, n.map.get(c));
        }
        return x;
    };

    private void colHelp(String s, List<String> x, Node n) {
        if (n.isKey) {
            x.add(s);
        }
        for (char c : n.map.keySet()) {
            colHelp(s + c, x, n.map.get(c));
        }
    }

    /** Returns the longest prefix of KEY that exists in the Trie
     * Not required for Lab 9. If you don't implement this, throw an
     * UnsupportedOperationException.
     */
    @Override
    public String longestPrefixOf(String key) {
        throw new UnsupportedOperationException();
    };

    public static void main(String[] args) {
        MyTrieSet t = new MyTrieSet();
        t.add("hello");
        t.add("hi");
        t.add("help");
        t.add("zebra");
        System.out.println(t.collect());
    }
}
