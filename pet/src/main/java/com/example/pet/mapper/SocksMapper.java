package com.example.pet.mapper;

import com.example.pet.dao.entity.Socks;
import com.example.pet.dto.SocksDto;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface SocksMapper {
    SocksDto toDto(Socks source);
    Socks toEntity(SocksDto source);
}
