package com.vezhur.soa.service;

import com.vezhur.soa.DTO.LabworkDetails;
import com.vezhur.soa.entity.LabworkEntity;
import com.vezhur.soa.exception.BadRequestException;
import com.vezhur.soa.exception.ResourceNotFoundException;
import com.vezhur.soa.repository.LabworkRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class LabworkService {

    @Autowired
    private LabworkRepository labworkRepository;

    public List<LabworkDetails> getAllLabworks() {
        try {
            List<LabworkDetails> labworkDetailsArrayList = new ArrayList<>();
            labworkRepository.findAll().forEach(it -> labworkDetailsArrayList.add(new LabworkDetails(it)));
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
            return labworkRepository.minimalPointSum();
        } catch (Exception ex) {
            throw new ResourceNotFoundException("Лабораторные работы не найдены");
        }
    }

}
