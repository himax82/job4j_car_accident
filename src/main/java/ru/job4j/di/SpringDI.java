package ru.job4j.di;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class SpringDI {
    public static void main(String[] args) {
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext();
        context.scan("ru.job4j.di");
        context.refresh();
        StartUI ui1 = context.getBean(StartUI.class);
        ui1.add();
        ui1.print();
        StartUI ui2 = context.getBean(StartUI.class);
        ui2.add();
        ui2.print();
    }
}