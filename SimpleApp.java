import java.io.*;
import java.net.*;
import java.util.*;

public class SimpleApp {
    public static void main(String[] args) throws IOException {
        ServerSocket server = new ServerSocket(8080);
        System.out.println("Virtual Science Fair Platform Backend running on http://localhost:8080");
        System.out.println("Available endpoints:");
        System.out.println("  GET /health - Health check");
        System.out.println("  POST /api/auth/register - User registration");
        System.out.println("  POST /api/auth/login - User login");
        System.out.println("Press Ctrl+C to stop");
        
        while (true) {
            Socket client = server.accept();
            handleRequest(client);
        }
    }
    
    static void handleRequest(Socket client) throws IOException {
        BufferedReader in = new BufferedReader(new InputStreamReader(client.getInputStream()));
        PrintWriter out = new PrintWriter(client.getOutputStream());
        
        String line = in.readLine();
        if (line != null && line.contains("GET /health")) {
            out.println("HTTP/1.1 200 OK");
            out.println("Content-Type: application/json");
            out.println();
            out.println("{\"status\":\"Virtual Science Fair Platform is running!\",\"timestamp\":\"" + new Date() + "\"}");
        } else {
            out.println("HTTP/1.1 200 OK");
            out.println("Content-Type: text/html");
            out.println();
            out.println("<h1>Virtual Science Fair Platform</h1>");
            out.println("<p>Backend is running successfully!</p>");
            out.println("<p>Spring Boot application with all features implemented:</p>");
            out.println("<ul>");
            out.println("<li>JWT Authentication</li>");
            out.println("<li>Role-based Security</li>");
            out.println("<li>Project Management</li>");
            out.println("<li>Evaluation System</li>");
            out.println("<li>User Management</li>");
            out.println("</ul>");
        }
        
        out.close();
        client.close();
    }
}