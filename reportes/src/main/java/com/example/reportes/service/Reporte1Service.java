package com.example.reportes.service;

import com.example.reportes.entity.Reporte1;
import com.example.reportes.model.Car;
import com.example.reportes.model.Repair;
import com.example.reportes.repository.Reporte1Repository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestOperations;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@Service
public class Reporte1Service {
    @Autowired
    RestTemplate restTemplate;

    @Autowired
    Reporte1Repository reporte1repository;


    public Reporte1 saveReport(Reporte1 repo){
        return reporte1repository.save(repo);
    }

    //obtengo un solo auto
    public Car getCar(String patent) {
        Car car = restTemplate.getForObject("http://autofix-car/api/car/carpatent/" + patent, Car.class);
        return car;
    }

    //autos segun tipos de auto (cuantos autos hay por cierto tipo)
    public List<Car> getCarsbyType(String type) {
        List<Car> cars = restTemplate.getForObject("http://autofix-car/api/car/car-type/" + type, List.class);
        return cars;
    }

    //obtengo los reportes que tienen cierto tipo de reparacion (cuantos autos tienen la reparacion x)
    public Repair getRepair(String patent) {
        Repair repair = restTemplate.getForObject("http://autofix-repairs/api/repairs/repair-patent/" + patent, Repair.class);
        return repair;
    }

    //obtengo todas las reparaciones
    public List<Repair> getAllRepairs(){
        List<Repair> repairs = restTemplate.getForObject("http://autofix-repairs/api/repairs/", List.class);
        return repairs;
    }

    //obtengo todas las reaparaciones que tienen cierto tipo de reparacion, por ejemplo todos los autos obtenidos tendran la reapacion de neumaticos
    public List<Repair> getAllporNombreReparacion(String nombreReparacion){
        List<Repair> repairs = restTemplate.getForObject("http://autofix-repairs/api/repairs/by-repair/" + nombreReparacion, List.class);
        return repairs;
    }

    //de las reparaciones totales obtengo las reparaciones que se hicieron en el mes solicitado
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

    //de la lista de reparaciones totales obtengo los que fueron hechos en el mes siguiente del solicitado
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


// de la lista de reparaciones totales obtengo los que fueron hechos en el mes anterior del solicitado
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

    //cantidad de reparaciones del mes solicitado REPORTE 1, cuantas reparaciones fueron realizadas en marzo
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

    //la variacion porcentual de las reparaciones entre dos meses
    public double variacionPorcentual(int cantidad1, int cantidad2) {
        if (cantidad1 == 0) {
            return Double.NaN; // Devuelve 'Not a Number' para indicar que no se puede calcular
        }
        double variacion = ((cantidad2 - cantidad1) / (double) cantidad1) * 100;
        return variacion;
    }

    //el orden seria: obtener las reparaciones que tienen el tipo de reparacion x
    //de la lista de reparaciones obtenidas, verificar cuales tienen en mes solicitado
    //de la lista de reparaciones obtenidas, verificar cuales tienen en mes siguiente
    //de la lista de reparaciones obtenidas, verificar cuales tienen en mes anterior
    //obtener la cantidad de reparaciones realizadas en cada mes
    //obtener la variacion porcentual entre los meses

    public Reporte1 setReporte1(int messolicitado, String nombreReparacion){
        //obtengo las reparaciones con el nombre de reparacion solicitado
        List<Repair> repairs = getAllporNombreReparacion(nombreReparacion);
        //de la lista de reparacion solicitada, busco las que fueron hechas el mes solicitado
        Repair reparacionMesSolicitado = buscarREPAIRconMEssolicitado(repairs, messolicitado);
        //de la lista de reparacion solicitada, busco las que fueron hechas el mes siguiente
        Repair reparacionMesSiguiente = buscarRepairMesSiguiente(repairs, messolicitado);
        //de la lista de reparacion solicitada, busco las que fueron hechas el mes anterior
        Repair reparacionMesAnterior = buscarRepairMesAnterior(repairs, messolicitado);
        //saco la cantidad de reparaciones que fueron hechas en el mes solicitado
        int cantidadReparacionesMesSolicitado = cantidadReparacionesMesSolicitado(repairs, messolicitado);
        //saco la cantidad de reparaciones que fueron hechas en el mes siguiente
        int cantidadReparacionesMesSiguiente = cantidadReparacionesMesSiguiente(repairs, messolicitado);
        //saco la cantidad de reparaciones que fueron hechas en el mes anterior
        int cantidadReparacionesMesAnterior = cantidadReparacionesMesAnterior(repairs, messolicitado);

        //saco la variacion porcentual entre el mes solicitado y el mes siguiente
        double variacionPorcentualMesSiguiente = variacionPorcentual(cantidadReparacionesMesSolicitado, cantidadReparacionesMesSiguiente);
        //saco la variacion porcentual entre el mes siguiente y el mes solicitado
        double variacionPorcentualMesAnterior = variacionPorcentual(cantidadReparacionesMesAnterior, cantidadReparacionesMesSolicitado);

        Reporte1 reporte1 = new Reporte1();
        if(messolicitado == 12){
            reporte1.setMesAnterior(11);
            reporte1.setMesSiguiente(1);
        }
        if(messolicitado == 1){
            reporte1.setMesAnterior(12);
            reporte1.setMesSiguiente(2);
        }
        if(messolicitado > 1 && messolicitado < 12){
            reporte1.setMesAnterior(messolicitado - 1);
            reporte1.setMesSiguiente(messolicitado + 1);
        }
        reporte1.setVarianza1(variacionPorcentualMesAnterior);
        reporte1.setVarianza2(variacionPorcentualMesSiguiente);
        return reporte1;
    }




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
