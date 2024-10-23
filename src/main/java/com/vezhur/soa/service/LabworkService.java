package com.vezhur.soa.service;

import com.vezhur.soa.DTO.LabworkDetails;
import com.vezhur.soa.entity.LabworkEntity;
import com.vezhur.soa.repository.LabworkRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class LabworkService {

    @Autowired
    private LabworkRepository labworkRepository;

    public LabworkDetails createLabwork(LabworkDetails labworkDetails) {
        LabworkEntity labworkEntity = LabworkEntity.createLabworkEntity(labworkDetails);
        return new LabworkDetails(labworkRepository.save(labworkEntity));
    }
}
