package com.ardecs.carconfiguration.services;

import com.ardecs.carconfiguration.exceptions.ResourceNotFoundIdException;
import com.ardecs.carconfiguration.exceptions.ResourceNotFoundNameException;
import com.ardecs.carconfiguration.models.entities.Brand;
import com.ardecs.carconfiguration.repositories.BrandRepository;
import com.ardecs.carconfiguration.exceptions.DuplicateNameException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import javax.transaction.Transactional;
import java.util.List;

/**
 * @author Nazarov Ivan
 * @date 10/14/2022
 */

@Service
@RequiredArgsConstructor
public class BrandService {
    private final String message = "Brand";
    private final BrandRepository brandRepository;

    public List<Brand> readAllBrands() {
        return brandRepository.findAll();
    }

    public Brand readOneBrand(long id) {
        return brandRepository.findById(id).orElseThrow(() -> new ResourceNotFoundIdException(message));
    }

    @Transactional
    public void create(Brand brand) {
        if (brandRepository.findByName(brand.getName()).isPresent()) {
            throw new DuplicateNameException(message);
        } else brandRepository.save(brand);
    }

    @Transactional
    public void update(Brand brand, long id) {
        Brand oldBrand = brandRepository.findById(id).orElseThrow(() -> new ResourceNotFoundIdException(message));
        oldBrand.setName(brand.getName());
        brandRepository.save(oldBrand);
    }

    @Transactional
    public void delete(long id) {
        brandRepository.findById(id).orElseThrow(() -> new ResourceNotFoundIdException(message));
        brandRepository.deleteById(id);
    }

    public Brand findByName(String name) {
        return brandRepository.findByName(name).orElseThrow(() -> new ResourceNotFoundNameException(message));
    }
}
