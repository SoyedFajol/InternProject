import java.io.*;
import java.util.*;


public class Solution {

    public static void main(String[] args) {
       
        Scanner in= new Scanner(System.in);
        int t=in.nextInt();
        for(int i=0; i<t; i++){
            int a=in.nextInt();
            int b=in.nextInt();
            int q=in.nextInt();
            
            for(int j=0; j<q; j++){
                a=a+b;
                System.out.print(a+" ");
                b=b*2;
                
                
            }
            System.out.println("");
        }
        in.close();
        
    }
}
