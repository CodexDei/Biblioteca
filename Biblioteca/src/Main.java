import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.List;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;
import javax.swing.JOptionPane;
import javax.swing.SwingUtilities;

class Libro {
    public String titulo;
    public String autor;
    public String año;
    public LocalDateTime fechaHoraPrestamo;
    public LocalDateTime fechahoraReserva;


    public Libro() {
    }

    public Libro(String titulo, String autor, String año) {
        this.titulo = titulo;
        this.autor = autor;
        this.año = año;
    }

//    public Libro(String libroPrestado, LocalDateTime fechaHoraPrestamo) {
//
//        this.libroPrestado = libroPrestado;
//        this.fechaHoraPrestamo = fechaHoraPrestamo;
//    }
//
//    public Libro(String libroReservado, LocalDateTime fechahoraReserva) {
//
//        this.libroReservado = libroReservado;
//        this.fechahoraReserva = fechahoraReserva;
//    }


    public String getTitulo() {
        return titulo;
    }

    public String getAutor() {
        return autor;
    }

    public String getAño() {
        return año;
    }
    public LocalDateTime getFechaHoraPrestamo() {
        return fechaHoraPrestamo = LocalDateTime.now();
    }
    public LocalDateTime getFechahoraReserva() {
        return fechahoraReserva = LocalDateTime.now();
    }

    @Override
    public String toString() {
        return
                "titulo='" + titulo + '\'' +
                        ", autor='" + autor + '\'' +
                        ", año='" + año + '\''+
                        "\n"
                ;
    }
}
public class Main {
    // Atributos de la clase
    public static ArrayList<Libro> catalogo = new ArrayList<>();
    public static ArrayList<Libro> prestados = new ArrayList<Libro>();
    public static ArrayList<Libro> disponibles = new ArrayList<>();
    public static ArrayList<Libro> reservados = new ArrayList<>();
    public static ArrayList<String> historialPrestamosArraylist = new ArrayList<String>();
    public static ArrayList<String> historialReservasArraylist = new ArrayList<String>();
    public static ArrayList<String> multasHistorialArraylist = new ArrayList<String>();
    public static boolean logueo = false;
    public static boolean estado = false;
    public static boolean estadoInicializarDatos = false;
    public final static String usuario = "admin";
    public final static String contraseña = "admin";
    public static String logueoUsuario;
    public static String logueoContrseña;
    public static StringBuilder mensaje;
    public static int totalMultas;

    public static void main(String[] args) {
        do {
            try {
                logueo();
                inicializarDatos();

                int menu = Integer.parseInt(JOptionPane.showInputDialog(
                        "1. Buscar Libro\n"
                                + "2. Solicitar prestado un libro\n"
                                + "3. Reservar un libro\n"
                                + "4. Libros prestados\n"
                                + "5. Libros Reservados\n"
                                + "6. Historial Prestamos\n"
                                + "7. Historial Reservas\n"
                                + "8. Gestion de multas\n"
                                + "9. Lista de libros\n"
                                + "10. Pagar Multas\n"
                                + "11. Busqueda avanzada\n"
                                + "12. Ayuda y soporte\n"
                                + "13. Seguridad\n"
                                + "14. Salir"));

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

                        librosPrestados();
                        break;

                    case 5:

                        librosReservados();
                        break;

                    case 6:

                        historialPrestamos();
                        break;

                    case 7:

                        historialReservas();
                        break;

                    case 8:

                        mostrarHistorialMultas();
                        break;

                    case 9:

                        listaLibros();
                        break;

                    case 10:

                        pagarMultas();
                        break;

                    case 14:
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
        Libro libro = new Libro();

        for (Libro librox : catalogo) {
            if (librox.getTitulo().equalsIgnoreCase(libroBuscar)) {
                libro = librox;
            }
        }

        if (disponibles.contains(libro)) {
            JOptionPane.showMessageDialog(null, "Información detallada:\n" +
                    "Título: " + libro.getTitulo() + "\n" +
                    "Autor: " + libro.getAutor() + "\n" +
                    "Año: " + libro.getAño() + "\n" +
                    "Estado: Disponible");
            return;

        } else if (prestados.contains(libro)) {
            JOptionPane.showMessageDialog(null, "Información detallada:\n" +
                    "Título: " + libro.getTitulo() + "\n" +
                    "Autor: " + libro.getAutor() + "\n" +
                    "Año: " + libro.getAño() + "\n" +
                    "Estado: Prestado");
            return;

        } else if (reservados.contains(libro)) {

            JOptionPane.showMessageDialog(null, "Información detallada:\n" +
                    "Título: " + libro.getTitulo() + "\n" +
                    "Autor: " + libro.getAutor() + "\n" +
                    "Año: " + libro.getAño() + "\n" +
                    "Estado: Reservado");
            return;

        } else {
            JOptionPane.showMessageDialog(null, "El libro '" + libroBuscar +
                    "' NO está en nuestra Biblioteca");
        }
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
        Libro libro = new Libro();
        mensaje = new StringBuilder("");

        for (Libro librox : catalogo) {
            if (librox.getTitulo().equalsIgnoreCase(libroPrestamo)) {
                libro = librox;
            }
        }

        if (disponibles.contains(libro)) {

            prestados.add(libro);
            historialPrestamosArraylist.add(String.valueOf(registroHistorial(libro.getTitulo(),"PRESTO",libro.getFechaHoraPrestamo())));
            disponibles.remove(libro);
            verificarTiempoPrestamo(libro); // Agregamos el método de verificación de tiempo
            JOptionPane.showMessageDialog(null,
                    "¡Ha tomado prestado el libro '" + libro.getTitulo() + "' a las "
                            + libro.getFechaHoraPrestamo());
            return;

        } else if (prestados.contains(libro)) {

            int respuestaRserva = JOptionPane.showConfirmDialog(null,
                    "No puede tomar prestado este libro porque está prestado\n" +
                            "¿Desea hacer la reserva del libro para cuando sea devuelto lo pueda tomar prestado?",
                    "Confirmacion Prestamo", JOptionPane.YES_NO_OPTION);

            if(respuestaRserva == JOptionPane.YES_OPTION){

                reservarLibro();
                return;

            }else {
                JOptionPane.showMessageDialog(null,"Gracias por confirmar");
                return;
            }

        } else if (reservados.contains(libro)) {

            JOptionPane.showMessageDialog(null,
                    "Este Libro esta prestado y ya ha sido reservado por otro usuario, " +
                            "usted a ingresado ahora a la lista de reservas\n" + "El libro " + libro.getTitulo()
                            + " queda reservado: " + libro.getFechahoraReserva());
            reservados.add(libro);
            return;
        }

        JOptionPane.showMessageDialog(null,
                "El libro '" + libroPrestamo + "' no está disponible en nuestra Biblioteca");

    }

    // Nuevo método para verificar el tiempo de préstamo
    private static void verificarTiempoPrestamo(Libro libro) {

        LocalDateTime tiempoPrestamo = LocalDateTime.now();

        ScheduledExecutorService scheduler = Executors.newScheduledThreadPool(1);
        scheduler.schedule(() -> {
            SwingUtilities.invokeLater(() -> {
                int devolucion = JOptionPane.showConfirmDialog(null,
                        "¿Desea devolver el libro '" + libro.getTitulo() + " '? son las: " + tiempoPrestamo
                                + "Sino lo devuelve le generara una multa",
                        "CONFIRMACION DEVOLUCION", JOptionPane.YES_NO_OPTION,JOptionPane.WARNING_MESSAGE);

                if (devolucion == JOptionPane.YES_OPTION){
                    prestados.remove(libro);
                    disponibles.add(libro);
                    JOptionPane.showMessageDialog(null,"Gracias por devolver el libro "
                            + libro.getTitulo());

                }else if(devolucion == JOptionPane.NO_OPTION) {

                    multasHistorialArraylist.add(multaRegistro(libro.getTitulo(), tiempoPrestamo));
                    verificarTiempoPrestamo(libro);
                }

            });
        }, 1, TimeUnit.MINUTES);
    }

    private static void reservarLibro() {

        String libroReservar = JOptionPane.showInputDialog("Ingrese el libro a reservar");
        Libro libro = new Libro();
        mensaje = new StringBuilder("");

        for (Libro librox : catalogo){

            if (librox.getTitulo().equalsIgnoreCase(libroReservar)) {
                libro = librox;
                break;
            }
        }

        if (disponibles.contains(libro)) {

            int respuestaPrestamo = JOptionPane.showConfirmDialog(null,
                    "El libro " + libroReservar + " esta disponible para tomar en prestamo\n"+
                            "¿Desea tomarlo en prestamo?", "CONFIRMACION PRESTAMO",JOptionPane.YES_NO_OPTION);

            if (respuestaPrestamo == JOptionPane.YES_OPTION){

                prestamoLibro();
                return;

            } else {
                JOptionPane.showMessageDialog(null,"Gracias por su respuesta");
                return;
            }

        } else if (prestados.contains(libro)) {

            JOptionPane.showMessageDialog(null, "El libro " + libro.getTitulo()
                    + " queda reservado: " + libro.getFechahoraReserva());
            reservados.add(libro);
            historialReservasArraylist.add(String.valueOf(registroHistorial(libro.getTitulo(),"RESERVO",
                    libro.getFechaHoraPrestamo())));
            return;

        } else if (reservados.contains(libro)) {

            JOptionPane.showMessageDialog(null,
                    "No puede reservar el libro " + libroReservar + " porque ya fue reservado el: "
                            + libro.getFechahoraReserva());
            return;
        }else {
            JOptionPane.showMessageDialog(null,"El libro " + libroReservar
                    + " no esta en nuestra Biblioteca");
        }
    }

    public static void librosPrestados() {

        mensaje = new StringBuilder("");

        int numeroPrestamo = 1;

        if (!prestados.isEmpty()) {
            for (Libro prestamo : prestados) {
                mensaje.append("Prestamo ").append(numeroPrestamo).append(": ")
                        .append(prestamo.getTitulo())
                        .append(" a las ")
                        .append(prestamo.getFechaHoraPrestamo())
                        .append("\n");
                numeroPrestamo++;
            }
        } else {
            mensaje.append("No hay Libros en prestamo");
        }

        JOptionPane.showMessageDialog(null, mensaje, "LIBROS PRESTADOS", JOptionPane.INFORMATION_MESSAGE);
    }

    public static void librosReservados(){

        mensaje = new StringBuilder("");

        int numeroReserva = 1;

        if (!reservados.isEmpty()){

            for (Libro reserva : reservados){

                mensaje.append("Reverva ").append(numeroReserva).append(" :").append(reserva.getTitulo()).
                        append("reservado a las ").append(reserva.getFechahoraReserva()).append("\n");
                ++numeroReserva;
            }
        }else {
            mensaje.append("No hay libros reservados");
        }
        JOptionPane.showMessageDialog(null,mensaje, "LIBROS RESERVADOS",JOptionPane.INFORMATION_MESSAGE);
    }

    public static StringBuilder registroHistorial(String libro, String tipo, LocalDateTime time){

        StringBuilder registro = mensaje.append("El libro " + libro + " se " + tipo + " el dia y hora: " + time);
        return registro;
    }

    public static void historialPrestamos(){

        mensaje = new StringBuilder("");
        int numeroPrestamos = 1;

        if (!historialPrestamosArraylist.isEmpty()){

            for (String historialPrestamos : historialPrestamosArraylist){

                mensaje.append("Prestamo: ").append(numeroPrestamos).append(": ")
                        .append(historialPrestamos).append("\n");
                ++numeroPrestamos;
            }

        }else {

            mensaje.append("No hay PRESTAMOS registrados");
        }
        JOptionPane.showMessageDialog(null,mensaje,"HISTORIAL PRESTAMOS",JOptionPane.INFORMATION_MESSAGE);
    }

    public static void historialReservas(){

        mensaje = new StringBuilder("");
        int numeroReservas = 1;

        if (!historialReservasArraylist.isEmpty()){

            for (String historialPrestamos : historialReservasArraylist){

                mensaje.append("Reserva: ").append(numeroReservas).append(": ")
                        .append(historialPrestamos).append("\n");
                ++numeroReservas;

            }

        }else {

            mensaje.append("No hay RESERVAS registradas");
        }
        JOptionPane.showMessageDialog(null,mensaje,"HISTORIAL RESERVAS",JOptionPane.INFORMATION_MESSAGE);
    }

    private static String multaRegistro(String tituloLibro, LocalDateTime tiempoPrestamo) {

        int valorMulta = 5000;
        String historialMultas = "Libro: " + tituloLibro + " fecha: " + tiempoPrestamo + " valor multa: " + valorMulta;
        totalMultas = totalMultas + valorMulta;
        return historialMultas;
    }

    private static void mostrarHistorialMultas(){

        mensaje = new StringBuilder("");
        int numeroMultas = 1;

        if (!multasHistorialArraylist.isEmpty()){

            for (String mostrarHistorialMultas : multasHistorialArraylist){

                mensaje.append("Multa ").append(numeroMultas).append(" : ").append(mostrarHistorialMultas).append("\n");
                ++numeroMultas;
            }

        }else {

            JOptionPane.showMessageDialog(null,"No tiene multas pendientes");
            return;
        }
        JOptionPane.showMessageDialog(null,mensaje.append("\n").append("Total multas: "
                + totalMultas),"HISTORIAL MULTAS",JOptionPane.INFORMATION_MESSAGE);
    }

    public static void listaLibros(){

        mensaje = new StringBuilder("");

        mensaje.append(catalogo.toString());

        JOptionPane.showMessageDialog(null,mensaje);
    }
    public static void pagarMultas(){

        mensaje = new StringBuilder("");

        int pagarMulta = JOptionPane.showConfirmDialog(null,"¿Desea pagar una multa?",
                "PAGO DE MULTAS",JOptionPane.YES_NO_OPTION);

        if (pagarMulta == JOptionPane.YES_OPTION){

            if (!multasHistorialArraylist.isEmpty()){
                int valorStandarMulta = 5000;
                int numeroMultas = 1;

                for (String mostrarHistorialMultas : multasHistorialArraylist){

                    mensaje.append("Multa ").append(numeroMultas).append(" : ").append(mostrarHistorialMultas).append("\n");
                    ++numeroMultas;
                }

                mensaje.append("\nTotal multas: " + totalMultas + "\n");

                int numeroMulta = Integer.parseInt(JOptionPane.showInputDialog(
                        mensaje.append("Ingrese el numero de multa que desea pagar\n"+
                                "Ingresando el numero cero paga todo\n")));

                if (numeroMulta > 0 && numeroMulta <= multasHistorialArraylist.size()){
                    totalMultas = totalMultas - valorStandarMulta;
                    numeroMulta = numeroMulta - 1;
                    multasHistorialArraylist.remove(numeroMulta);
                    numeroMulta = numeroMulta + 1;

                    JOptionPane.showMessageDialog(null,
                            "Gracias por pagar la multa: " + numeroMulta);

                } else if (numeroMulta == 0) {
                    multasHistorialArraylist.clear();
                    totalMultas = 0;
                    JOptionPane.showMessageDialog(null,
                            "Gracias por pagar TODAS TUS MULTAS");

                } else {
                    JOptionPane.showMessageDialog(null,"Ingrese un numero de multa valido");
                    pagarMultas();
                }

            } else if (multasHistorialArraylist.isEmpty()) {
                JOptionPane.showMessageDialog(null,"No tiene multas pendientes");
            }

        } else if (pagarMulta == JOptionPane.NO_OPTION) {
            JOptionPane.showMessageDialog(null,
                    "No olvide pagar sus multas lo mas pronto posible", "PAGUE!!!!",JOptionPane.WARNING_MESSAGE);
        }
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

    public static <T> void mostrarList(ArrayList<T> lista){

        for (T listaX: lista){

            mensaje.append(lista);
        }
    }
}