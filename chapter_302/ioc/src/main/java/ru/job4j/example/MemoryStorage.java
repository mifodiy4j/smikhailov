package ru.job4j.example;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Component
public class MemoryStorage implements Storage {
    private static final Logger Log = LoggerFactory.getLogger(MemoryStorage.class);

    @Override
    public void add(User user) {
        System.out.println("store in memory");
    }
}
