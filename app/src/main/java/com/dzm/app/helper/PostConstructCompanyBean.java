package com.dzm.app.helper;

import com.dzm.app.domain.Company;
import com.dzm.app.domain.Station;
import com.dzm.app.repository.CompanyRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.List;
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
        log.info("Deleting dummy data and creating again");
        companyRepository.deleteAll();

        Station republicSquare = Station.builder()
                .stationName("Station Republic Square")
                .latitude(40.177391879910395)
                .longitude(44.51295960190806)
                .build();

        Station mashtots = Station.builder()
                .stationName("Station Mashtots")
                .latitude(40.18424070432027)
                .longitude(44.5116439087157)
                .build();

        Station paronyan = Station.builder()
                .stationName("Station Paronyan")
                .latitude(40.17809325175044)
                .longitude(44.502678986048124)
                .build();

        Station khanjyan = Station.builder()
                .stationName("Station Khanjyan")
                .latitude(40.17397571525264)
                .longitude(44.518394954848425)
                .build();

        Station williamSaroyan = Station.builder()
                .stationName("William Saroyan statue")
                .latitude(40.187920698133006)
                .longitude(44.51592500876424)
                .build();

        Station koryun13 = Station.builder()
                .stationName("13 Koryun street")
                .latitude(40.18865248068826)
                .longitude(44.52335872017372)
                .build();

        Company tsla = Company.builder().companyName("TSLA").build();
        Company nio = Company.builder().companyName("NIO").build();
        Company liAuto = Company.builder().companyName("Li Auto").parentCompany(nio).build();
        Company workhorse = Company.builder().companyName("Work horse").parentCompany(tsla).build();

        workhorse.setStations(Set.of(republicSquare,mashtots,paronyan,khanjyan));
        liAuto.setStations(Set.of(williamSaroyan,koryun13));

        companyRepository.saveAll(List.of(tsla,nio,liAuto,workhorse));
    }
}
