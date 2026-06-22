package org.example.promotionservice.repository;

import org.example.promotionservice.entity.PromotionsEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;


public interface PromotionRepository extends JpaRepository<PromotionsEntity, String> {
    @Modifying
    @Transactional
    @Query("UPDATE PromotionsEntity p SET p.usedCount = p.usedCount + 1 " +
            "WHERE p.id = :id")
    Integer incrementUsedCount(@Param("id") String id);
}
