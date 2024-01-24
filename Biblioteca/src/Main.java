import java.time.Clock;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;
import javax.swing.JOptionPane;
import javax.swing.SwingUtilities;

class Libro {
    public String titulo;
    public String autor;
    public String año;

    public Libro(String titulo, String autor, String año) {
        this.titulo = titulo;
        this.autor = autor;
        this.año = año;
    }

    public String getTitulo() {
        return titulo;
    }

    public String getAutor() {
        return autor;
    }

    public String getAño() {
        return año;
    }
}

class RegistroPrestamoLibro{

    public String libroPrestado;
    public LocalDateTime fechaHoraPrestamo;

    // constructor por defecto
    public RegistroPrestamoLibro(){}
    public RegistroPrestamoLibro(String libroPrestado, LocalDateTime fechaHoraPrestamo){

        this.libroPrestado = libroPrestado;
        this.fechaHoraPrestamo = fechaHoraPrestamo;
    }

    public String getLibroPrestado(){ return libroPrestado; }
    public LocalDateTime getFechaHoraPrestamo(){ return fechaHoraPrestamo; }
}
class RegistroReservaLibro{

    public String libroReservado;
    public LocalDateTime fechahoraReserva;

    public RegistroReservaLibro(String libroReservado, LocalDateTime fechahoraReserva){

        this.libroReservado = libroReservado;
        this.fechahoraReserva = fechahoraReserva;
    }

    public String getLibroReservado(){ return libroReservado; }
    public LocalDateTime getFechahoraReserva() { return fechahoraReserva; }
}

public class Main {
    // Atributos de la clase
    public static ArrayList<Libro> catalogo = new ArrayList<>();
    public static ArrayList<RegistroPrestamoLibro> prestados = new ArrayList<RegistroPrestamoLibro>();
    public static ArrayList<Libro> disponibles = new ArrayList<>();
    public static ArrayList<Libro> reservados = new ArrayList<>();

   // public static ArrayList<LocalDateTime> fechaHoraPrestamos = new ArrayList<LocalDateTime>();
   // public static ArrayList<LocalDateTime> fechaHoraReservas = new ArrayList<LocalDateTime>();
    public static boolean logueo = false;
    public static boolean estado = false;
    public static boolean estadoInicializarDatos = false;
    public final static String usuario = "admin";
    public final static String contraseña = "admin";
    public static String logueoUsuario;
    public static String logueoContrseña;
    public static StringBuilder mensaje;
    
    public static void main(String[] args) {
        do {
            try {
                logueo();
                inicializarDatos();

                int menu = Integer.parseInt(JOptionPane.showInputDialog(
                        "1. Buscar Libro\n"
                                + "2. Solicitar prestado un libro\n"
                                + "3. Reservar un libro\n"
                                + "4. Historial de prestamos\n"
                                + "5. Notificaciones\n"
                                + "6. Gestion de multas\n"
                                + "7. Configuraciones de usuario\n"
                                + "8. Busqueda avanzada\n"
                                + "9. Ayuda y soporte\n"
                                + "10. Seguridad\n"
                                + "11. Salir"));

                switch (menu) {
                    case 1:
                        buscarLibro();
                        break;

                    case 2:
                        prestamoLibro();
                        break;

                    case 3:

                        reservarLibro();
                        break;
                        
                    case 4:

                        historialPrestamos();
                        break;

                    case 11:
                        estado = true;
                        break;
                    default:
                        throw new IllegalArgumentException("Ingrese solo las opciones del menú");
                }
            } catch (InputMismatchException e) {
                JOptionPane.showMessageDialog(null, "ERROR! Ingrese un dato válido");
            } catch (Exception e) {
                JOptionPane.showMessageDialog(null, "ERROR EN LA EJECUCIÓN: " + e.getMessage());
            }
        } while (!estado);
    }

    private static void logueo() {
        while (!logueo) {
            JOptionPane.showMessageDialog(null,"Ingrese usuario y contraseña\n\n");
            logueoUsuario = JOptionPane.showInputDialog("Ingrese usuario\n");
            logueoContrseña = JOptionPane.showInputDialog("Ingrese contraseña\n");

            if (logueoUsuario.equals(usuario) && logueoContrseña.equals(contraseña)) {
                JOptionPane.showMessageDialog(null,"Bienvenid@!!!\n"
                        + "Usuario y contraseña correctos");
                logueo = true;
            } else {
                JOptionPane.showMessageDialog(null, ("Usuario y contraseña incorrectos"));
            }
        }
    }

    private static void buscarLibro() {
        String libroBuscar = JOptionPane.showInputDialog("Ingrese el libro a buscar");
        for (Libro libro : catalogo) {
            if (libro.getTitulo().equalsIgnoreCase(libroBuscar)) {
                if (disponibles.contains(libro)) {
                    JOptionPane.showMessageDialog(null, "Información detallada:\n" +
                            "Título: " + libro.getTitulo() + "\n" +
                            "Autor: " + libro.getAutor() + "\n" +
                            "Año: " + libro.getAño() + "\n" +
                            "Estado: Disponible");
                } else if (prestados.contains(libro)) {
                    JOptionPane.showMessageDialog(null, "Información detallada:\n" +
                            "Título: " + libro.getTitulo() + "\n" +
                            "Autor: " + libro.getAutor() + "\n" +
                            "Año: " + libro.getAño() + "\n" +
                            "Estado: Prestado");
                } else {
                    JOptionPane.showMessageDialog(null, "El libro '" + libroBuscar + "' está en nuestra biblioteca, pero su estado es desconocido.");
                }
                return; // Se encontró el libro, se puede salir del método
            }
        }
        JOptionPane.showMessageDialog(null, "El libro '" + libroBuscar + "' NO está en nuestra Biblioteca");
    }

    private static boolean estatusLibro(ArrayList<String> catalogo) {
        return catalogo.contains(catalogo);
    }

    public static void agregarLibro(String titulo, String autor, String año) {
        Libro nuevoLibro = new Libro(titulo, autor, año);
        catalogo.add(nuevoLibro);
    }



    // Dentro del método prestamoLibro
    private static void prestamoLibro() {

        String libroPrestamo = JOptionPane.showInputDialog("¿Qué libro desea tomar en préstamo?: ");
        LocalDateTime registroPrestamo = LocalDateTime.now();
        RegistroPrestamoLibro objectPrestamoLibro = new RegistroPrestamoLibro(libroPrestamo, registroPrestamo);

        for (Libro libro : catalogo) {
            if (libro.getTitulo().equalsIgnoreCase(libroPrestamo)) {
                if (disponibles.contains(libro)) {

                    prestados.add(objectPrestamoLibro);
                    disponibles.remove(libro);
                    verificarTiempoPrestamo(libro); // Agregamos el método de verificación de tiempo
                    JOptionPane.showMessageDialog(null,
                            "¡Ha tomado prestado el libro '" + objectPrestamoLibro.libroPrestado + "' a las " + objectPrestamoLibro.fechaHoraPrestamo);
                    return;
                } else if (prestados.contains(libro)) {
                    JOptionPane.showMessageDialog(null,
                            "No puede tomar prestado este libro porque está prestado");
                    return;
                }
            }
        }

        JOptionPane.showMessageDialog(null,
                "El libro '" + libroPrestamo + "' no está disponible en nuestra Biblioteca");
    }

    // Nuevo método para verificar el tiempo de préstamo
    private static void verificarTiempoPrestamo(Libro libro) {
        ScheduledExecutorService scheduler = Executors.newScheduledThreadPool(1);
        scheduler.schedule(() -> {
            SwingUtilities.invokeLater(() -> {
                JOptionPane.showMessageDialog(null,
                        "¡Debe devolver el libro '" + libro.getTitulo() + "' antes de las " + LocalDateTime.now());
                prestados.remove(libro);
                disponibles.add(libro);
            });
        }, 1, TimeUnit.MINUTES);
    }

    private static void reservarLibro() {

        String libroReservar = JOptionPane.showInputDialog("Ingrese el libro a reservar");
        LocalDateTime registroReserva = LocalDateTime.now(Clock.systemDefaultZone());
        RegistroReservaLibro objectReservaLibro = new RegistroReservaLibro(libroReservar,registroReserva);


        for (Libro libro : catalogo){

            if (libro.getTitulo().equalsIgnoreCase(libroReservar)) {

                if (disponibles.contains(libro)) {

                    JOptionPane.showMessageDialog(null, "El libro " + libroReservar + " esta disponible para tomar en prestamo");
                    prestamoLibro();
                    return;

                } else if (prestados.contains(libro)) {

                    JOptionPane.showMessageDialog(null, "El libro " + objectReservaLibro.libroReservado + " queda reservado: " + objectReservaLibro.fechahoraReserva);
                    reservados.add(libro);
                    prestados.remove(libro);
                    return;

                } else if (reservados.contains(libro)) {

                    JOptionPane.showMessageDialog(null,
                            "No puede reservar el libro " + libroReservar + " porque ya fue reservado el: " + objectReservaLibro.fechahoraReserva);
                    return;
                }

            }
        }
        JOptionPane.showMessageDialog(null,"El libro " + libroReservar + " no esta en nuestra Biblioteca");
    }

    public static void historialPrestamos() {

        StringBuilder mensaje = new StringBuilder("Historial de prestamos\n\n");

        int numeroPrestamo = 1;

        if (!prestados.isEmpty()) {
            for (RegistroPrestamoLibro prestamo : prestados) {
                mensaje.append("Prestamo ").append(numeroPrestamo).append(": ")
                        .append(prestamo.getLibroPrestado())
                        .append(" a las ")
                        .append(prestamo.getFechaHoraPrestamo())
                        .append("\n");
                numeroPrestamo++;
            }
        } else {
            mensaje.append("No hay prestamos registrados");
        }

        JOptionPane.showMessageDialog(null, mensaje);
    }
    public static void inicializarDatos() {
        while (!estadoInicializarDatos) {
            agregarLibro("Biblia", "Dios", "aproximadamente el siglo XIII a.C. hasta el primer siglo d.C");
            agregarLibro("Cien años de soledad", "Gabriel Garcia Marquez", "1967");
            agregarLibro("Don Quijote de la Mancha", "Miguel de Cervantes Saavedra", "Primera parte en 1605, segunda parte en 1615");
            agregarLibro("Harry Potter", "J.K. Rowling", "1997");
            agregarLibro("1984", "George Orwell", "1949");
            agregarLibro("Crimen y castigo", "Fyodor Dostoevsky", "1866");
            agregarLibro("Orgullo y prejuicio", "Jane Austen", "1813");
            agregarLibro("Ulises", "James Joyce", "1922");
            agregarLibro("El señor de los anillos (trilogía)", "J.R.R. Tolkien", "1954");
            agregarLibro("Matar a un ruiseñor", "Harper Lee", "1960");
            agregarLibro("En busca del tiempo perdido", "Marcel Proust", "1913-1927");
            agregarLibro("El Gran Gatsby", "F. Scott Fitzgerald", "1981");
            agregarLibro("cronica de una muerte anunciada", "Gabriel García Márquez", "1963");
            agregarLibro("Rayuela", "Julio Cortázar", "1963");

            // Catalogo disponibles
            disponibles.add(catalogo.get(0));
            disponibles.add(catalogo.get(1));
            disponibles.add(catalogo.get(2));
            disponibles.add(catalogo.get(3));
            disponibles.add(catalogo.get(4));
            disponibles.add(catalogo.get(5));
            disponibles.add(catalogo.get(6));
            disponibles.add(catalogo.get(7));
            disponibles.add(catalogo.get(8));
            disponibles.add(catalogo.get(9));
            disponibles.add(catalogo.get(10));
            disponibles.add(catalogo.get(11));
            disponibles.add(catalogo.get(12));
            disponibles.add(catalogo.get(13));

            estadoInicializarDatos = true;
        }
    }


}
