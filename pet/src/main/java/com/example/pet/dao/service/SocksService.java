package com.example.pet.dao.service;

import com.example.pet.dao.entity.Socks;
import com.example.pet.dao.repository.SocksRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
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
}
