public class OffByOne implements CharacterComparator {
    public boolean equalChars(char x, char y){
        return x - y == 1 || x - y == -1;
    }
}
