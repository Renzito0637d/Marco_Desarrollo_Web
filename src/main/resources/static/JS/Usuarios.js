// Espera a que cargue completamente el DOM
document.addEventListener('DOMContentLoaded', () => {
    // Filtro en tiempo real para administradores y usuarios
    const filtroAdmin = document.getElementById('filtroAdmin');
    const filtroUser = document.getElementById('filtroUser');

    if (filtroAdmin) {
        filtroAdmin.addEventListener('input', function () {
            filtrarTabla('tablaAdmins', this.value);
        });
    }

    if (filtroUser) {
        filtroUser.addEventListener('input', function () {
            filtrarTabla('tablaUsers', this.value);
        });
    }

    // Escucha del formulario de edición
    const formEditar = document.getElementById('formEditarUsuario');
    if (formEditar) {
        formEditar.addEventListener('submit', function (event) {
            event.preventDefault();

            const id = document.getElementById('editarId').value;
            const rol = document.getElementById('editarRol').value;

            const usuario = {
                id: id,
                nombre: document.getElementById('editarNombre').value,
                apellido: document.getElementById('editarApellido').value,
                email: document.getElementById('editarEmail').value,
                telefono: document.getElementById('editarTelefono').value,
                direccion: {
                    id: document.getElementById('editarDireccionId').value,
                    calle: document.getElementById('editarCalle').value,
                    distrito: document.getElementById('editarDistrito').value,
                    provincia: document.getElementById('editarProvincia').value,
                    departamento: document.getElementById('editarDepartamento').value
                }
            };

            fetch(rol === 'ADMIN' ? '/admin/actualizarAdmin' : '/admin/actualizarUser', {
                method: 'POST',
                headers: { 'Content-Type': 'application/json' },
                body: JSON.stringify(usuario)
            })
                .then(response => {
                    if (response.ok) {
                        alert('Usuario actualizado correctamente');
                        window.location.reload();
                    } else {
                        throw new Error('Error al actualizar');
                    }
                })
                .catch(error => {
                    console.error('Error:', error);
                    alert('Error al actualizar el usuario');
                });
        });
    }
});

// Función para filtrar tabla por texto
function filtrarTabla(idTabla, filtro) {
    const texto = filtro.toLowerCase();
    const filas = document.getElementById(idTabla).getElementsByTagName('tr');

    for (let fila of filas) {
        const contenido = fila.textContent.toLowerCase();
        fila.style.display = contenido.includes(texto) ? '' : 'none';
    }
}

// Función para eliminar un usuario
function eliminarUsuario(id) {
    if (confirm('¿Estás seguro de eliminar este usuario?')) {
        fetch(`/admin/usuarios/eliminar/${id}`, {
            method: 'DELETE'
        })
        .then(res => {
            if (res.ok) location.reload();
            else alert('Error al eliminar');
        });
    }
}

// Función para cargar datos en el formulario de edición
function editarUsuario(id, rol) {
    fetch(`/admin/usuario/${id}`)
        .then(response => response.json())
        .then(data => {
            // Llenar formulario con los datos del usuario
            document.getElementById('editarId').value = data.id;
            document.getElementById('editarRol').value = rol;
            document.getElementById('editarDireccionId').value = data.direccion?.id || '';
            document.getElementById('editarNombre').value = data.nombre;
            document.getElementById('editarApellido').value = data.apellido;
            document.getElementById('editarEmail').value = data.email;
            document.getElementById('editarTelefono').value = data.telefono;
            document.getElementById('editarCalle').value = data.direccion?.calle || '';
            document.getElementById('editarDistrito').value = data.direccion?.distrito || '';
            document.getElementById('editarProvincia').value = data.direccion?.provincia || '';
            document.getElementById('editarDepartamento').value = data.direccion?.departamento || '';

            // Mostrar el modal de edición
            const modal = new bootstrap.Modal(document.getElementById('modalEditarUsuario'));
            modal.show();
        })
        .catch(error => {
            console.error('Error al obtener usuario:', error);
            alert('No se pudo cargar el usuario');
        });
}

// Función para abrir el modal de registro según el tipo de rol
function abrirFormulario(tipo) {
    const modalId = tipo === 'ADMIN' ? 'modalRegistrarAdmin' : 'modalRegistrarUser';
    const modalElement = document.getElementById(modalId);

    if (!modalElement) {
        console.error('No se encontró el modal con ID:', modalId);
        return;
    }

    const modal = new bootstrap.Modal(modalElement);

    // Preseleccionar rol en el formulario (si existe el select)
    const rolSelect = modalElement.querySelector('[name="rol"]');
    if (rolSelect) {
        rolSelect.value = tipo;
    }

    modal.show();
}
