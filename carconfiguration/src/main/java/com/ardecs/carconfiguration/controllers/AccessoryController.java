package com.ardecs.carconfiguration.controllers;

import com.ardecs.carconfiguration.dto.AccessoryDTO;
import com.ardecs.carconfiguration.services.AccessoryService;
import com.ardecs.carconfiguration.util.ValidationHelper;
import com.ardecs.carconfiguration.util.mapper.AccessoryMapper;
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
@RequestMapping("/accessory")
@RequiredArgsConstructor
public class AccessoryController {
    private final AccessoryService accessoryService;
    private final AccessoryMapper modelMapper;

    @GetMapping()
    public List<AccessoryDTO> getAccessories() {
        return accessoryService.readAllAccessories().stream().map(modelMapper::toDto)
                .collect(Collectors.toList());
    }

    @GetMapping("/{id}")
    public AccessoryDTO getAccessory(@PathVariable("id") long id) {
        return modelMapper.toDto(accessoryService.readOneAccessory(id));
    }

    @PostMapping()
    public ResponseEntity<HttpStatus> create(@RequestBody @Valid AccessoryDTO accessoryDTO,
                                             BindingResult bindingResult) {
        ValidationHelper.checkingErrorsMethod(bindingResult);
        accessoryService.create(modelMapper.toEntity(accessoryDTO));
        return ResponseEntity.ok(HttpStatus.CREATED);
    }

    @PatchMapping("/{id}")
    public void update(@PathVariable("id") long id, @RequestBody @Valid AccessoryDTO accessoryDTO,
                       BindingResult bindingResult) {
        ValidationHelper.checkingErrorsMethod(bindingResult);
        accessoryService.update(modelMapper.toEntity(accessoryDTO), id);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<HttpStatus> delete(@PathVariable("id") long id) {
        accessoryService.delete(id);
        return ResponseEntity.ok(HttpStatus.OK);
    }
}
