import java.util.*;



public class problem5 {
    public static boolean isPalindrom(String s){
        s = s.replaceAll("[^a-zA-Z0-9]", "").toLowerCase();

        String reversed = new StringBuilder(s).reverse().toString();
        return s.equals(reversed);
    }
    public static void main(String[] args) {
        System.out.println(isPalindrom("A man, a plan, a canal: Panama"));
        System.out.println(isPalindrom("race a car")); 
        System.out.println(isPalindrom(" "));
    }
}
