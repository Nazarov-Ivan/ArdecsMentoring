package com.ardecs.carconfiguration.controllers;

import com.ardecs.carconfiguration.dto.EngineDTO;
import com.ardecs.carconfiguration.models.entities.Engine;
import com.ardecs.carconfiguration.services.EngineService;
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
@RequestMapping("/engine")
@RequiredArgsConstructor
public class EngineController {
    private final EngineService engineService;
    private final ModelMapper modelMapper;

    @GetMapping()
    public List<EngineDTO> getEngines() {
        return engineService.readAllEngines().stream().map(this::convertToEngineDTO)
                .collect(Collectors.toList());
    }

    @GetMapping("/{id}")
    public EngineDTO getEngine(@PathVariable("id") long id) {
        return convertToEngineDTO(engineService.readOneEngine(id));
    }

    @PostMapping()
    public ResponseEntity<HttpStatus> create(@RequestBody @Valid EngineDTO engineDTO,
                                             BindingResult bindingResult) {
        ValidationHelper.checkingErrorsMethod(bindingResult);
        engineService.create(convertToEngine(engineDTO));
        return ResponseEntity.ok(HttpStatus.CREATED);
    }

    @PatchMapping("/{id}")
    public void update(@PathVariable("id") long id, @RequestBody @Valid EngineDTO engineDTO,
                       BindingResult bindingResult) {
        ValidationHelper.checkingErrorsMethod(bindingResult);
        engineService.update(convertToEngine(engineDTO), id);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<HttpStatus> delete(@PathVariable("id") int id) {
        engineService.delete(id);
        return ResponseEntity.ok(HttpStatus.OK);
    }

    private Engine convertToEngine(EngineDTO engineDTO) {
        return modelMapper.map(engineDTO, Engine.class);
    }

    private EngineDTO convertToEngineDTO(Engine engine) {
        return modelMapper.map(engine, EngineDTO.class);
    }
}
