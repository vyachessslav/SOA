package com.vezhur.soa.service;

import com.vezhur.soa.DTO.LabworkDetails;
import com.vezhur.soa.entity.LabworkEntity;
import com.vezhur.soa.exception.BadRequestException;
import com.vezhur.soa.exception.ResourceNotFoundException;
import com.vezhur.soa.parser.FilterSpecification;
import com.vezhur.soa.parser.SortingParser;
import com.vezhur.soa.repository.LabworkRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;

@Service
public class LabworkService {

    @Autowired
    private LabworkRepository labworkRepository;

    public List<LabworkDetails> getAllLabworks(String sort, String filter, int pageNumber, int pageSize) {
        try {
            SortingParser sortingParser = new SortingParser();
            FilterSpecification filterSpecification = new FilterSpecification();
            Sort sorting = sortingParser.parseSort(sort);
            Pageable pageable = PageRequest.of(pageNumber, pageSize, sorting);
            Specification<LabworkEntity> exampleFilter = filterSpecification.parseFilter(filter);

            List<LabworkDetails> labworkDetailsArrayList = new ArrayList<>();
            labworkRepository.findAll(exampleFilter, pageable).forEach(it ->
                    labworkDetailsArrayList.add(new LabworkDetails(it)));
            return labworkDetailsArrayList;
        } catch (Exception ex) {
            throw new BadRequestException("Лабораторные работы не найдены");
        }
    }

    public LabworkDetails getLabWorkById(Integer id) {
        Optional<LabworkEntity> labworkEntity;
        try {
            labworkEntity = labworkRepository.findById(id);
        } catch (Exception ex) {
            throw new ResourceNotFoundException("Лабораторная работа не найдена");
        }
        if (labworkEntity.isPresent()) {
            return new LabworkDetails(labworkEntity.get());
        }
        else {
            throw new BadRequestException("Некорректный ID лабораторной работы");
        }
    }

    public LabworkDetails createLabwork(LabworkDetails labworkDetails) {
        try {
            LabworkEntity labworkEntity = LabworkEntity.createLabworkEntity(labworkDetails);
            return new LabworkDetails(labworkRepository.save(labworkEntity));
        } catch (Exception ex) {
            throw new BadRequestException("Некорректные данные для добавления лабораторной работы");
        }
    }

    public LabworkEntity updateLabwork(Integer id, LabworkDetails labworkDetails) {
        Optional<LabworkEntity> labworkEntity;
        try {
            labworkEntity = labworkRepository.findById(id);
        } catch (Exception ex) {
            throw new ResourceNotFoundException("Лабораторная работа не найдена");
        }
        try {
            return labworkRepository.save(new LabworkEntity(labworkDetails, id, labworkEntity.get().getCreationDate()));
        }
        catch (Exception ex) {
            throw new BadRequestException("Некорректные данные для добавления лабораторной работы");
        }
    }

    public void deleteLabwork(Integer id) {
        if (!labworkRepository.existsById(id)) {
            throw new ResourceNotFoundException("Лабораторная работа не найдена");
        }
        labworkRepository.deleteById(id);
    }

    public Float getMinimalPointSum() {
        try {
            return labworkRepository.getMinimalPointSum();
        } catch (Exception ex) {
            throw new ResourceNotFoundException("Лабораторные работы не найдены");
        }
    }

    public LabworkEntity getEasiestLabwork() {
        try {
            List<LabworkEntity> labworks = labworkRepository.findAll();
            labworks.sort(Comparator.comparing(lab -> lab.getDifficulty().getValue()));
            return labworks.get(0);
        } catch (Exception ex) {
            throw new ResourceNotFoundException("Лабораторные работы не найдены");
        }
    }

    public Integer countAllByAuthorName(String authorName) {
        try {
            return labworkRepository.countAllByAuthor_Name(authorName);
        } catch (Exception ex) {
            throw new ResourceNotFoundException("Лабораторные работы не найдены");
        }
    }

}
