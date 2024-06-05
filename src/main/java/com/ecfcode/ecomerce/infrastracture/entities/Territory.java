package com.ecfcode.ecomerce.infrastracture.entities;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity(name = "Territory")
@Table(name = "territories",
        uniqueConstraints = {
                @UniqueConstraint(name = "territory_id_unique", columnNames = "territory_id")
        })
public class Territory {
    @Id
    @Column(name = "territory_id", length = 20, nullable = false)
    private String id;

    @Column(name = "territory_description", nullable = false)
    private String description;

    @ManyToOne
    @JoinColumn(
            name = "region_id",
            nullable = false,
            referencedColumnName = "region_id",
            foreignKey = @ForeignKey(
                    name = "region_territory_fk"
            )
    )
    private Region region;

    public Territory() {}
    public Territory(String id, String description, Region region) {}

    @Override
    public String toString() {
        return "Territory{" +
                "id='" + id + '\'' +
                ", description='" + description + '\'' +
                ", region=" + region +
                '}';
    }
}
