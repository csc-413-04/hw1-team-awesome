package simpleserver;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;

class SimpleServer {

  public static void main(String[] args) throws IOException {
    parser.CreateUsers();
    parser.CreatePosts();
    ServerSocket ding;
    Socket dong = null;
    String resource = null;
    String url = null;
    try {
      ding = new ServerSocket(1299);
      System.out.println("Opened socket " + 1299);
      while (true) {

        // keeps listening for new clients, one at a time
        try {
          dong = ding.accept(); // waits for client here
        } catch (IOException e) {
          System.out.println("Error opening socket");
          System.exit(1);
        }

        InputStream stream = dong.getInputStream();
        BufferedReader in = new BufferedReader(new InputStreamReader(stream));
        try {

          // read the first line to get the request method, URI and HTTP version
          String line = in.readLine();
          url = line;

          System.out.println("----------REQUEST START---------");
          System.out.println(line);
          // read only headers
          line = in.readLine();
          while (line != null && line.trim().length() > 0) {
            int index = line.indexOf(": ");
            if (index > 0) {
              System.out.println(line);
            } else {
              break;
            }
            line = in.readLine();
          }
          System.out.println("----------REQUEST END---------\n\n");
        } catch (IOException e) {
          System.out.println("Error reading");
          System.exit(1);

        }

        BufferedOutputStream out = new BufferedOutputStream(dong.getOutputStream());
        PrintWriter writer = new PrintWriter(out, true);  // char output to the client

        // every response will always have the status-line, date, and server name
        writer.println("HTTP/1.1 200 OK");
        writer.println("Server: TEST");
        writer.println("Connection: close");
        writer.println("Content-type: text/html");
        writer.println("");

        // Body of our response
        String response = parser.process(url).replaceAll(",", ",<br/>");
        response = response.replaceAll("\\[", "[<br/>");
        response = response.replaceAll("]", "<br/>]");
        response = response.replaceAll("}", "</p><p style=\"position:relative; left:100px\">}");
        response = response.replaceAll("\\{", "{</p><p style=\"position:relative;left:150px\">");


        writer.println("{");
        writer.println(response);
        writer.println("}");
        dong.close();
      }

    } catch (IOException e) {
      System.out.println("Error opening socket");
      System.exit(1);
    }
  }
}
