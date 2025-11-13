package com.picpay.desafiopicpay.business;

import com.picpay.desafiopicpay.business.apiexterna.Notificar;
import com.picpay.desafiopicpay.infrastructure.entity.UserEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;

@Service
public class NotificarService {


    private final RestTemplate restTemplate;
    private static final String urlPrincipal = "https://util.devi.tools/api/v1/notify";

    public NotificarService(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    public void notificar(UserEntity recebedor) {
        try {
            ResponseEntity<Notificar> responseEntity = restTemplate.postForEntity(urlPrincipal, null, Notificar.class);


            if (responseEntity.hasBody()) {
                Notificar dados = responseEntity.getBody();
                if ("error".equalsIgnoreCase(dados.getStatus())) {

                    System.err.println("Notificação falhou (Status JSON). Recebedor: " + recebedor.getEmail());
                    return;
                }
            }


            System.out.println(" Notificação enviada com sucesso para: " + recebedor.getEmail());

        } catch (HttpClientErrorException e) {

            System.err.println(" ERRO HTTP (" + e.getStatusCode() + ") na notificação para " + recebedor.getEmail() + ": " + e.getMessage());
        } catch (Exception e) {

            System.err.println(" ERRO de comunicação ou I/O na notificação para " + recebedor.getEmail() + ": " + e.getMessage());
        }

    }
}