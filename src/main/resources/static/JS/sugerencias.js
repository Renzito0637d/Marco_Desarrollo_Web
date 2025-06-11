document.addEventListener("DOMContentLoaded", function () {
    const form = document.getElementById("formSugerencia");
    const sugerenciaInput = document.getElementById("sugerencia");
    const contador = document.getElementById("contador");

    // Mostrar contador de caracteres
    if (sugerenciaInput && contador) {
        contador.textContent = `${sugerenciaInput.value.length} / 200 caracteres`;

        sugerenciaInput.addEventListener("input", () => {
            contador.textContent = `${sugerenciaInput.value.length} / 200 caracteres`;
        });
    }

    // Envío del formulario
    if (form) {
        form.addEventListener("submit", function (e) {
            e.preventDefault();

            const nombre = document.getElementById("nombre").value.trim();
            const correo = document.getElementById("correo").value.trim();
            const texto = sugerenciaInput.value.trim();

            if (!nombre || !correo || !texto) {
                alert("Por favor, complete todos los campos.");
                return;
            }

            alert("Gracias por tu sugerencia. ¡La valoramos mucho!");
            form.reset();
            contador.textContent = "0 / 200 caracteres";

            // Cierra el modal (si está abierto)
            const modalElement = document.getElementById("modalSugerencia");
            const modal = bootstrap.Modal.getInstance(modalElement);
            if (modal) modal.hide();
        });
    }

    // Abrir modal automáticamente si viene de otra página
    const params = new URLSearchParams(window.location.search);
    if (params.get("modal") === "abrir") {
        const modalElement = document.getElementById("modalSugerencia");
        if (modalElement) {
            const modal = new bootstrap.Modal(modalElement);
            modal.show();
        }
    }
});

