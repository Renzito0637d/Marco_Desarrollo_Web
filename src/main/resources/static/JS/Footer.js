function createFooter() {
    // Cargar el CSS principal
    const link = document.createElement('link');
    link.rel = 'stylesheet';
    link.href = '/CSS/Footer.css';
    document.head.appendChild(link);

    const headerHTML = `
    <footer>
        <div class="conte row row-cols-1 row-cols-sm-2 row-cols-md-5">
            <div class="col mb-3"> 
                <a href="../templates/reclamos.html" >
                <img src="/IMG/libroreclacion.png" alt="Logo UrbanFeet" class="d-inline-block align-text-top libro" style="width:100%">
                </a>
            </div>
            <div class="col mb-3">
            </div>
            <div class="col mb-3">
                <h5>Menu</h5>
                <ul class="nav flex-column">
                    <li class="nav-item mb-2"><a href="#" class="nav-link p-0 text-body-secondary">Inicio</a></li>
                    <li class="nav-item mb-2"><a href="#" class="nav-link p-0 text-body-secondary">Catalogo</a></li>
                    <li class="nav-item mb-2"><a href="#" class="nav-link p-0 text-body-secondary">Nosotros</a></li>
                    <li class="nav-item mb-2"><a href="#" class="nav-link p-0 text-body-secondary">Contactanos</a></li>
                    <li class="nav-item mb-2"><a href="#" class="nav-link p-0 text-body-secondary">Redes</a></li>
                    <li class="nav-item mb-2"><a href="#" class="nav-link p-0 text-body-secondary">Mis pedidos</a></li>
                </ul>
            </div>
            <div class="col mb-3">
                <h5>Redes</h5>
                <ul class="nav flex-column">
                    <li class="nav-item mb-2"><a href="#" class="nav-link p-0 text-body-secondary">Facebook</a></li>
                    <li class="nav-item mb-2"><a href="#" class="nav-link p-0 text-body-secondary">Instagram</a></li>
                    <li class="nav-item mb-2"><a href="#" class="nav-link p-0 text-body-secondary">Twitter</a></li>
                    <li class="nav-item mb-2"><a href="#" class="nav-link p-0 text-body-secondary">Tiktok</a></li>                    
                </ul>
            </div>
            <div class="col mb-3">
                <h5>Catálogo</h5>
                <ul class="nav flex-column">
                    <li class="nav-item mb-2"><a href="#" class="nav-link p-0 text-body-secondary">Mujer</a></li>
                    <li class="nav-item mb-2"><a href="#" class="nav-link p-0 text-body-secondary">Hombre</a></li>
                    <li class="nav-item mb-2"><a href="#" class="nav-link p-0 text-body-secondary">Niñas</a></li>
                    <li class="nav-item mb-2"><a href="#" class="nav-link p-0 text-body-secondary">Niños</a></li>                    
                </ul>
            </div>            
        </div>
        <div class="border-top conte text">
                Copyright © 2024 - UrbanFeet - Todos los derechos reservados
            </div>
    </footer>
    `;
    document.body.insertAdjacentHTML('beforeend', headerHTML);
}
