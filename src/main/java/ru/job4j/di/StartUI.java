package ru.job4j.di;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Scanner;
@Component
public class StartUI {

    @Autowired
    private Store store;

    @Autowired
    private ConsoleInput input;

    public void add() {
        store.add(input.getString());
    }

    public void print() {
        for (String value : store.getAll()) {
            System.out.println(value);
        }
    }
}