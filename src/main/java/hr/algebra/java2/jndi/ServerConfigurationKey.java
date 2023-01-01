package hr.algebra.java2.jndi;

public enum ServerConfigurationKey {

    RMI_SERVER_PORT("rmi.port"),
    SERVER_PORT("server.port"),
    SERVER_CLIENT_SOCKET_PORT("server.client.socket.port");

    private String key;

    private ServerConfigurationKey(String key) {
        this.key = key;
    }

    public String getKey() {
        return key;
    }
}
