package com.example.pet.controllers;

import com.example.pet.dao.entity.Socks;
import com.example.pet.dto.SocksDto;
import com.example.pet.mapper.SocksMapper;
import com.example.pet.service.SocksService;
import com.example.pet.utils.Operation;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequiredArgsConstructor
public class SocksController {
    private final SocksService service;
    private final SocksMapper mapper;

    @GetMapping("/socks")
    public Integer findAll(@RequestParam("color") String color,
                                  @RequestParam("operation") String operation,
                                  @RequestParam("cottonPart") Integer cottonPart) {
        List<Socks> socks = switch (operation) {
            case "lessThan"-> service.findAllByColorAndCottonPartLessThan(color, cottonPart);
            case "moreThan" -> service.findAllByColorAndCottonPartGreaterThan(color, cottonPart);
            case "equal" -> service.findAllByColorAndCottonPartEquals(color, cottonPart);
            default -> throw new RuntimeException();
        };

        return socks.stream()
                .map(mapper::toDto)
                .mapToInt(SocksDto::getQuantity)
                .sum();
    }

    @GetMapping("/socks/{id}")
    public ResponseEntity<SocksDto> findByID(@PathVariable Long id) {
        return ResponseEntity.of(service.findById(id).map(mapper::toDto));
    }

    @PostMapping("/socks")
    public SocksDto create(@Valid @RequestBody SocksDto socksDto) {
        Socks socks = service.save(mapper.toEntity(socksDto));
        return mapper.toDto(socks);
    }

    @PostMapping("/socks/income")
    public SocksDto income(@Valid @RequestBody SocksDto socksDto) {
        Socks socksIncome = mapper.toEntity(socksDto);
        Socks oldSocks = service.findByColorAndCottonPart(socksIncome.getColor(), socksIncome.getCottonPart());
        oldSocks.setQuantity(oldSocks.getQuantity() + socksIncome.getQuantity());
        return mapper.toDto(service.save(oldSocks));
    }

    @PostMapping("/socks/outcome")
    public SocksDto outcome(@Valid @RequestBody SocksDto socksDto) {
        Socks socksIncome = mapper.toEntity(socksDto);
        Socks oldSocks = service.findByColorAndCottonPart(socksIncome.getColor(), socksIncome.getCottonPart());
        oldSocks.setQuantity(oldSocks.getQuantity() - socksIncome.getQuantity());
        return mapper.toDto(service.save(oldSocks));
    }
}
