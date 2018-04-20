package ru.job4j.application_2513;

public class UserWrapper{

    private int id;

    private String name;
    private String login;
    private String email;
    private String createDate;

    private String password;
    private String role;

    private String country;
    private String city;

    public UserWrapper(int id, User user) {
        this.id = id;
        this.name = user.getName();
        this.login = user.getLogin();
        this.email = user.getEmail();
        this.createDate = user.getCreateDate();
        this.password = user.getPassword();
        this.role = user.getRole();
        this.country = user.getCountry();
        this.city = user.getCity();
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getCreateDate() {
        return createDate;
    }

    public void setCreateDate(String createDate) {
        this.createDate = createDate;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }
}
