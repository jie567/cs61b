public class OffByOne implements CharacterComparator {
    // offbyone 大小差1
    @Override
    public boolean equalChars(char x, char y){
        if(x-y==1||y-x==1){
            return true;
        }
        return false;
    };
}