function createHeader() {
    // Cargar el CSS principal
  const link = document.createElement('link');
  link.rel = 'stylesheet';
  link.href = '../static/CSS/Header.css';
  document.head.appendChild(link);

    const headerHTML = `
        <header class="p-3 bg-dark text-white">
      <div class="contenedor">
        <div class="d-flex flex-wrap align-items-center justify-content-center justify-content-lg-start">
          <a href="../templates/Inicio.html" class="d-flex align-items-center mb-2 mb-lg-0 text-white text-decoration-none">
            <img src="../static/IMG/logo.png" alt="Logo" class="logo">
          </a>

          <ul class="nav col-12 col-lg-auto me-lg-auto mb-2 justify-content-center mb-md-0">
            <li><a href="#" class="nav-link px-2 text-secondary">Inicio</a></li>
            <li><a href="#" class="nav-link px-2 text-white">Nosotros</a></li>
            <li><a href="#" class="nav-link px-2 text-white">Contactanos</a></li>
            <li><a href="#" class="nav-link px-2 text-white">Redes</a></li>
            <li><a href="#" class="nav-link px-2 text-white">About</a></li>
          </ul>

          <div class="text-end">
            <button type="button" class="btn btn-outline-light me-2">Login</button>
            <button type="button" class="btn btn-warning">Sign-up</button>
          </div>
        </div>
      </div>
    </header>
    `;
    document.body.insertAdjacentHTML('afterbegin', headerHTML);
}