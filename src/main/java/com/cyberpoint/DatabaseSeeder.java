package com.cyberpoint;

import com.cyberpoint.entity.Person;
import com.cyberpoint.repository.PersonRepository;
import java.util.ArrayList;
import org.springframework.boot.CommandLineRunner;
import java.util.List;
import org.springframework.stereotype.Component;
import com.cyberpoint.entity.Product;
import com.cyberpoint.repository.ProductRepository;


//essa classe é independente a import.sql, aqui posso fazer mais inserções ao subir aplicação independente da import.sql
@Component
public class DatabaseSeeder implements CommandLineRunner {

  private final ProductRepository productRepository;
  private final PersonRepository personRepository;

  public DatabaseSeeder(ProductRepository productRepository, PersonRepository personRepository) {
    this.productRepository = productRepository;
    this.personRepository = personRepository;
  }

  @Override
  public void run(String... args) throws Exception {
    List<Product> products = seedProducts();
    List<Person> persons = seedPerson();

    products.get(0).setPerson(persons.get(0)); //definindo o relacionamento
    products.get(1).setPerson(persons.get(1));

   productRepository.saveAll(products); //salvando ja com o relacionamento
}

  private List<Product> seedProducts() {
    List<Product> products = new ArrayList<>();
   Product product = new Product("Cadeira gamerrrrr", 1500d, "Cadeira ergometrica", "electronic",
       "https://encrypted-tbn3.gstatic.com/shopping?q=tbn:ANd9GcRA8skCQjfadR_IwFKCbHBIQGOglbKGOESbkBGxE7w64Sy1wa7SWHBRSM4cd1bdWOYQu6cQLOuFCIyPjwuVU_Zb0AExrSxPubrYDlDTCsyw9xE-WwvIHAN9&usqp=CAE");
    products.add(product);
    products.add(new Product("Cadeira gamer", 1500d, "Cadeira ergometrica", "electronic",
        "https://encrypted-tbn3.gstatic.com/shopping?q=tbn:ANd9GcRA8skCQjfadR_IwFKCbHBIQGOglbKGOESbkBGxE7w64Sy1wa7SWHBRSM4cd1bdWOYQu6cQLOuFCIyPjwuVU_Zb0AExrSxPubrYDlDTCsyw9xE-WwvIHAN9&usqp=CAE"));
   return productRepository.saveAll(products);
  }



  private List<Person> seedPerson() {
    List<Person> persons = new ArrayList<>();

    persons.add(new Person("Lucas Nunes", "lucaspnunes1", "lucas@dev.com", "123456", "R. Acioli Vaz de Andrade, 51, Andrade"));

    persons.add(new Person("Julia Trindade Modernel", "jtm", "julia@gmail.com", "123456", "R. Acioli Vaz de Andrade, 51, Andrade"));
 return personRepository.saveAll(persons);
}


}
