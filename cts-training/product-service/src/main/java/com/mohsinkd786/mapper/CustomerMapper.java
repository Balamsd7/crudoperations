package com.mohsinkd786.mapper;

import com.mohsinkd786.data.entities.Address;
import com.mohsinkd786.data.entities.Credentials;
import com.mohsinkd786.data.entities.Customer;
import com.mohsinkd786.dtos.AddressDto;
import com.mohsinkd786.dtos.CustomerDto;
import ma.glasnost.orika.CustomMapper;
import ma.glasnost.orika.MapperFactory;
import ma.glasnost.orika.MappingContext;
import ma.glasnost.orika.impl.ConfigurableMapper;
import org.springframework.stereotype.Component;

@Component
public class CustomerMapper extends ConfigurableMapper {
    @Override
    protected void configure(MapperFactory factory) {
        super.configure(factory);
        factory.registerClassMap(
                factory.classMap(CustomerDto.class, Customer.class)
                        .byDefault()
                        .field("username", "credentials.username")
                        .field("password", "credentials.password")
                        .customize(new AddressCustomMapper())
                        .toClassMap());

        factory.registerClassMap(
                factory.classMap(CustomerDto.class, Credentials.class)
                        .byDefault());
    }
}
class AddressCustomMapper extends CustomMapper<CustomerDto, Customer> {

    @Override
    public void mapAtoB(final CustomerDto customerDto,final Customer customer, MappingContext context) {
        customer.getAddresses().clear();
        customerDto.getAddresses().forEach(addressDto->{
            Address address = new Address();
            address.setCity(addressDto.getCity());
            address.setZipCode(addressDto.getPinCode());
            address.setStreet(addressDto.getStreetName());
            customer.getAddresses().add(address);
        });
    }

    @Override
    public void mapBtoA(final Customer customer,final CustomerDto customerDto, MappingContext context) {
        customerDto.getAddresses().clear();
        customer.getAddresses().forEach(address->{
            AddressDto addressDto = new AddressDto();
            addressDto.setCity(address.getCity());
            addressDto.setPinCode(address.getZipCode());
            addressDto.setStreetName(address.getStreet());
            customerDto.getAddresses().add(addressDto);
        });
    }
}
