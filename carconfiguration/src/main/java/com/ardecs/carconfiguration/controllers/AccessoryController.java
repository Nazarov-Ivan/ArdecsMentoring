package com.ardecs.carconfiguration.controllers;

import com.ardecs.carconfiguration.dto.AccessoryDTO;
import com.ardecs.carconfiguration.models.entities.Accessory;
import com.ardecs.carconfiguration.services.AccessoryService;
import com.ardecs.carconfiguration.util.ResourceErrorResponse;
import com.ardecs.carconfiguration.util.ResourceNotCreatedException;
import com.ardecs.carconfiguration.util.ResourceNotFoundException;
import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.ExceptionHandler;
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
@RequestMapping("/accessories")
public class AccessoryController {

    private final AccessoryService accessoryService;
    private final ModelMapper modelMapper;

    public AccessoryController(AccessoryService accessoryService, ModelMapper modelMapper) {
        this.accessoryService = accessoryService;
        this.modelMapper = modelMapper;
    }

    @GetMapping()
    public List<AccessoryDTO> getAccessories() {
        return accessoryService.readAllAccessories().stream().map(this::convertToAccessoryDTO)
                .collect(Collectors.toList());
    }

    @GetMapping("/{id}")
    public AccessoryDTO getAccessory(@PathVariable("id") long id) {
        return convertToAccessoryDTO(accessoryService.readOneAccessory(id));
    }

    @PostMapping("/create")
    public ResponseEntity<HttpStatus> create(@RequestBody @Valid AccessoryDTO accessoryDTO,
                                             BindingResult bindingResult) {
        ResourceNotCreatedException.checkingErrorsMethod(bindingResult);
        accessoryService.create(convertToAccessory(accessoryDTO));
        return ResponseEntity.ok(HttpStatus.CREATED);
    }

    @PatchMapping("/update/{id}")
    public void update(@PathVariable("id") long id, @RequestBody @Valid AccessoryDTO accessoryDTO,
                       BindingResult bindingResult) {
        ResourceNotCreatedException.checkingErrorsMethod(bindingResult);
        accessoryService.update(convertToAccessory(accessoryDTO), id);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<HttpStatus> delete(@PathVariable("id") int id) {
        accessoryService.delete(id);
        return ResponseEntity.ok(HttpStatus.OK);
    }

    @ExceptionHandler
    private ResponseEntity<ResourceErrorResponse> handleException(ResourceNotFoundException e) {
        ResourceErrorResponse response = new ResourceErrorResponse(
                "Accessory with this id wasn't found!",
                System.currentTimeMillis()
        );
        return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler
    private ResponseEntity<ResourceErrorResponse> handleException(ResourceNotCreatedException e) {
        ResourceErrorResponse response = new ResourceErrorResponse(
                e.getMessage(),
                System.currentTimeMillis()
        );
        return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
    }

    private Accessory convertToAccessory(AccessoryDTO accessoryDTO) {
        return modelMapper.map(accessoryDTO, Accessory.class);
    }

    private AccessoryDTO convertToAccessoryDTO(Accessory accessory) {
        return modelMapper.map(accessory, AccessoryDTO.class);
    }
}
