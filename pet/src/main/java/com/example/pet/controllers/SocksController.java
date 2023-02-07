package com.example.pet.controllers;

import com.example.pet.dao.entity.Socks;
import com.example.pet.dto.SocksDto;
import com.example.pet.mapper.SocksMapper;
import com.example.pet.service.SocksService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api")
public class SocksController {
    private final SocksService service;
    private final SocksMapper mapper;

    @GetMapping("/socks")
    public ResponseEntity<String> findAll(@RequestParam("color") String color,
                                  @RequestParam("operation") String operation,
                                  @RequestParam("cottonPart") Integer cottonPart) {
        List<Socks> socks = switch (operation) {
            case "lessThan"-> service.findAllByColorAndCottonPartLessThan(color, cottonPart);
            case "moreThan" -> service.findAllByColorAndCottonPartGreaterThan(color, cottonPart);
            case "equal" -> service.findAllByColorAndCottonPartEquals(color, cottonPart);
            default -> null;
        };
        if (socks == null)
            return ResponseEntity.badRequest().body("Неверные параметры запроса");

        int sum = socks.stream()
                .map(mapper::toDto)
                .mapToInt(SocksDto::getQuantity)
                .sum();
        if (sum > 0)
            return ResponseEntity.status(HttpStatus.OK).body(String.valueOf(sum));
        else
            return ResponseEntity.badRequest().body("Неверные параметры запроса");
    }

    @PostMapping("/socks/income")
    public ResponseEntity<String> income(@Valid @RequestBody SocksDto socksDto) {
        Socks socksIncome = mapper.toEntity(socksDto);
        Socks oldSocks = service.findByColorAndCottonPart(socksIncome.getColor(), socksIncome.getCottonPart());
        if (oldSocks == null){
            oldSocks = socksIncome;
        } else {
            oldSocks.setQuantity(oldSocks.getQuantity() + socksIncome.getQuantity());
        }
        mapper.toDto(service.save(oldSocks));
        return ResponseEntity.status(HttpStatus.OK).body("Удалось добавить приход");
    }

    @PostMapping("/socks/outcome")
    public ResponseEntity<String> outcome(@Valid @RequestBody SocksDto socksDto) {
        Socks socksIncome = mapper.toEntity(socksDto);
        Socks oldSocks = service.findByColorAndCottonPart(socksIncome.getColor(), socksIncome.getCottonPart());
        if (oldSocks == null){
            oldSocks = socksIncome;
        } else {
            int diff = oldSocks.getQuantity() - socksIncome.getQuantity();
            if (diff > 0)
                oldSocks.setQuantity(diff);
            else
                return ResponseEntity.badRequest()
                        .body("Количество носков запрашиваемых на отпуск больше, чем хранится на складе");
        }
        mapper.toDto(service.save(oldSocks));
        return ResponseEntity.status(HttpStatus.OK).body("Удалось зарегистрировать отпуск");
    }
}
