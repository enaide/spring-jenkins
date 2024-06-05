package com.ecfcode.ecomerce.infrastracture.repository;

import com.ecfcode.ecomerce.infrastracture.entities.Territory;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TerritoryRepository extends JpaRepository<Territory, Long> {
}
