package com.example.booksapp.service.impl;

import com.example.booksapp.model.Country;
import com.example.booksapp.model.dto.CountryDto;
import com.example.booksapp.model.exceptions.CountryNotFoundException;
import com.example.booksapp.repository.CountryRepository;
import com.example.booksapp.service.CountryService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CountryServiceImplementation implements CountryService {

    private final CountryRepository countryRepository;

    public CountryServiceImplementation(CountryRepository countryRepository) {
        this.countryRepository = countryRepository;
    }

    @Override
    public List<Country> findAll() {
        return this.countryRepository.findAll();
    }

    @Override
    public Optional<Country> findById(Long id) {
        return this.countryRepository.findById(id);
    }

    @Override
    public Optional<Country> save(String name, String continent) {
        Country country = new Country(name, continent);

        return Optional.of(this.countryRepository.save(country));
    }

    @Override
    public Optional<Country> save(CountryDto countryDto) {
        Country country = new Country(countryDto.getName(), countryDto.getContinent());

        return Optional.of(this.countryRepository.save(country));
    }

    @Override
    public Optional<Country> edit(Long id, String name, String continent) {
        Country country = this.findById(id)
                .orElseThrow(() -> new CountryNotFoundException(id));

        country.setName(name);
        country.setContinent(continent);

        return Optional.of(this.countryRepository.save(country));
    }

    @Override
    public Optional<Country> edit(Long id, CountryDto countryDto) {
        Country country = this.findById(id)
                .orElseThrow(() -> new CountryNotFoundException(id));

        country.setName(countryDto.getName());
        country.setContinent(countryDto.getContinent());

        return Optional.of(this.countryRepository.save(country));
    }

    @Override
    public void deleteById(Long id) {
        this.countryRepository.deleteById(id);
    }
}
