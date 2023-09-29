package controller;

import common.Library;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import model.Country;
import view.Menu;

public class ManageEastAsiaCountries extends Menu<String> {

    static String[] mc = {"Input the information of 11 countries in East Asia", "Display the information of country you've just input", "Search the information of country by user-entered name",
        "Display the information of countries sorted name in ascending", "Exit"};
    Library l;
    List<Country> list;

    public ManageEastAsiaCountries() {
        super("\nMENU", mc);
        l = new Library();
        list = new ArrayList<>();

    }

    @Override
    public void execute(int n) {
        switch (n) {
            case 1:
                inputCountry(list);
                break;
            case 2:
                printCountry(list);
                break;
            case 3:
                searchInformationByName(list);
                break;
            case 4:
                printCountrySorted(list);
                break;
            case 5:
                System.exit(0);
        }
    }

    public void inputCountry(List<Country> lc) {
        String countryCode = l.getString("Enter code of contry: ");
        while (!l.checkCountryExist(lc, countryCode)) {
            System.err.println("Country exist.");
            countryCode = l.getString("Enter code of contry again: ");
        }
        String countryName = l.getString("Enter name of contry:");
        double countryArea = l.checkInputDouble("Enter total area");
        while (countryArea <= 0) {
            System.out.println("Please enter a number greater than 0");
            countryArea = l.checkInputDouble("Enter total area again");
        }
        String countryTerrain = l.getString("Enter terrain of contry: ");
        lc.add(new Country(countryTerrain, countryCode, countryName, countryArea));
        System.err.println("Add successful.");
    }

    public void printCountry(List<Country> lc) {
        if (lc.isEmpty()) {
            System.out.println("Empty");
        } else {
            System.out.printf("%-10s%-25s%-20s%-25s\n", "ID", "Name", "Total Area",
                    "Terrain");
            for (Country country : lc) {
                country.display();
                System.out.println();
            }
        }

    }

    public void printCountrySorted(List<Country> lc) {
        List<Country> temp = lc;
        Collections.sort(temp);
        printCountry(temp);
    }

    public void searchInformationByName(List<Country> lc) {
        String countryName = l.getString("Enter the name you want to search for: ");
        List<Country> listsearch = new ArrayList<>();
        for (Country country : lc) {
            if (country.getCountryName().equalsIgnoreCase(countryName)) {
                listsearch.add(country);
            }
        }
        if (listsearch.isEmpty()) {
            System.out.println("No result");
        } else {
            printCountry(listsearch);
        }
    }
}
