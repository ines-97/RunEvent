package com.runevent.runnerz.run;

import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Optional;

//code where sql request and call  Repository functions
@RestController
@RequestMapping("/api/runs")//global url

public class Runcontroller {

    private final RunRepository runRepository; // call to the repository

    public Runcontroller(RunRepository runRepository){
        this.runRepository= runRepository;
    } //constructor


  //get
    @GetMapping("/All") //url
    public List<Run> findAll(){
        return runRepository.findAll();
    }
/*dynamic search for id
    @GetMapping("/{id}") //url
    public Run findById(@PathVariable Integer id){
        return runRepository.findById(id);
    }
    */


   // dynamic search for id with the keyword Optional
    @GetMapping("/{id}") //url
    public Run findById(@PathVariable Integer id){
        Optional <Run> o= runRepository.findById(id);
        if(o.isEmpty()){
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }
        return o.get();
    }
//post-add
    @ResponseStatus(HttpStatus.CREATED)//- 200 created- montione the status of http request(created....)
    @PostMapping("")//url
    public void create (@Valid @RequestBody Run run){ //@valid to use validation dependacy from Run record
        runRepository.create(run);
}
    //put-modify
    //@ResponseStatus(HttpStatus.NO_CONTENT)//200 no content
    @PutMapping("m/{id}")
    public void update (@RequestBody Run run,@PathVariable Integer id){
        runRepository.update(run,id);
    }

    //delete-delete
    @ResponseStatus(HttpStatus.NO_CONTENT)//no
    @DeleteMapping("d/{id}")
    public void delete (@PathVariable Integer id){
        runRepository.delete(id);
    }
}
