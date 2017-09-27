package ru.job4j.bank;

import ru.job4j.*;

import java.util.*;

import org.junit.Test;
import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

/**
* Test.
*
* @author Sergey Mikhailov (mailto:mifodiy67@mail.ru)
* @version $Id$
* @since 0.1
*/
public class BankTest {
	/**
	* Test addUser.
	*/
   @Test
   public void whenAdd3UsersWithNullAccount() {
       Bank bank = new Bank();
       User user1 = new User("Anna","123456");
       bank.addUser(user1);
       User user2 = new User("Bin","222222");
       bank.addUser(user2);
       User user3 = new User("Victor","111111");
       bank.addUser(user3);

       Map<User, List<Account>> expected = new HashMap<>();
       expected.put(user1, new ArrayList<>());
       expected.put(user2, new ArrayList<>());
       expected.put(user3, new ArrayList<>());
       assertThat(bank.usersBank, is(expected));
   }

    /**
     * Test deleteUser.
     */
    @Test
    public void whenAdd3UsersWithNullAccountAndDelete1User() {
        Bank bank = new Bank();
        User user1 = new User("Anna","123456");
        bank.addUser(user1);
        User user2 = new User("Bin","222222");
        bank.addUser(user2);
        User user3 = new User("Victor","111111");
        bank.addUser(user3);
        bank.deleteUser(user2);

        Map<User, List<Account>> expected = new HashMap<>();
        expected.put(user1, new ArrayList<>());
        expected.put(user3, new ArrayList<>());
        assertThat(bank.usersBank, is(expected));
    }

    /**
     * Test getUserAccounts.
     */
    @Test
    public void whenGetUserAccounts() {
        Bank bank = new Bank();

        User user1 = new User("Anna","123456");
        bank.addUser(user1);
        User user2 = new User("Bin","222222");
        bank.addUser(user2);

        Account account1User1 = new Account(100000.55, 1234_5678_9123_4567l);
        Account account2User1 = new Account(999.99, 5678_9123_4567_8912l);
        Account account3User1 = new Account(3333.33, 9123_4567_8912_3456l);
        bank.addAccountToUser(user1, account1User1);
        bank.addAccountToUser(user1, account2User1);
        bank.addAccountToUser(user1, account3User1);

        Account account1User2 = new Account(77777.77, 4567_8912_3456_7891l);
        bank.addAccountToUser(user2, account1User2);

        Map<User, List<Account>> expected = new HashMap<>();
        List<Account> listAccount1 = new ArrayList<>();
        listAccount1.add(account1User1);
        listAccount1.add(account2User1);
        listAccount1.add(account3User1);
        List<Account> listAccount2 = new ArrayList<>();
        listAccount2.add(account1User2);
        expected.put(user1, listAccount1);
        expected.put(user2, listAccount2);
        assertThat(bank.usersBank, is(expected));
    }

    /**
     * Test transferMoney.
     */
    @Test
    public void whenControlValidTransferMoneyFromUser1Account2ToUser2() {
        Bank bank = new Bank();

        User user1 = new User("Anna","123456");
        bank.addUser(user1);
        User user2 = new User("Bin","222222");
        bank.addUser(user2);

        Account account1User1 = new Account(100000.55, 1234_5678_9123_4567l);
        Account account2User1 = new Account(999.99, 5678_9123_4567_8912l);
        Account account3User1 = new Account(3333.33, 9123_4567_8912_3456l);
        bank.addAccountToUser(user1, account1User1);
        bank.addAccountToUser(user1, account2User1);
        bank.addAccountToUser(user1, account3User1);

        Account account1User2 = new Account(77777.77, 4567_8912_3456_7891l);
        bank.addAccountToUser(user2, account1User2);

        //bank.transferMoney(user1, account1User2, user2, account1User2, 111.11);

        /*List<Account> listAccount = bank.usersBank.get(user1);
        Account resultAccount = listAccount.get(1);
        double resultValueUser1 = resultAccount.getValue();
        double expectedValueUser1 = 888.88;*/

        assertThat(bank.transferMoney(user1, account2User1, user2, account1User2, 111.11), is(true));
    }

    /**
     * Test transferMoney.
     */
    @Test
    public void whenControlAccountTransferMoneyFromUser1Account2ToUser2() {
        Bank bank = new Bank();

        User user1 = new User("Anna","123456");
        bank.addUser(user1);
        User user2 = new User("Bin","222222");
        bank.addUser(user2);

        Account account1User1 = new Account(100000.55, 1234_5678_9123_4567l);
        Account account2User1 = new Account(999.99, 5678_9123_4567_8912l);
        Account account3User1 = new Account(3333.33, 9123_4567_8912_3456l);
        bank.addAccountToUser(user1, account1User1);
        bank.addAccountToUser(user1, account2User1);
        bank.addAccountToUser(user1, account3User1);

        Account account1User2 = new Account(77777.77, 4567_8912_3456_7891l);
        bank.addAccountToUser(user2, account1User2);

        bank.transferMoney(user1, account2User1, user2, account1User2, 111.11);

        List<Account> listAccountUser1 = bank.usersBank.get(user1);
        Account resultAccountUser1 = listAccountUser1.get(1);
        double resultValueUser1 = resultAccountUser1.getValue();

        List<Account> listAccountUser2 = bank.usersBank.get(user2);
        Account resultAccountUser2 = listAccountUser2.get(0);
        double resultValueUser2 = resultAccountUser2.getValue();

        assertThat(resultValueUser1, is(888.88));
        assertThat(resultValueUser2, is(77888.88));
    }
}