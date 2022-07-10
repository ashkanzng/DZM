package com.dzm.app.service;

import com.dzm.app.domain.Company;
import com.dzm.app.repository.CompanyRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CompanyService {

    private final CompanyRepository companyRepository;

    public CompanyService(CompanyRepository companyRepository) {
        this.companyRepository = companyRepository;
    }

    public List<Company> getAll() {
        return companyRepository.findAll();
    }

    public Company createCompany (Company company,long parentCompanyId) {
        companyRepository.findById(parentCompanyId).ifPresent(company::setParentCompany);
        return companyRepository.save(company);
    }
}
