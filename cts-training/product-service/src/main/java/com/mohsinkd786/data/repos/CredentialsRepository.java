package com.mohsinkd786.data.repos;

import com.mohsinkd786.data.entities.Credentials;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CredentialsRepository extends JpaRepository<Credentials,Integer> {
}
