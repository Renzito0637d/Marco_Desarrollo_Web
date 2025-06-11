document.addEventListener("DOMContentLoaded", () => {
    const tabla = document.querySelector("table tbody");
    const btnGuardar = document.querySelector(".modal-footer .btn-dark");
    const btnActualizar = document.querySelector(".btn-outline-primary");
    const btnEliminar = document.querySelector(".btn-outline-danger");
    const btnConsultar = document.querySelector(".btn-outline-secondary");

    let filaSeleccionada = null;
    let modo = "registrar"; // 'registrar' o 'actualizar'

    // Seleccionar fila al hacer clic
    tabla.addEventListener("click", (e) => {
        const fila = e.target.closest("tr");
        if (fila) {
            // Deseleccionar otras filas
            [...tabla.rows].forEach(f => f.classList.remove("table-active"));
            fila.classList.add("table-active");
            filaSeleccionada = fila;
        }
    });

    // Evento para Registrar
    document.querySelector('[data-bs-target="#modalRegistrarAlmacen"]').addEventListener("click", () => {
        limpiarFormulario();
        modo = "registrar";
        btnGuardar.textContent = "Guardar";
        filaSeleccionada = null;
    });

    // Evento para Actualizar
    btnActualizar.addEventListener("click", () => {
        if (!filaSeleccionada) {
            alert("Selecciona una fila de la tabla para actualizar.");
            return;
        }

        document.getElementById("direccion").value = filaSeleccionada.cells[1].textContent;
        document.getElementById("ubicacion").value = filaSeleccionada.cells[2].textContent;
        document.getElementById("capacidad").value = filaSeleccionada.cells[3].textContent;
        document.getElementById("producto").value = filaSeleccionada.cells[4].textContent;
        document.getElementById("stock").value = filaSeleccionada.cells[6].textContent;

        btnGuardar.textContent = "Actualizar";
        modo = "actualizar";

        const modal = new bootstrap.Modal(document.getElementById("modalRegistrarAlmacen"));
        modal.show();
    });

    // Evento para Guardar (Registrar o Actualizar)
    btnGuardar.addEventListener("click", () => {
        const direccion = document.getElementById("direccion").value;
        const ubicacion = document.getElementById("ubicacion").value;
        const capacidad = document.getElementById("capacidad").value;
        const producto = document.getElementById("producto");
        const idProducto = producto.value;
        const nombreProducto = producto.options[producto.selectedIndex].text.split(" - ")[1];
        const stock = document.getElementById("stock").value;

        if (!direccion || !ubicacion || !capacidad || !idProducto || !stock) {
            alert("Por favor, completa todos los campos.");
            return;
        }

        if (modo === "registrar") {
            const idAlmacen = tabla.rows.length + 1;

            const fila = document.createElement("tr");
            fila.innerHTML = `
                <td>${idAlmacen.toString().padStart(3, '0')}</td>
                <td>${direccion}</td>
                <td>${ubicacion}</td>
                <td>${capacidad}</td>
                <td>${idProducto}</td>
                <td>${nombreProducto}</td>
                <td>${stock}</td>
            `;
            tabla.appendChild(fila);
        } else if (modo === "actualizar" && filaSeleccionada) {
            filaSeleccionada.cells[1].textContent = direccion;
            filaSeleccionada.cells[2].textContent = ubicacion;
            filaSeleccionada.cells[3].textContent = capacidad;
            filaSeleccionada.cells[4].textContent = idProducto;
            filaSeleccionada.cells[5].textContent = nombreProducto;
            filaSeleccionada.cells[6].textContent = stock;
        }

        limpiarFormulario();
        bootstrap.Modal.getInstance(document.getElementById("modalRegistrarAlmacen")).hide();
        btnGuardar.textContent = "Guardar";
        modo = "registrar";
        filaSeleccionada = null;
    });

    // Evento para Eliminar
    btnEliminar.addEventListener("click", () => {
        if (!filaSeleccionada) {
            alert("Selecciona una fila para eliminar.");
            return;
        }

        if (confirm("¿Estás seguro de que deseas eliminar este registro?")) {
            filaSeleccionada.remove();
            filaSeleccionada = null;
        }
    });

    // Evento para Consultar
    btnConsultar.addEventListener("click", () => {
        const idBuscado = prompt("Ingrese el ID del almacén (ej. 001):");

        if (!idBuscado) {
            alert("Debe ingresar un ID válido.");
            return;
        }

        const filas = tabla.querySelectorAll("tr");
        let encontrado = false;

        filas.forEach((fila) => {
            const id = fila.cells[0].textContent;
            fila.classList.remove("table-active"); // Limpia selección previa

            if (id === idBuscado) {
                fila.classList.add("table-active");
                fila.scrollIntoView({ behavior: "smooth", block: "center" });
                filaSeleccionada = fila;
                encontrado = true;
            }
        });

        if (!encontrado) {
            alert(`No se encontró el almacén con ID ${idBuscado}.`);
        }
    });

    function limpiarFormulario() {
        document.getElementById("direccion").value = "";
        document.getElementById("ubicacion").value = "";
        document.getElementById("capacidad").value = "";
        document.getElementById("producto").selectedIndex = 0;
        document.getElementById("stock").value = "";
    }
});


