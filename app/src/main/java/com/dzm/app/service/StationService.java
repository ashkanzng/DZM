package com.dzm.app.service;

import com.dzm.app.domain.Company;
import com.dzm.app.domain.Station;
import com.dzm.app.repository.CompanyRepository;
import com.dzm.app.repository.StationRepository;
import com.dzm.app.service.errors.FieldErrorException;
import com.dzm.app.service.errors.ObjectNotFoundException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.env.MissingRequiredPropertiesException;
import org.springframework.data.crossstore.ChangeSetPersister;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.server.ResponseStatusException;

import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

@Service
public class StationService {

    private final Logger log = LoggerFactory.getLogger(StationService.class);

    private final StationRepository stationRepository;
    private final CompanyRepository companyRepository;

    public StationService(StationRepository stationRepository, CompanyRepository companyRepository) {
        this.stationRepository = stationRepository;
        this.companyRepository = companyRepository;
    }

    public Station createStation(Station station, Long companyId) {
        if (companyId == null) {
            log.error("companyId can not be null");
            throw new FieldErrorException("company_id can not be null");
        }
        Optional<Company> company = companyRepository.findById(companyId);
        if (company.isEmpty()) {
            throw new ObjectNotFoundException("Company can not be found");
        }
        station.setCompany(company.get());
        stationRepository.save(station);
        return station;
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
