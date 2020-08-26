package com.nbprod.eaviculture.service.impl;

import com.nbprod.eaviculture.service.PhaseProductionService;
import com.nbprod.eaviculture.domain.PhaseProduction;
import com.nbprod.eaviculture.repository.PhaseProductionRepository;
import com.nbprod.eaviculture.service.dto.PhaseProductionDTO;
import com.nbprod.eaviculture.service.mapper.PhaseProductionMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.LinkedList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * Service Implementation for managing {@link PhaseProduction}.
 */
@Service
@Transactional
public class PhaseProductionServiceImpl implements PhaseProductionService {

    private final Logger log = LoggerFactory.getLogger(PhaseProductionServiceImpl.class);

    private final PhaseProductionRepository phaseProductionRepository;

    private final PhaseProductionMapper phaseProductionMapper;

    public PhaseProductionServiceImpl(PhaseProductionRepository phaseProductionRepository, PhaseProductionMapper phaseProductionMapper) {
        this.phaseProductionRepository = phaseProductionRepository;
        this.phaseProductionMapper = phaseProductionMapper;
    }

    @Override
    public PhaseProductionDTO save(PhaseProductionDTO phaseProductionDTO) {
        log.debug("Request to save PhaseProduction : {}", phaseProductionDTO);
        PhaseProduction phaseProduction = phaseProductionMapper.toEntity(phaseProductionDTO);
        phaseProduction = phaseProductionRepository.save(phaseProduction);
        return phaseProductionMapper.toDto(phaseProduction);
    }

    @Override
    @Transactional(readOnly = true)
    public List<PhaseProductionDTO> findAll() {
        log.debug("Request to get all PhaseProductions");
        return phaseProductionRepository.findAll().stream()
            .map(phaseProductionMapper::toDto)
            .collect(Collectors.toCollection(LinkedList::new));
    }


    @Override
    @Transactional(readOnly = true)
    public Optional<PhaseProductionDTO> findOne(Long id) {
        log.debug("Request to get PhaseProduction : {}", id);
        return phaseProductionRepository.findById(id)
            .map(phaseProductionMapper::toDto);
    }

    @Override
    public void delete(Long id) {
        log.debug("Request to delete PhaseProduction : {}", id);
        phaseProductionRepository.deleteById(id);
    }
}
