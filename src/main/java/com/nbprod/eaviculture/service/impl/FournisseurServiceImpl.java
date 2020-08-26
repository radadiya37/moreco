package com.nbprod.eaviculture.service.impl;

import com.nbprod.eaviculture.service.FournisseurService;
import com.nbprod.eaviculture.domain.Fournisseur;
import com.nbprod.eaviculture.repository.FournisseurRepository;
import com.nbprod.eaviculture.service.dto.FournisseurDTO;
import com.nbprod.eaviculture.service.mapper.FournisseurMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.LinkedList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * Service Implementation for managing {@link Fournisseur}.
 */
@Service
@Transactional
public class FournisseurServiceImpl implements FournisseurService {

    private final Logger log = LoggerFactory.getLogger(FournisseurServiceImpl.class);

    private final FournisseurRepository fournisseurRepository;

    private final FournisseurMapper fournisseurMapper;

    public FournisseurServiceImpl(FournisseurRepository fournisseurRepository, FournisseurMapper fournisseurMapper) {
        this.fournisseurRepository = fournisseurRepository;
        this.fournisseurMapper = fournisseurMapper;
    }

    @Override
    public FournisseurDTO save(FournisseurDTO fournisseurDTO) {
        log.debug("Request to save Fournisseur : {}", fournisseurDTO);
        Fournisseur fournisseur = fournisseurMapper.toEntity(fournisseurDTO);
        fournisseur = fournisseurRepository.save(fournisseur);
        return fournisseurMapper.toDto(fournisseur);
    }

    @Override
    @Transactional(readOnly = true)
    public List<FournisseurDTO> findAll() {
        log.debug("Request to get all Fournisseurs");
        return fournisseurRepository.findAll().stream()
            .map(fournisseurMapper::toDto)
            .collect(Collectors.toCollection(LinkedList::new));
    }


    @Override
    @Transactional(readOnly = true)
    public Optional<FournisseurDTO> findOne(Long id) {
        log.debug("Request to get Fournisseur : {}", id);
        return fournisseurRepository.findById(id)
            .map(fournisseurMapper::toDto);
    }

    @Override
    public void delete(Long id) {
        log.debug("Request to delete Fournisseur : {}", id);
        fournisseurRepository.deleteById(id);
    }
}
