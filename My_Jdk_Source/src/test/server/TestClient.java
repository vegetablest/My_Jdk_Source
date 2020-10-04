package test.server;

public class TestClient {

    public static void main(String[] args) {
        Server server = new Server();

        ClientA clientA = new ClientA(server);
        clientA.process(5,6);

        ClientB clientB = new ClientB(server);
        clientB.process(50,60);

    }

}