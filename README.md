# Gestión de Alumnos Aprobados en Java

Este programa en Java lee un archivo de texto con información de alumnos, filtra a los alumnos aprobados y escribe sus datos en un nuevo archivo. Se utiliza `FileChannel` y `ByteBuffer` para la gestión eficiente de archivos, junto con estructuras de datos como `ArrayList` para almacenar y manipular los datos de los alumnos.

## Funcionalidades

1. **Lectura de un archivo de texto** con datos de alumnos (matrícula, nombre y nota).
2. **Almacenamiento de datos** en una lista de objetos `Alumno`.
3. **Filtrado de alumnos aprobados** (nota mayor a 5.0).
4. **Escritura de los alumnos aprobados** en un nuevo archivo de texto.

## Estructura del Archivo de Entrada

El archivo de texto debe contener los datos de los alumnos en formato CSV, con las siguientes columnas por línea:
