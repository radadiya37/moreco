package com.nbprod.eaviculture.service.impl;

import com.nbprod.eaviculture.service.BatimentService;
import com.nbprod.eaviculture.domain.Batiment;
import com.nbprod.eaviculture.repository.BatimentRepository;
import com.nbprod.eaviculture.service.dto.BatimentDTO;
import com.nbprod.eaviculture.service.mapper.BatimentMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.LinkedList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * Service Implementation for managing {@link Batiment}.
 */
@Service
@Transactional
public class BatimentServiceImpl implements BatimentService {

    private final Logger log = LoggerFactory.getLogger(BatimentServiceImpl.class);

    private final BatimentRepository batimentRepository;

    private final BatimentMapper batimentMapper;

    public BatimentServiceImpl(BatimentRepository batimentRepository, BatimentMapper batimentMapper) {
        this.batimentRepository = batimentRepository;
        this.batimentMapper = batimentMapper;
    }

    @Override
    public BatimentDTO save(BatimentDTO batimentDTO) {
        log.debug("Request to save Batiment : {}", batimentDTO);
        Batiment batiment = batimentMapper.toEntity(batimentDTO);
        batiment = batimentRepository.save(batiment);
        return batimentMapper.toDto(batiment);
    }

    @Override
    @Transactional(readOnly = true)
    public List<BatimentDTO> findAll() {
        log.debug("Request to get all Batiments");
        return batimentRepository.findAll().stream()
            .map(batimentMapper::toDto)
            .collect(Collectors.toCollection(LinkedList::new));
    }


    @Override
    @Transactional(readOnly = true)
    public Optional<BatimentDTO> findOne(Long id) {
        log.debug("Request to get Batiment : {}", id);
        return batimentRepository.findById(id)
            .map(batimentMapper::toDto);
    }

    @Override
    public void delete(Long id) {
        log.debug("Request to delete Batiment : {}", id);
        batimentRepository.deleteById(id);
    }
}
