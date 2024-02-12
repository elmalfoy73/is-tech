package Banks;

import Banks.Accounts.*;
import lombok.Getter;
import java.math.BigDecimal;
import java.util.ArrayList;

/**
 * Класс клиента, создается с помощью билдера
 */
public class Client {
    @Getter
    private ArrayList<Account> accounts;
    @Getter
    private Bank bank;
    @Getter
    private String fullName;
    @Getter
    private String phone;
    @Getter
    private String address;
    @Getter
    private Passport passport;
    @Getter
    private BigDecimal creditLimit;
    @Getter
    private boolean isSuspicious;
    @Getter
    private DebitAccount defaultAccount;

    public Client(ClientBuilder clientBuilder)
    {
        bank = clientBuilder.bank;
        fullName = clientBuilder.fullName;
        phone = clientBuilder.phone;
        address = clientBuilder.address;
        passport = clientBuilder.passport;
        creditLimit = clientBuilder.creditLimit;
        isSuspicious = true;
        if (address != null & passport != null)
            isSuspicious = false;
        defaultAccount = new DebitAccount("default", this, new BigDecimal("0"));
        accounts = new ArrayList<Account>();
        accounts.add(defaultAccount);
    }

    /**
     * Добавление адресса, проверка перестал ли клиент быть "подозрительным".
     * @param address Адресс
     */
    public void addAddress(String address)
    {
        if (address == null || address.trim().isEmpty())
            throw new BankException("Banks.Client's address is null or white space");
        this.address = address;
        if (passport != null)
            isSuspicious = false;
    }

    /**
     * Добавление пасспорта, проверка перестал ли клиент быть "подозрительным".
     * @param series Серия пасспорт
     * @param no Номер пасспорт
     */
    public void addPassport(int series, int no)
    {
        this.passport = new Passport(series, no);
        if (address != null)
            isSuspicious = false;
    }

    /**
     * Изменение/добавление кредитного лимита.
     * @param limit Новый кредитный лимит
     */
    public void addCreditLimit(BigDecimal limit)
    {
        creditLimit = limit;
    }

    /**
     * Создание аккаунта.
     * @param type Тип аккаунта (Debit/Credit/Deposit)
     * @param name Название аккаунта
     * @param startBalance Стартовый баланс
     * @return {@link Account}
     */
    public Account createAccount(String type, String name, BigDecimal startBalance)
    {
        Account account;
        switch (type)
        {
            case "Debit":
                account = new DebitAccount(name, this, startBalance);
                break;
            case "Credit":
                account = new CreditAccount(name, this, startBalance);
                break;
            case "Deposit":
                account = new CreditAccount(name, this, startBalance);
                break;
            default:
                throw new BankException("This type of account isn't exist");
        }

        accounts.add(account);
        return account;
    }

    /**
     * Удаление аккаунта.
     * @param account Аккаунт
     */
    public void removeAccount(Account account)
    {
        accounts.remove(account);
    }

}
