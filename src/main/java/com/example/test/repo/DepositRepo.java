package com.example.test.repo;

import com.example.test.model.Deposit;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

/*
 * Created by Aleksei Vekovshinin on 12.11.2020
 */
@Repository
public interface DepositRepo extends JpaRepository<Deposit, Long> {

    @Query("SELECT d FROM Deposit d WHERE d.openTimeInMonths > ?1")
    List<Deposit> getDepositsFilteredByOpenTime(Short openTimeInMonths);

//    @Query("SELECT d FROM Deposit d ORDER BY ?1 DESC")
//    List<Deposit> getDepositsSortedByTarget(String orderTarget);

//    @Query(value = "SELECT d FROM Deposit d ORDER BY LOWER(:orderTarget) DESC")
//    List<Deposit> getDepositsSortedByTarget(@Param("orderTarget") String orderTarget);

    @Query(value = "SELECT d FROM Deposit d")
    List<Deposit> getDepositsSortedByTarget(Sort sort);

    @Query("SELECT d FROM Deposit d WHERE d.openTimeInMonths > ?1")
    List<Deposit> getDepositsFilteredByOpenMonthsAndSortedByTarget(Short openTimeInMonths, Sort sortTarget);

}
