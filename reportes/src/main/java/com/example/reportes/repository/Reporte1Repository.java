package com.example.reportes.repository;

import com.example.reportes.entity.Reporte1;
import com.example.reportes.model.Car;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;

@Repository
public interface Reporte1Repository extends JpaRepository<Reporte1, Long> {


}
