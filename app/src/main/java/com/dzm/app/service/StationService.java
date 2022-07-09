package com.dzm.app.service;

import com.dzm.app.domain.Company;
import com.dzm.app.domain.Station;
import com.dzm.app.repository.CompanyRepository;
import com.dzm.app.repository.StationRepository;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class StationService {
    private final StationRepository stationRepository;
    private final CompanyRepository companyRepository;

    public StationService(StationRepository stationRepository, CompanyRepository companyRepository) {
        this.stationRepository = stationRepository;
        this.companyRepository = companyRepository;
    }

    public List<Station> findAllNearestStations(Long companyId){
        Set<Station> companyStations = new HashSet<>();
        companyRepository.findById(companyId).ifPresent(company -> findAllCompanyStationIncludeChildren(companyStations,company));
        List<Station> nearestStations = stationRepository.findAllNearest(40.181634444726775,44.508565357788,25);
        return nearestStations.stream().filter(station -> companyStations.contains(station)).collect(Collectors.toList());
    }

    /**
     * Find all company station include children
     */
    private void findAllCompanyStationIncludeChildren(Set<Station> stations, Company company){
        stations.addAll(company.getStations());
        company.getCompanies().forEach( child -> findAllCompanyStationIncludeChildren(stations,child));
    }
}
