package com.compter.copter.service;

import com.compter.copter.domain.Company;
import com.compter.copter.model.CompanyDTO;
import com.compter.copter.repos.CompanyRepository;
import com.compter.copter.util.NotFoundException;
import java.util.List;
import java.util.stream.Collectors;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;


@Service
public class CompanyService {

    private final CompanyRepository companyRepository;

    public CompanyService(final CompanyRepository companyRepository) {
        this.companyRepository = companyRepository;
    }

    public List<CompanyDTO> findAll() {
        final List<Company> companys = companyRepository.findAll(Sort.by("companyId"));
        return companys.stream()
                .map((company) -> mapToDTO(company, new CompanyDTO()))
                .collect(Collectors.toList());
    }

    public CompanyDTO get(final Long companyId) {
        return companyRepository.findById(companyId)
                .map(company -> mapToDTO(company, new CompanyDTO()))
                .orElseThrow(() -> new NotFoundException());
    }

    public Long create(final CompanyDTO companyDTO) {
        final Company company = new Company();
        mapToEntity(companyDTO, company);
        return companyRepository.save(company).getCompanyId();
    }

    public void update(final Long companyId, final CompanyDTO companyDTO) {
        final Company company = companyRepository.findById(companyId)
                .orElseThrow(() -> new NotFoundException());
        mapToEntity(companyDTO, company);
        companyRepository.save(company);
    }

    public void delete(final Long companyId) {
        companyRepository.deleteById(companyId);
    }

    private CompanyDTO mapToDTO(final Company company, final CompanyDTO companyDTO) {
        companyDTO.setCompanyId(company.getCompanyId());
        companyDTO.setCompanyName(company.getCompanyName());
        return companyDTO;
    }

    private Company mapToEntity(final CompanyDTO companyDTO, final Company company) {
        company.setCompanyName(companyDTO.getCompanyName());
        return company;
    }

}
