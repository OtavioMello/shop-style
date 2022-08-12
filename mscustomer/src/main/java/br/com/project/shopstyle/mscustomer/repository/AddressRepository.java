package br.com.project.shopstyle.mscustomer.repository;

import br.com.project.shopstyle.mscustomer.entity.Address;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AddressRepository extends JpaRepository<Address, Long> {
}
