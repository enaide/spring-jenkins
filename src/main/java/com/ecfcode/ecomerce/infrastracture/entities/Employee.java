package com.ecfcode.ecomerce.infrastracture.entities;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

import static jakarta.persistence.GenerationType.AUTO;

@Setter
@Getter
@Entity(name = "Employee")
@Table(name = "employees")
public class Employee {
    @Id
    @SequenceGenerator(
            name = "employee_sequence",
            sequenceName = "employee_sequence",
            allocationSize = 1
    )
    @GeneratedValue(
            strategy = AUTO,
            generator = "employee_sequence"
    )
    @Column(name = "employee_id", nullable = false)
    private int id;

    @Column(name="first_name")
    private String firstName;

    @Column(name="last_name")
    private String lastName;

    @Column(name="title")
    private String title;

    @Column(name="birth_date")
    private LocalDate birthDate;

    @Column(name="hire_date")
    private LocalDate hireDate;

    @Column(name="address")
    private String address;

    @Column(name="city")
    private String city;

    @Column(name="region")
    private String region;

    @Column(name="postal_code")
    private String postalCode;

    @Column(name="country")
    private String country;

    @Column(name="home_phone")
    private String homePhone;

    @Column(name="extension")
    private String extension;

    @Column(name="notes",
            columnDefinition = "TEXT")
    private String notes;

    @Column(name = "reports_to")
    private Integer reportsTo;

    public Employee() {
    }

    public Employee(int id,
                    String firstName,
                    String lastName,
                    String title,
                    LocalDate birthDate,
                    LocalDate hireDate,
                    String address,
                    String city,
                    String region,
                    String postalCode,
                    String country,
                    String homePhone,
                    String extension,
                    String notes,
                    Integer reportsTo) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.title = title;
        this.birthDate = birthDate;
        this.hireDate = hireDate;
        this.address = address;
        this.city = city;
        this.region = region;
        this.postalCode = postalCode;
        this.country = country;
        this.homePhone = homePhone;
        this.extension = extension;
        this.notes = notes;
        this.reportsTo = reportsTo;
    }

    public Employee(String firstName,
                    String lastName,
                    String title,
                    LocalDate birthDate,
                    LocalDate hireDate,
                    String address,
                    String city,
                    String region,
                    String postalCode,
                    String country,
                    String homePhone,
                    String extension,
                    String notes,
                    Integer reportsTo) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.title = title;
        this.birthDate = birthDate;
        this.hireDate = hireDate;
        this.address = address;
        this.city = city;
        this.region = region;
        this.postalCode = postalCode;
        this.country = country;
        this.homePhone = homePhone;
        this.extension = extension;
        this.notes = notes;
        this.reportsTo = reportsTo;
    }
}
