package edu.escuelaing.arep.spark;

import java.io.IOException;
import java.net.*;
import java.util.concurrent.*;

import edu.escuelaing.arep.httpServer.HttpServer;

public class Spark {

    
    /**
     *  Metodo que inicara servidor
     * @param args 
     * @throws IOException 
     */
    public static void main(String[] args) throws IOException {

        ServerSocket serverSocket = new ServerSocket(getPort());
        System.out.println("Listo para recibir ...");
        ExecutorService pool = Executors.newCachedThreadPool();
        while (true) {
            Socket socket = serverSocket.accept();
            HttpServer req = new HttpServer(socket);
            pool.execute((Runnable) req);
        }
    }

    /**
     * Gestion de puerto
     * @return 
     */
    private static int getPort() {
        if (System.getenv("PORT") != null) {
            return Integer.parseInt(System.getenv("PORT"));
        }
        return 35000;
    }
}