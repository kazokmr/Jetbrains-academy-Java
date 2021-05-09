package carsharing.service;

import carsharing.dao.CompanyRepository;
import carsharing.entity.Company;

import java.util.List;

public class CompanyService {
    private final CompanyRepository repository;

    public CompanyService() {
        this.repository = new CompanyRepository();
    }

    public List<Company> findAll() {
        return repository.findAll();
    }

    public void save(Company company) {
        repository.save(company);
    }
}
