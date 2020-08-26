package com.nbprod.eaviculture.service.impl;

import com.nbprod.eaviculture.service.DepenseService;
import com.nbprod.eaviculture.domain.Depense;
import com.nbprod.eaviculture.repository.DepenseRepository;
import com.nbprod.eaviculture.service.dto.DepenseDTO;
import com.nbprod.eaviculture.service.mapper.DepenseMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.LinkedList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * Service Implementation for managing {@link Depense}.
 */
@Service
@Transactional
public class DepenseServiceImpl implements DepenseService {

    private final Logger log = LoggerFactory.getLogger(DepenseServiceImpl.class);

    private final DepenseRepository depenseRepository;

    private final DepenseMapper depenseMapper;

    public DepenseServiceImpl(DepenseRepository depenseRepository, DepenseMapper depenseMapper) {
        this.depenseRepository = depenseRepository;
        this.depenseMapper = depenseMapper;
    }

    @Override
    public DepenseDTO save(DepenseDTO depenseDTO) {
        log.debug("Request to save Depense : {}", depenseDTO);
        Depense depense = depenseMapper.toEntity(depenseDTO);
        depense = depenseRepository.save(depense);
        return depenseMapper.toDto(depense);
    }

    @Override
    @Transactional(readOnly = true)
    public List<DepenseDTO> findAll() {
        log.debug("Request to get all Depenses");
        return depenseRepository.findAll().stream()
            .map(depenseMapper::toDto)
            .collect(Collectors.toCollection(LinkedList::new));
    }


    @Override
    @Transactional(readOnly = true)
    public Optional<DepenseDTO> findOne(Long id) {
        log.debug("Request to get Depense : {}", id);
        return depenseRepository.findById(id)
            .map(depenseMapper::toDto);
    }

    @Override
    public void delete(Long id) {
        log.debug("Request to delete Depense : {}", id);
        depenseRepository.deleteById(id);
    }
}
