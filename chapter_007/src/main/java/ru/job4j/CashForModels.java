package ru.job4j;

import java.util.concurrent.ConcurrentHashMap;
import java.util.function.BiFunction;

public class CashForModels {

    private final ConcurrentHashMap<Model, Integer> map = new ConcurrentHashMap<>();

    public void add(Model model) {
        map.put(model, 0);
    }

    public void update(Model newModel) {
        map.computeIfPresent(newModel, new BiFunction<Model, Integer, Integer>() {
            @Override
            public Integer apply(Model model, Integer oldVersion) {
                if (oldVersion == newModel.getVersion()) {
                    oldVersion++;
                } else {
                    throw new OplimisticException("different version");
                }
                return oldVersion;
            }
        });
    }

    public void delete(Model model) {
        map.remove(model);
    }

    class OplimisticException extends RuntimeException {
        public OplimisticException(String message) {
            super(message);
        }
    }
}
