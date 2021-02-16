package edu.escuelaing.arep.httpServer;

import javax.imageio.ImageIO;

import edu.escuelaing.arep.persistencia.DataBase;

import java.awt.image.BufferedImage;
import java.net.*;
import java.io.*;
import java.nio.file.*;
import java.util.*;
import java.util.logging.*;

/**
 * HttpServer
 */
public class HttpServer {

	private int port = 36000;
	int limite = 100;

	/**
	 * Constructor
	 */
	public HttpServer() {
		this.port = getPort();
	}

	public HttpServer(Socket socket) {
		this.getPort();
	}

	/**
	 * Inicializacion del servicio
	 */
	public void start() {
		try {
			ServerSocket serverSocket = null;
			this.port = getPort();

			try {
				serverSocket = new ServerSocket(port);
			} catch (IOException e) {
				System.err.println("Could not listen on port: " + getPort());
				System.exit(1);
			}

			int cont=0;
			while (cont<limite) {
				cont++;
				System.out.println(cont);
				try {
					Socket clientSocket = null;
					try {
						System.out.println("Listo para recibir ...");
						clientSocket = serverSocket.accept();
					} catch (IOException e) {
						System.err.println("Accept failed.");
						System.exit(1);
					}
					BufferedReader in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
					String inputLine;
					boolean requestLineReady = true;
					Request req = null;
					while ((inputLine = in.readLine()) != null) {
						if (requestLineReady) {
							req = new Request(inputLine);
							requestLineReady = false;
						}
						if (!in.ready()) {
							break;
						}
					}
					if (req != null) {
						createResponse(req, new PrintWriter(clientSocket.getOutputStream(), true), clientSocket);
					}
					in.close();
					clientSocket.close();
				} catch (IOException ex) {
					Logger.getLogger(HttpServer.class.getName()).log(Level.SEVERE, null, ex);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
			System.out.println("fiiiiin");
			serverSocket.close();
		} catch (IOException ex) {
			Logger.getLogger(HttpServer.class.getName()).log(Level.SEVERE, null, ex);
		}
	}

	/**
	 * Funcion encargada de vista de la base datos
	 * 
	 * @param req
	 * @param out
	 * @param clientSocket
	 * @throws Exception
	 */
	private void createResponse(Request req, PrintWriter out, Socket clientSocket) throws Exception {
		if (req.getRequestURI().startsWith("/base")) {
			String salida = "HTTP/1.1 200 OK\r\n" + "Content-Type: text/html\r\n" + "\r\n" + "<!DOCTYPE html>\n"
					+ "<html>\n" + "<head>\n" + "<meta charset=\"UTF-8\">\n" + "<title>Data Base</title>\n"
					+ "</head>\n" + "<body>\n" + "<h1>Basketball items</h1>\n" + "</body>\n" + "</html>\n";
			salida += getDataBase();
			out.println(salida);
		} else {
			getStaticResource(req.getRequestURI(), out, clientSocket);
		}
		out.close();
	}

	/**
	 * Funcion encargada de mostrar los recursos
	 * 
	 * @param path
	 * @param out
	 * @param clientSocket
	 * @throws Exception
	 */
	private void getStaticResource(String path, PrintWriter out, Socket clientSocket) throws Exception {
		Path file = Paths.get("src/main/resources/index.html");
		String resource = "HTTP/1.1 200 OK\r\n";
		if (path.contains(".html") || path.equals("/")) {
			System.out.println("ENTRE");
			path = "index.html";
			file = Paths.get("src/main/resources/" + path);
			resource += ("Content-Type: text/html\r\n" + "\r\n" + "<!DOCTYPE html>\n");
		} else if (path.contains("cancha")) {
			path = "cancha.jpg";
			file = Paths.get("src/main/resources/" + path);
			getImage(path, clientSocket.getOutputStream());
		}else if (path.contains("balon")) {
			path = "balon.jpg";
			file = Paths.get("src/main/resources/" + path);
			getImage(path, clientSocket.getOutputStream());
		}else if (path.contains("tenis")) {
			path = "tenis.jpg";
			file = Paths.get("src/main/resources/" + path);
			getImage(path, clientSocket.getOutputStream());
		}
		try (InputStream in = Files.newInputStream(file);
				BufferedReader reader = new BufferedReader(new InputStreamReader(in))) {
			out.println(resource);
			String line = null;
			while ((line = reader.readLine()) != null) {
				out.println(line);
			}
		} catch (IOException ex) {
			Logger.getLogger(HttpServer.class.getName()).log(Level.SEVERE, null, ex);
		}
	}

	/**
	 * Funcion enccargada de encontrar los recursos de imagen y mostrarlos
	 * 
	 * @param path
	 * @param outputStream
	 */
	public void getImage(String path, OutputStream out) throws Exception {
		File file = new File("src/main/resources/" + path);
		try {
			BufferedImage imagen = ImageIO.read(file);
			ByteArrayOutputStream salida = new ByteArrayOutputStream();
			DataOutputStream impresion = new DataOutputStream(out);
			ImageIO.write(imagen, "jpg", salida);
			impresion.writeBytes("HTTP/1.1 200 OK\r\n" + "Content-Type: image/jpg \r\n\r\n");
			impresion.write(salida.toByteArray());
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	/**
	 * Funcion que se encarga de traer los elementos de BD
	 * 
	 * @return
	 * @throws ClassNotFoundException 
	 */
	public String getDataBase() throws ClassNotFoundException {
		DataBase basket = new DataBase();
		ArrayList<String[]> datos = basket.getTable();
		String list = "";
		for (String[] d : datos) {
			list += "Nombre producto: " + d[0] + ": " + d[1] + " ";
		}
		return list;
	}
	/**
	 * metodo que pausa el server
	 */
	public void limite(int in) {
		this.limite=in;
	}

	/**
	 * Gestion de puerto
	 * @return
	 */
	public int getPort() {
		if (System.getenv("PORT") != null) {
			return Integer.parseInt(System.getenv("PORT"));
		}
		return 36000;
	}
}