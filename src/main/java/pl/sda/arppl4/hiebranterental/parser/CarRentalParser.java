package pl.sda.arppl4.hiebranterental.parser;

import pl.sda.arppl4.hiebranterental.dao.CarDao;
import pl.sda.arppl4.hiebranterental.model.BodyType;
import pl.sda.arppl4.hiebranterental.model.Car;
import pl.sda.arppl4.hiebranterental.model.GearBox;

import java.time.DateTimeException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Optional;
import java.util.Scanner;

public class CarRentalParser {
    private static DateTimeFormatter FORMATTER = DateTimeFormatter.ofPattern("yyyy-MM-dd");

    private final Scanner scanner;
    private final CarDao dao;

    public CarRentalParser(Scanner scanner, CarDao dao) {
        this.scanner = scanner;
        this.dao = dao;
    }

    public void parse() {
        String command = null;
        do {
            System.out.println("Command: [add,delete, returnCar]");
            command = scanner.next();

            if (command.equalsIgnoreCase("add")) {
                handleAddCommand();//
            } else if (command.equalsIgnoreCase("returnCar")) {
                handleListCommand();
            } else if (command.equalsIgnoreCase("delete")) {
                handleDeleteCommand();

            }
        } while (!command.equals("quit"));
    }

    private void handleDeleteCommand() {
        System.out.println("Provide id of the car u'dlike to remove");
        Long id = scanner.nextLong();

        Optional<Car> carOptional = dao.returnCar(id);
        if (carOptional.isPresent()) {
            Car car = carOptional.get();
            dao.deleteCar(car);

            System.out.println("Car remover");
        } else {
            System.out.println("Car not found");
        }

    }




    private void handleListCommand() {
        List<Car> carList=dao.returnListCars();
        for (Car car : carList) {
            System.out.println(car);
        }
        System.out.println();
    }




    private void handleAddCommand() {

        System.out.println("Provide name: ");
        String name = scanner.next();

        System.out.println("Provide brand");
        String brand = scanner.next();

        LocalDate productionDate = loadProductionDateFromUser();

//        System.out.println("Provide body type:");
//        String bodyType= scanner.next();
        BodyType bodyType = loadBodyTypeFromUser();

        System.out.println("Provide number of seats");
        Double numberOfSeats = scanner.nextDouble();

//        System.out.println("Provide type of gear box");
//        String gearBox= scanner.next();
        GearBox gearBox = loadGearBoxFromUser();

        System.out.println("Provide engine capacity:");
        Double engineCapacity = scanner.nextDouble();

        Car car = new Car(null, name, brand, productionDate, bodyType, numberOfSeats, gearBox, engineCapacity);
        dao.addCar(car);
    }

    private LocalDate loadProductionDateFromUser() {
        LocalDate productionDate = null;
        do {
            try {
                System.out.println("Provide productinDate:");
                String productionDateString = scanner.next();


                productionDate = LocalDate.parse(productionDateString, FORMATTER);

                LocalDate today = LocalDate.now();
                if (productionDate.isAfter(today)) {
                    //bląd, production date jest po dzisiejszym dniu)
                    throw new IllegalArgumentException("Date is after today.");
                }
            } catch (IllegalArgumentException | DateTimeException iae) {
                System.err.println("wrong date,please provide date in format :yyyy-MM-dd ");
            }
        } while (productionDate == null);
        return productionDate;
    }

    private BodyType loadBodyTypeFromUser() {
        BodyType bodyType = null;
        do {
            try {
                System.out.println("Provide bodyType:");
                String bodyTypeString = scanner.next();
                bodyType = BodyType.valueOf(bodyTypeString.toUpperCase());
            } catch (IllegalArgumentException iae) {
                System.err.println("Wrong ubody type, please provide body type from: sedan,cabrio,suv");
            }
        } while (bodyType == null);
        return bodyType;
    }

    private GearBox loadGearBoxFromUser() {
        GearBox gearBox = null;
        do {
            try {
                System.out.println("Provide gearbox type:automatic, manual");
                String gearBoxString = scanner.next();
                gearBox = GearBox.valueOf(gearBoxString.toUpperCase());
            } catch (IllegalArgumentException iae) {
                System.err.println("Wrong gear box type, please provide gearbox type from: automatic, manual");
            }
        } while (gearBox == null);
        return gearBox;
    }

}





