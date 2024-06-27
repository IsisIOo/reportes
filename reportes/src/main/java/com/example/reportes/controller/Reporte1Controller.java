package com.example.reportes.controller;

import com.example.reportes.entity.Reporte1;
import com.example.reportes.model.Car;
import com.example.reportes.model.Repair;
import com.example.reportes.service.Reporte1Service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/reportes")

public class Reporte1Controller {
    @Autowired
    Reporte1Service reporte1Service;


    @PostMapping("/newReporte1/")
    public ResponseEntity<Reporte1> updateRecord(int mes, String reparacion){
        Reporte1 report = reporte1Service.setReporte1(mes, reparacion);
        return ResponseEntity.ok(report);
    }

    @GetMapping("/")
    //este obtiene todos los registros existentes
    public ResponseEntity<List<Repair>> getAllRepair() {
        List<Repair> recordHistory = reporte1Service.getAllPARACONECTAR();
        return ResponseEntity.ok(recordHistory);
    }

    @GetMapping("/car/")
    public ResponseEntity<List<Car>> getOneRepairByPatent(@RequestParam String patent) {
        List<Car> recordHistory = reporte1Service.getallCars();
        return ResponseEntity.ok(recordHistory);
    }


}
