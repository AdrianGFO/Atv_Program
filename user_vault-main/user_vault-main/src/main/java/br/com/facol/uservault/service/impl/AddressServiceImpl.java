package br.com.facol.uservault.service.impl;

import br.com.facol.uservault.dto.AddressDTO;
import br.com.facol.uservault.helpers.GetData;
import br.com.facol.uservault.service.AddressService;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class AddressServiceImpl implements AddressService {

    @Override
    public AddressDTO getAddressByCep(String cep) {
        RestTemplate restTemplate = new RestTemplate();
        GetData data = new GetData(restTemplate);

        return data.sendRequest(
                "https://cep.awesomeapi.com.br/json/" + cep,
                HttpMethod.GET,
                null,
                AddressDTO.class,
                new HttpHeaders()
        ).getBody();
    }
}
