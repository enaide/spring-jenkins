package com.ecfcode.ecomerce.infrastracture.entities;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity(name = "EmployeeTerritory")
@Table(name = "employee_territories")
public class EmployeeTerritory {
    @EmbeddedId
    private EmployeeTerritoryId id;
    @ManyToOne
    @MapsId("employeeId")
    @JoinColumn(name = "employeeId")
    private Employee employee;

    @ManyToOne
    @MapsId("territoryId")
    @JoinColumn(name = "territoryId")
    private Territory territory;

    public EmployeeTerritory() {
    }

    public EmployeeTerritory(Employee employee, Territory territory) {
        this.employee = employee;
        this.territory = territory;
    }
}
