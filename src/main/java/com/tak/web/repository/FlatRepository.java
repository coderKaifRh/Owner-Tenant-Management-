package com.tak.web.repository;

import com.tak.web.model.Flat;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface FlatRepository extends JpaRepository<Flat, Integer> {
    List<Flat> findByOwnerId(Integer ownerId);
    List<Flat> findByStatus(String status);

    @Query("SELECT f FROM Flat f WHERE f.status = 'Available' " +
           "AND (:location IS NULL OR :location = '' OR f.location = :location) " +
           "AND (:maxRent IS NULL OR f.rent <= :maxRent) " +
           "AND (:minSize IS NULL OR f.size >= :minSize) " +
           "ORDER BY f.rent ASC, f.size DESC")
    List<Flat> searchFlats(@Param("location") String location,
                           @Param("maxRent") Double maxRent,
                           @Param("minSize") Integer minSize);
}
