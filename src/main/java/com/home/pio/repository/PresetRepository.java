package com.home.pio.repository;

import com.home.pio.entity.Preset;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
public interface PresetRepository extends CrudRepository<Preset, UUID> {
    List<Preset> findAll();
    boolean existsByName(String name);
    Optional<Preset> findByName(String name);
}
