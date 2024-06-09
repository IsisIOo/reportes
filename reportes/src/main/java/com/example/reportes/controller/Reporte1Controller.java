package com.example.reportes.controller;

import com.example.reportes.entity.Reporte1;
import com.example.reportes.service.Reporte1Service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/reporte1")

public class Reporte1Controller {
    @Autowired
    Reporte1Service reporte1Service;


    @PostMapping("/newReporte1/")
    public ResponseEntity<Reporte1> updateRecord(int mes, String reparacion){
        Reporte1 report = reporte1Service.setReporte1(mes, reparacion);
        return ResponseEntity.ok(report);
    }
}
