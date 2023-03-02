import java.io.BufferedWriter;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.net.InetAddress;
import java.net.Socket;
import java.util.HashSet;

public class Client {
    public static void main(String[] args){
        // var socket = new Socket("ip address" , port);
        // у нас локал сервер того використовуємо getlocalHost(). getHostAddres переводить все в текст.
        try(var socket = new Socket(InetAddress.getLocalHost().getHostAddress(),8899)){
            // отримуємо вихідний потік нашого сокета
            var writer = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));
            var message = "Hello from Vova \n";
            writer.write(message); // записуємо повідомлення в буфер.
            writer.flush(); // відсилаємо його на сервер.

        } catch (IOException e){
            System.out.println("Oops" + e);
        }
    }
}
