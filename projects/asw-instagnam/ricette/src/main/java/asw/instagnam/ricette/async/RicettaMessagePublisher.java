package asw.instagnam.ricette.async;

import asw.instagnam.ricette.domain.RicettaCompleta;

public interface RicettaMessagePublisher extends MessagePublisher<RicettaCompleta> {

    void sendMessage(RicettaCompleta message);

}
