package ru.job4j;

/**
 * Engineer.
 *
 *@author smikhailov
 *@since 03.08.2017
 *@version 1
 */
public class Engineer extends Profession {

    public Engineer(String name, int age) {
        super(name, age);
    }

    /**
     * Heal.
     * @param
     * @return String
     */
    public String develops(String device) {
        return "Инженер " + this.getName() + " разрабатывает " + device;
    }
}
