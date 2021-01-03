package asw.instagnam.ricette.async;

public interface MessagePublisher<T> {

    void sendMessage(T message);

}
