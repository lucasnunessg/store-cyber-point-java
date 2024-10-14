package com.cyberpoint.controller;

import com.cyberpoint.dto.PersonCreateDto;
import com.cyberpoint.dto.PersonDto;
import com.cyberpoint.entity.Person;
import com.cyberpoint.exception.PersonNotFoundException;
import com.cyberpoint.exception.ProductNotFoundException;
import com.cyberpoint.service.PersonService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/persons")
public class PersonController {

  PersonService personService;

  @Autowired

  public PersonController(PersonService personService) {
    this.personService = personService;
  }

  @GetMapping
  public List<PersonDto> getAllPerson() {
    List<Person> persons = personService.findAll();
    return persons.stream()
        .map(PersonDto::fromEntity)
        .toList();
  }

  @GetMapping("/{id}")
  public PersonDto getById(@PathVariable Long id) {
    return PersonDto.fromEntity(personService.findPersonById(id));
  }

  @PostMapping
  @ResponseStatus(HttpStatus.CREATED)
  public PersonDto create(@RequestBody PersonCreateDto personCreateDto) {
    return PersonDto.fromEntity(personService.create(personCreateDto.toEntity()));
  }

  @PutMapping("/{id}")
  public PersonDto updatePerson(@PathVariable Long id,
      @RequestBody PersonCreateDto personCreateDto) {
    return PersonDto.fromEntity(personService.updatePerson(id, personCreateDto.toEntity()));
  }

  @DeleteMapping("/{id}")
  public ResponseEntity<String> deleteProduct(@PathVariable Long id)
      throws PersonNotFoundException { //dto nao é util aqui, so em get, post e put
    personService.deleteById(id);
    return ResponseEntity.ok("Usuário(a) deletado(a com sucesso!");
  }
}
