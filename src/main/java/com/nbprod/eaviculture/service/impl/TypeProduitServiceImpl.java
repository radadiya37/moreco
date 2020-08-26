package com.nbprod.eaviculture.service.impl;

import com.nbprod.eaviculture.service.TypeProduitService;
import com.nbprod.eaviculture.domain.TypeProduit;
import com.nbprod.eaviculture.repository.TypeProduitRepository;
import com.nbprod.eaviculture.service.dto.TypeProduitDTO;
import com.nbprod.eaviculture.service.mapper.TypeProduitMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.LinkedList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * Service Implementation for managing {@link TypeProduit}.
 */
@Service
@Transactional
public class TypeProduitServiceImpl implements TypeProduitService {

    private final Logger log = LoggerFactory.getLogger(TypeProduitServiceImpl.class);

    private final TypeProduitRepository typeProduitRepository;

    private final TypeProduitMapper typeProduitMapper;

    public TypeProduitServiceImpl(TypeProduitRepository typeProduitRepository, TypeProduitMapper typeProduitMapper) {
        this.typeProduitRepository = typeProduitRepository;
        this.typeProduitMapper = typeProduitMapper;
    }

    @Override
    public TypeProduitDTO save(TypeProduitDTO typeProduitDTO) {
        log.debug("Request to save TypeProduit : {}", typeProduitDTO);
        TypeProduit typeProduit = typeProduitMapper.toEntity(typeProduitDTO);
        typeProduit = typeProduitRepository.save(typeProduit);
        return typeProduitMapper.toDto(typeProduit);
    }

    @Override
    @Transactional(readOnly = true)
    public List<TypeProduitDTO> findAll() {
        log.debug("Request to get all TypeProduits");
        return typeProduitRepository.findAll().stream()
            .map(typeProduitMapper::toDto)
            .collect(Collectors.toCollection(LinkedList::new));
    }


    @Override
    @Transactional(readOnly = true)
    public Optional<TypeProduitDTO> findOne(Long id) {
        log.debug("Request to get TypeProduit : {}", id);
        return typeProduitRepository.findById(id)
            .map(typeProduitMapper::toDto);
    }

    @Override
    public void delete(Long id) {
        log.debug("Request to delete TypeProduit : {}", id);
        typeProduitRepository.deleteById(id);
    }
}
