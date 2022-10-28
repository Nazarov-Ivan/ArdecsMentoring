package com.ardecs.carconfiguration.controllers;

import com.ardecs.carconfiguration.dto.AccessoriesAndColorDTO;
import com.ardecs.carconfiguration.dto.AccessoryModelComplectDTO;
import com.ardecs.carconfiguration.dto.ColorModelComplectDTO;
import com.ardecs.carconfiguration.dto.ComplectationDTO;
import com.ardecs.carconfiguration.dto.EngineModelComplectDTO;
import com.ardecs.carconfiguration.dto.ModelComplectationDTO;
import com.ardecs.carconfiguration.dto.TransModelComplectDTO;
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
    private final ComplectationMapper complectationMapper;
    private final ModelComplectationMapper modelComplectationMapper;
    private final EngineModelComplectMapper engineModelComplectMapper;
    private final TransModelComplectMapper transModelComplectMapper;
    private final AccessoryModelComplectMapper accessoryModelComplectMapper;
    private final ColorModelComplectMapper colorModelComplectMapper;

    @GetMapping("/{modelId}")
    public List<ComplectationDTO> getComplectationsByOneModel(@PathVariable("modelId") long modelId) {
        return modelComplectationService.readAllModelComplectations(modelId).stream().map(complectationMapper::toDto)
                .collect(Collectors.toList());
    }

    @GetMapping("/{modelId}/{complectationId}")
    public ModelComplectationDTO getComplectationInfo(@PathVariable("modelId") long modelId,
                                                  @PathVariable("complectationId") long compId) {
        return modelComplectationMapper.toDto(modelComplectationService.readOneModelComplectation(modelId, compId));
    }

    @PostMapping("/{modelId}/{complectationId}")
    public Integer chooseAccessoriesAndColorPlusGetComplectationPrice(@PathVariable("modelId") long modelId,
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

    @DeleteMapping("/{modelId}/{complectationId}")
    public ResponseEntity<HttpStatus> deleteModelComplectation(@PathVariable("complectationId") long complectationId,
                                                               @PathVariable("modelId") long modelId) {
        modelComplectationService.delete(modelId, complectationId);
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

    @PatchMapping("/{modelId}/{complectationId}/engine/price")
    public void updateModelComplectationEnginePrice(@PathVariable("modelId") long modelId,
                                               @PathVariable("complectationId") long complectationId,
                                               @RequestBody @Valid int price,
                                               BindingResult bindingResult) {
        ValidationHelper.checkingErrorsMethod(bindingResult);
        engineModelComplectService.updatePrice(modelId, complectationId, price);
    }

    @PatchMapping("/{modelId}/{complectationId}/trans")
    public void updateModelComplectationTransmission(@PathVariable("modelId") long modelId,
                                                     @PathVariable("complectationId") long complectationId,
                                                     @RequestBody @Valid TransModelComplectDTO transModelComplectDTO,
                                                     BindingResult bindingResult) {
        ValidationHelper.checkingErrorsMethod(bindingResult);
        transModelComplectService.update(transModelComplectMapper.toEntity(transModelComplectDTO), modelId, complectationId);
    }

    @PatchMapping("/{modelId}/{complectationId}/trans/price")
    public void updateModelComplectationTransmissionPrice(@PathVariable("modelId") long modelId,
                                                     @PathVariable("complectationId") long complectationId,
                                                     @RequestBody @Valid int price,
                                                     BindingResult bindingResult) {
        ValidationHelper.checkingErrorsMethod(bindingResult);
        transModelComplectService.updatePrice(modelId, complectationId, price);
    }

    @PostMapping("/{modelId}/{complectationId}/access")
    public ResponseEntity<HttpStatus> createModelComplectationAccessory(@PathVariable("modelId") long modelId,
                                                      @PathVariable("complectationId") long compId,
                                                      @RequestBody @Valid AccessoryModelComplectDTO accessoryModelComplectDTO,
                                                               BindingResult bindingResult) {
        ValidationHelper.checkingErrorsMethod(bindingResult);
        accessoryModelComplectService.create(accessoryModelComplectMapper.toEntity(accessoryModelComplectDTO), modelId, compId);
        return ResponseEntity.ok(HttpStatus.CREATED);
    }

    @PatchMapping("/{modelId}/{complectationId}/access/{accessId}")
    public void updateModelComplectationAccessoryPrice(@PathVariable("modelId") long modelId,
                                                       @PathVariable("complectationId") long complectationId,
                                     @PathVariable("accessId") long accessId,
                                     @RequestBody @Valid int price,
                                     BindingResult bindingResult) {
        ValidationHelper.checkingErrorsMethod(bindingResult);
        accessoryModelComplectService.updatePrice(accessId, modelId, complectationId, price);
    }

    @DeleteMapping("/{modelId}/{complectationId}/access/{accessId}")
    public ResponseEntity<HttpStatus> deleteModelComplectationAccessory(@PathVariable("modelId") long modelId,
                                                      @PathVariable("complectationId") long compId,
                                                      @PathVariable("accessId") long accessId) {
        accessoryModelComplectService.delete(accessId, modelId, compId);
        return ResponseEntity.ok(HttpStatus.OK);
    }

    @PostMapping("/{modelId}/{complectationId}/color")
    public ResponseEntity<HttpStatus> createModelComplectationColor(@PathVariable("modelId") long modelId,
                                                  @PathVariable("complectationId") long compId,
                                                      @RequestBody @Valid ColorModelComplectDTO colorModelComplectDTO,
                                                      BindingResult bindingResult) {
        ValidationHelper.checkingErrorsMethod(bindingResult);
        colorModelComplectService.create(colorModelComplectMapper.toEntity(colorModelComplectDTO), modelId, compId);
        return ResponseEntity.ok(HttpStatus.CREATED);
    }

    @PatchMapping("/{modelId}/{complectationId}/color/{colorId}")
    public void updateModelComplectationColorPrice(@PathVariable("modelId") long modelId,
                                                   @PathVariable("complectationId") long complectationId,
                                 @PathVariable("colorId") long colorId,
                                 @RequestBody @Valid int price,
                                 BindingResult bindingResult) {
        ValidationHelper.checkingErrorsMethod(bindingResult);
        colorModelComplectService.updatePrice(colorId, modelId, complectationId, price);
    }

    @DeleteMapping("/{modelId}/{complectationId}/color/{colorId}")
    public ResponseEntity<HttpStatus> deleteModelComplectationColor(@PathVariable("modelId") long modelId,
                                                  @PathVariable("complectationId") long compId,
                                                  @PathVariable("colorId") long colorId) {
        colorModelComplectService.delete(colorId, modelId, compId);
        return ResponseEntity.ok(HttpStatus.OK);
    }
}
