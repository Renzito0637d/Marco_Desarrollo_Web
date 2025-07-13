document.addEventListener("DOMContentLoaded", function () {
    const tablaAdmins = document.getElementById("tabla-admins");
    const tablaClientes = document.getElementById("tabla-clientes");
    let filaSeleccionada = null;
    let modoEdicion = false;
    tablaAdmins.innerHTML = "";
    tablaClientes.innerHTML = "";
    // Cargar usuarios desde backend al iniciar
    fetch("/api/usuarios")
        .then((res) => res.json())
        .then((data) => {
            data.forEach((usuario) => agregarFila(usuario));
        })
        .catch((error) => {
            console.error("Error al cargar usuarios:", error);
        });

    // Función para crear y agregar una fila
    function agregarFila(usuario) {
        const fila = document.createElement("tr");
        fila.innerHTML = `
            <td>${usuario.id}</td>
            <td>${usuario.usuario}</td>
            <td>${usuario.apellido}</td>
            <td>${usuario.nombre}</td>
            <td>${usuario.email}</td>
            <td>${usuario.rol}</td>
            <td>${usuario.telefono}</td>
            <td>${usuario.direccion ? usuario.direccion.calle : ""}</td>
            <td>***************</td>
        `;
        asignarEventosFila(fila);
        if (usuario.rol === "ADMIN") {
            tablaAdmins.appendChild(fila);
        } else {
            tablaClientes.appendChild(fila);
        }
    }

    // Selección de fila
    function asignarEventosFila(fila) {
        fila.addEventListener("click", () => {
            if (filaSeleccionada) filaSeleccionada.classList.remove("table-active");
            filaSeleccionada = fila;
            fila.classList.add("table-active");
        });
    }

    // Botón Consultar por ID
    document
        .querySelector(".btn-outline-secondary")
        .addEventListener("click", () => {
            const idBuscado = prompt("Ingrese el ID del usuario:");
            if (!idBuscado) return alert("ID no ingresado.");

            let encontrado = false;
            tabla.querySelectorAll("tr").forEach((fila) => {
                const celdas = fila.querySelectorAll("td");
                if (celdas.length > 0 && celdas[0].textContent === idBuscado) {
                    fila.scrollIntoView({ behavior: "smooth", block: "center" });
                    fila.classList.add("table-success");
                    setTimeout(() => fila.classList.remove("table-success"), 3000);
                    encontrado = true;
                }
            });

            if (!encontrado)
                alert(`No se encontró ningún usuario con ID ${idBuscado}.`);
        });

    // Botón Guardar(modal)
    document
        .querySelector("#modalRegistrarUsuario .btn-dark")
        .addEventListener("click", () => {
            const usuario = document.getElementById("usuario").value.trim();
            const correo = document.getElementById("correo").value.trim();
            const nombre = document.getElementById("nombre").value.trim();
            const apellido = document.getElementById("apellido").value.trim();
            const telefono = document.getElementById("telefono").value.trim();
            const direccion = document.getElementById("direccion").value.trim();
            const contrasena = document.getElementById("contrasena").value.trim();
            const rolSeleccionado = document.getElementById("rol").value.trim().toUpperCase();
            const rol = rolSeleccionado === "CLIENTE" ? "USER" : rolSeleccionado;

            if (
                usuario &&
                correo &&
                nombre &&
                apellido &&
                telefono &&
                direccion &&
                contrasena &&
                rol !== "Seleccione un rol" &&
                (!modoEdicion || contrasena)
            ) {
                const nuevoUsuario = {
                    usuario: usuario,
                    nombre: nombre,
                    apellido: apellido,
                    email: correo,
                    password: contrasena,
                    telefono: telefono,
                    rol: rol,
                    direccion: {
                        calle: direccion,
                        distrito: "Desconocido", // Puedes personalizar esto
                        provincia: "Desconocido",
                        departamento: "Desconocido",
                        referencia: "",
                    },
                };

                const id = filaSeleccionada ? filaSeleccionada.children[0].textContent : null;
                if (modoEdicion && id) {
                    nuevoUsuario.id = parseInt(id);
                }
                const esAdmin = rol.toLowerCase() === "admin";

                const endpoint = modoEdicion
                    ? `/api/usuarios/${id}` // Si está editando, usamos PUT
                    : esAdmin
                        ? "/registroAdmin"
                        : "/registrarCliente";

                const metodo = modoEdicion ? "PUT" : "POST";
                /*console.log("Enviando al backend:", JSON.stringify(nuevoUsuario, null, 2));
                console.log("Endpoint:", endpoint, "Método:", metodo);*/
                fetch(endpoint, {
                    method: metodo,
                    headers: {
                        "Content-Type": "application/json",
                    },
                    body: JSON.stringify(nuevoUsuario),
                })
                    .then((res) => {
                        if (!res.ok) throw new Error("Error al registrar o actualizar el usuario");
                        return metodo === "POST" ? res.json() : res.text();
                    })
                    .then((data) => {
                        alert(modoEdicion ? "Usuario actualizado correctamente." : "Usuario registrado correctamente.");

                        if (!modoEdicion) {
                            const fila = document.createElement("tr");
                            fila.innerHTML = `
            <td>--</td>
            <td>${nombre}</td>
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
                        } else {
                            // Actualiza la fila seleccionada
                            const celdas = filaSeleccionada.children;
                            celdas[1].textContent = nombre;
                            celdas[2].textContent = apellido;
                            celdas[3].textContent = nombre;
                            celdas[4].textContent = correo;
                            celdas[5].textContent = rol;
                            celdas[6].textContent = telefono;
                            celdas[7].textContent = direccion;
                        }

                        // Limpiar y cerrar
                        document.querySelector("#modalRegistrarUsuario form").reset();
                        modoEdicion = false;
                        filaSeleccionada = null;
                        bootstrap.Modal.getInstance(document.getElementById("modalRegistrarUsuario")).hide();
                    })
                    .catch((err) => {
                        console.error(err);
                        alert("Error al registrar o actualizar el usuario.");
                    });

            } else {
                alert("Completa todos los campos.");
            }
        });

    // Botón Actualizar
    document
        .querySelector(".btn-outline-primary")
        .addEventListener("click", () => {
            if (!filaSeleccionada)
                return alert("Selecciona un usuario para actualizar.");

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
            new bootstrap.Modal(
                document.getElementById("modalRegistrarUsuario")
            ).show();
        });

    // Botón Eliminar
    document
        .querySelector(".btn-outline-danger")
        .addEventListener("click", () => {
            if (!filaSeleccionada)
                return alert("Selecciona un usuario para eliminar.");

            const id = filaSeleccionada.children[0].textContent;

            if (confirm(`¿Seguro que deseas eliminar al usuario con ID ${id}?`)) {
                fetch(`/api/usuarios/${id}`, {
                    method: "DELETE",
                })
                    .then((response) => {
                        if (!response.ok)
                            throw new Error("Error al eliminar en el servidor.");
                        filaSeleccionada.remove();
                        filaSeleccionada = null;
                        alert("Usuario eliminado correctamente.");
                    })
                    .catch((err) => {
                        console.error("Error:", err);
                        alert("No se pudo eliminar el usuario.");
                    });
            }
        });
});
