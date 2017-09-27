package ru.job4j.bank;

public class User {
    private String name;
    private String passport;

    public User(String name, String passport) {
        this.name = name;
        this.passport = passport;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setPassport(String passport) {
        this.passport = passport;
    }

    public String getPassport() {
        return passport;
    }

    public boolean eqauls(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;

        User otherUser = (User) obj;
        return (name.equals(otherUser.name) && passport.equals(otherUser.passport));
    }

    public int hashCode() {
        int hash = 17;
        hash = 37 * hash + name.hashCode();
        hash = 37 * hash + passport.hashCode();
        return hash;
    }
}
