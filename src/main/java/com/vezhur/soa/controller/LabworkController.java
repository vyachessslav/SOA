package com.vezhur.soa.controller;

import com.vezhur.soa.DTO.LabworkDetails;
import com.vezhur.soa.entity.LabworkEntity;
import com.vezhur.soa.exception.BadRequestException;
import com.vezhur.soa.exception.ResourceNotFoundException;
import com.vezhur.soa.service.LabworkService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("${api.endpoints.base-url}/labworks")
public class LabworkController {

    @Autowired
    private LabworkService labworkService;

    @GetMapping
    public ResponseEntity<List<LabworkDetails>> getAllLabworks(
            @RequestParam(required = false) String sort,
            @RequestParam(required = false) String filter,
            @RequestParam(required = false) Integer pageNumber,
            @RequestParam(defaultValue = "10") Integer pageSize) {

        //TODO:Add logic for filtering, sorting, and pagination

        return ResponseEntity.ok(labworkService.getAllLabworks());
    }

    @PostMapping
    public ResponseEntity<LabworkDetails> createLabwork(@RequestBody LabworkDetails labwork) {
        return ResponseEntity.ok(labworkService.createLabwork(labwork));
    }

    @GetMapping("/{labwork-id}")
    public ResponseEntity<LabworkDetails> getLabWorkById(@PathVariable("labwork-id") Integer id) {
        return ResponseEntity.ok(labworkService.getLabWorkById(id));
    }

    @PutMapping("/{labwork-id}")
    public ResponseEntity<LabworkEntity> updateLabwork(@PathVariable("labwork-id") Integer id, @RequestBody LabworkDetails labwork) {
        return ResponseEntity.ok(labworkService.updateLabwork(id, labwork));
    }

    @DeleteMapping("/{labwork-id}")
    public ResponseEntity<Void> deleteLabwork(@PathVariable("labwork-id") Integer id) {
        labworkService.deleteLabwork(id);
        return ResponseEntity.ok().build();
    }

    @GetMapping("/minimal-point/sum")
    public ResponseEntity<Float> getMinimalPointSum() {
        return ResponseEntity.ok(labworkService.getMinimalPointSum());
    }

    @GetMapping("/difficulty/min")
    public ResponseEntity<String> getEasiestLabwork() {
        return ResponseEntity.ok("OK");
    }

    @GetMapping("/author/count")
    public ResponseEntity<String> getLabworksCountByAuthor() {
        return ResponseEntity.ok("OK");
    }
}
