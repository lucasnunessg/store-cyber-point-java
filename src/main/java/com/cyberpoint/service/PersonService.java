package com.cyberpoint.service;

import com.cyberpoint.entity.Person;
import com.cyberpoint.exception.PersonNotFoundException;
import java.util.List;
import com.cyberpoint.repository.PersonRepository;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PersonService {

  PersonRepository personRepository;

  @Autowired
  public PersonService(PersonRepository personRepository) {
    this.personRepository = personRepository;
  }

  public List<Person> findAll() {

    return personRepository.findAll();
  }

  public Person findPersonById(Long id) {
    Optional<Person> person = personRepository.findById(id);

    if (person.isEmpty()) {
      throw new PersonNotFoundException();
    }
    return person.get();
  }

  public Person create(Person person) {
    return personRepository.save(person);
  }

  public Person deleteById(Long id) {
    Person person = findPersonById(id);

    personRepository.deleteById(id);
    return person;

  }

  public Person updatePerson(Long id, Person person) {
    Person persomFromDb = findPersonById(id);

    persomFromDb.setFullname(person.getFullname());
    persomFromDb.setAddress(person.getAddress());
    persomFromDb.setEmail(person.getEmail());
    persomFromDb.setUsername(person.getUsername());
    persomFromDb.setPassword(person.getPassword());

    return personRepository.save(persomFromDb);
  }


}
