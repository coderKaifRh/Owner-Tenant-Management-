package com.tak.web.service;

import com.tak.web.model.Flat;
import com.tak.web.repository.FlatRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class FlatService {

    private final FlatRepository flatRepository;

    @Autowired
    public FlatService(FlatRepository flatRepository) {
        this.flatRepository = flatRepository;
    }

    public Flat saveFlat(Flat flat) {
        return flatRepository.save(flat);
    }

    public List<Flat> getFlatsByOwner(Integer ownerId) {
        return flatRepository.findByOwnerId(ownerId);
    }

    public List<Flat> searchFlats(String location, Double maxRent, Integer minSize) {
        return flatRepository.searchFlats(location, maxRent, minSize);
    }

    public Optional<Flat> getFlatById(Integer id) {
        return flatRepository.findById(id);
    }

    public void updateStatus(Integer flatId, String status) {
        flatRepository.findById(flatId).ifPresent(flat -> {
            flat.setStatus(status);
            flatRepository.save(flat);
        });
    }
}
