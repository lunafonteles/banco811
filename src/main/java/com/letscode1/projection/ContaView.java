package com.letscode1.projection;

import com.letscode1.model.TipoConta;
import org.springframework.beans.factory.annotation.Value;

public interface ContaView {
    Integer getSaldo();
    TipoConta getTipoConta();
    @Value("#{target.numero + ' - ' + target.agencia}")
    String getNumeroAgencia();
    String getUsuario();

}
