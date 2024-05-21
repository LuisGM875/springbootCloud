package com.example.crud.service;

import com.example.crud.entity.person;
import com.example.crud.repository.PersonRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;


@Service
public class PersonService {
    @Autowired
    PersonRepository personRepository;

    public List<person> getAllPersons(){
        return personRepository.findAll();
    }

    public Optional<person> getPerson(Long id){
        return personRepository.findById(id);
    }

    public void saveOrUpdate(person person){
        personRepository.save(person);
    }

    public void delete(Long id){
        personRepository.deleteById(id);
    }


}
