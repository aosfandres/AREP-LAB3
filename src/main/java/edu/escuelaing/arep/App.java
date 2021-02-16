package edu.escuelaing.arep;


import edu.escuelaing.arep.httpServer.HttpServer;

public class App 
{
    public static void main( String[] args )
    {
    	 HttpServer httpServer = new HttpServer();
         httpServer.start();
    }
}
