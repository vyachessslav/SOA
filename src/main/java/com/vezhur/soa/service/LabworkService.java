package com.vezhur.soa.service;

import com.vezhur.soa.DTO.LabworkData;
import com.vezhur.soa.entity.LabworkEntity;
import com.vezhur.soa.repository.LabworkRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class LabworkService {

    @Autowired
    private LabworkRepository labworkRepository;

    public LabworkData createLabwork(LabworkData labworkData) {
        LabworkEntity labworkEntity = LabworkEntity.createLabworkEntity(labworkData);
        return new LabworkData(labworkRepository.save(labworkEntity));
    }
}
