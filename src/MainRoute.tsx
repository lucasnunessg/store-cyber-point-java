import './MainRouteStyle.css'

function MainRoute() {
  return (
    <div className="main-container">
      <section className="project-info">
        <h1>Projeto desenvolvido por Lucas Nunes</h1>
      </section>

      <h2 className="technologies-title">Tecnologias utilizadas:</h2>
      
      <div className="technologies-container">
        <img src="https://static.vecteezy.com/system/resources/thumbnails/048/332/150/small/java-programming-language-java-logo-free-png.png" alt="Java" className="technology-icon" />
        <img src="https://bgasparotto.com/wp-content/uploads/2017/12/spring-boot-logo.png" alt="Spring Boot" className="technology-icon" />
        <img src="https://seeklogo.com/images/J/jwt-logo-65D86B4640-seeklogo.com.png" alt="JWT" className="technology-icon" />
        <img src="https://edermfl.wordpress.com/wp-content/uploads/2017/04/boot-data.png" alt="JPA" className="technology-icon" />
        <img src="https://seeklogo.com/images/R/react-logo-65B7CD91B5-seeklogo.com.png" alt="React" className="technology-icon" />
        <img src="https://static.vecteezy.com/system/resources/previews/027/127/463/non_2x/javascript-logo-javascript-icon-transparent-free-png.png" alt="JavaScript" className="technology-icon" />
        <img src="https://avatars.githubusercontent.com/u/874086?s=280&v=4" alt="JUnit" className="technology-icon" />
      </div>

      <section className="product-link">
        <p>Para acessar os produtos</p>
        <a href="/products" target="_blank" className="product-link-text">Clique aqui</a>
      </section>

      <footer className="footer">
        <h3 className="contact-title">Contato:</h3>
        <div className="social-links">
          {/* Link para o LinkedIn */}
          <a href="https://www.linkedin.com/in/lucas-nunes-750688241/" target="_blank" rel="noopener noreferrer" className="social-link">
            <img src="https://static.vecteezy.com/system/resources/previews/018/930/480/non_2x/linkedin-logo-linkedin-icon-transparent-free-png.png" alt="LinkedIn" className="social-icon" />
          </a>
          
          {/* Link para o GitHub */}
          <a href="https://github.com/lucasnunessg" target="_blank" rel="noopener noreferrer" className="social-link">
            <img src="https://upload.wikimedia.org/wikipedia/commons/9/91/Octicons-mark-github.svg" alt="GitHub" className="social-icon" />
          </a>
        </div>
      </footer>
    </div>
  );
}

export default MainRoute;
