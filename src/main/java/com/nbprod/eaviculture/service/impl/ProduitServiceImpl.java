package com.nbprod.eaviculture.service.impl;

import com.nbprod.eaviculture.service.ProduitService;
import com.nbprod.eaviculture.domain.Produit;
import com.nbprod.eaviculture.repository.ProduitRepository;
import com.nbprod.eaviculture.service.dto.ProduitDTO;
import com.nbprod.eaviculture.service.mapper.ProduitMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.LinkedList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * Service Implementation for managing {@link Produit}.
 */
@Service
@Transactional
public class ProduitServiceImpl implements ProduitService {

    private final Logger log = LoggerFactory.getLogger(ProduitServiceImpl.class);

    private final ProduitRepository produitRepository;

    private final ProduitMapper produitMapper;

    public ProduitServiceImpl(ProduitRepository produitRepository, ProduitMapper produitMapper) {
        this.produitRepository = produitRepository;
        this.produitMapper = produitMapper;
    }

    @Override
    public ProduitDTO save(ProduitDTO produitDTO) {
        log.debug("Request to save Produit : {}", produitDTO);
        Produit produit = produitMapper.toEntity(produitDTO);
        produit = produitRepository.save(produit);
        return produitMapper.toDto(produit);
    }

    @Override
    @Transactional(readOnly = true)
    public List<ProduitDTO> findAll() {
        log.debug("Request to get all Produits");
        return produitRepository.findAll().stream()
            .map(produitMapper::toDto)
            .collect(Collectors.toCollection(LinkedList::new));
    }


    @Override
    @Transactional(readOnly = true)
    public Optional<ProduitDTO> findOne(Long id) {
        log.debug("Request to get Produit : {}", id);
        return produitRepository.findById(id)
            .map(produitMapper::toDto);
    }

    @Override
    public void delete(Long id) {
        log.debug("Request to delete Produit : {}", id);
        produitRepository.deleteById(id);
    }
}
