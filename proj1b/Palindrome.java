public class Palindrome {
    public Deque<Character> wordToDeque(String word) {
        LinkedListDeque<Character> d = new LinkedListDeque<>();
        for (int i = 0; i < word.length(); i++) {
            d.addLast(word.charAt(i));
        }
        return d;
    }

    public boolean isPalindrome(String word) {
        Deque<Character> d = wordToDeque(word);

        while (d.size() > 1) {
            if (d.removeFirst() != d.removeLast()) {
                return false;
            }
        }

        return true;
    }

    public boolean isPalindrome(String word, CharacterComparator cc){
        Deque<Character> d = wordToDeque(word);
        while (d.size() > 1) {
            if (!cc.equalChars(d.removeFirst(), d.removeLast())) {
                return false;
            }
        }

        return true;
    }
}



