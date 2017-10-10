package ru.job4j;

public class UserStore extends AbstractStore {
    private int lengthUserArray;
    SimpleArray<Base> userArray = new SimpleArray<>(lengthUserArray);

    public UserStore(int lengthUserArray) {
        super(lengthUserArray);
    }
}
