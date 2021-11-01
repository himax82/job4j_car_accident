package ru.job4j.di;

import java.util.Scanner;

public class StartUI {

    private Store store;
    private ConsoleInput input;

    public StartUI(Store store, ConsoleInput input) {
        this.store = store;
        this.input = input;
    }

    public void add() {
        store.add(input.getString());
    }

    public void print() {
        for (String value : store.getAll()) {
            System.out.println(value);
        }
    }
}