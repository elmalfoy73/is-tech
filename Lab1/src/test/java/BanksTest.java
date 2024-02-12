import Banks.*;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;


public class BanksTest {
    private CentralBank cb = new CentralBank();

    @Test
    public void createClient()
    {
        Bank tink = cb.createBank("Tinkoff", new BigDecimal("100"), new BigDecimal("3.65"), new BigDecimal("50000"));
        ClientBuilder builder = new ClientBuilder(tink);
        builder.AddFullName("Трегубович Елизавета").AddPhone("+79215504851");
        Client client = tink.createClient(builder);
        BigDecimal startBalance = new BigDecimal("10000");
        client.getDefaultAccount().recive(startBalance);

        assertTrue(cb.getBanks().contains(tink));
        assertTrue(tink.getClients().contains(client));
        assertEquals(startBalance, client.getDefaultAccount().getBalance());
    }

    @Test
    public void sendWithSBP()
    {
        Bank tink = cb.createBank("Tinkoff", new BigDecimal("100"), new BigDecimal("3.65"), new BigDecimal("50000"));
        ClientBuilder builder = new ClientBuilder(tink);
        builder.AddFullName("Трегубович Елизавета").AddPhone("+79215504851");
        Client client = tink.createClient(builder);
        BigDecimal startBalance = new BigDecimal("10000");
        client.getDefaultAccount().recive(startBalance);

        Bank sber = cb.createBank("Sber", new BigDecimal(100), new BigDecimal("3.65"), new BigDecimal(50000));
        ClientBuilder builder1 = new ClientBuilder(sber);
        builder1.AddFullName("Мастер по ноготочкам").AddPhone("+79*********");
        Client client1 = sber.createClient(builder1);

        BigDecimal sum = new BigDecimal(2000);
        client.getDefaultAccount().send(sber, client1.getPhone(), sum);

        assertEquals(startBalance.subtract(sum), client.getDefaultAccount().getBalance());
        assertEquals(sum, client1.getDefaultAccount().getBalance());
        assertEquals(2, client.getDefaultAccount().getTransactions().size());
        assertEquals(1, client1.getDefaultAccount().getTransactions().size());
    }

    @Test
    public void interestOnAccount()
    {
        Bank tink = cb.createBank("Tinkoff", new BigDecimal("100"), new BigDecimal("3.65"), new BigDecimal("50000"));
        ClientBuilder builder = new ClientBuilder(tink);
        builder.AddFullName("Трегубович Елизавета").AddPhone("+79215504851");
        Client client = tink.createClient(builder);
        client.addAddress("x");
        client.addPassport(1234, 123456);

        client.getDefaultAccount().recive(new BigDecimal("100000"));
        client.getDefaultAccount().recive(new BigDecimal("100000"));
        client.getDefaultAccount().withdraw(new BigDecimal("150000"));
        assertEquals(0, new BigDecimal("35").compareTo(client.getDefaultAccount().getInterestOnAccount()));
        assertEquals(new BigDecimal("50000"), client.getDefaultAccount().getBalance());

        BigDecimal interestOnAccount = client.getDefaultAccount().getInterestOnAccount();
        BigDecimal endMonthBalance = client.getDefaultAccount().getBalance();
        cb.nextMonth();
        assertEquals(endMonthBalance.add(interestOnAccount), client.getDefaultAccount().getBalance());
    }
}