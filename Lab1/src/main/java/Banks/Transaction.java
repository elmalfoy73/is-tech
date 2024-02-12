package Banks;

import java.math.BigDecimal;
import java.util.Calendar;

/**
 * Класс транзакции (для истории операций)
 */
public class Transaction {
    public BigDecimal sum;
    public Calendar date;
    public Transaction(BigDecimal sum, Calendar date)
    {
        this.sum = sum;
        this.date = date;
    }
}
