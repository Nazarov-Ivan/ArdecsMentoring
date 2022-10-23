package com.ardecs.carconfiguration.services;

import com.ardecs.carconfiguration.models.entities.Model;
import com.ardecs.carconfiguration.repositories.ModelRepository;
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
public class ModelService {

    private final String message = "Model";
    private final ModelRepository modelRepository;
    private final BrandService brandService;

    public ModelService(ModelRepository modelRepository, BrandService brandService) {
        this.modelRepository = modelRepository;
        this.brandService = brandService;
    }

    public List<Model> readAllModels() {
        return modelRepository.findAll();
    }

    public Model readOneModel(long id) {
        return modelRepository.findById(id).orElseThrow(resourceNotFoundIdException(message));
    }

    @Transactional
    public void create(Model model, Long brandId) {
        brandService.readOneBrand(brandId);
        if (modelRepository.findByName(model.getName()).isPresent()) {
            throw new DuplicateNameException(message);
        } else modelRepository.save(model);
    }

    @Transactional
    public void update(Model model, long id) {
        if (modelRepository.existsById(id)) {
            model.setId(id);
            modelRepository.save(model);
        } else throw new ResourceNotFoundIdException(message);
    }

    @Transactional
    public void delete(long id) {
        if (modelRepository.existsById(id)) {
            modelRepository.deleteById(id);
        } else throw new ResourceNotFoundIdException(message);
    }

    public Model findByName(String name) {
        return modelRepository.findByName(name).orElseThrow(resourceNotFoundNameException(message));
    }
}
