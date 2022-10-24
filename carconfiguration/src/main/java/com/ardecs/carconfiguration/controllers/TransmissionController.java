package com.ardecs.carconfiguration.controllers;

import com.ardecs.carconfiguration.dto.TransmissionDTO;
import com.ardecs.carconfiguration.models.entities.Transmission;
import com.ardecs.carconfiguration.services.TransmissionService;
import com.ardecs.carconfiguration.util.ValidationHelper;
import lombok.RequiredArgsConstructor;
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
@RequestMapping("/transmission")
@RequiredArgsConstructor
public class TransmissionController {

    private final TransmissionService transmissionService;
    private final ModelMapper modelMapper;

    @GetMapping()
    public List<TransmissionDTO> getAccessories() {
        return transmissionService.readAllTransmissions().stream().map(this::convertToTransmissionDTO)
                .collect(Collectors.toList());
    }

    @GetMapping("/{id}")
    public TransmissionDTO getAccessory(@PathVariable("id") long id) {
        return convertToTransmissionDTO(transmissionService.readOneTransmission(id));
    }

    @PostMapping()
    public ResponseEntity<HttpStatus> create(@RequestBody @Valid TransmissionDTO transmissionDTO,
                                             BindingResult bindingResult) {
        ValidationHelper.checkingErrorsMethod(bindingResult);
        transmissionService.create(convertToTransmission(transmissionDTO));
        return ResponseEntity.ok(HttpStatus.CREATED);
    }

    @PatchMapping("/{id}")
    public void update(@PathVariable("id") long id, @RequestBody @Valid TransmissionDTO transmissionDTO,
                       BindingResult bindingResult) {
        ValidationHelper.checkingErrorsMethod(bindingResult);
        transmissionService.update(convertToTransmission(transmissionDTO), id);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<HttpStatus> delete(@PathVariable("id") int id) {
        transmissionService.delete(id);
        return ResponseEntity.ok(HttpStatus.OK);
    }

    private Transmission convertToTransmission(TransmissionDTO transmissionDTO) {
        return modelMapper.map(transmissionDTO, Transmission.class);
    }

    private TransmissionDTO convertToTransmissionDTO(Transmission transmission) {
        return modelMapper.map(transmission, TransmissionDTO.class);
    }
}
