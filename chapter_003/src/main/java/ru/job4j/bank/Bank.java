package ru.job4j.bank;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Bank {
    Map<User, List<Account>> usersBank= new HashMap<>();

    public void addUser(User user) {
        List<Account> listAccount = new ArrayList<>();
        usersBank.put(user, listAccount);
    }

    public void deleteUser(User user) {
        usersBank.remove(user);
    }

    public void addAccountToUser(User user, Account account) {
        usersBank.get(user).add(account);
    }

    public void deleteAccountFromUser(User user, Account account) {
        usersBank.get(user).remove(account);
    }

    public List<Account> getUserAccounts (User user) {
        return usersBank.get(user);
    }

    public boolean transferMoney (User srcUser, Account srcAccount, User dstUser, Account dstAccount, double amount) {
        boolean statusTransfer = false;

        List<Account> accounts = usersBank.get(srcUser);
        Account accountSrc = accounts.get(accounts.indexOf(srcAccount));

        accounts = usersBank.get(dstUser);
        Account accountDst = accounts.get(accounts.indexOf(dstAccount));

        if (accountSrc.getValue() >= amount) {
            accountSrc.setValue(accountSrc.getValue() - amount);
            accountDst.setValue(accountDst.getValue() + amount);
            statusTransfer = true;
        }

        return statusTransfer;
    }
}
