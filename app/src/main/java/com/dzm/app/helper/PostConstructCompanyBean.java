package com.dzm.app.helper;

import com.dzm.app.domain.Company;
import com.dzm.app.domain.Station;
import com.dzm.app.repository.CompanyRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.Set;

@Component
public class PostConstructCompanyBean {


    private final Logger log = LoggerFactory.getLogger(PostConstructCompanyBean.class);

    private final CompanyRepository companyRepository;

    public PostConstructCompanyBean(CompanyRepository companyRepository) {
        this.companyRepository = companyRepository;
    }

    @PostConstruct
    public void init() {
        //companyRepository.deleteAll();
        if (companyRepository.findAll().isEmpty()) {
            log.info("Creating dummy data");

            Station stationA = Station.builder()
                    .stationName("Station Republic Square")
                    .latitude(40.177391879910395)
                    .longitude(44.51295960190806)
                    .build();

            Station stationB = Station.builder()
                    .stationName("Station Mashtots")
                    .latitude(40.18424070432027)
                    .longitude(44.5116439087157)
                    .build();

            Station stationC = Station.builder()
                    .stationName("Station Paronyan")
                    .latitude(40.17809325175044)
                    .longitude(44.502678986048124)
                    .build();

            Station stationD = Station.builder()
                    .stationName("Station Khanjyan")
                    .latitude(40.17397571525264)
                    .longitude(44.518394954848425)
                    .build();

            Company companyA = Company.builder().companyName("A").build();
            Company companyB = Company.builder().companyName("B").build();
            Company companyC = Company.builder().companyName("C").build();
            Company companyD = Company.builder().companyName("D").build();
            companyB.setCompanies(Set.of(companyC,companyD));
            companyC.setStations(Set.of(stationA,stationB,stationC));
            companyD.setStations(Set.of(stationD));

            companyA.setCompanies(Set.of(companyB));
            companyRepository.save(companyA);
        } else {
            log.warn("Dummy data already inserted");
        }
    }
}
