## Taller 4 - Aplicación Android con Fragmentos y Widget Interactivo

### **Descripción General**
Taller 4 es una aplicación Android desarrollada en **Kotlin**, que tiene como objetivo proporcionar una experiencia de usuario rica mediante el uso de componentes modernos, como **fragmentos** reutilizables, **widgets de aplicación** interactivos y **sensores** del dispositivo. La aplicación está organizada de manera modular, fácil de mantener y expandir.

### **Clases Principales**

#### **1. Pantalla de Carga (`PantallaCarga`)**
Muestra una pantalla inicial durante unos segundos mientras la aplicación se carga. Esto proporciona una mejor experiencia de usuario al iniciar la aplicación.

**Atributos**:
- `progressIndicator`: Un `ImageView` animado que muestra un indicador de progreso mientras se carga la aplicación.

**Uso**: Se encarga de presentar una pantalla amigable al usuario mientras la aplicación se inicia en segundo plano.

#### **2. Actividad Principal (`ActividadPrincipal`)**
La pantalla principal de la aplicación, que contiene los fragmentos para mostrar una lista de elementos (`ListFragment`) y los detalles de un elemento seleccionado (`DetailFragment`). Además, se utiliza el acelerómetro del dispositivo para detectar el movimiento y cambiar el color de fondo.

**Atributos**:
- `fragment_list_container`: Contenedor para mostrar la lista de elementos.
- `fragment_detail_container`: Contenedor para mostrar los detalles del elemento seleccionado.
- `sensorManager` y `accelerometer`: Para manejar el sensor de acelerómetro y permitir cambios de fondo según el movimiento detectado.

**Uso**: Permite al usuario gestionar una lista de elementos y mostrar sus detalles, mientras que el acelerómetro añade una capa de interactividad.

#### **3. Widget de Aplicación (`MyAppWidgetProvider`)**
El widget interactivo se muestra en la pantalla de inicio del dispositivo y permite al usuario ver los datos más recientes de la aplicación. También cuenta con un botón para actualizar la información.

**Atributos**:
- `widgetSummaryText`: Muestra un resumen de datos.
- `widgetUpdateButton`: Permite actualizar los datos mostrados en el widget.

**Uso**: Ofrece al usuario acceso rápido a la información clave de la aplicación directamente desde la pantalla de inicio.

#### **4. Fragmentos (`ListFragment` y `DetailFragment`)**
- **`ListFragment`**: Muestra una lista de elementos que el usuario puede añadir y actualizar. Usa `RecyclerView` y `MyAdapter` para gestionar la lista.
- **`DetailFragment`**: Muestra el detalle del elemento seleccionado, incluyendo un ID generado aleatoriamente.

**Uso**: Permite modular la interfaz, facilitando la reutilización y la gestión de la vista en pantallas grandes.

### **Estructura del Proyecto**

#### **1. Interfaz de Usuario (`ui`)**
##### **1.1. Main (`main`)**
- **`ActividadPrincipal.kt`**: Maneja la actividad principal que contiene los fragmentos.
- **`ListFragment.kt`** y **`DetailFragment.kt`**: Gestionan la lista de elementos y los detalles del elemento seleccionado.
- **`MyAdapter.kt`**: Adaptador para el `RecyclerView` en `ListFragment`.

##### **1.2. Widget (`widget`)**
- **`MyAppWidgetProvider.kt`**: Implementa el comportamiento del widget.

##### **1.3. Pantalla de Inicio (`PantallaInicio`)**
- **`PantallaCarga.kt`**: Maneja la pantalla de carga inicial.
- **`PantallaInicio.kt`**: Pantalla auxiliar para la navegación inicial.

#### **2. Lógica Compartida (`SharedViewModel.kt`)**
- **`SharedViewModel.kt`**: ViewModel que gestiona la lógica compartida entre `ListFragment` y `DetailFragment`, utilizando `LiveData` para compartir datos de manera reactiva.

### **Características Clave**

- **Widget de Aplicación Interactivo**: El widget muestra información clave de la aplicación y permite actualizaciones rápidas desde la pantalla de inicio.
- **Uso del Acelerómetro**: La actividad principal cambia su color de fondo cuando se detecta un movimiento brusco del dispositivo, mejorando la interactividad.
- **Validaciones y Manejo de Errores**: Se implementa validación de entradas y manejo de errores para garantizar una experiencia de usuario estable y confiable.
- **Diseño Modular**: Cada clase tiene una responsabilidad clara, asegurando un código flexible y reutilizable.

### **Conclusión**
Taller 4 es una aplicación modular que hace uso de las mejores prácticas de desarrollo Android, incluyendo una estructura modular y componentes modernos como widgets y sensores. Esta estructura permite una gestión clara de los datos, una interfaz de usuario intuitiva y una experiencia rica e interactiva.

### **Contacto**
Patrik Paul Sirbu - [https://github.com/paatriksirbu](https://github.com/paatriksirbu)

Mail: patrikpsirbu@gmail.com

Enlace del proyecto: [https://github.com/paatriksirbu/Taller4.git](https://github.com/paatriksirbu/Taller4.git)

