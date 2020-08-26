package com.nbprod.eaviculture.service.impl;

import com.nbprod.eaviculture.service.VenteService;
import com.nbprod.eaviculture.domain.Vente;
import com.nbprod.eaviculture.repository.VenteRepository;
import com.nbprod.eaviculture.service.dto.VenteDTO;
import com.nbprod.eaviculture.service.mapper.VenteMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.LinkedList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * Service Implementation for managing {@link Vente}.
 */
@Service
@Transactional
public class VenteServiceImpl implements VenteService {

    private final Logger log = LoggerFactory.getLogger(VenteServiceImpl.class);

    private final VenteRepository venteRepository;

    private final VenteMapper venteMapper;

    public VenteServiceImpl(VenteRepository venteRepository, VenteMapper venteMapper) {
        this.venteRepository = venteRepository;
        this.venteMapper = venteMapper;
    }

    @Override
    public VenteDTO save(VenteDTO venteDTO) {
        log.debug("Request to save Vente : {}", venteDTO);
        Vente vente = venteMapper.toEntity(venteDTO);
        vente = venteRepository.save(vente);
        return venteMapper.toDto(vente);
    }

    @Override
    @Transactional(readOnly = true)
    public List<VenteDTO> findAll() {
        log.debug("Request to get all Ventes");
        return venteRepository.findAll().stream()
            .map(venteMapper::toDto)
            .collect(Collectors.toCollection(LinkedList::new));
    }


    @Override
    @Transactional(readOnly = true)
    public Optional<VenteDTO> findOne(Long id) {
        log.debug("Request to get Vente : {}", id);
        return venteRepository.findById(id)
            .map(venteMapper::toDto);
    }

    @Override
    public void delete(Long id) {
        log.debug("Request to delete Vente : {}", id);
        venteRepository.deleteById(id);
    }
}
