package com.pool.tronik.database;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TasksRepository extends JpaRepository<ScheduleEntity, Long> {
     List<ScheduleEntity> findByRelay(int relay);
}
