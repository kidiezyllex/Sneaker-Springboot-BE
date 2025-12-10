package com.sneaker.repository;

import com.sneaker.entity.AccountAddress;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface AccountAddressRepository extends JpaRepository<AccountAddress, Integer> {
    List<AccountAddress> findByAccountId(Integer accountId);
    Optional<AccountAddress> findByIdAndAccountId(Integer id, Integer accountId);
    void deleteByIdAndAccountId(Integer id, Integer accountId);
}

