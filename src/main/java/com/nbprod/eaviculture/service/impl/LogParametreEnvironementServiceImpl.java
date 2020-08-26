package com.nbprod.eaviculture.service.impl;

import com.nbprod.eaviculture.service.LogParametreEnvironementService;
import com.nbprod.eaviculture.domain.LogParametreEnvironement;
import com.nbprod.eaviculture.repository.LogParametreEnvironementRepository;
import com.nbprod.eaviculture.service.dto.LogParametreEnvironementDTO;
import com.nbprod.eaviculture.service.mapper.LogParametreEnvironementMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.LinkedList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * Service Implementation for managing {@link LogParametreEnvironement}.
 */
@Service
@Transactional
public class LogParametreEnvironementServiceImpl implements LogParametreEnvironementService {

    private final Logger log = LoggerFactory.getLogger(LogParametreEnvironementServiceImpl.class);

    private final LogParametreEnvironementRepository logParametreEnvironementRepository;

    private final LogParametreEnvironementMapper logParametreEnvironementMapper;

    public LogParametreEnvironementServiceImpl(LogParametreEnvironementRepository logParametreEnvironementRepository, LogParametreEnvironementMapper logParametreEnvironementMapper) {
        this.logParametreEnvironementRepository = logParametreEnvironementRepository;
        this.logParametreEnvironementMapper = logParametreEnvironementMapper;
    }

    @Override
    public LogParametreEnvironementDTO save(LogParametreEnvironementDTO logParametreEnvironementDTO) {
        log.debug("Request to save LogParametreEnvironement : {}", logParametreEnvironementDTO);
        LogParametreEnvironement logParametreEnvironement = logParametreEnvironementMapper.toEntity(logParametreEnvironementDTO);
        logParametreEnvironement = logParametreEnvironementRepository.save(logParametreEnvironement);
        return logParametreEnvironementMapper.toDto(logParametreEnvironement);
    }

    @Override
    @Transactional(readOnly = true)
    public List<LogParametreEnvironementDTO> findAll() {
        log.debug("Request to get all LogParametreEnvironements");
        return logParametreEnvironementRepository.findAll().stream()
            .map(logParametreEnvironementMapper::toDto)
            .collect(Collectors.toCollection(LinkedList::new));
    }


    @Override
    @Transactional(readOnly = true)
    public Optional<LogParametreEnvironementDTO> findOne(Long id) {
        log.debug("Request to get LogParametreEnvironement : {}", id);
        return logParametreEnvironementRepository.findById(id)
            .map(logParametreEnvironementMapper::toDto);
    }

    @Override
    public void delete(Long id) {
        log.debug("Request to delete LogParametreEnvironement : {}", id);
        logParametreEnvironementRepository.deleteById(id);
    }
}
