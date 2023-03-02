import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.ServerSocket;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

public class Main {
    public static void main(String[] args) {
        var users = new HashSet<String>(); //створюємо таблицю з унікальністю(1 user з ip)
        // CompletableFuture клас дозволяє виконувати асинхроні операції та отримувати результати коли вони будуть готові
        CompletableFuture.runAsync(() -> { // runAsync дозволяє запускати операції без отримання результату
            while(true){
            System.out.println("Unique users: " + users.size()); // розмір
            try{
                TimeUnit.SECONDS.sleep(5); // затримка
            } catch(InterruptedException e){
                e.printStackTrace();
            }
        }
        });
        var threads = new ArrayList<Thread>(); // список потоків
        try(ServerSocket serverSocket = new ServerSocket(8899)){ // сервер
            for (int i = 0; i < 40; i++) {
                var t = new Thread(() -> { // потоки
                    try(var client = serverSocket.accept()){ //чекаємо на зєднання від клієнта
                        var reader = new BufferedReader(new InputStreamReader(client.getInputStream()));// читаємо
                        var line = reader.readLine();
                        String clientAddress = client.getInetAddress().getHostAddress(); // отримуємо IpAddress в текстому форматі
                        System.out.println(clientAddress + " - " + line);
                        users.add(clientAddress); // добавляємо обєкт в таблицю
                    } catch (Exception e){
                        System.out.println("Oops");
                    }
                });
                t.start();// старт потоку
                threads.add(t); // добавляємо його в список потоків
            }
            threads.forEach(t -> {
                try {
                    t.join();
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            });
        } catch(IOException ex){
            System.out.println("Oops" + ex);

        }
    }
}