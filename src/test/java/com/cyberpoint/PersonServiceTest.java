package com.cyberpoint;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

import com.cyberpoint.entity.Person;
import com.cyberpoint.exception.PersonNotFoundException;
import com.cyberpoint.exception.ProductNotFoundException;
import com.cyberpoint.repository.PersonRepository;
import com.cyberpoint.service.PersonService;
import java.util.Optional;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

@SpringBootTest
public class PersonServiceTest {

  @Autowired
  PersonService personService;

  @MockBean
  PersonRepository personRepository;

  @Test
  public void testPersonService() {
    Person person = new Person();
    person.setId(10L);
    person.setFullname("Lucas Nunes");
    person.setUsername("lucaspnunes1");
    person.setEmail("lucasnunespacheco@gmail.com");
    person.setPassword("feicebuque");
    person.setAddress("R Acioli Vaz de Andrade, 51");

    when(personRepository.findById(10L)).thenReturn(Optional.of(person));

    Person returnedPerson = personService.findPersonById(10L);

    assertEquals(returnedPerson.getId(), person.getId());
    assertEquals(returnedPerson.getFullname(), person.getFullname());
    assertEquals(returnedPerson.getUsername(), person.getUsername());
    assertEquals(returnedPerson.getEmail(), person.getEmail());
    assertEquals(returnedPerson.getPassword(), person.getPassword());
    assertEquals(returnedPerson.getAddress(), person.getAddress());

  }

  @Test
  public void PersonNotFound(){
    when(personRepository.findById(any()))
        .thenReturn(Optional.empty());

    assertThrows(PersonNotFoundException.class, () -> personService.findPersonById(699L));
    Mockito.verify(personRepository).findById(699L);
  }

}
