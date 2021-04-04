public class Palindrome {
    //  帮助识别英语中广义回文的类
    public Deque<Character> wordToDeque(String word){
        LinkedListDeque<Character> d=new LinkedListDeque<>();
        for(int i=0;i<word.length();i++){
            d.addLast(word.charAt(i));
        }
        return d;
    }

    private boolean isPalindromeHelper(Deque list){
        if(list.size()<=1){
            return true;
        }
        else if(!list.removeFirst().equals(list.removeLast())){
            return false;
        }
        return isPalindromeHelper(list);
    }

    // a.equals(b) 字符串的比较有不同  == 是确定指向地址相同
    public boolean isPalindrome(String word){
//        if(word.length()<=0||word.length()<=1){
//            return true;
//        }
//        Palindrome palindrome = new Palindrome();
//        Deque b = palindrome.wordToDeque(word);
//        String actual = "";
//        for (int i = 0; i < word.length(); i++) {
//            actual += b.removeLast();
//        }
//        return word.equals(actual);
        if(word.length()<=0||word.length()<=1){
            return true;
        }
        Palindrome palindrome = new Palindrome();
        Deque b = palindrome.wordToDeque(word);
        return isPalindromeHelper(b);
    }

    //
    public boolean isPalindrome(String word, OffByOne cc){
        if(word.length()<=1){
            return true;
        }
        int n=word.length();
        Deque w = wordToDeque(word);
        for(int j=0;j<n/2;j++){
            char first=(char)w.removeFirst();
            char last=(char)w.removeLast();
            if(!cc.equalChars(first,last)){
                return false;
            }
        }
        return true;
    }
}
