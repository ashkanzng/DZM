package com.dzm.app.repository;

import com.dzm.app.domain.Station;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface StationRepository extends JpaRepository<Station, Long> {

    @Query(value = "SELECT station_name FROM (SELECT *,( 6371 * acos(cos(radians(:latitude)) * cos(radians(latitude)) * cos(radians(longitude) - radians(:longitude)) " +
            " + sin(radians(:latitude)) * sin(radians(latitude )))) AS distance FROM station ORDER BY distance) as dis where distance < :radius", nativeQuery = true)
    List<String> findAllNearest(@Param("latitude") Double latitude, @Param("longitude") Double longitude, @Param("radius") int radius );
}
