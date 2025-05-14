function filtrarPedidos() {
  const filtro = document.getElementById('filtroEstado').value;
  const filas = document.querySelectorAll('#tablaPedidos tr');

  filas.forEach(fila => {
    const estado = fila.getAttribute('data-estado');
    fila.style.display = (filtro === 'todos' || estado === filtro) ? '' : 'none';
  });
}

function cambiarEstado(boton, nuevoEstado) {
  const fila = boton.closest('tr');
  fila.setAttribute('data-estado', nuevoEstado);

  const badge = fila.querySelector('td:nth-child(6) span');

  if (nuevoEstado === 'en camino') {
    badge.textContent = 'En camino';
    badge.className = 'badge bg-info text-dark';
    boton.textContent = 'Marcar entregado';
    boton.className = 'btn btn-sm btn-success';
    boton.setAttribute('onclick', "cambiarEstado(this, 'entregado')");
  } else if (nuevoEstado === 'entregado') {
    badge.textContent = 'Entregado';
    badge.className = 'badge bg-success';
    boton.remove(); // No más acciones posibles
  }
}

function cerrarSesion() {
  alert('Sesión cerrada.');
  // Aquí podrías redirigir o limpiar la sesión real
}
