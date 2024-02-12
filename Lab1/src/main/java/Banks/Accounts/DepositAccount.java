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
 * Класс депозитного аккаунта, реализует аккаунт
 */
public class DepositAccount implements Account{
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
    public DepositAccount(String name, Client client, BigDecimal balance){
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
        throw new BankException("You can't withdraw money from the deposit account");
    }

    @Override
    public void send(String phone, BigDecimal sum) {
        throw new BankException("You can't withdraw money from the deposit account");
    }

    @Override
    public void send(Bank bank, String phone, BigDecimal sum) {
        throw new BankException("You can't withdraw money from the deposit account");
    }

    @Override
    public void send(Account account, BigDecimal sum) {
        throw new BankException("You can't withdraw money from the deposit account");
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
        BigDecimal perDay = client.getBank().getInterestOnAccount().divide(new BigDecimal("365000"));
        interestOnAccount = balance.multiply(perDay);
    }

    /**
     * Закрытие дипозита.
     */
    public void CloseDeposit()
    {
        client.removeAccount(this);
        client.getDefaultAccount().recive(balance);
        balance = new BigDecimal("0");
    }

}
