        function actualizarEstado(pedidoId, selectId) {
            const nuevoEstado = document.getElementById(selectId).value;
            alert(`✅ Estado del pedido #${pedidoId} actualizado a: ${nuevoEstado}`);
        }

        function eliminarPedido(pedidoId) {
            if (confirm(`¿Estás seguro de eliminar el pedido #${pedidoId}? Esta acción no se puede deshacer.`)) {
                document.getElementById(`pedido-${pedidoId}`).remove();
                alert(`🗑️ Pedido #${pedidoId} eliminado correctamente.`);
            }
        }

        function activarEdicion(id) {
            document.getElementById(`direccionTexto-${id}`).classList.add('d-none');
            document.getElementById(`pagoTexto-${id}`).classList.add('d-none');
            document.getElementById(`direccionInput-${id}`).classList.remove('d-none');
            document.getElementById(`pagoSelect-${id}`).classList.remove('d-none');

            document.querySelectorAll(`#articulos-${id} .cantidad-texto`).forEach(span => span.classList.add('d-none'));
            document.querySelectorAll(`#articulos-${id} .cantidad-input`).forEach(input => input.classList.remove('d-none'));

            document.getElementById(`botonesEdicion-${id}`).classList.add('d-none');
            document.getElementById(`accionesEdicion-${id}`).classList.remove('d-none');
        }

        function guardarEdicion(id) {
            // Dirección y método de pago
            const nuevaDireccion = document.getElementById(`direccionInput-${id}`).value;
            const nuevoPago = document.getElementById(`pagoSelect-${id}`).value;
            document.getElementById(`direccionTexto-${id}`).textContent = nuevaDireccion;
            document.getElementById(`pagoTexto-${id}`).textContent = nuevoPago;

            // Cantidades de artículos
            const listaArticulos = document.querySelectorAll(`#articulos-${id} li`);
            listaArticulos.forEach(item => {
                const input = item.querySelector('.cantidad-input');
                const texto = item.querySelector('.cantidad-texto');
                const cantidad = input.value;
                texto.textContent = `Cantidad: ${cantidad}`;
            });

            cancelarEdicion(id);
            alert(`✅ Datos del pedido #${id} actualizados correctamente.`);
        }

        function cancelarEdicion(id) {
            document.getElementById(`direccionTexto-${id}`).classList.remove('d-none');
            document.getElementById(`pagoTexto-${id}`).classList.remove('d-none');
            document.getElementById(`direccionInput-${id}`).classList.add('d-none');
            document.getElementById(`pagoSelect-${id}`).classList.add('d-none');

            document.querySelectorAll(`#articulos-${id} .cantidad-texto`).forEach(span => span.classList.remove('d-none'));
            document.querySelectorAll(`#articulos-${id} .cantidad-input`).forEach(input => input.classList.add('d-none'));

            document.getElementById(`botonesEdicion-${id}`).classList.remove('d-none');
            document.getElementById(`accionesEdicion-${id}`).classList.add('d-none');
        }