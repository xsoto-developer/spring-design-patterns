# Spring Design Patterns Store Backend

Backend de "react-desing-patterns", una solución basada en una arquitectura de microservicios construida con Spring Boot. Este repositorio incluye el microservicio `msvc-patterns-sales`, encargado de gestionar el inventario de patitos de hule y procesar órdenes de envío. Utiliza una base de datos H2 en memoria, Hibernate como ORM, y Swagger para documentación de la API. Está preparado para despliegue con Docker y aplica patrones de diseño como Factory y Strategy para resolver requerimientos complejos.

## Arquitectura
El backend está diseñado como un sistema de microservicios:
- **`msvc-patterns-sales`**: Microservicio principal de este repositorio, enfocado en el módulo de almacén (CRUD de patitos) y el módulo de tienda (procesamiento de órdenes).
- Otros microservicios (futuros o en otros repositorios) podrían integrarse para funcionalidades adicionales como autenticación, pagos, etc.

## Características
- **Módulo Almacén** (en `msvc-patterns-sales`):
    - Operaciones CRUD para patitos: crear, listar, actualizar, y borrado lógico.
    - Suma cantidades si se intenta agregar un patito existente con el mismo color, tamaño y precio.
- **Módulo Tienda** (en `msvc-patterns-sales`):
    - Endpoint REST para procesar órdenes, calculando tipo de paquete, protección y costo total según reglas específicas.
- **Borrado Lógico**: Los patitos no se eliminan físicamente; se marcan como `borrado = true`.
- **Documentación**: API documentada con Swagger, accesible en `/swagger-ui.html`.

## Tecnologías Utilizadas
- **Spring Boot 3.x**: Framework base para construir microservicios RESTful.
- **Hibernate**: ORM para mapear entidades a la base de datos H2.
- **H2 Database**: Base de datos en memoria para desarrollo y pruebas.
- **Swagger (Springdoc OpenAPI)**: Documentación automática de endpoints.
- **Docker**: Contenedorización para despliegue portátil.
- **Maven**: Gestión de dependencias y construcción.
- **Java 17**: Lenguaje principal con soporte LTS.

## Patrones de Diseño Implementados
- **Factory Pattern**: Utilizado para determinar el tipo de paquete (madera, cartón, plástico) según el tamaño del patito en el módulo de tienda.
- **Strategy Pattern**: Implementado para calcular el costo total aplicando reglas específicas basadas en país, modo de envío y tipo de paquete.
- **Buenas Prácticas**:
    - Validaciones con Bean Validation (`@NotNull`) para garantizar datos consistentes.
    - Inyección de dependencias con Spring IoC para desacoplar componentes.
    - Separación de preocupaciones entre controladores, servicios y repositorios.
    - Manejo centralizado de excepciones.

## Requisitos
- Java 17+
- Maven 3.8+
- Docker (opcional, para contenedorización)

## Instalación
1. Clona el repositorio:
   ```bash
   git clone https://github.com/xsoto-developer/spring-design-patterns.git
   cd spring-design-patterns