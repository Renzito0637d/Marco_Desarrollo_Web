function createHeader() {
    // Cargar el CSS principal
  const link = document.createElement('link');
  link.rel = 'stylesheet';
  link.href = '../static/CSS/Header.css';
  document.head.appendChild(link);

    const headerHTML = `
        <header>
            <nav class="container navbar navbar-expand-lg navbar-light bg-light">
                <div class="container-fluid">
                    <a class="navbar-brand" href="#">Urban logo.png</a>                    
                    <div class="collapse navbar-collapse" id="navbarNav">
                        <ul class="navbar-nav">
                            <li class="nav-item">
                                <a class="nav-link active" aria-current="page" href="#">Inicio</a>
                            </li>
                            <li class="nav-item">
                                <a class="nav-link" href="#">Nosotros</a>
                            </li>
                            <li class="nav-item">
                                <a class="nav-link" href="#">Contactanos</a>
                            </li>
                            <li class="nav-item">
                                <a class="nav-link" href="#">Redes</a>
                            </li>
                        </ul>
                    </div>
                </div>
            </nav>
        </header>
    `;
    document.body.insertAdjacentHTML('afterbegin', headerHTML);
}