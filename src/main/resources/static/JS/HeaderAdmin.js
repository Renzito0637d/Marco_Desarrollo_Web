function createHeader() {
    // Cargar el CSS principal
  const link = document.createElement('link');
  link.rel = 'stylesheet';
  link.href = '../static/CSS/Header.css';
  document.head.appendChild(link);

    const headerHTML = `
    <nav class="navbar navbar-expand-lg navbar-dark bg-dark">
    <div class="container-fluid">
        <a class="navbar-brand" href="#">Panel de Administración</a>

        <!-- Botón para desplegar menú en pantallas pequeñas -->
        <button class="navbar-toggler" type="button" data-bs-toggle="collapse" data-bs-target="#navbarAdmin" aria-controls="navbarAdmin" aria-expanded="false" aria-label="Toggle navigation">
        <span class="navbar-toggler-icon"></span>
        </button>

        <!-- Opciones del menú -->
        <div class="collapse navbar-collapse" id="navbarAdmin">
        <ul class="navbar-nav me-auto mb-2 mb-lg-0">
            <li class="nav-item">
            <a class="nav-link active" href="Pedidos.html">Pedidos</a>
            </li>
            <li class="nav-item">
            <a class="nav-link" href="Productos.html">Productos</a>
            </li>
            <li class="nav-item">
            <a class="nav-link" href="Usuarios.html">Usuarios</a>
            </li>
            <li class="nav-item">
            <a class="nav-link" href="Almacen.html">Almacen</a>
            </li>
        </ul>

        <!-- Botón de cerrar sesión -->
        <a href="../Inicio.html"><button class="btn btn-outline-light" onclick="cerrarSesion()">Cerrar sesión</button></a>
        </div>
    </div>
    </nav>
    `;
    document.body.insertAdjacentHTML('afterbegin', headerHTML);
}