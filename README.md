# Aplicación web para una tienda de zapatillas de diferentes marcas
![Spring](https://img.shields.io/badge/spring-%236DB33F.svg?style=for-the-badge&logo=spring&logoColor=white)
![JWT](https://img.shields.io/badge/JWT-black?style=for-the-badge&logo=JSON%20web%20tokens)
![MySQL](https://img.shields.io/badge/mysql-4479A1.svg?style=for-the-badge&logo=mysql&logoColor=white)
![Bootstrap](https://img.shields.io/badge/bootstrap-%238511FA.svg?style=for-the-badge&logo=bootstrap&logoColor=white)
--
<small>Proyecto del curso marco desarrollo web</small>

# 🛍️ Tienda de Zapatillas – Spring Boot + Thymeleaf
Aplicación web desarrollada con **Spring Boot** y **Thymeleaf** para la gestión y venta de zapatillas de diversas marcas. Incluye funcionalidades para usuarios y administradores, con un diseño **responsive** y seguro. Utiliza **MySQL** como base de datos y **Spring Security** para la autenticación y autorización.  


## 🛠️ Características del proyecto
- **Catálogo de productos** con filtrado y búsqueda.  
- **Carrito de compras** persistente por usuario.  
- **Registro e inicio de sesión** con autenticación segura (JWT + Spring Security).  
- **Roles y permisos** diferenciados:
  - **Usuario:** navegación, compras, gestión de carrito.
  - **Administrador:** CRUD de productos, gestión de inventario y pedidos.
- **Diseño responsive** para móviles, tablets y escritorio.
- **Validación de formularios** en el lado del servidor.
- **Protección de rutas** según el rol.
- Integración con **MySQL** usando **Spring Data JPA**.
- Plantillas dinámicas con **Thymeleaf**.


## 📂 Tecnologías utilizadas

**Backend:**
- Java 17+
- Spring Boot (Web, Data JPA, Security, Validation)
- JWT (Json Web Token) para autenticación
- Lombok para reducir código boilerplate

**Frontend:**
- Thymeleaf para renderizado del lado del servidor
- HTML5, CSS3 y Bootstrap

**Base de datos:**
- MySQL

## :gear: Requisitos del sistema

Para poder ejecutar el proyecto, se necesitan los siguientes requisitos:
- :wrench: Proyecto:
    - Java JDK 24 o modificar en [pom.xml](https://github.com/Renzito0637d/Marco_Desarrollo_Web/blob/main/pom.xml#L30 "Aqui podras ver en donde modificar la version de tu JAVA") segun tus necesidades
    - Maven 3.8+
    - Base de datos MySQL 8+
    - Spring Boot 3.x
    - Postman (opcional, para pruebas de API)
    - IDE recomendadas:
        - IntelliJ IDEA
        - Spring Tools Suite
- :computer: Entorno de Desarrollo:
    - Sistema operativo:
        - Windows 10/11
        - Linux
        - macOS
    - Navegadores web modernos:
        - Chrome
        - Firefox
        - Edge


> [!NOTE] 
> ## :inbox_tray: Instalación
>Para ejecutar el proyecto localmente, sigue los siguientes pasos:
>1. Clona este repositorio:
>     ~~~bash
>     git clone https://github.com/Renzito0637d/Proyect_Integrator_1.git
>     ~~~
>2. Abre el proyecto en tu entorno de desarrollo.
> 
>      ![Visual Studio Code](https://img.shields.io/badge/Visual%20Studio%20Code-0078d7.svg?style=for-the-badge&logo=visual-studio-code&logoColor=white)
> ![IntelliJ IDEA](https://img.shields.io/badge/IntelliJIDEA-000000.svg?style=for-the-badge&logo=intellij-idea&logoColor=white)
> 
>   3. Compila y ejecuta el proyecto.
>   
>       ~~~bash
>       cd Marco_Desarrollo_Web
>       mvn clean install
>       mvn spring-boot:run         
>       ~~~
>
>       **Base de datos**:
>
>       Ejecutar este script en MySQL Workbench, **en caso de no haber conexión  con la base de datos configurar [app properties](https://github.com/Renzito0637d/Marco_Desarrollo_Web/blob/main/src/main/resources/application.properties "En este archivo del proyecto podrás configurar la conexión de la base de datos MySQL") debes de configurar la _url_, _username_ y _password_ de la DB**.
>       ~~~bash
>       create DATABASE ucvincidencia;
>       use ucvincidencia;         
>       ~~~
> 4. Acceder desde el navegador
>    
>     - **Usuario:** `http://localhost:8080`  
>     - **Administrador:** `http://localhost:8080/admin`

## 👥 Roles del sistema

- **Usuario:** Ver catálogo, agregar al carrito, realizar pedidos.  
- **Administrador:** Crear, editar, eliminar productos, gestionar usuarios y pedidos.  


## 📱 Vista responsive

La aplicación está optimizada para funcionar correctamente en **dispositivos móviles, tablets y computadoras**.



