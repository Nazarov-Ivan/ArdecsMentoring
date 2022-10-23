package com.ardecs.carconfiguration.services;

import com.ardecs.carconfiguration.models.entities.Brand;
import com.ardecs.carconfiguration.repositories.BrandRepository;
import com.ardecs.carconfiguration.util.DuplicateNameException;
import com.ardecs.carconfiguration.util.ResourceNotFoundIdException;
import org.springframework.stereotype.Service;
import javax.transaction.Transactional;
import java.util.List;
import static com.ardecs.carconfiguration.util.ResourceNotFoundIdException.resourceNotFoundIdException;
import static com.ardecs.carconfiguration.util.ResourceNotFoundNameException.resourceNotFoundNameException;

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
        if (brandRepository.existsById(id)) {
            brand.setId(id);
            brandRepository.save(brand);
        } else throw new ResourceNotFoundIdException(message);
    }

    @Transactional
    public void delete(long id) {
        if (brandRepository.existsById(id)) {
            brandRepository.deleteById(id);
        } else throw new ResourceNotFoundIdException(message);
    }

    public Brand findByName(String name) {
        return brandRepository.findByName(name).orElseThrow(resourceNotFoundNameException(message));
    }
}
