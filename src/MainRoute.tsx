function MainRoute() {
  return (
    <div style={{ display: "flex", flexDirection: "column", alignItems: "center", height: "100vh", justifyContent: "center" }}>
      <section><h1>Projeto desenvolvido por Lucas Nunes</h1>      </section>

      <h2>Tecnologias utilizadas:</h2>
      
      <div style={{ display: "flex", gap: "10px" }}>
        <img src="https://static.vecteezy.com/system/resources/thumbnails/048/332/150/small/java-programming-language-java-logo-free-png.png" alt="Java" width="50" />
        <img src="https://bgasparotto.com/wp-content/uploads/2017/12/spring-boot-logo.png" alt="Spring Boot" width="50" />
        <img src="https://seeklogo.com/images/J/jwt-logo-65D86B4640-seeklogo.com.png" alt="JWT" width="50" />
        <img src="https://edermfl.wordpress.com/wp-content/uploads/2017/04/boot-data.png" alt="JPA" width="50" />
        <img src="https://seeklogo.com/images/R/react-logo-65B7CD91B5-seeklogo.com.png" alt="React" width="50" />
        <img src="https://static.vecteezy.com/system/resources/previews/027/127/463/non_2x/javascript-logo-javascript-icon-transparent-free-png.png" alt="JavaScript" width="50" />
        <img src="https://avatars.githubusercontent.com/u/874086?s=280&v=4" alt="JUnit" width="50" />

      </div>

      <section>
        <p>Para acessar os produtos</p>
        <a href= "/products" target="_blank">Clique aqui</a>
      </section>
      <footer>
        <br>
        </br>
        <br>
        </br>

      <h3>Contato:</h3>
      <div style={{ display: "flex", gap: "20px" }}>
        {/* Link para o LinkedIn */}
        <a href="https://www.linkedin.com/in/lucas-nunes-750688241/" target="_blank" rel="noopener noreferrer">
          <img src="https://static.vecteezy.com/system/resources/previews/018/930/480/non_2x/linkedin-logo-linkedin-icon-transparent-free-png.png" alt="LinkedIn" width="40" />
        </a>
        
        {/* Link para o GitHub */}
        <a href="https://github.com/lucasnunessg" target="_blank" rel="noopener noreferrer">
          <img src="https://upload.wikimedia.org/wikipedia/commons/9/91/Octicons-mark-github.svg" alt="GitHub" width="40" />
        </a>
      </div>
      </footer>

    </div>
  );
}


export default MainRoute;
