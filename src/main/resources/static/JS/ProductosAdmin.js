document.addEventListener("DOMContentLoaded", function () {
    const tabla = document.getElementById("tablaProductos");
    let filaSeleccionada = null;
    let modoEdicion = false;

    // Función para agregar eventos de selección a las filas
    function aplicarEventosSeleccion() {
        const filas = tabla.querySelectorAll("tr");
        filas.forEach(fila => {
            fila.addEventListener("click", () => {
                if (filaSeleccionada) filaSeleccionada.classList.remove("table-active");
                filaSeleccionada = fila;
                fila.classList.add("table-active");
            });
        });
    }

    aplicarEventosSeleccion();

    // Consultar productos
document.getElementById("btnConsultar").addEventListener("click", () => {
    const idBuscado = prompt("Ingrese el ID del producto (Ej: 001):");

    if (!idBuscado) {
        alert("ID no ingresado.");
        return;
    }

    const filas = tabla.querySelectorAll("tr");
    let encontrado = false;

    filas.forEach(fila => {
        const celdas = fila.querySelectorAll("td");
        if (celdas.length > 0 && celdas[0].textContent === idBuscado) {
            // Resaltar fila
            fila.scrollIntoView({ behavior: "smooth", block: "center" });
            fila.classList.add("table-success");

            // Quitar resaltado después de unos segundos
            setTimeout(() => {
                fila.classList.remove("table-success");
            }, 3000);

            encontrado = true;
        }
    });

    if (!encontrado) {
        alert(`No se encontró ningún producto con ID ${idBuscado}.`);
    }
});
    // Botón Guardar
    document.getElementById("btnGuardarProducto").addEventListener("click", () => {
        const nombre = document.getElementById("nombre").value.trim();
        const marca = document.getElementById("marca").value.trim();
        const descripcion = document.getElementById("descripcion").value.trim();
        const genero = document.getElementById("genero").value;
        const tipo = document.getElementById("tipo").value;
        const colores = document.getElementById("colores").value.trim();
        const precio = document.getElementById("precio").value.trim();

        if (nombre && marca && descripcion && genero !== "Seleccionar" && tipo !== "Seleccionar" && colores && precio) {
            if (modoEdicion && filaSeleccionada) {
                // Actualizar datos en la fila seleccionada
                filaSeleccionada.children[1].textContent = nombre;
                filaSeleccionada.children[2].textContent = descripcion;
                filaSeleccionada.children[3].textContent = marca;
                filaSeleccionada.children[4].textContent = genero;
                filaSeleccionada.children[5].textContent = tipo;
                filaSeleccionada.children[6].textContent = colores;
                filaSeleccionada.children[7].textContent = `S/${parseFloat(precio).toFixed(2)}`;
                alert("Producto actualizado.");
            } else {
                // Crear nueva fila
                const nuevaFila = document.createElement("tr");
                nuevaFila.innerHTML = `
                    <td>${String(tabla.rows.length + 1).padStart(3, '0')}</td>
                    <td>${nombre}</td>
                    <td>${descripcion}</td>
                    <td>${marca}</td>
                    <td>${genero}</td>
                    <td>${tipo}</td>
                    <td>${colores}</td>
                    <td>S/${parseFloat(precio).toFixed(2)}</td>
                `;
                nuevaFila.addEventListener("click", () => {
                    if (filaSeleccionada) filaSeleccionada.classList.remove("table-active");
                    filaSeleccionada = nuevaFila;
                    nuevaFila.classList.add("table-active");
                });
                tabla.appendChild(nuevaFila);
                alert("Producto registrado.");
            }

            // Resetear formulario y variables
            document.getElementById("formProducto").reset();
            modoEdicion = false;
            filaSeleccionada = null;

            const modal = bootstrap.Modal.getInstance(document.getElementById("modalRegistrarProducto"));
            modal.hide();
        } else {
            alert("Por favor, completa todos los campos.");
        }
    });

    // Botón Actualizar
    document.getElementById("btnActualizar").addEventListener("click", () => {
        if (!filaSeleccionada) {
            alert("Selecciona una fila para actualizar.");
            return;
        }

        // Cargar datos de la fila en el formulario
        document.getElementById("nombre").value = filaSeleccionada.children[1].textContent;
        document.getElementById("descripcion").value = filaSeleccionada.children[2].textContent;
        document.getElementById("marca").value = filaSeleccionada.children[3].textContent;
        document.getElementById("genero").value = filaSeleccionada.children[4].textContent;
        document.getElementById("tipo").value = filaSeleccionada.children[5].textContent;
        document.getElementById("colores").value = filaSeleccionada.children[6].textContent;
        document.getElementById("precio").value = filaSeleccionada.children[7].textContent.replace("S/", "").trim();

        modoEdicion = true;

        const modal = new bootstrap.Modal(document.getElementById("modalRegistrarProducto"));
        modal.show();
    });

    // Botón Eliminar
    document.getElementById("btnEliminar").addEventListener("click", () => {
        if (!filaSeleccionada) {
            alert("Selecciona una fila para eliminar.");
            return;
        }
        filaSeleccionada.remove();
        filaSeleccionada = null;
        alert("Producto eliminado.");
    });
});
