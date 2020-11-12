package com.example.test.repo;

import com.example.test.model.Bank;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/*
 * Created by Aleksei Vekovshinin on 12.11.2020
 */
@Repository
public interface BankRepo extends JpaRepository<Bank, Long> {
}
