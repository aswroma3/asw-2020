package asw.instagnam.connessioni.async;


import asw.instagnam.connessioni.domain.Connessione;

public interface ConnessioneMessagePublisher extends MessagePublisher<Connessione> {

    void sendMessage(Connessione message);

}
