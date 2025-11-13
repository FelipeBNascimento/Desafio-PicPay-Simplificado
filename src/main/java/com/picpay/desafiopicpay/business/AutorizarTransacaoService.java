package com.picpay.desafiopicpay.business;
import com.picpay.desafiopicpay.business.apiexterna.Autorizar;
import com.picpay.desafiopicpay.infrastructure.exceptions.AutorizacaoNegada;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service

public class AutorizarTransacaoService {

    private final RestTemplate restTemplate;

    public AutorizarTransacaoService(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    private String urlPrincipal = "https://util.devi.tools/api/v2/authorize";

    public Autorizar autorizacao() {
        return restTemplate.getForObject(urlPrincipal, Autorizar.class);
    }

    public void autorizacaoTransferencia(){

        Autorizar response = autorizacao();

        if (response.getStatus().equals("fail")){
            throw new AutorizacaoNegada("Autorização negada");
        }
    }
}