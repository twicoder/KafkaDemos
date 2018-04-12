package demos;

public class CalcDemo1 {
    public static void main(String[] args) {
        double rate = 0.15;
        int times = 10;
        double result = 1;
        for(int i=0;i<times;i++){
            result *= (1 + rate);
        }
        System.out.println(result);
    }
}
