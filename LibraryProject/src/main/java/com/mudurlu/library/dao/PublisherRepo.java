package com.mudurlu.library.dao;

import com.mudurlu.library.entitites.Publisher;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PublisherRepo extends JpaRepository<Publisher,Integer> {
}
