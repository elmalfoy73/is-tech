package Banks;

/**
 * Класс паспорта
 */
public class Passport {
    public int series;
    public int no;
    public Passport(int series, int no) {
        if (String.valueOf(series).length() != 4)
            throw new BankException("Banks.Client's passport series is incorrect");
        if (String.valueOf(no).length()!= 6)
            throw new BankException("Banks.Client's passport № is incorrect");
        this.series = series;
        this.no = no;
    }
}
