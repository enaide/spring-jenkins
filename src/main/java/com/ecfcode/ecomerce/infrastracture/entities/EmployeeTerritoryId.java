package com.ecfcode.ecomerce.infrastracture.entities;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;

import java.io.Serializable;
import java.util.Objects;

@Embeddable
public class EmployeeTerritoryId implements Serializable {

    @Column(name = "employee_id")
    private Integer employeeId;

    @Column(name = "territory_id")
    private String territoryId;

    public EmployeeTerritoryId() {}
    public EmployeeTerritoryId(Integer employeeId, Integer territoryId) {
        this.employeeId = employeeId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof EmployeeTerritoryId)) return false;
        EmployeeTerritoryId that = (EmployeeTerritoryId) o;
        return Objects.equals(employeeId, that.employeeId) && Objects.equals(territoryId, that.territoryId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(employeeId, territoryId);
    }
}
