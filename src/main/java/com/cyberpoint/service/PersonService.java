package com.cyberpoint.service;

import com.cyberpoint.entity.Person;
import com.cyberpoint.exception.EmailDuplicateException;
import com.cyberpoint.exception.PersonDuplicateException;
import com.cyberpoint.exception.PersonNotFoundException;
import java.util.List;
import com.cyberpoint.repository.PersonRepository;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class PersonService implements UserDetailsService {

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
    String hashedPassword = new BCryptPasswordEncoder()
        .encode(person.getPassword());
    person.setPassword(hashedPassword);
    if(personRepository.existsByUsername(person.getUsername()) ){
      throw new PersonDuplicateException("Usuário já existe!");
    }
    if(personRepository.existsByemail(person.getEmail())) {
      throw new EmailDuplicateException("E-mail já em uso");
    }

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


  @Override
  public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException { //entender o funcionamento disso, acho q posso criar buscas no db customizadas a partir de userdetailsService
    return personRepository.findByusername(username)
        .orElseThrow(() -> new UsernameNotFoundException(username)); //pesqusiar como validar o que ta recebendo, se é email ou username.
  }


}
