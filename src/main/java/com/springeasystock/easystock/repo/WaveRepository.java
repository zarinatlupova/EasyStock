package com.springeasystock.easystock.repo;

import com.springeasystock.easystock.model.Wave;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface WaveRepository extends JpaRepository<Wave, Long> {
}
