package pl.sda.arppl4.hiebranterental.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
@Data
@Entity
@NoArgsConstructor
@AllArgsConstructor
public class Car {
    @Id//primary key
    @GeneratedValue(strategy= GenerationType.IDENTITY)//autoincrement
    private Long id;
//    nazwa
//- marka
//- data produkcji (pełna data, nie tylko rocznik, ale bez czasu)
//- typ nadwozia (SEDAN, CABRIO, SUV)
//- ilość pasażerów / miejsc siedzących
//- *typ skrzyni biegów (MANUAL, AUTO)
//- *pojemność silnika (Double)

private String name;
private  String brand;
private LocalDate productionDate;
@Enumerated (EnumType.STRING)
private  BodyType bodyType;
private Double numberOfSeats;
@Enumerated(EnumType.STRING)
private  GearBox gearbox;
private Double engineCapacity;
}
