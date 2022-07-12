package com.dzm.app.service;

import com.dzm.app.domain.Company;
import com.dzm.app.domain.Station;
import com.dzm.app.repository.CompanyRepository;
import com.dzm.app.repository.StationRepository;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

@Service
public class StationService {

    private final StationRepository stationRepository;
    private final CompanyRepository companyRepository;

    public StationService(StationRepository stationRepository, CompanyRepository companyRepository) {
        this.stationRepository = stationRepository;
        this.companyRepository = companyRepository;
    }

    public Optional<Station> findNearestStations(Long companyId,Double latitude, Double longitude,Integer radius){
        Set<Station> companyStations = new HashSet<>();
        if (companyId == null){
            companyStations.addAll(stationRepository.findAll());
        }else {
            companyRepository.findById(companyId).ifPresent(company -> findAllCompanyStationIncludeChildren(companyStations,company));
        }
        final List<Station> nearestStations = stationRepository.findAllNearest(latitude,longitude,radius);
        return nearestStations.stream().filter(station -> companyStations.contains(station)).findFirst();
    }

    /**
     * Find all company children's stations
     */
    private void findAllCompanyStationIncludeChildren(Set<Station> stations, Company company){
        stations.addAll(company.getStations());
        company.getCompanies().forEach( child -> findAllCompanyStationIncludeChildren(stations,child));
    }
}
