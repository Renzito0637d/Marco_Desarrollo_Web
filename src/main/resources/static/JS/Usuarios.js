document.addEventListener("DOMContentLoaded", function () {
    const tabla = document.querySelector("table tbody");
    let filaSeleccionada = null;
    let modoEdicion = false;

    // Asignar eventos a las filas existentes
    function asignarEventosFila(fila) {
        fila.addEventListener("click", () => {
            if (filaSeleccionada) filaSeleccionada.classList.remove("table-active");
            filaSeleccionada = fila;
            fila.classList.add("table-active");
        });
    }

    // Asignar eventos a todas las filas al cargar
    tabla.querySelectorAll("tr").forEach(asignarEventosFila);

    // Botón Consultar
    document.querySelector(".btn-outline-secondary").addEventListener("click", () => {
        const idBuscado = prompt("Ingrese el ID del usuario:");

        if (!idBuscado) return alert("ID no ingresado.");

        let encontrado = false;
        tabla.querySelectorAll("tr").forEach(fila => {
            const celdas = fila.querySelectorAll("td");
            if (celdas.length > 0 && celdas[0].textContent === idBuscado) {
                fila.scrollIntoView({ behavior: "smooth", block: "center" });
                fila.classList.add("table-success");
                setTimeout(() => fila.classList.remove("table-success"), 3000);
                encontrado = true;
            }
        });

        if (!encontrado) alert(`No se encontró ningún usuario con ID ${idBuscado}.`);
    });

    // Botón Guardar (en modal)
    document.querySelector("#modalRegistrarUsuario .btn-dark").addEventListener("click", () => {
        const id = String(tabla.rows.length + 1).padStart(3, '0');
        const usuario = document.getElementById("usuario").value.trim();
        const correo = document.getElementById("correo").value.trim();
        const nombre = document.getElementById("nombre").value.trim();
        const apellido = document.getElementById("apellido").value.trim();
        const telefono = document.getElementById("telefono").value.trim();
        const direccion = document.getElementById("direccion").value.trim();
        const contrasena = document.getElementById("contrasena").value.trim();
        const rol = document.getElementById("rol").value;

        if (usuario && correo && nombre && apellido && telefono && direccion && contrasena && rol !== "Seleccione un rol") {
            if (modoEdicion && filaSeleccionada) {
                // Actualiza la fila existente
                const celdas = filaSeleccionada.children;
                celdas[1].textContent = usuario;
                celdas[2].textContent = apellido;
                celdas[3].textContent = nombre;
                celdas[4].textContent = correo;
                celdas[5].textContent = rol;
                celdas[6].textContent = telefono;
                celdas[7].textContent = direccion;
                celdas[8].textContent = "***************";

                alert("Usuario actualizado.");
            } else {
                // Crea nueva fila
                const fila = document.createElement("tr");
                fila.innerHTML = `
                    <td>${id}</td>
                    <td>${usuario}</td>
                    <td>${apellido}</td>
                    <td>${nombre}</td>
                    <td>${correo}</td>
                    <td>${rol}</td>
                    <td>${telefono}</td>
                    <td>${direccion}</td>
                    <td>***************</td>
                `;
                asignarEventosFila(fila);
                tabla.appendChild(fila);

                alert("Usuario registrado.");
            }

            // Limpiar formulario y cerrar modal
            document.querySelector("#modalRegistrarUsuario form").reset();
            modoEdicion = false;
            filaSeleccionada = null;

            bootstrap.Modal.getInstance(document.getElementById("modalRegistrarUsuario")).hide();
        } else {
            alert("Completa todos los campos.");
        }
    });

    // Botón Actualizar
    document.querySelector(".btn-outline-primary").addEventListener("click", () => {
        if (!filaSeleccionada) return alert("Selecciona un usuario para actualizar.");

        const celdas = filaSeleccionada.children;
        document.getElementById("usuario").value = celdas[1].textContent;
        document.getElementById("apellido").value = celdas[2].textContent;
        document.getElementById("nombre").value = celdas[3].textContent;
        document.getElementById("correo").value = celdas[4].textContent;
        document.getElementById("rol").value = celdas[5].textContent;
        document.getElementById("telefono").value = celdas[6].textContent;
        document.getElementById("direccion").value = celdas[7].textContent;
        document.getElementById("contrasena").value = "";

        modoEdicion = true;

        new bootstrap.Modal(document.getElementById("modalRegistrarUsuario")).show();
    });

    // Botón Eliminar
    document.querySelector(".btn-outline-danger").addEventListener("click", () => {
        if (!filaSeleccionada) return alert("Selecciona un usuario para eliminar.");
        filaSeleccionada.remove();
        filaSeleccionada = null;
        alert("Usuario eliminado.");
    });
});
