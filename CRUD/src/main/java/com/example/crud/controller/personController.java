package com.example.crud.controller;

import com.example.crud.entity.person;
import com.example.crud.service.PersonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping(path = "api/v1/user")
public class personController {

    @Autowired
    private  PersonService personService;

    @GetMapping
    public List<person> getAll(){
        return personService.getAllPersons();
    }

    @GetMapping("/{id}")
    public Optional<person> getById(@PathVariable("id") Long id){
        return personService.getPerson(id);
    }

    @PostMapping
    public void saveUpdate(@RequestBody person person){
         personService.saveOrUpdate(person);
    }

    @DeleteMapping("/delete/{id}")
    public void delete(@PathVariable("id")Long id){
        personService.delete(id);
    }

    @GetMapping("/sinSegurity")
    public String hola(){
        return "Sin seguridad";
    }

    @GetMapping("/conProtected")
    public String holaProtected(){
        return "Con proteccion";
    }

}
