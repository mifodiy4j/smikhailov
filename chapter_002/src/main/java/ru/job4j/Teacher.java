package ru.job4j;

/**
 * Teacher.
 *
 *@author smikhailov
 *@since 03.08.2017
 *@version 1
 */
public class Teacher extends Profession {

    public Teacher(String name, int age) {
        super(name, age);
    }

    /**
     * StudyCountStudents.
     * @param count
     * @return String
     */
    public String studyCountStudents(int count) {
        return "Учитель " + this.getName() + " учит " + count + " учеников";
    }
}
