
# Sistema de Getión de Barrio Privado — Grupo OttoCode

Trabajo Práctico de Programación Orientada a Objetos (UADE) — Etapa 2.

## Descripción del sistema

Aplicación de consola en Java para administrar un barrio privado. Permite gestionar:

* **Lotes y residentes:** consulta de los lotes y de las personas que viven en cada uno.
* **Expensas y pagos:** registro de expensas por lote y de pagos parciales o totales, con cálculo automático del interés por mora (3 % mensual sobre el saldo, cobrado antes que el capital).
* **Visitas y accesos:** autorización de visitas y proveedores por un período, y registro de ingreso y salida por guardia.
* **Reclamos:** alta de reclamos con prioridad automática según el tipo, asignación a empleados (máximo 5 en curso por empleado), cambios de estado con historial, reapertura dentro de los 5 días y cierre automático posterior.
* **Mantenimiento:** registro de fallas de cámaras y luminarias, y generación y cierre de órdenes de mantenimiento con prioridad automática.
* **Reportes:** expensas con saldo pendiente por lote, reclamos pendientes y equipos fuera de servicio.

Los datos se guardan en archivos `.dat` (serialización de objetos) en la carpeta desde donde se ejecuta el programa, y se conservan entre ejecuciones.

## Integrantes

* Uma Gotfryd
* Ignacio Derkacz
* Facundo Miron





Ejecución

El programa necesita datos iniciales (lotes, residentes, empleados y equipos), porque el menú no permite darlos de alta. Por eso, **la primera vez hay que ejecutar `CrearDatosPrueba` antes que `Main`**.


### Uso del menú

El menú principal tiene 6 módulos y la opción `0` para salir y guardar. Cada módulo tiene su propio submenú; con `0` se vuelve al menú principal. Si se ingresa un dato inválido, el sistema muestra el error y permanece en el módulo.

## Datos de prueba

`CrearDatosPrueba` genera los siguientes datos:

|Tipo|ID|Datos|
|-|-|-|
|Lote|101|Superficie 450, titular 1001|
|Residente|1001|Juan Pérez, DNI 30111222, lote 101|
|Empleado|1|Carlos Mantenimiento, legajo 12345, personal de mantenimiento|
|Cámara|501|Entrada Principal, 1080p, 15 días de grabación|
|Luminaria|502|Calle Los Patos, LED, 100 W|

### Recorrido de ejemplo



1. **Registrar una expensa:** módulo `2` → opción `2` → `1` → `101` → `50000` → `2026-09-10` → `2026-09`.
Resultado: `Expensa registrada: 1`.
2. **Registrar un pago con mora:** opción `3` → `1` → `1` → `20000` → `2026-11-07` → `transferencia`.
Resultado: se cobran $2.900 de interés por 58 días de mora; al listar (opción `1`) la expensa queda con saldo $32.900, estado PARCIAL.
3. **Autorizar una visita:** módulo `3` → opción `2` → `1` → `1001` → `Pepe` → `30123456` → `2026-11-01T00:00` → `2026-12-31T23:59` → `false`.
Luego registrar el ingreso con opción `3` → `1` → `1` → `1`, y la salida con opción `4` → `1`.
(La fecha y hora actual debe estar dentro del período autorizado.)
4. **Registrar y asignar un reclamo:** módulo `4` → opción `2` → `1` → `1001` → `seguridad` → `Porton roto`.
Asignarlo con opción `4` → `1` → `1`, y avanzar su estado con opción `3` → `1` → `en\_curso` → `Arrancando`.
5. **Registrar una falla y su orden:** módulo `5` → opción `2` → `502` → `1` → `No prende`.
Generar la orden con opción `3` → `1` → `1` → `Cambiar lampara` (prioridad MEDIA).
Cerrarla con opción `4` → `1` → `Lampara cambiada`.
6. **Ver los reportes:** módulo `6`.

### Reiniciar los datos

Para volver al estado inicial, borrar todos los archivos `.dat` de la carpeta del proyecto y ejecutar nuevamente `CrearDatosPrueba`.

Si se modifica alguna clase que se guarda en los archivos, los `.dat` anteriores pueden dejar de leerse (el sistema informa que no los encontró). En ese caso, también hay que borrarlos y regenerarlos con `CrearDatosPrueba`.


