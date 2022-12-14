package com.ardecs.carconfiguration.controllers;

import com.ardecs.carconfiguration.dto.TransmissionDTO;
import com.ardecs.carconfiguration.services.TransmissionService;
import com.ardecs.carconfiguration.util.ValidationHelper;
import com.ardecs.carconfiguration.util.mapper.TransmissionMapper;
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
@RequestMapping("/transmission")
@RequiredArgsConstructor
public class TransmissionController {

    private final TransmissionService transmissionService;
    private final TransmissionMapper modelMapper;

    @GetMapping()
    public List<TransmissionDTO> getAccessories() {
        return transmissionService.readAllTransmissions().stream().map(modelMapper::toDto)
                .collect(Collectors.toList());
    }

    @GetMapping("/{id}")
    public TransmissionDTO getAccessory(@PathVariable("id") long id) {
        return modelMapper.toDto(transmissionService.readOneTransmission(id));
    }

    @PostMapping()
    public ResponseEntity<HttpStatus> create(@RequestBody @Valid TransmissionDTO transmissionDTO,
                                             BindingResult bindingResult) {
        ValidationHelper.checkingErrorsMethod(bindingResult);
        transmissionService.create(modelMapper.toEntity(transmissionDTO));
        return ResponseEntity.ok(HttpStatus.CREATED);
    }

    @PatchMapping("/{id}")
    public void update(@PathVariable("id") long id, @RequestBody @Valid TransmissionDTO transmissionDTO,
                       BindingResult bindingResult) {
        ValidationHelper.checkingErrorsMethod(bindingResult);
        transmissionService.update(modelMapper.toEntity(transmissionDTO), id);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<HttpStatus> delete(@PathVariable("id") long id) {
        transmissionService.delete(id);
        return ResponseEntity.ok(HttpStatus.OK);
    }
}
