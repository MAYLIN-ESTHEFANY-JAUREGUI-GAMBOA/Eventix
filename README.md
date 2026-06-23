# Eventix - Sistema de Gestión de Eventos

Sistema Desktop para la gestión de eventos desarrollado con Java Swing, MySQL y el patrón MVC.

## Requisitos Previos

- Java 17 o superior
- MySQL 8.0 o superior
- Maven 3.6 o superior
- IDE (IntelliJ IDEA, Eclipse, NetBeans)

## Configuración de la Base de Datos

1. Ejecutar el script SQL ubicado en `database/database.sql`
2. El script crea la base de datos `eventix_db` con las tablas:
   - `usuarios` (id, usuario, contraseña)
   - `eventos` (id, nombreEvento, tipoEvento, modalidad, servicios, fechaEvento, costo, estado)
3. Usuarios de prueba:
   - Usuario: `admin` / Contraseña: `admin123`
   - Usuario: `usuario` / Contraseña: `usuario123`

## Configuración de la Conexión a MySQL

Editar el archivo `src/main/java/pe/edu/vallegrande/eventix/util/MySQLConnection.java` y modificar las credenciales según tu configuración:

```java
private static final String URL = "jdbc:mysql://localhost:3306/eventix_db";
private static final String USER = "root";
private static final String PASSWORD = "tu_contraseña";
```

## Instalación y Ejecución

### Compilar el proyecto

```bash
mvn clean compile
```

### Ejecutar la aplicación

```bash
mvn exec:java -Dexec.mainClass="pe.edu.vallegrande.eventix.EventixApplication"
```

O desde tu IDE, ejecutar la clase `EventixApplication.java`

## Estructura del Proyecto (MVC)

```
src/main/java/pe/edu/vallegrande/eventix/
├── EventixApplication.java       # Clase principal (Main)
├── model/                        # Model (Modelos de datos)
│   ├── Usuario.java
│   └── Evento.java
├── view/                         # View (Interfaces gráficas)
│   ├── LoginView.java
│   ├── DashboardView.java
│   └── EventoView.java
├── controller/                   # Controller (Lógica de negocio)
│   ├── LoginController.java
│   ├── DashboardController.java
│   └── EventoController.java
├── dao/                          # DAO (Acceso a datos)
│   ├── UsuarioDAO.java
│   └── EventoDAO.java
└── util/                         # Utilidades
    ├── MySQLConnection.java
    └── ReporteUtil.java
```

## Funcionalidades

### 1. Login
- Autenticación de usuarios conectada a MySQL
- Validación de usuario y contraseña
- Mensajes de acceso correcto e incorrecto

### 2. Dashboard
- Menú principal con acceso a:
  - Gestión de Eventos
  - Reportes
  - Cerrar Sesión

### 3. CRUD de Eventos
- **Registrar**: Crear nuevos eventos con todos sus datos
- **Modificar**: Actualizar eventos existentes
- **Buscar**: Buscar eventos por nombre
- **Listar**: Ver todos los eventos en una tabla
- **Eliminar**: Eliminación lógica de eventos (cambia estado a INACTIVO)

### 4. Componentes Swing Utilizados
- **JTextField**: Nombre del evento, Costo
- **JComboBox**: Tipo de Evento (Taller, Seminario, Conferencia, Webinar)
- **JRadioButton**: Modalidad (Presencial, Virtual)
- **JCheckBox**: Servicios (Certificado, Material Digital, Coffee Break)
- **JTable**: Listado de eventos registrados
- **JButton**: Operaciones CRUD y reportes
- **JSpinner**: Selección de fecha del evento

### 5. Reportes
- Generación de reportes con JasperReports
- Visualización de reportes en el visor de JasperReports
- Exportación a formato PDF

## Datos de Prueba

El script SQL incluye 4 eventos de prueba:
1. Taller de Java - Presencial - S/ 150.00
2. Seminario de Arquitectura - Virtual - S/ 200.00
3. Conferencia de Tecnología - Presencial - S/ 100.00
4. Webinar de Spring Boot - Virtual - S/ 50.00

## Tecnologías Utilizadas

- **Java 17**: Lenguaje de programación
- **Java Swing**: Framework para interfaces gráficas de escritorio
- **MySQL**: Sistema de gestión de bases de datos
- **JasperReports**: Generación de reportes
- **Maven**: Gestión de dependencias y construcción
- **MVC**: Patrón de diseño Model-View-Controller

## Archivos del Proyecto

- `pom.xml`: Configuración de Maven y dependencias
- `src/main/resources/database.sql`: Script de base de datos
- `src/main/resources/reporte_eventos.jrxml`: Plantilla de reporte JasperReports

## Notas Importantes

- Asegúrese de que el servicio de MySQL esté ejecutándose antes de iniciar la aplicación
- Verifique que las credenciales en `MySQLConnection.java` sean correctas
- Los reportes se generan en el directorio raíz del proyecto
- La eliminación de eventos es lógica (cambia estado a INACTIVO)

## Autor

Proyecto desarrollado para el curso de Programación Orientada a Objetos.
