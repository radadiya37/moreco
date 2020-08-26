package com.nbprod.eaviculture.service.impl;

import com.nbprod.eaviculture.service.FactureService;
import com.nbprod.eaviculture.domain.Facture;
import com.nbprod.eaviculture.repository.FactureRepository;
import com.nbprod.eaviculture.service.dto.FactureDTO;
import com.nbprod.eaviculture.service.mapper.FactureMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.LinkedList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * Service Implementation for managing {@link Facture}.
 */
@Service
@Transactional
public class FactureServiceImpl implements FactureService {

    private final Logger log = LoggerFactory.getLogger(FactureServiceImpl.class);

    private final FactureRepository factureRepository;

    private final FactureMapper factureMapper;

    public FactureServiceImpl(FactureRepository factureRepository, FactureMapper factureMapper) {
        this.factureRepository = factureRepository;
        this.factureMapper = factureMapper;
    }

    @Override
    public FactureDTO save(FactureDTO factureDTO) {
        log.debug("Request to save Facture : {}", factureDTO);
        Facture facture = factureMapper.toEntity(factureDTO);
        facture = factureRepository.save(facture);
        return factureMapper.toDto(facture);
    }

    @Override
    @Transactional(readOnly = true)
    public List<FactureDTO> findAll() {
        log.debug("Request to get all Factures");
        return factureRepository.findAll().stream()
            .map(factureMapper::toDto)
            .collect(Collectors.toCollection(LinkedList::new));
    }


    @Override
    @Transactional(readOnly = true)
    public Optional<FactureDTO> findOne(Long id) {
        log.debug("Request to get Facture : {}", id);
        return factureRepository.findById(id)
            .map(factureMapper::toDto);
    }

    @Override
    public void delete(Long id) {
        log.debug("Request to delete Facture : {}", id);
        factureRepository.deleteById(id);
    }
}
