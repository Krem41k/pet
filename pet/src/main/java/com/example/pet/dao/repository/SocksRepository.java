package com.example.pet.dao.repository;

import com.example.pet.dao.entity.Socks;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SocksRepository extends JpaRepository<Socks, Long> {
    public Socks findByColorAndCottonPart(String color, Integer cottonPart);
    public List<Socks> findAllByColorAndCottonPartGreaterThan(String color, Integer cottonPart);
    public List<Socks> findAllByColorAndCottonPartLessThan(String color, Integer cottonPart);
    public List<Socks> findAllByColorAndCottonPartEquals(String color, Integer cottonPart);
}
