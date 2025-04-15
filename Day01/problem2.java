public class problem2 {
    public static void main(String[] args) {
        int pyramid = 5;
        for(int i = 1; i<=pyramid; i++) {
            for(int space=1; space<=pyramid-i; space++){
                System.out.print("  ");
            }
            for(int star=1; star<=2*i-1; star++){
                System.out.print("* ");
            }
            System.out.println();
         }
         
        }
    }

