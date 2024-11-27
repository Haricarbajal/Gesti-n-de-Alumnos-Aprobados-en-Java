package dam2adt1;

import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;
import java.nio.charset.StandardCharsets;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

public class AlumnoMain {
    public static void main(String[] args) {
    	
    	Scanner scan = new Scanner(System.in);
    	System.out.println("Escribe la ruta en la que se encuentra el archivo txt con los alumnos");
    	String ruta = scan.nextLine();
    	System.out.println("Ahora dime la ruta para crear el archivo de los alumnos aprobados: ");
    	String nuevaRuta = scan.nextLine();
        Path rutaArchivo = Paths.get(ruta);
        Path nuevoPath = Paths.get(nuevaRuta);
        //Arraylista donde se guardaran los a los alumnos con la antiguas notas
        ArrayList<Alumno> alumnos = new ArrayList<>();
        
        //ArrayLIst donde se gardaran a los alumnos con los nuevas notas
        ArrayList<Alumno> nuevosAlumnos = new ArrayList<>();
        
        
        
        try (FileChannel canal = FileChannel.open(rutaArchivo, StandardOpenOption.READ);
        		FileChannel destinoDatos = FileChannel.open(nuevoPath, StandardOpenOption.WRITE, StandardOpenOption.CREATE)) {
            // Crear buffer del tamaño del archivo para poder ahorrar recursos
            ByteBuffer buffer = ByteBuffer.allocate((int) canal.size());
            
            canal.read(buffer);
            
            buffer.flip();
            
            // Convertir bytes a String para poder manejarlo de una mejor manera
            String contenido = new String(buffer.array(), StandardCharsets.UTF_8);
            
            //Esta linea de comando nos ayuda a procesar cada linea
            String[] lineas = contenido.split("\\n");
            
            System.out.println("Contenido del archivo:");
            System.out.println("---------------------");
            int contador = 0;
            for (String linea : lineas) {
                // Estamos Eliminando los espacios en blanco y retornos de carro
                linea = linea.trim();
                if (!linea.isEmpty()) {
                	Alumno alumno = new Alumno();
                	alumnos.add(alumno);
                    String[] datos = linea.split(",");
                    String matricula = datos[0];
                    String nombre = datos[1];
                    double nota = Double.parseDouble(datos[2]);
                    alumno.setMatricula(matricula);
                    alumno.setNombre(nombre);
                    alumno.setNota(nota);
                    System.out.println("ID: " + datos[0].getClass());
                    System.out.println("Nombre: " + datos[1]);
                    System.out.println("Nota: " + datos[2]);
                    System.out.println("---------------------");
                }
            }
            
            System.out.println(Arrays.toString(alumnos.toArray()));
            for(int i = 0; i < alumnos.size(); i++) {
            	System.out.println(alumnos.get(i).getNota());
            	if(alumnos.get(i).getNota() > 5.0) {
            		String nuevaMatricula = alumnos.get(i).getMatricula();
            		String nuevoNombre = alumnos.get(i).getNombre();
            		double nuevaNota = alumnos.get(i).getNota();
            		Alumno nuevoAlumno = new Alumno();
            		nuevoAlumno.setMatricula(nuevaMatricula);
            		nuevoAlumno.setNombre(nuevoNombre);
            		nuevoAlumno.setNota(nuevaNota);
            		nuevosAlumnos.add(nuevoAlumno);
            	}
            }
            
            
            ByteBuffer alumnosAprobados = ByteBuffer.wrap(Arrays.toString(nuevosAlumnos.toArray()).getBytes());
            destinoDatos.write(alumnosAprobados);
        } catch (IOException e) {
            System.err.println("Error al leer el archivo: " + e.getMessage());
            e.printStackTrace();
        }
    }
}