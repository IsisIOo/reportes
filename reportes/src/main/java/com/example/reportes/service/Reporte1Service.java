package com.example.reportes.service;

import com.example.reportes.model.Car;
import com.example.reportes.model.Repair;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestOperations;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@Service
public class Reporte1Service {
    @Autowired
    RestTemplate restTemplate;

    public Car getCar(String patent) {
        Car car = restTemplate.getForObject("http://autofix-car/api/car/carpatent/" + patent, Car.class);
        return car;
    }

    public Repair getRepair(String patent) {
        Repair repair = restTemplate.getForObject("http://autofix-repairs/api/repairs/repair-patent/" + patent, Repair.class);
        return repair;
    }

    //obtengo todas las reparaciones para asi poder buscar los que tienen un cierto tipo de reparacion
    public List<Repair> getAllRepairs(){
        List<Repair> repairs = restTemplate.getForObject("http://autofix-repairs/api/repairs/", List.class);
        return repairs;
    }

    public List<Repair> getAllporNombreReparacion(String nombreReparacion){
        List<Repair> repairs = restTemplate.getForObject("http://autofix-repairs/api/repairs/by-repair/" + nombreReparacion, List.class);
        return repairs;
    }

    public Repair buscarREPAIRconMEssolicitado(List<Repair> repairs, int messolicitado) {
        int tamano = repairs.size();
        for (int i = 0; i < tamano; i++) {
            if (repairs.get(i).getAdmissionDateMonth() == messolicitado) {
                return repairs.get(i);
            }
        }
        //si no hay ninguno con ese mes
        return null;
    }

    public Repair buscarRepairMesSiguiente (List <Repair> repairs, int messolicitado){
        int tamano = repairs.size();
        for (int i = 0; i < tamano; i++) {
            if (repairs.get(i).getAdmissionDateMonth() == messolicitado + 1) {
                return repairs.get(i);
            }
        }
        //si no hay ninguno con ese mes
        return null;
    }

    public Repair buscarRepairMesAnterior (List <Repair> repairs, int messolicitado){
        int tamano = repairs.size();
        for (int i = 0; i < tamano; i++) {
            if (repairs.get(i).getAdmissionDateMonth() == messolicitado - 1) {
                return repairs.get(i);
            }
        }
        //si no hay ninguno con ese mes
        return null;
    }

    //cantidad de reparaciones del mes solicitado REPORTE 1
    public int cantidadReparacionesMesSolicitado(List<Repair> repairs, int messolicitado) {
        int tamano = repairs.size();
        int cantidad = 0;
        for (int i = 0; i < tamano; i++) {
            if (repairs.get(i).getAdmissionDateMonth() == messolicitado) {
                cantidad++;
            }
        }
        return cantidad;
    }

    //cantidad de reparaciones del mes siguiente REPORTE 1
    public int cantidadReparacionesMesSiguiente(List<Repair> repairs, int messolicitado) {
        int tamano = repairs.size();
        int cantidad = 0;
        for (int i = 0; i < tamano; i++) {
            if (repairs.get(i).getAdmissionDateMonth() == messolicitado + 1) {
                cantidad++;
            }
        }
        return cantidad;
    }

    //cantidad de reparaciones del mes anterior REPORTE 1
    public int cantidadReparacionesMesAnterior(List<Repair> repairs, int messolicitado) {
        int tamano = repairs.size();
        int cantidad = 0;
        for (int i = 0; i < tamano; i++) {
            if (repairs.get(i).getAdmissionDateMonth() == messolicitado - 1) {
                cantidad++;
            }
        }
        return cantidad;
    }

    public double variacionPorcentual(int cantidad1, int cantidad2) {
        if (cantidad1 == 0) {
            return Double.NaN; // Devuelve 'Not a Number' para indicar que no se puede calcular
        }
        double variacion = ((cantidad2 - cantidad1) / (double) cantidad1) * 100;
        return variacion;
    }




}
