package Banks.Accounts;
import Banks.*;

import java.math.BigDecimal;

/**
 * Интерфейс банковского акканта
 */
public interface Account {
    /**
     * Получение денег.
     * @param sum Сумма
     */
    void recive(BigDecimal sum);
    /**
     * Снятие денег.
     * @param sum Сумма снятия
     */
    void withdraw(BigDecimal sum);

    /**
     * Перевод на уровне банка.
     * @param phone Номер телефона получателя
     * @param sum Сумма перевода
     */
    void send(String phone, BigDecimal sum);

    /**
     * Межбанковский перевод.
     * @param bank Банк получателя
     * @param phone Номер телефона получателя
     * @param sum Сумма перевода
     */
    void send(Bank bank, String phone, BigDecimal sum);

    /**
     * Перевод непосредственно на конкреттный акк.
     * @param account Аккаунт получателя
     * @param sum Сумма перевода
     */
    void send(Account account, BigDecimal sum);

    /**
     * Перемотка на след месяц.
     */
    void nextMonth();
}
