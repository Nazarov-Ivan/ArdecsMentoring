package com.ardecs.carconfiguration.controllers;

import com.ardecs.carconfiguration.dto.BrandDTO;
import com.ardecs.carconfiguration.models.entities.Brand;
import com.ardecs.carconfiguration.services.BrandService;
import com.ardecs.carconfiguration.util.ResourceNotCreatedException;
import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import javax.validation.Valid;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author Nazarov Ivan
 * @date 10/22/2022
 */
@RestController
@RequestMapping()
public class BrandController {
    private final BrandService brandService;
    private final ModelMapper modelMapper;

    public BrandController(BrandService brandService, ModelMapper modelMapper) {
        this.brandService = brandService;
        this.modelMapper = modelMapper;
    }

    @GetMapping("/brands")
    public List<BrandDTO> getBrands() {
        return brandService.readAllBrands().stream().map(this::convertToBrandDTO)
                .collect(Collectors.toList());
    }

    @GetMapping("brand/{id}")
    public BrandDTO getBrand(@PathVariable("id") long id) {
        return convertToBrandDTO(brandService.readOneBrand(id));
    }

    @PostMapping("brand/create")
    public ResponseEntity<HttpStatus> create(@RequestBody @Valid BrandDTO brandDTO,
                                             BindingResult bindingResult) {
        ResourceNotCreatedException.checkingErrorsMethod(bindingResult);
        brandService.create(convertToBrand(brandDTO));
        return ResponseEntity.ok(HttpStatus.CREATED);
    }

    @PatchMapping("brand/update/{id}")
    public void update(@PathVariable("id") long id, @RequestBody @Valid BrandDTO brandDTO,
                       BindingResult bindingResult) {
        ResourceNotCreatedException.checkingErrorsMethod(bindingResult);
        brandService.update(convertToBrand(brandDTO), id);
    }

    @DeleteMapping("brand/delete/{id}")
    public ResponseEntity<HttpStatus> delete(@PathVariable("id") int id) {
        brandService.delete(id);
        return ResponseEntity.ok(HttpStatus.OK);
    }

    private Brand convertToBrand(BrandDTO brandDTO) {
        return modelMapper.map(brandDTO, Brand.class);
    }

    private BrandDTO convertToBrandDTO(Brand brand) {
        return modelMapper.map(brand, BrandDTO.class);
    }
}
