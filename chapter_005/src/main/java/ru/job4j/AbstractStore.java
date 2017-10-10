package ru.job4j;

public abstract class AbstractStore implements Store {
    private int lengthArray;
    SimpleArray<Base> array = new SimpleArray<>(lengthArray);

    public AbstractStore(int lengthArray) {
        this.lengthArray = lengthArray;
    }

    @Override
    public Base add(Base model) {
        array.add(model);
        return model;
    }

    @Override
    public Base update(Base model) {
        for (int i = 0; i < lengthArray; i++) {
            if (array.get(i).equals(model)) {
                array.update(i, model);
                return model;
            }
        }
        return model;
    }

    @Override
    public boolean delete(String id) {
        for (int i = 0; i < lengthArray; i++) {
            if (array.get(i).getId().equals(id)) {
                array.delete(i);
                return true;
            }
        }
        return false;
    }
}
