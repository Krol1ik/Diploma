package com.vlad.tms.diploma.repository;

import com.vlad.tms.diploma.model.product.StockBalance;
import org.springframework.data.jpa.repository.JpaRepository;

public interface StockBalanceRepository extends JpaRepository<StockBalance, Long> {
}
