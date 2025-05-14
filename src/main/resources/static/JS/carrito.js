function vaciarCarrito() {
    Swal.fire({
        title: "¿Estás seguro de que deseas vaciar todo el carrito?",
        text: "Esta acción no se puede deshacer.",
        icon: 'warning',
        showCancelButton: true,
        confirmButtonText: 'Sí, vaciar',
        cancelButtonText: 'Cancelar'
    }).then((result) => {
        if (result.isConfirmed) {
            // Vaciar carrito en localStorage
            localStorage.removeItem('carrito');
            carrito = [];
            actualizarCarrito(); // Actualiza la vista del carrito
            Swal.fire({
                icon: 'success',
                title: 'El carrito ha sido vaciado.',
                confirmButtonText: 'Cerrar'
            });
        }
    });
}
function procederCompra() {
    if (carrito.length === 0) {
        // Si el carrito está vacío
        Swal.fire({
            title: "El carrito está vacío",
            text: "Agrega productos antes de proceder a la compra.",
            icon: 'warning',
            confirmButtonText: 'Cerrar'
        });
        return;
    }

    Swal.fire({
        title: "¿Estás seguro de que deseas proceder con la compra?",
        icon: 'question',
        showCancelButton: true,
        confirmButtonText: 'Sí, proceder',
        cancelButtonText: 'Cancelar'
    }).then((result) => {
        if (result.isConfirmed) {
            // Aquí puedes agregar lógica para procesar la compra
            // Como simular un pago o redirigir a una página de pago
            localStorage.removeItem('carrito'); // Limpiar carrito del localStorage
            carrito = []; // Vaciar variable carrito
            actualizarCarrito(); // Actualizar la vista del carrito

            Swal.fire({
                icon: 'success',
                title: '¡Gracias por tu compra! El carrito ha sido vaciado.',
                confirmButtonText: 'Cerrar'
            });
        }
    });
}
function actualizarCarrito() {
    const carritoProductos = document.querySelector('#carrito-productos');
    const subtotalElement = document.querySelector('#subtotal');

    // Limpia el contenedor del carrito
    carritoProductos.innerHTML = '';

    if (carrito.length === 0) {
        carritoProductos.innerHTML = '<p>El carrito está vacío</p>';
        subtotalElement.textContent = 'S/ 0.00';
        return;
    }

    carrito.forEach(producto => {
        const productoCarritoHTML = crearProductoCarritoHTML(producto);
        carritoProductos.innerHTML += productoCarritoHTML;
    });

    actualizarSubtotal(); // Actualiza el subtotal después de agregar/eliminar productos
}
function actualizarSubtotal() {
    const subtotalElement = document.querySelector('#subtotal');
    const subtotal = carrito.reduce((total, producto) => total + producto.precio, 0);
    subtotalElement.textContent = `S/ ${subtotal.toFixed(2)}`;
}