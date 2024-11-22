package com.cyberpoint;

import com.cyberpoint.entity.Person;
import com.cyberpoint.repository.PersonRepository;
import com.cyberpoint.security.Role;
import java.util.ArrayList;
import org.springframework.boot.CommandLineRunner;
import java.util.List;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;
import com.cyberpoint.entity.Product;
import com.cyberpoint.repository.ProductRepository;


//essa classe é independente a import.sql, aqui posso fazer mais inserções ao subir aplicação independente da import.sql
@Component
public class DatabaseSeeder implements CommandLineRunner {

  private final ProductRepository productRepository;
  private final PersonRepository personRepository;
  private final PasswordEncoder passwordEncoder;

  public DatabaseSeeder(ProductRepository productRepository, PersonRepository personRepository,
      PasswordEncoder passwordEncoder) {
    this.productRepository = productRepository;
    this.personRepository = personRepository;
    this.passwordEncoder = passwordEncoder;
  }

  @Override
  public void run(String... args) throws Exception {
    List<Product> products = seedProducts();
    List<Person> persons = seedPerson();

    productRepository.saveAll(products); //salvando ja com o relacionamento
  }

  private List<Product> seedProducts() {
    List<Product> products = new ArrayList<>();
    products.add(new Product("Cadeira gamerrrrr", 1500d, "Cadeira ergonômica", "electronic",
        "https://encrypted-tbn3.gstatic.com/shopping?q=tbn:ANd9GcRA8skCQjfadR_IwFKCbHBIQGOglbKGOESbkBGxE7w64Sy1wa7SWHBRSM4cd1bdWOYQu6cQLOuFCIyPjwuVU_Zb0AExrSxPubrYDlDTCsyw9xE-WwvIHAN9&usqp=CAE"));
    products.add(new Product("Cadeira gamer", 1500d, "Cadeira ergonômica", "electronic",
        "https://encrypted-tbn3.gstatic.com/shopping?q=tbn:ANd9GcRA8skCQjfadR_IwFKCbHBIQGOglbKGOESbkBGxE7w64Sy1wa7SWHBRSM4cd1bdWOYQu6cQLOuFCIyPjwuVU_Zb0AExrSxPubrYDlDTCsyw9xE-WwvIHAN9&usqp=CAE"));

    products.add(new Product("Teclado Mecânico RGB", 300d, "Teclado com iluminação RGB", "peripheral",
        "https://images.tcdn.com.br/img/img_prod/406359/teclado_gamer_semi_mecanico_anti_ghosting_suporte_para_celular_revestimento_em_metal_clanm_cl_tm8153_4997_2_0f8b4437b36be18c510fed281b159d80.jpg"));
    products.add(new Product("Mouse Gamer", 150d, "Mouse ergonômico com sensor óptico", "peripheral",
        "https://cdn.awsli.com.br/2500x2500/2602/2602619/produto/240442636/mouse-gamer-razer-cobra-rgb-6-botoes-programaveis-8500-dpi-black-rz0104650100r3u-33mbetfesk.jpg"));
    products.add(new Product("Monitor 24 polegadas", 900d, "Monitor Full HD 24 polegadas", "electronic",
        "https://cdn.shoppub.io/cdn-cgi/image/w=1000,h=1000,q=80,f=auto/oficinadosbits/media/uploads/produtos/foto/hkqqjqsm/file.png"));
    products.add(new Product("Headset Gamer", 200d, "Headset com som surround 7.1", "electronic",
        "https://cdn.shoppub.io/cdn-cgi/image/w=1000,h=1000,q=80,f=auto/oficinadosbits/media/uploads/produtos/foto/kpprwqsz/file.png"));
    products.add(new Product("Notebook Gamer", 4500d, "Notebook com placa de vídeo dedicada", "electronic",
        "https://images4.kabum.com.br/produtos/fotos/339504/notebook-gamer-acer-nitro-5-amd-ryzen-7-4800h-geforce-gtx-1650-8gb-ram-ssd-256gb-hdd-1tb-15-6-fhd-ips-144hz-windows-11-an515-44-r629_1652732334_g.jpg"));
    products.add(new Product("Smartphone Android", 1200d, "Smartphone com sistema Android", "electronic",
        "https://images.kabum.com.br/produtos/fotos/sync_mirakl/525904/Smartphone-Samsung-Galaxy-A15-A155m-128GB-4GB-RAM-C-mera-Traseira-Tripla-C-mera-Frontal-13MP-Tela-6-5-4G_1730146660_gg.jpg"));
    products.add(new Product("Tablet", 800d, "Tablet com tela de 10 polegadas", "electronic",
        "https://m.media-amazon.com/images/I/71cIZrMWNHL._AC_UF1000,1000_QL80_.jpg"));
    products.add(new Product("Smartwatch", 300d, "Relógio inteligente com monitor cardíaco", "electronic",
        "https://mirandacomputacao.jetassets.com.br/produto/multifotos/47856-1_47856.jpg"));
    products.add(new Product("Impressora Multifuncional", 700d, "Impressora com scanner e copiadora", "electronic",
        "https://s3-sa-east-1.amazonaws.com/interplacefiles/produtos/imagens/010264/010264_98615.jpg"));
    products.add(new Product("Drone", 1500d, "Drone com câmera HD", "electronic",
        "https://http2.mlstatic.com/D_NQ_NP_979905-MLB52430759556_112022-O.webp"));

    return productRepository.saveAll(products);
  }



  private List<Person> seedPerson() {
    List<Person> persons = new ArrayList<>();

    persons.add(
        new Person("Lucas Nunes", "lucaspnunes1", "lucas@dev.com", passwordEncoder.encode("123456"),
            "R. Acioli Vaz de Andrade, 51, Andrade", Role.ADMIN));

    persons.add(new Person("Julia Trindade Modernel", "jtm", "julia@gmail.com",
        passwordEncoder.encode("123456"),
        "R. Acioli Vaz de Andrade, 51, Andrade", Role.ADMIN));

    persons.add(new Person("Cristian Nunes", "cristianpnunes", "cristian@gmail.com",
        passwordEncoder.encode("123456"),
        "R. Acioli Vaz de Andrade, 51, Andrade", Role.CLIENT));
    return personRepository.saveAll(persons);
  }


}
