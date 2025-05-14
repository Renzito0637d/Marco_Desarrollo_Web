const container = document.getElementById('containerr');
const registerBtn = document.getElementById('register');
const loginBtn = document.getElementById('login');

registerBtn.addEventListener('click', () => {
    container.classList.add("active");
});

loginBtn.addEventListener('click', () => {
    container.classList.remove("active");
});



const botones = document.getElementsByClassName(`prueba`);

// Itera sobre todos los elementos con la clase `prueba`
for (let i = 0; i < botones.length; i++) {
    botones[i].addEventListener(`click`, () => {
        alert(`Funciona`);
    });
}


 // Función para cerrar la alerta
 function closeAlert() {
    document.querySelector('.dark-overlay').style.display = 'none';
}

// Mostrar la alerta al cargar la página 
window.onload = function() {
    document.querySelector('.dark-overlay').style.display = 'flex';
};