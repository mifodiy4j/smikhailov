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
        if (usersBank.containsKey(user)) {
            List<Account> listAccount = new ArrayList<>();
            listAccount = usersBank.get(user);
            listAccount.add(account);
            usersBank.put(user, listAccount);
        } else {
            System.out.println("User not found");
        }
    }

    public void deleteAccountFromUser(User user, Account account) {
        if (usersBank.containsKey(user)) {
            List<Account> listAccount = new ArrayList<>();
            listAccount = usersBank.get(user);
            listAccount.remove(account);
            usersBank.put(user, listAccount);
        } else {
            System.out.println("User not found");
        }
    }

    public List<Account> getUserAccounts (User user) {
        List<Account> listAccount = new ArrayList<>();

        if (usersBank.containsKey(user)) {
            listAccount = usersBank.get(user);
        } else {
            System.out.println("User not found");
        }
        return listAccount;
    }

    public boolean transferMoney (User srcUser, Account srcAccount, User dstUser, Account dstAccount, double amount) {
        boolean statusTransfer = false;
        if (usersBank.containsKey(srcUser) && usersBank.containsKey(dstUser)) {
            List<Account> listAccountSrc = usersBank.get(srcUser);
            List<Account> listAccountDst = usersBank.get(dstUser);
            if (listAccountSrc.contains(srcAccount) && listAccountDst.contains(dstAccount)) {
                for (Account accountSrc : listAccountSrc) {
                    if (accountSrc.equals(srcAccount)) {
                        for (Account accountDst : listAccountDst) {
                            if (accountDst.equals(dstAccount)) {

                                if (accountSrc.getValue() >= amount) {
                                    accountSrc.setValue(accountSrc.getValue() - amount);
                                    accountDst.setValue(accountDst.getValue() + amount);
                                    statusTransfer = true;
                                }
                            }
                        }


                    }
                }
            }
        }
        return statusTransfer;
    }
}


/*
Необходимо реализовать возможность перечислять деньги, как с одного счёта User на другой счёт того же User, так и на счёт другого User.

В программе должны быть методы:

public void addUser(User user) {} - добавление пользователя.

public void deleteUser(User user) {} - удаление пользователя.

public void addAccountToUser(User user, Account account) {} - добавить счёт пользователю.

public void deleteAccountFromUser(User user, Account account) {} - удалить один счёт пользователя.

public List<Accounts> getUserAccounts (User user) {} - получить список счетов для пользователя.

public boolean transferMoney (User srcUser, Account srcAccount, User dstUser, Account dstAccount, double amount) - метод для перечисления денег с одного счёта на другой счёт:
если счёт не найден или не хватает денег на счёте srcAccount (с которого переводят) должен вернуть false.
*/