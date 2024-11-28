package br.com.facol.uservault.domain.factory;

import br.com.facol.uservault.domain.Address;
import br.com.facol.uservault.domain.UserCreate;
import br.com.facol.uservault.domain.UserProfile;
import br.com.facol.uservault.dto.AddressDTO;
import br.com.facol.uservault.dto.UserCreateRequestDTO;
import br.com.facol.uservault.service.AddressService;
//import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import java.util.List;

@Component
public class UserCreateFactoryImpl implements UserCreateFactory{

    private final AddressService addressService;

    public UserCreateFactoryImpl(AddressService addressService) {
        this.addressService = addressService;
    }

    @Override
    public UserCreate createUser(UserCreateRequestDTO requestDTO) {

        UserCreate userCreate = new UserCreate();
        userCreate.setEmail(requestDTO.getEmail());
        userCreate.setName(requestDTO.getName());
        userCreate.setProfile(getProfile(requestDTO.getProfile()));
        userCreate.setPassword(new BCryptPasswordEncoder().encode(requestDTO.getPassword()));
        if(StringUtils.hasText(requestDTO.getCep())){
            userCreate.setAddresses(createAddress(requestDTO.getCep(), userCreate));
        }

        return userCreate;
    }

    private UserProfile getProfile(String profile){
        return profile.contains("ADMIN") ? UserProfile.ADMIN : UserProfile.USER;
    }

    private List<Address> createAddress(String cep, UserCreate userCreate) {
        AddressDTO addressByCep = addressService.getAddressByCep(cep);

        Address address = new Address(
                addressByCep.getCep(),
                addressByCep.getAddress_name(),
                addressByCep.getDistrict(),
                addressByCep.getCity(),
                addressByCep.getState(),
                userCreate
        );

        return List.of(address);
    }


}
