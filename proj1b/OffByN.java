public class OffByN implements CharacterComparator {
    public int number;
    public OffByN(int N){
        N=number;
    }

    @Override
    public boolean equalChars(char x,char y){
        if(x-y==number||y-x==number){
            return true;
        }
        return false;
    }
}
