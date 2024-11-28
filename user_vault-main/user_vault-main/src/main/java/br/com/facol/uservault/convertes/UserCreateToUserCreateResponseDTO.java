package br.com.facol.uservault.convertes;

import br.com.facol.uservault.domain.Address;
import br.com.facol.uservault.domain.UserCreate;
import br.com.facol.uservault.dto.AddressDTO;
import br.com.facol.uservault.dto.UserCreateResponseDTO;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class UserCreateToUserCreateResponseDTO implements Mapper<UserCreate, UserCreateResponseDTO>{
    @Override
    public UserCreateResponseDTO map(UserCreate userCreate) {
        UserCreateResponseDTO userCreateResponseDTO = new UserCreateResponseDTO();
        userCreateResponseDTO.setId(userCreate.getId().toString());
        userCreateResponseDTO.setName(userCreate.getName());
        userCreateResponseDTO.setEmail(userCreate.getEmail());
        userCreateResponseDTO.setProfile(userCreate.getProfile().toString());
        userCreateResponseDTO.setAddress(getAdresses(userCreate.getAddresses()));
        return userCreateResponseDTO;
    }


    private List<AddressDTO> getAdresses(List<Address> addresses) {
        List<AddressDTO> addressDTOs = new ArrayList<>();

        addresses.forEach(address -> {
            AddressDTO addressDTO = new AddressDTO();
            addressDTO.setAddress_name(address.getAddress_name());
            addressDTO.setCep(address.getCep());
            addressDTO.setDistrict(address.getDistrict());
            addressDTO.setState(address.getState());
            addressDTO.setCity(address.getCity());
            addressDTOs.add(addressDTO);
        });

        return addressDTOs;
    }
}
