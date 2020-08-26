package com.nbprod.eaviculture.service.impl;

import com.nbprod.eaviculture.service.EmployeService;
import com.nbprod.eaviculture.domain.Employe;
import com.nbprod.eaviculture.repository.EmployeRepository;
import com.nbprod.eaviculture.service.dto.EmployeDTO;
import com.nbprod.eaviculture.service.mapper.EmployeMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.LinkedList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * Service Implementation for managing {@link Employe}.
 */
@Service
@Transactional
public class EmployeServiceImpl implements EmployeService {

    private final Logger log = LoggerFactory.getLogger(EmployeServiceImpl.class);

    private final EmployeRepository employeRepository;

    private final EmployeMapper employeMapper;

    public EmployeServiceImpl(EmployeRepository employeRepository, EmployeMapper employeMapper) {
        this.employeRepository = employeRepository;
        this.employeMapper = employeMapper;
    }

    @Override
    public EmployeDTO save(EmployeDTO employeDTO) {
        log.debug("Request to save Employe : {}", employeDTO);
        Employe employe = employeMapper.toEntity(employeDTO);
        employe = employeRepository.save(employe);
        return employeMapper.toDto(employe);
    }

    @Override
    @Transactional(readOnly = true)
    public List<EmployeDTO> findAll() {
        log.debug("Request to get all Employes");
        return employeRepository.findAll().stream()
            .map(employeMapper::toDto)
            .collect(Collectors.toCollection(LinkedList::new));
    }


    @Override
    @Transactional(readOnly = true)
    public Optional<EmployeDTO> findOne(Long id) {
        log.debug("Request to get Employe : {}", id);
        return employeRepository.findById(id)
            .map(employeMapper::toDto);
    }

    @Override
    public void delete(Long id) {
        log.debug("Request to delete Employe : {}", id);
        employeRepository.deleteById(id);
    }
}
