document.addEventListener("DOMContentLoaded", function () {
    const tabla = document.getElementById("tablaProductos");
    let filaSeleccionada = null;

    
    // Aplicar evento de selección a cada fila
    function aplicarEventosSeleccion() {
        const filas = tabla.querySelectorAll("tbody tr");
        filas.forEach(fila => {
            fila.addEventListener("click", () => {
                if (filaSeleccionada) {
                    filaSeleccionada.classList.remove("table-active");
                }
                filaSeleccionada = fila;
                fila.classList.add("table-active");
            });
        });
    }

    aplicarEventosSeleccion();

    // Funcionalidad de búsqueda por ID
    const botonConsultar = document.getElementById("btnConsultar");
    if (botonConsultar) {
        botonConsultar.addEventListener("click", () => {
            const idBuscado = prompt("Ingrese el ID del producto:");

            if (!idBuscado) {
                alert("ID no ingresado.");
                return;
            }

            const filas = tabla.querySelectorAll("tbody tr");
            let encontrado = false;

            filas.forEach(fila => {
                const celdas = fila.querySelectorAll("td");
                if (celdas.length > 0 && celdas[0].textContent.trim() === idBuscado.trim()) {
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
    }
});
// Función global: se ejecuta desde el botón HTML
function abrirModalEditar(button) {
    document.getElementById("edit-id").value = button.getAttribute("data-id");
    document.getElementById("edit-nombre").value = button.getAttribute("data-nombre");
    document.getElementById("edit-descripcion").value = button.getAttribute("data-descripcion");
    document.getElementById("edit-marca").value = button.getAttribute("data-marca");
    document.getElementById("edit-genero").value = button.getAttribute("data-genero");
    document.getElementById("edit-tipo").value = button.getAttribute("data-tipo");
    document.getElementById("edit-colores").value = button.getAttribute("data-colores");
    document.getElementById("edit-precio").value = button.getAttribute("data-precio");
    document.getElementById("edit-stock").value = button.getAttribute("data-stock");
}