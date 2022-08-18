package br.com.project.shopstyle.mscustomer.builder;

import br.com.project.shopstyle.mscustomer.constants.State;
import br.com.project.shopstyle.mscustomer.dto.AddressDTO;
import br.com.project.shopstyle.mscustomer.dto.UpdateAddressFORM;
import br.com.project.shopstyle.mscustomer.entity.Address;

public class AddressBuilder {

    public static Address getAddress(){
        return new Address(
                1L,
                State.SAO_PAULO,
                "Mogi Guaçu",
                "Baixa Mogiana",
                "Rua 02",
                "1917",
                "13871910",
                " ",
                1L
        );
    }

    public static Address getUpdatedAddress(){
        return new Address(
                1L,
                State.SAO_PAULO,
                "Mogi Mirim",
                "Baixa Mogiana",
                "Rua 01",
                "2345",
                "12345678",
                " ",
                1L
        );
    }

    public static AddressDTO getAddressDTO() {
        return new AddressDTO(
                1L,
                State.SAO_PAULO,
                "Mogi Guaçu",
                "Baixa Mogiana",
                "Rua 02",
                "1917",
                "13871910",
                " ",
                1L
        );
    }

    public static AddressDTO getUpdatedAddressDTO(){
        return new AddressDTO(
                1L,
                State.SAO_PAULO,
                "Mogi Mirim",
                "Baixa Mogiana",
                "Rua 01",
                "2345",
                "12345678",
                " ",
                1L
        );
    }

    public static UpdateAddressFORM getUpdateAddressFORM() {
        return new UpdateAddressFORM(
                State.SAO_PAULO,
                "Mogi Mirim",
                "Baixa Mogiana",
                "Rua 01",
                "2345",
                "12345678",
                " "
        );
    }
}
