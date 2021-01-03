package asw.instagnam.connessioni.async;

public interface MessagePublisher<T> {

    void sendMessage(T message);

}
