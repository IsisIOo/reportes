package com.example.reportes.service;

import com.example.reportes.model.Car;
import com.example.reportes.model.Repair;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@Service
public class Reporte1Service {
    @Autowired
    RestTemplate restTemplate;


    //AQUI HAGO UN FEIGN
    //YA LO HABIA HECHO XD

    public  List<Repair> getAllPARACONECTAR(){
        List<Repair> repairs = restTemplate.getForObject("http://autofix-repairs/api/repairs/", List.class);
        return repairs;
    }

    public List<Car> getallCars(){
        List<Car> cars = restTemplate.getForObject("http://autofix-car/api/car/", List.class);
        return cars;
    }







}
