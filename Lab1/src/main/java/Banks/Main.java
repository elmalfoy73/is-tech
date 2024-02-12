package Banks;

import java.math.BigDecimal;

public class Main {
    public static void main(String[] args) {
        BigDecimal a = new BigDecimal("50000");
        System.out.print(a.divide(new BigDecimal("100")));
    }
}
