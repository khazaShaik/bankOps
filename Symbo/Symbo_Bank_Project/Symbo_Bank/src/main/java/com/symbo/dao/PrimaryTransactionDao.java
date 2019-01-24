package com.symbo.dao;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import com.symbo.domain.PrimaryTransaction;

public interface PrimaryTransactionDao extends CrudRepository<PrimaryTransaction, Long> {

    List<PrimaryTransaction> findAll();
}
