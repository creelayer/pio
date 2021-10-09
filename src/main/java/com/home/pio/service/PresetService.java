package com.home.pio.service;

import com.home.pio.entity.Preset;
import com.home.pio.repository.PresetRepository;
import com.home.pio.validator.EntityExists;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@AllArgsConstructor
@Service
public class PresetService implements EntityExists {

    private final PresetRepository presetRepository;

    public Preset save(Preset preset) {
        return presetRepository.save(preset);
    }

    public Optional<Preset> findOne(UUID uuid) {
        return presetRepository.findById(uuid);
    }

    public Optional<Preset> findOne(String name) {
        return presetRepository.findByName(name);
    }

    public List<Preset> findAll() {
        return presetRepository.findAll();
    }

    @Override
    public boolean fieldValueExists(Object value, String fieldName) throws UnsupportedOperationException {
        Assert.notNull(fieldName, fieldName + " can't be null");

        if (!fieldName.equals("name")) {
            throw new UnsupportedOperationException("Field name not supported");
        }

        if (value == null) {
            return false;
        }

        return presetRepository.existsByName(value.toString());
    }
}