package Banks;

import java.math.BigDecimal;

/**
 * Класс билдера для клиента
 */
public class ClientBuilder {
    protected Bank bank;
    protected String fullName;
    protected String phone;
    protected String address;
    protected Passport passport;
    protected BigDecimal creditLimit;

    public ClientBuilder(Bank bank) {
        this.bank = bank;
    }

    /**
     * Добавление имени
     * @param fullName
     * @return ClientBuilder
     */
    public ClientBuilder AddFullName(String fullName) {
        if (fullName == null || fullName.trim().isEmpty())
            throw new BankException("Banks.Client's name is null or white space");
        this.fullName = fullName;
        return this;
    }

    /**
     * Добавление телефона
     * @param phone
     * @return ClientBuilder
     */
    public ClientBuilder AddPhone(String phone) {
        this.phone = phone;
        return this;
    }

    /**
     * Добавление адресса
     * @param address
     * @return ClientBuilder
     */
    public ClientBuilder AddAddress(String address) {
        if (address == null || address.trim().isEmpty())
            throw new BankException("Banks.Client's address is null or white space");
        this.address = address;
        return this;
    }

    /**
     * Добавление пасспорта
     * @param series
     * @param no
     * @return ClientBuilder
     */
    public ClientBuilder AddPassport(int series, int no) {
        this.passport = new Passport(series, no);
        return this;
    }

    /**
     * Добавление кредитного лимита
     * @param limit
     * @return
     */
    public ClientBuilder AddCreditLimit(BigDecimal limit) {
        this.creditLimit = limit;
        return this;
    }

    /**
     * "Билд клиента"
     * @return Client
     */
    public Client Build() {
        Client client = null;
        if (ValidateClient())
            client = new Client(this);
        else
            throw new BankException("Fullname and phone are required");
        return client;
    }

    /**
     * Проверка на заполнение обязательных полей
     * @return bolean
     */
    public boolean ValidateClient() {
        return (fullName != null && !fullName.trim().isEmpty() && phone != null);
    }
}
