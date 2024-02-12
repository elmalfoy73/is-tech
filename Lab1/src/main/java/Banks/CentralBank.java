package Banks;

import lombok.Getter;

import java.math.BigDecimal;
import java.util.ArrayList;

/**
 * Класс центрального банка
 */
public class CentralBank {
    @Getter
    private ArrayList<Bank> banks;
    public CentralBank()
    {
        banks = new ArrayList<>();
    }

    /**
     * Создание банка.
     * @param name Название банка
     * @param fees Коммисия по кредитам
     * @param interestOnAccount Процент на остаток
     * @param limit Лимит для "подозрительных клиентов"
     * @return {@link Bank}
     */
    public Bank createBank(String name, BigDecimal fees, BigDecimal interestOnAccount, BigDecimal limit)
    {
        if (banks.stream().anyMatch(b -> b.getName() == name))
            throw new BankException("Bank's name isn't unique");
        Bank bank = new Bank(name, fees, interestOnAccount, limit, this);
        banks.add(bank);
        return bank;
    }

    /**
     * Поиск клиента.
     * @param bank Банк клиента
     * @param phone Номер телефона клиента
     * @return {@link Client}
     */
    public Client findClient(Bank bank, String phone)
    {
        return bank.getClients().stream().filter(c -> c.getPhone() == phone).findFirst().get();
    }

    /**
     * Перевод на межбанковском уровне, "СБП".
     * @param bank Банк, в которой хотим перевести
     * @param phone Номер клиента
     * @param sum Сумма перевода
     */
    public void sendWithSBP(Bank bank, String phone, BigDecimal sum)
    {
        Client client = findClient(bank, phone);
        client.getDefaultAccount().recive(sum);
    }

    /**
     * Отмотка времени на след месяц.
     */
    public void nextMonth()
    {
        banks.forEach(b -> b.nextMonth());
    }
}