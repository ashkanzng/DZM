package com.dzm.app.helper;

import com.dzm.app.domain.Company;
import com.dzm.app.domain.Station;
import com.dzm.app.repository.CompanyRepository;
import jakarta.annotation.PostConstruct;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.util.Set;

@Component
public class PostConstructCompanyBean {


    private final Logger log = LoggerFactory.getLogger(PostConstructCompanyBean.class);

    private CompanyRepository companyRepository;

    public PostConstructCompanyBean(CompanyRepository companyRepository) {
        this.companyRepository = companyRepository;
    }

    @PostConstruct
    public void init() {
        if (companyRepository.findAll().isEmpty()) {
            log.info("Creating dummy data");
            Station stationA = Station.builder()
                    .stationName("s 1")
                    .latitude(40.17723058095113)
                    .longitude(44.511008166268056).build();
            Company companyB = Company.builder().companyName("B").build();
            companyB.setStations(Set.of(stationA));
            Company companyA = Company.builder().companyName("A").build();
            companyRepository.save(companyB);
            companyRepository.save(companyA);
        } else {
            log.info("Dummy data already inserted");
        }
    }
}
