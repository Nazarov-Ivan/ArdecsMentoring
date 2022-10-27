package com.ardecs.carconfiguration.controllers;

import com.ardecs.carconfiguration.dto.AccessoriesAndColorDTO;
import com.ardecs.carconfiguration.dto.AccessoryModelComplectDTO;
import com.ardecs.carconfiguration.dto.ColorModelComplectDTO;
import com.ardecs.carconfiguration.dto.ComplectationDTO;
import com.ardecs.carconfiguration.dto.EngineModelComplectDTO;
import com.ardecs.carconfiguration.dto.ModelComplectationDTO;
import com.ardecs.carconfiguration.dto.TransModelComplectDTO;
import com.ardecs.carconfiguration.models.entities.AccessoryModelComplect;
import com.ardecs.carconfiguration.models.entities.ColorModelComplect;
import com.ardecs.carconfiguration.models.entities.ModelComplectation;
import com.ardecs.carconfiguration.services.AccessoryModelComplectService;
import com.ardecs.carconfiguration.services.ColorModelComplectService;
import com.ardecs.carconfiguration.services.EngineModelComplectService;
import com.ardecs.carconfiguration.services.ModelComplectationService;
import com.ardecs.carconfiguration.services.TransModelComplectService;
import com.ardecs.carconfiguration.util.ValidationHelper;
import com.ardecs.carconfiguration.util.mapper.AccessoryModelComplectMapper;
import com.ardecs.carconfiguration.util.mapper.ColorModelComplectMapper;
import com.ardecs.carconfiguration.util.mapper.ComplectationMapper;
import com.ardecs.carconfiguration.util.mapper.EngineModelComplectMapper;
import com.ardecs.carconfiguration.util.mapper.ModelComplectationMapper;
import com.ardecs.carconfiguration.util.mapper.TransModelComplectMapper;
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
@RequestMapping("/configuration")
@RequiredArgsConstructor
public class ModelComplectationController {
    private final ModelComplectationService modelComplectationService;
    private final EngineModelComplectService engineModelComplectService;
    private final TransModelComplectService transModelComplectService;
    private final AccessoryModelComplectService accessoryModelComplectService;
    private final ColorModelComplectService colorModelComplectService;
    private final ComplectationMapper modelMapper;
    private final ModelComplectationMapper modelComplectationMapper;
    private final EngineModelComplectMapper engineModelComplectMapper;
    private final TransModelComplectMapper transModelComplectMapper;
    private final AccessoryModelComplectMapper accessoryModelComplectMapper;
    private final ColorModelComplectMapper colorModelComplectMapper;

    @GetMapping("/{modelId}")
    public List<ComplectationDTO> getModelComplectations(@PathVariable("modelId") long modelId) {
        return modelComplectationService.readAllModelComplectations(modelId).stream().map(modelMapper::toDto)
                .collect(Collectors.toList());
    }

    @GetMapping("/{modelId}/{complectationId}")
    public ModelComplectationDTO getComplectation(@PathVariable("modelId") long modelId,
                                                  @PathVariable("complectationId") long compId) {
        return modelComplectationMapper.toDto(modelComplectationService.readOneModelComplectation(modelId, compId));
    }

    @PostMapping("/{modelId}/{complectationId}/choose")
    public Integer chooseAccessoriesAndColorAndGetPrice(@PathVariable("modelId") long modelId,
                                                        @PathVariable("complectationId") long compId,
                                          @RequestBody @Valid AccessoriesAndColorDTO accessoriesAndColorDTO,
                                          BindingResult bindingResult) {
        ValidationHelper.checkingErrorsMethod(bindingResult);
        return modelComplectationService.getPrice(modelId, compId, accessoriesAndColorDTO);
    }

    @PostMapping()
    public ResponseEntity<HttpStatus> createModelComplectation(@RequestBody @Valid ModelComplectationDTO modelComplectationDTO,
                                                               BindingResult bindingResult) {
        ValidationHelper.checkingErrorsMethod(bindingResult);
        ModelComplectation modelComplectation = modelComplectationMapper.toEntity(modelComplectationDTO);
        modelComplectationService.create(modelComplectation, modelComplectation.getEngineModelComplect(),
                modelComplectation.getTransModelComplect(), modelComplectation.getAccessoryModelComplects(),
                modelComplectation.getColorModelComplects());
        return ResponseEntity.ok(HttpStatus.CREATED);
    }

    @PostMapping("/{modelId}/{complectationId}/access")
    public ResponseEntity<HttpStatus> createAccessory(@PathVariable("modelId") long modelId,
                                                      @PathVariable("complectationId") long compId,
                                                      @RequestBody @Valid AccessoryModelComplectDTO accessoryModelComplectDTO,
                                                               BindingResult bindingResult) {
        ValidationHelper.checkingErrorsMethod(bindingResult);
        AccessoryModelComplect accessoryModelComplect = accessoryModelComplectMapper.toEntity(accessoryModelComplectDTO);
        accessoryModelComplectService.create(accessoryModelComplect.getAccessory().getId(), modelId, compId,
                accessoryModelComplect.getPrice());
        return ResponseEntity.ok(HttpStatus.CREATED);
    }

    @PatchMapping("/{modelId}/{complectationId}/access")
    public void updateAccessoryPrice(@PathVariable("modelId") long modelId, @PathVariable("complectationId") long complectationId,
                                  @RequestBody @Valid AccessoryModelComplectDTO accessoryModelComplectDTO,
                                     BindingResult bindingResult) {
        ValidationHelper.checkingErrorsMethod(bindingResult);
        accessoryModelComplectService.updatePrice(accessoryModelComplectMapper.toEntity(accessoryModelComplectDTO),
                modelId, complectationId);
    }

    @DeleteMapping("/{modelId}/{complectationId}/access")
    public ResponseEntity<HttpStatus> deleteAccessory(@PathVariable("modelId") long modelId,
                                                      @PathVariable("complectationId") long compId,
                                                      @RequestBody @Valid AccessoryModelComplectDTO accessoryModelComplectDTO,
                                                      BindingResult bindingResult) {
        ValidationHelper.checkingErrorsMethod(bindingResult);
        accessoryModelComplectService.delete(accessoryModelComplectMapper.toEntity(accessoryModelComplectDTO), modelId, compId);
        return ResponseEntity.ok(HttpStatus.OK);
    }

    @PatchMapping("/{modelId}/{complectationId}/engine")
    public void updateModelComplectationEngine(@PathVariable("modelId") long modelId,
                                               @PathVariable("complectationId") long complectationId,
                                         @RequestBody @Valid EngineModelComplectDTO engineModelComplectDTO,
                                               BindingResult bindingResult) {
        ValidationHelper.checkingErrorsMethod(bindingResult);
        engineModelComplectService.update(engineModelComplectMapper.toEntity(engineModelComplectDTO), modelId, complectationId);
    }

    @PatchMapping("/{modelId}/{complectationId}/trans")
    public void updateModelComplectationTransmission(@PathVariable("modelId") long modelId,
                                                     @PathVariable("complectationId") long complectationId,
                                               @RequestBody @Valid TransModelComplectDTO transModelComplectDTO,
                                                     BindingResult bindingResult) {
        ValidationHelper.checkingErrorsMethod(bindingResult);
        transModelComplectService.update(transModelComplectMapper.toEntity(transModelComplectDTO), modelId, complectationId);
    }

    @DeleteMapping("/{modelId}/{complectationId}")
    public ResponseEntity<HttpStatus> deleteModelComplectation(@PathVariable("complectationId") long complectationId,
                                             @PathVariable("modelId") long modelId) {
        modelComplectationService.delete(modelId, complectationId);
        return ResponseEntity.ok(HttpStatus.OK);
    }

    @PostMapping("/{modelId}/{complectationId}/color")
    public ResponseEntity<HttpStatus> createColor(@PathVariable("modelId") long modelId,
                                                  @PathVariable("complectationId") long compId,
                                                      @RequestBody @Valid ColorModelComplectDTO colorModelComplectDTO,
                                                      BindingResult bindingResult) {
        ValidationHelper.checkingErrorsMethod(bindingResult);
        ColorModelComplect colorModelComplect = colorModelComplectMapper.toEntity(colorModelComplectDTO);
        colorModelComplectService.create(colorModelComplect.getColor().getId(), modelId, compId, colorModelComplect.getPrice());
        return ResponseEntity.ok(HttpStatus.CREATED);
    }

    @PatchMapping("/{modelId}/{complectationId}/color")
    public void updateColorPrice(@PathVariable("modelId") long modelId, @PathVariable("complectationId") long complectationId,
                                     @RequestBody @Valid ColorModelComplectDTO colorModelComplectDTO,
                                 BindingResult bindingResult) {
        ValidationHelper.checkingErrorsMethod(bindingResult);
        colorModelComplectService.updatePrice(colorModelComplectMapper.toEntity(colorModelComplectDTO), modelId, complectationId);
    }

    @DeleteMapping("/{modelId}/{complectationId}/color")
    public ResponseEntity<HttpStatus> deleteColor(@PathVariable("modelId") long modelId,
                                                  @PathVariable("complectationId") long compId,
                                                      @RequestBody @Valid ColorModelComplectDTO colorModelComplectDTO,
                                                      BindingResult bindingResult) {
        ValidationHelper.checkingErrorsMethod(bindingResult);
        colorModelComplectService.delete(colorModelComplectMapper.toEntity(colorModelComplectDTO), modelId, compId);
        return ResponseEntity.ok(HttpStatus.OK);
    }
}
