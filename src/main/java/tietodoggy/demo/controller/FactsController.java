package tietodoggy.demo.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import tietodoggy.demo.entity.Facts;
import tietodoggy.demo.repository.FactsRepository;

import java.net.URI;
import java.util.List;

@CrossOrigin(origins = {"http://localhost:3000", "http://localhost:4200"})
@RestController
@RequestMapping("/facts")
public class FactsController {
    @Autowired
    FactsRepository factsRepository;


    @GetMapping("/facts")
    public List<Facts> getAllFacts(){
        return factsRepository.findAll();
    }

    @DeleteMapping("/facts/{id}")
    public ResponseEntity<Facts> deleteTask(@PathVariable long id) {//void
        Facts Fact = factsRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(
                        HttpStatus.NOT_FOUND, "Entity not found"
                ));
        factsRepository.delete(Fact);
        return ResponseEntity.ok().build();
    }
    @GetMapping("/facts/{id}")
    public Facts getFact(@PathVariable long id) {
        return factsRepository.findById(id)
                .orElseThrow(()->new ResponseStatusException(
                        HttpStatus.NOT_FOUND, "Entity not found"
                ));
    }
    @PostMapping("/facts")
    public ResponseEntity<Facts> createFact(@RequestBody Facts fact) {//void
        Facts createdFact = factsRepository.save(fact);

        URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(createdFact.getFactId())
                .toUri();
        return ResponseEntity.created(uri).build();
    }

}
