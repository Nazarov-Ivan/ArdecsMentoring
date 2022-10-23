package com.ardecs.carconfiguration.controllers;

import com.ardecs.carconfiguration.dto.ColorDTO;
import com.ardecs.carconfiguration.models.entities.Color;
import com.ardecs.carconfiguration.services.ColorService;
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
public class ColorController {
    private final ColorService colorService;
    private final ModelMapper modelMapper;

    public ColorController(ColorService colorService, ModelMapper modelMapper) {
        this.colorService = colorService;
        this.modelMapper = modelMapper;
    }

    @GetMapping("/colors")
    public List<ColorDTO> getColors() {
        return colorService.readAllColors().stream().map(this::convertToColorDTO)
                .collect(Collectors.toList());
    }

    @GetMapping("/color/{id}")
    public ColorDTO getColor(@PathVariable("id") long id) {
        return convertToColorDTO(colorService.readOneColor(id));
    }

    @PostMapping("/color/create")
    public ResponseEntity<HttpStatus> create(@RequestBody @Valid ColorDTO colorDTO,
                                             BindingResult bindingResult) {
        ResourceNotCreatedException.checkingErrorsMethod(bindingResult);
        colorService.create(convertToColor(colorDTO));
        return ResponseEntity.ok(HttpStatus.CREATED);
    }

    @PatchMapping("/color/update/{id}")
    public void update(@PathVariable("id") long id, @RequestBody @Valid ColorDTO colorDTO,
                       BindingResult bindingResult) {
        ResourceNotCreatedException.checkingErrorsMethod(bindingResult);
        colorService.update(convertToColor(colorDTO), id);
    }

    @DeleteMapping("/color/delete/{id}")
    public ResponseEntity<HttpStatus> delete(@PathVariable("id") int id) {
        colorService.delete(id);
        return ResponseEntity.ok(HttpStatus.OK);
    }

    private Color convertToColor(ColorDTO colorDTO) {
        return modelMapper.map(colorDTO, Color.class);
    }

    private ColorDTO convertToColorDTO(Color color) {
        return modelMapper.map(color, ColorDTO.class);
    }
}