package pl.sda.arppl4.hiebranterental.util;

import pl.sda.arppl4.hiebranterental.dao.CarDao;
import pl.sda.arppl4.hiebranterental.dao.ICarDao;
import pl.sda.arppl4.hiebranterental.parser.CarRentalParser;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner= new Scanner(System.in);
        CarDao carDao= new CarDao();

        CarRentalParser parser= new CarRentalParser(scanner, carDao);

        parser.parse();
    }
}
