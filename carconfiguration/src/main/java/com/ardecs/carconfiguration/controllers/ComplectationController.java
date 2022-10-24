package com.ardecs.carconfiguration.controllers;

import com.ardecs.carconfiguration.dto.ComplectationDTO;
import com.ardecs.carconfiguration.models.entities.Complectation;
import com.ardecs.carconfiguration.services.ComplectationService;
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
 * @date 10/23/2022
 */
@RestController
@RequestMapping("/complectation")
@RequiredArgsConstructor
public class ComplectationController {
    private final ComplectationService complectationService;
    private final ModelMapper modelMapper;

    @GetMapping()
    public List<ComplectationDTO> getColors() {
        return complectationService.readAllComplectations().stream().map(this::convertToComplectationDTO)
                .collect(Collectors.toList());
    }

    @GetMapping("/{id}")
    public ComplectationDTO getColor(@PathVariable("id") long id) {
        return convertToComplectationDTO(complectationService.readOneComplectation(id));
    }

    @PostMapping()
    public ResponseEntity<HttpStatus> create(@RequestBody @Valid ComplectationDTO complectationDTO,
                                             BindingResult bindingResult) {
        ValidationHelper.checkingErrorsMethod(bindingResult);
        complectationService.create(convertToComplectation(complectationDTO));
        return ResponseEntity.ok(HttpStatus.CREATED);
    }

    @PatchMapping("/{id}")
    public void update(@PathVariable("id") long id, @RequestBody @Valid ComplectationDTO complectationDTO,
                       BindingResult bindingResult) {
        ValidationHelper.checkingErrorsMethod(bindingResult);
        complectationService.update(convertToComplectation(complectationDTO), id);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<HttpStatus> delete(@PathVariable("id") int id) {
        complectationService.delete(id);
        return ResponseEntity.ok(HttpStatus.OK);
    }

    private Complectation convertToComplectation(ComplectationDTO complectationDTO) {
        return modelMapper.map(complectationDTO, Complectation.class);
    }

    private ComplectationDTO convertToComplectationDTO(Complectation color) {
        return modelMapper.map(color, ComplectationDTO.class);
    }
}
