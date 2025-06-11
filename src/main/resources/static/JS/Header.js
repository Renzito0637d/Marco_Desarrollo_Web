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
            <li><a href="../templates/Inicio.html" class="nav-link px-2 text-secondary">Inicio</a></li>
            <li><a href="../templates/Catalogo.html" class="nav-link px-2 text-white">Catalogo</a></li>
            <li><a href="../templates/MyV.html" class="nav-link px-2 text-white">Nosotros</a></li>
            <li><a href="../templates/contacto.html" class="nav-link px-2 text-white">Contactanos</a></li>
            <li><a href="../templates/Redes.html" class="nav-link px-2 text-white">Redes</a></li>
            <li><a href="../templates/MisPedidos.html" class="nav-link px-2 text-white">Mis pedidos</a></li>
          </ul>

            <div class="text-end">
                <a href="../templates/carrito.html" class="btn btn-outline-light position-relative">
                        <i class="bi bi-cart-fill"></i>
                        <svg xmlns="http://www.w3.org/2000/svg" width="16" height="16" fill="currentColor" class="bi bi-cart-fill" viewBox="0 0 16 16">
                            <path d="M0 1.5A.5.5 0 0 1 .5 1H2a.5.5 0 0 1 .485.379L2.89 3H14.5a.5.5 0 0 1 .491.592l-1.5 8A.5.5 0 0 1 13 12H4a.5.5 0 0 1-.491-.408L2.01 3.607 1.61 2H.5a.5.5 0 0 1-.5-.5M5 12a2 2 0 1 0 0 4 2 2 0 0 0 0-4m7 0a2 2 0 1 0 0 4 2 2 0 0 0 0-4m-7 1a1 1 0 1 1 0 2 1 1 0 0 1 0-2m7 0a1 1 0 1 1 0 2 1 1 0 0 1 0-2"/>
                        </svg>                        
                </a>
                <a href="../templates/Login.html"><button type="button" class="btn btn-outline-light me-2">Iniciar sección</button></a>
                <button type="button" class="btn btn-warning">Registrarse</button>
            </div>
        </div>
      </div>
    </header>
    `;
    document.body.insertAdjacentHTML('afterbegin', headerHTML);
}