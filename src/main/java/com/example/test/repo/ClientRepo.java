package com.example.test.repo;

import com.example.test.model.Client;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/*
 * Created by Aleksei Vekovshinin on 12.11.2020
 */
@Repository
public interface ClientRepo extends JpaRepository<Client, Long> {
}
