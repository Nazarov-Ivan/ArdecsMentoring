package com.ardecs.carconfiguration.services;

import com.ardecs.carconfiguration.exceptions.ResourceNotFoundIdException;
import com.ardecs.carconfiguration.exceptions.ResourceNotFoundNameException;
import com.ardecs.carconfiguration.models.entities.Model;
import com.ardecs.carconfiguration.repositories.ModelRepository;
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
public class ModelService {

    private final String message = "Model";
    private final ModelRepository modelRepository;
    private final BrandService brandService;

    public List<Model> readAllModels(long brandId) {
        List<Model> models = modelRepository.findAllByBrand(brandService.readOneBrand(brandId));
        if (models.isEmpty()) {
            throw new ResourceNotFoundIdException("Models in brand");
        } else return models;
    }

    public Model readOneModel(long id, long brandId) {
        brandService.readOneBrand(brandId);
        return modelRepository.findById(id).orElseThrow(() -> new ResourceNotFoundIdException(message));
    }

    @Transactional
    public void create(Model model, Long brandId) {
        brandService.readOneBrand(brandId);
        if (modelRepository.findByName(model.getName()).isPresent()) {
            throw new DuplicateNameException(message);
        } else modelRepository.save(model);
    }

    @Transactional
    public void update(Model model, long id, long brandId) {
        brandService.readOneBrand(brandId);
        Model oldModel = modelRepository.findById(id).orElseThrow(() -> new ResourceNotFoundIdException(message));
        oldModel.setBrand(model.getBrand());
        oldModel.setPrice(model.getPrice());
        oldModel.setName(model.getName());
        modelRepository.save(oldModel);
    }

    @Transactional
    public void delete(long id, long brandId) {
        brandService.readOneBrand(brandId);
        modelRepository.findById(id).orElseThrow(() -> new ResourceNotFoundIdException(message));
        modelRepository.deleteById(id);
    }

    public Model findByName(String name) {
        return modelRepository.findByName(name).orElseThrow(() -> new ResourceNotFoundNameException(message));
    }
}
