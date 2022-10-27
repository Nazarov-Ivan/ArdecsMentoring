package com.ardecs.carconfiguration.services;

import com.ardecs.carconfiguration.models.entities.Brand;
import com.ardecs.carconfiguration.repositories.BrandRepository;
import com.ardecs.carconfiguration.exceptions.DuplicateNameException;
import org.springframework.stereotype.Service;
import javax.transaction.Transactional;
import java.util.List;

import static com.ardecs.carconfiguration.exceptions.ResourceNotFoundIdException.resourceNotFoundIdException;
import static com.ardecs.carconfiguration.exceptions.ResourceNotFoundNameException.resourceNotFoundNameException;

/**
 * @author Nazarov Ivan
 * @date 10/14/2022
 */

@Service
public class BrandService {
    private final String message = "Brand";
    private final BrandRepository brandRepository;

    public BrandService(BrandRepository brandRepository) {
        this.brandRepository = brandRepository;
    }

    public List<Brand> readAllBrands() {
        return brandRepository.findAll();
    }

    public Brand readOneBrand(long id) {
        return brandRepository.findById(id).orElseThrow(resourceNotFoundIdException(message));
    }

    @Transactional
    public void create(Brand brand) {
        if (brandRepository.findByName(brand.getName()).isPresent()) {
            throw new DuplicateNameException(message);
        } else brandRepository.save(brand);
    }

    @Transactional
    public void update(Brand brand, long id) {
        Brand oldBrand = brandRepository.findById(id).orElseThrow(resourceNotFoundIdException(message));
        oldBrand.setName(brand.getName());
        brandRepository.save(oldBrand);
    }

    @Transactional
    public void delete(long id) {
        brandRepository.findById(id).orElseThrow(resourceNotFoundIdException(message));
        brandRepository.deleteById(id);
    }

    public Brand findByName(String name) {
        return brandRepository.findByName(name).orElseThrow(resourceNotFoundNameException(message));
    }
}
