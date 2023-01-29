package com.example.pet.dao.controllers;

import com.example.pet.dao.entity.Socks;
import com.example.pet.dao.service.SocksService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class SocksController {
    private final SocksService service;

    @GetMapping("/socks")
    public List<Socks> findAll(){
        return service.findAll();
    }

    public ResponseEntity<Socks> findByID(@PathVariable Long id){
        return ResponseEntity.of(service.findById(id));
    }
}
