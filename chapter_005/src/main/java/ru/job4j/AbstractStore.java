package ru.job4j;

public abstract class AbstractStore implements Store {

    private int position = 0;
    SimpleArray<Base> array;

    public AbstractStore(int lengthArray) {
        array = new SimpleArray<>(lengthArray);
    }

    @Override
    public Base add(Base model) {
        array.add(model);
        position++;
        return model;
    }

    @Override
    public Base update(Base model) {
        for (int i = 0; i < position; i++) {
            if (array.get(i).getId().equals(model.getId())) {
                array.update(i, model);
                return model;
            }
        }
        return model;
    }

    @Override
    public boolean delete(String id) {
        for (int i = 0; i < position; i++) {
            if (array.get(i).getId().equals(id)) {
                array.delete(i);
                position--;
                return true;
            }
        }
        return false;
    }
}
