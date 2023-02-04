package com.example.pet.service;

import com.example.pet.dao.entity.Socks;
import com.example.pet.dao.repository.SocksRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class SocksService {
    private final SocksRepository repository;

    public List<Socks> findAll(){
        return repository.findAll();
    }

    public Optional<Socks> findById(Long id){
        return repository.findById(id);
    }
    public Socks save(Socks socks){
        return repository.save(socks);
    }

    public Socks findByColorAndCottonPart(String color, Integer cottonPart){
        return repository.findByColorAndCottonPart(color, cottonPart);
    }
}
