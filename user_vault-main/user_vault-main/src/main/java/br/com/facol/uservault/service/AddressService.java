package br.com.facol.uservault.service;

import br.com.facol.uservault.dto.AddressDTO;

public interface AddressService {
    AddressDTO getAddressByCep(String cep);
}
