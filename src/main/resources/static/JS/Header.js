function createHeader() {
    const headerHTML = `
        <header>
            <nav class="navbar navbar-expand-lg navbar-light bg-light">
                <div class="container-fluid">
                    <a class="navbar-brand" href="#">MyWebsite</a>                    
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