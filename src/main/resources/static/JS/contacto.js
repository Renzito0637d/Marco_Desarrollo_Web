document.addEventListener("DOMContentLoaded", function () {
    const form = document.getElementById("formContacto");
    const descripcionInput = document.getElementById("descripcion");
    const contador = document.getElementById("contador");

    // Inicializa el contador al cargar la página
    contador.textContent = `${descripcionInput.value.length} / 80 caracteres`;

    // Actualiza el contador mientras el usuario escribe
    descripcionInput.addEventListener("input", () => {
        contador.textContent = `${descripcionInput.value.length} / 80 caracteres`;
    });

    // Manejo del envío del formulario
    form.addEventListener("submit", function (e) {
        e.preventDefault();

        const nombre = document.getElementById("nombre").value.trim();
        const correo = document.getElementById("correo").value.trim();
        const descripcion = descripcionInput.value.trim();

        if (!nombre || !correo || !descripcion) {
            alert("Por favor, complete todos los campos.");
            return;
        }

        alert("Mensaje enviado. Gracias por contactarnos.");
        form.reset();
        contador.textContent = "0 / 80 caracteres"; // Reinicia el contador
    });
});



