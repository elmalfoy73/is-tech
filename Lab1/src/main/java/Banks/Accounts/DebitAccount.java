package Banks.Accounts;

import Banks.*;
import lombok.Getter;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.GregorianCalendar;

/**
 * Класс дебетового аккаунта, реализует аккаунт
 */
public class DebitAccount implements Account{
    @Getter
    private String name;
    @Getter
    private Client client;
    @Getter
    private BigDecimal balance;
    @Getter
    private BigDecimal interestOnAccount;
    @Getter
    private ArrayList<Transaction> transactions;
    public DebitAccount(String name, Client client, BigDecimal balance){
        this.name = name;
        this.client = client;
        this.balance = balance;
        this.interestOnAccount = new BigDecimal("0");
        transactions = new ArrayList<>();
    }
    @Override
    public void recive(BigDecimal sum) {
        balance = balance.add(sum);
        addInterestOnAccount();
        transactions.add(new Transaction(sum, GregorianCalendar.getInstance()));
    }

    @Override
    public void withdraw(BigDecimal sum) {
        if (client.isSuspicious() && sum.compareTo(client.getBank().getLimit()) > 0)
            throw new BankException("Limit exceeded");
        if (balance.subtract(sum).signum() < 0)
            throw new BankException("insufficient funds");
        balance = balance.subtract(sum);
        addInterestOnAccount();
        transactions.add(new Transaction(sum.negate(), GregorianCalendar.getInstance()));
    }

    @Override
    public void send(String phone, BigDecimal sum) {
        if (client.isSuspicious() && sum.compareTo(client.getBank().getLimit()) > 0)
            throw new BankException("Limit exceeded");
        if (balance.subtract(sum).signum() < 0)
            throw new BankException("insufficient funds");
        client.getBank().send(phone, sum);
        balance = balance.subtract(sum);
        addInterestOnAccount();
        transactions.add(new Transaction(sum.negate(), GregorianCalendar.getInstance()));
    }

    @Override
    public void send(Bank bank, String phone, BigDecimal sum) {
        if (client.isSuspicious() && sum.compareTo(client.getBank().getLimit()) > 0)
            throw new BankException("Limit exceeded");
        if (balance.subtract(sum).signum() < 0)
            throw new BankException("insufficient funds");
        bank.getCentralBank().sendWithSBP(bank, phone, sum);
        balance = balance.subtract(sum);
        addInterestOnAccount();
        transactions.add(new Transaction(sum.negate(), GregorianCalendar.getInstance()));
    }

    @Override
    public void send(Account account, BigDecimal sum) {
        if (client.isSuspicious() && sum.compareTo(client.getBank().getLimit()) > 0)
            throw new BankException("Limit exceeded");
        if (balance.subtract(sum).signum() < 0)
            throw new BankException("insufficient funds");
        account.recive(sum);
        balance = balance.subtract(sum);
        addInterestOnAccount();
        transactions.add(new Transaction(sum.negate(), GregorianCalendar.getInstance()));
    }

    @Override
    public void nextMonth() {
        balance = balance.add(interestOnAccount);
        interestOnAccount = new BigDecimal("0");
    }

    /**
     * Расчет процета на остаток.
     */
    public void addInterestOnAccount()
    {
        BigDecimal perDay = client.getBank().getInterestOnAccount().divide(new BigDecimal("100")).divide(new BigDecimal("365"));
        interestOnAccount = interestOnAccount.add(balance.multiply(perDay));
    }
}
