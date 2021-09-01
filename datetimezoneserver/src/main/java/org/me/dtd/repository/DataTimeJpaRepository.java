package org.me.dtd.repository;

import org.me.dtd.entity.Data;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DataTimeJpaRepository extends JpaRepository<Data, Integer> {

}
