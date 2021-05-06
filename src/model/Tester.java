package model;

import api.MainMenu;

import java.text.ParseException;

import static api.MainMenu.mainMenu;

public class Tester {

    public static void main(String[] args) throws ParseException {

//        Customer customer2 = new Customer("First", "Second", "email");
//        System.out.println(customer2);
//
//        Customer customer = new Customer("Uche", "Nzei", "uchenzei18@gmail.com");
//        System.out.println(customer);

        MainMenu mainMenu = new MainMenu();
        mainMenu();

    }
}
