package com.nbprod.eaviculture.service.impl;

import com.nbprod.eaviculture.service.LigneEclairageService;
import com.nbprod.eaviculture.domain.LigneEclairage;
import com.nbprod.eaviculture.repository.LigneEclairageRepository;
import com.nbprod.eaviculture.service.dto.LigneEclairageDTO;
import com.nbprod.eaviculture.service.mapper.LigneEclairageMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.LinkedList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * Service Implementation for managing {@link LigneEclairage}.
 */
@Service
@Transactional
public class LigneEclairageServiceImpl implements LigneEclairageService {

    private final Logger log = LoggerFactory.getLogger(LigneEclairageServiceImpl.class);

    private final LigneEclairageRepository ligneEclairageRepository;

    private final LigneEclairageMapper ligneEclairageMapper;

    public LigneEclairageServiceImpl(LigneEclairageRepository ligneEclairageRepository, LigneEclairageMapper ligneEclairageMapper) {
        this.ligneEclairageRepository = ligneEclairageRepository;
        this.ligneEclairageMapper = ligneEclairageMapper;
    }

    @Override
    public LigneEclairageDTO save(LigneEclairageDTO ligneEclairageDTO) {
        log.debug("Request to save LigneEclairage : {}", ligneEclairageDTO);
        LigneEclairage ligneEclairage = ligneEclairageMapper.toEntity(ligneEclairageDTO);
        ligneEclairage = ligneEclairageRepository.save(ligneEclairage);
        return ligneEclairageMapper.toDto(ligneEclairage);
    }

    @Override
    @Transactional(readOnly = true)
    public List<LigneEclairageDTO> findAll() {
        log.debug("Request to get all LigneEclairages");
        return ligneEclairageRepository.findAll().stream()
            .map(ligneEclairageMapper::toDto)
            .collect(Collectors.toCollection(LinkedList::new));
    }


    @Override
    @Transactional(readOnly = true)
    public Optional<LigneEclairageDTO> findOne(Long id) {
        log.debug("Request to get LigneEclairage : {}", id);
        return ligneEclairageRepository.findById(id)
            .map(ligneEclairageMapper::toDto);
    }

    @Override
    public void delete(Long id) {
        log.debug("Request to delete LigneEclairage : {}", id);
        ligneEclairageRepository.deleteById(id);
    }
}
