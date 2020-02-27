package com.pool.tronik.database;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PoolTronickRepository extends JpaRepository<ScheduleEntity, Long> {
}
