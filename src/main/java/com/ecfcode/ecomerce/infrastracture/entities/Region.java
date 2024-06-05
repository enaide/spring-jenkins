package com.ecfcode.ecomerce.infrastracture.entities;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;
import static jakarta.persistence.GenerationType.AUTO;

@Getter
@Setter
@Entity(name = "Region")
@Table(name = "region")
public class Region {
    @Id
    @SequenceGenerator(
            name = "region_sequence",
            sequenceName = "region_sequence",
            allocationSize = 1
    )
    @GeneratedValue(
            strategy = AUTO,
            generator = "region_sequence"
    )
    @Column(name = "region_id", nullable = false)
    private Integer id;
    @Column(name = "region_description", nullable = false)
    private String description;

    @OneToMany(
            mappedBy = "region",
            orphanRemoval = true,
            cascade = CascadeType.ALL,
            fetch = FetchType.EAGER
    )
    List<Territory> territories = new ArrayList<Territory>();

    public Region() {
    }

    public Region(String description) {
        this.description = description;
    }

    public Region(int id, String description) {
        this.id = id;
        this.description = description;
    }

    @Override
    public String toString() {
        return "Region{" +
                "id=" + id +
                ", description='" + description + '\'' +
                '}';
    }
}
