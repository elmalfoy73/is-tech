package Banks.Accounts;

import Banks.Bank;
import Banks.BankException;
import Banks.Client;
import Banks.Transaction;
import lombok.Getter;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.GregorianCalendar;

/**
 * Класс кредитного аккаунта, реализует аккаунт
 */
public class CreditAccount implements Account{
    @Getter
    private String name;
    @Getter
    private Client client;
    @Getter
    private BigDecimal balance;
    @Getter
    private BigDecimal fees;
    @Getter
    private ArrayList<Transaction> transactions;
    public CreditAccount(String name, Client client, BigDecimal balance)
    {
        this.name = name;
        this.client = client;
        this.balance = balance;
        this.fees = new BigDecimal("0");
        transactions = new ArrayList<>();
    }
    @Override
    public void recive(BigDecimal sum) {
        balance = balance.add(sum);
        checkFees();
        transactions.add(new Transaction(sum, GregorianCalendar.getInstance()));
    }

    @Override
    public void withdraw(BigDecimal sum) {
        if (client.isSuspicious() && sum.compareTo(client.getBank().getLimit()) > 0)
            throw new BankException("Limit exceeded");
        if (balance.subtract(sum).compareTo(client.getCreditLimit().negate()) < 0)
            throw new BankException("insufficient funds");
        balance = balance.subtract(sum);
        checkFees();
        transactions.add(new Transaction(sum.negate(), GregorianCalendar.getInstance()));
    }

    @Override
    public void send(String phone, BigDecimal sum) {
        if (client.isSuspicious() && sum.compareTo(client.getBank().getLimit()) > 0)
            throw new BankException("Limit exceeded");
        if (balance.subtract(sum).compareTo(client.getCreditLimit().negate()) < 0)
            throw new BankException("insufficient funds");
        client.getBank().send(phone, sum);
        balance = balance.subtract(sum);
        checkFees();
        transactions.add(new Transaction(sum.negate(), GregorianCalendar.getInstance()));
    }

    @Override
    public void send(Bank bank, String phone, BigDecimal sum) {
        if (client.isSuspicious() && sum.compareTo(client.getBank().getLimit()) > 0)
            throw new BankException("Limit exceeded");
        if (balance.subtract(sum).compareTo(client.getCreditLimit().negate()) < 0)
            throw new BankException("insufficient funds");
        bank.getCentralBank().sendWithSBP(bank, phone, sum);
        balance = balance.subtract(sum);
        checkFees();
        transactions.add(new Transaction(sum.negate(), GregorianCalendar.getInstance()));
    }

    @Override
    public void send(Account account, BigDecimal sum) {
        if (client.isSuspicious() && sum.compareTo(client.getBank().getLimit()) > 0)
            throw new BankException("Limit exceeded");
        if (balance.subtract(sum).compareTo(client.getCreditLimit().negate()) < 0)
            throw new BankException("insufficient funds");
        account.recive(sum);
        balance = balance.subtract(sum);
        checkFees();
        transactions.add(new Transaction(sum.negate(), GregorianCalendar.getInstance()));
    }

    @Override
    public void nextMonth() {
        balance = balance.subtract(fees);
        fees = new BigDecimal("0");
    }

    /**
     * Провека на начисление коммисии.
     */
    public void checkFees()
    {
        if (balance.signum() < 0)
            fees = fees.add(client.getBank().getFees());
    }

}
