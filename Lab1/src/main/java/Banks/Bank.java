package Banks;

import Banks.Accounts.Account;
import lombok.Getter;

import java.math.BigDecimal;
import java.util.ArrayList;


/**
 * Класс банка
 */
public class Bank {
    @Getter
    private ArrayList<Client> clients;
    @Getter
    private String name;
    @Getter
    private BigDecimal fees;
    @Getter
    private BigDecimal interestOnAccount;
    @Getter
    private BigDecimal limit;
    @Getter
    private CentralBank centralBank;
    public Bank(String name, BigDecimal fees, BigDecimal interestOnAccount, BigDecimal limit, CentralBank centralBank) {
        if (name == null || name.isEmpty() || name.trim().isEmpty()) throw new BankException("Banks.Bank's name is null or white space");
        this.name = name;
        this.fees = fees;
        this.interestOnAccount = interestOnAccount;
        this.limit = limit;
        this.centralBank = centralBank;
        clients = new ArrayList<>();
    }

    /**
     * Метод создания клиента.
     * @param builder Билдер
     * @return {@link Client}
     */
    public Client createClient(ClientBuilder builder)
    {
        if (clients.stream().anyMatch(c -> c.getPhone().equals(builder.phone)))
            throw new BankException("Client's phone isn't unique");
        Client client = builder.Build();
        clients.add(client);
        return client;
    }

    /**
     * Перевод денег на уровне банка.
     * @param phone Номер телефона получателя
     * @param sum Сумма перевода
     */
    public void send(String phone, BigDecimal sum)
    {
        Client client = clients.stream().filter(c -> c.getPhone().equals(phone)).findFirst().get();
        client.getDefaultAccount().recive(sum);
    }

    /**
     * Изменение лимита для "подозрительных" клиентов.
     * @param limit Новый лимит
     */
    public void changeLimit(BigDecimal limit)
    {
        this.limit = limit;
    }

    /**
     * Изменение процента на остаток.
     * @param interestOnAccount Новый процент на остаток
     */
    public void changeInterestOnAccount(BigDecimal interestOnAccount)
    {
        this.interestOnAccount = interestOnAccount;
    }

    /**
     * Изменение коммисии по кредитам.
     * @param fees Новая коммисия по кредитам
     */
    public void changeFees(BigDecimal fees)
    {
        this.fees = fees;
    }

    /**
     * Отмотка времени на след месяц.
     */
    public void nextMonth()
    {
        for (Client client : clients)
        {
            for (Account account : client.getAccounts())
            {
                account.nextMonth();
            }
        }
    }
}
