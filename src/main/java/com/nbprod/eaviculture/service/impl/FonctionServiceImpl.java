package com.nbprod.eaviculture.service.impl;

import com.nbprod.eaviculture.service.FonctionService;
import com.nbprod.eaviculture.domain.Fonction;
import com.nbprod.eaviculture.repository.FonctionRepository;
import com.nbprod.eaviculture.service.dto.FonctionDTO;
import com.nbprod.eaviculture.service.mapper.FonctionMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.LinkedList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * Service Implementation for managing {@link Fonction}.
 */
@Service
@Transactional
public class FonctionServiceImpl implements FonctionService {

    private final Logger log = LoggerFactory.getLogger(FonctionServiceImpl.class);

    private final FonctionRepository fonctionRepository;

    private final FonctionMapper fonctionMapper;

    public FonctionServiceImpl(FonctionRepository fonctionRepository, FonctionMapper fonctionMapper) {
        this.fonctionRepository = fonctionRepository;
        this.fonctionMapper = fonctionMapper;
    }

    @Override
    public FonctionDTO save(FonctionDTO fonctionDTO) {
        log.debug("Request to save Fonction : {}", fonctionDTO);
        Fonction fonction = fonctionMapper.toEntity(fonctionDTO);
        fonction = fonctionRepository.save(fonction);
        return fonctionMapper.toDto(fonction);
    }

    @Override
    @Transactional(readOnly = true)
    public List<FonctionDTO> findAll() {
        log.debug("Request to get all Fonctions");
        return fonctionRepository.findAll().stream()
            .map(fonctionMapper::toDto)
            .collect(Collectors.toCollection(LinkedList::new));
    }


    @Override
    @Transactional(readOnly = true)
    public Optional<FonctionDTO> findOne(Long id) {
        log.debug("Request to get Fonction : {}", id);
        return fonctionRepository.findById(id)
            .map(fonctionMapper::toDto);
    }

    @Override
    public void delete(Long id) {
        log.debug("Request to delete Fonction : {}", id);
        fonctionRepository.deleteById(id);
    }
}
