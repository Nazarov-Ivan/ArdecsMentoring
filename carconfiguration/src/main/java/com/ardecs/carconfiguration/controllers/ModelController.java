package com.ardecs.carconfiguration.controllers;

import com.ardecs.carconfiguration.dto.ModelDTO;
import com.ardecs.carconfiguration.services.ModelService;
import com.ardecs.carconfiguration.util.ValidationHelper;
import com.ardecs.carconfiguration.util.mapper.MyModelMapper;
import lombok.RequiredArgsConstructor;
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
@RequestMapping("/model/{brandId}")
@RequiredArgsConstructor
public class ModelController {
    private final ModelService modelService;
    private final MyModelMapper myModelMapper;

    @GetMapping()
    public List<ModelDTO> getModels(@PathVariable("brandId") long brandId) {
        return modelService.readAllModels(brandId).stream().map(myModelMapper::toDto)
                .collect(Collectors.toList());
    }

    @GetMapping("/{id}")
    public ModelDTO getModel(@PathVariable("id") long id,
                             @PathVariable("brandId") long brandId) {
        return myModelMapper.toDto(modelService.readOneModel(id, brandId));
    }

    @PostMapping()
    public ResponseEntity<HttpStatus> create(@PathVariable("brandId") long brandId,
                                             @RequestBody @Valid ModelDTO modelDTO,
                                             BindingResult bindingResult) {
        ValidationHelper.checkingErrorsMethod(bindingResult);
        modelService.create(myModelMapper.toEntity(modelDTO), brandId);
        return ResponseEntity.ok(HttpStatus.CREATED);
    }

    @PatchMapping("/{id}")
    public void update(@PathVariable("id") long id, @PathVariable("brandId") long brandId,
                       @RequestBody @Valid ModelDTO modelDTO, BindingResult bindingResult) {
        ValidationHelper.checkingErrorsMethod(bindingResult);
        modelService.update(myModelMapper.toEntity(modelDTO), id, brandId);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<HttpStatus> delete(@PathVariable("id") long id,
                                             @PathVariable("brandId") long brandId) {
        modelService.delete(id, brandId);
        return ResponseEntity.ok(HttpStatus.OK);
    }
}
