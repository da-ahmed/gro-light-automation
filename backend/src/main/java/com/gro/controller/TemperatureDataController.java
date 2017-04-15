package com.gro.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.gro.model.RPiComponent;
import com.gro.model.RPiComponentNotFoundException;
import com.gro.model.TemperatureDTO;
import com.gro.model.TemperatureData;
import com.gro.repository.RPiComponentRepository;
import com.gro.repository.TemperatureDataRepository;

@RestController
@RequestMapping(value="/api/component")
public class TemperatureDataController {
    
    @Autowired
    private TemperatureDataRepository temperatureDataRepository;
    
    @Autowired
    private RPiComponentRepository rPiComponentRepository;
    
    @Value("${exception.rpi-component-not-found}")
    private String componentNotFoundException;
    
    
    @RequestMapping(value="/{id}/temperature", method=RequestMethod.GET)
    public Page<TemperatureData> getTemperatureDataPage(
            @PathVariable("id") Integer id,
            @PageableDefault(sort={"timestamp"}, page=0, size=20) Pageable pageable) {
        
        RPiComponent component = rPiComponentRepository.findOne(id);
        if(component == null)
            throw new RPiComponentNotFoundException(componentNotFoundException);
        return temperatureDataRepository.findAllByComponent(component, pageable);
    }
    
    
    
    @RequestMapping(value="/{id}/temperature", method=RequestMethod.POST)
    public void postTemperatureData(@PathVariable("id") Integer id,
                                    @RequestBody TemperatureData data) {
        RPiComponent component = rPiComponentRepository.findOne(id);
        if(component == null)
            throw new RPiComponentNotFoundException(componentNotFoundException);
        data.setComponent(component);
        temperatureDataRepository.save(data);
    }
    
    
    
    @RequestMapping(value="/{id}/temperature/monthlyAverage", method=RequestMethod.GET)
    public Page<TemperatureDTO> getTemperatureDataMonthlyAverage(
            @PathVariable("id") Integer id,
            @PageableDefault(sort={"timestamp"}, page=0, size=12) Pageable pageable) {
        
        RPiComponent component = rPiComponentRepository.findOne(id);
        if(component == null)
            throw new RPiComponentNotFoundException(componentNotFoundException);
        return temperatureDataRepository.findMonthlyAverageByComponent(component, pageable);
    }
    
    
    
    @RequestMapping(value="/{id}/temperature/dailyAverage", method=RequestMethod.GET)
    public Page<TemperatureDTO> getTemperatureDataDailyAverage(
            @PathVariable("id") Integer id,
            @PageableDefault(sort={"timestamp"}, page=0, size=30) Pageable pageable) {
        
        RPiComponent component = rPiComponentRepository.findOne(id);
        if(component == null)
            throw new RPiComponentNotFoundException(componentNotFoundException);
        return temperatureDataRepository.findDailyAverageByComponent(component, pageable);
    }
    
    
    
    @RequestMapping(value="/{id}/temperature/dailyHigh", method=RequestMethod.GET)
    public Page<TemperatureDTO> getTemperatureDataDailyHigh(
            @PathVariable("id") Integer id,
            @PageableDefault(sort={"timestamp"}, page=0, size=30) Pageable pageable) {
        
        RPiComponent component = rPiComponentRepository.findOne(id);
        if(component == null)
            throw new RPiComponentNotFoundException(componentNotFoundException);
        return temperatureDataRepository.findDailyHighByComponent(component, pageable);
    }
    
    
    
    @RequestMapping(value="/{id}/temperature/dailyLow", method=RequestMethod.GET)
    public Page<TemperatureDTO> getTemperatureDataDailyLow(
            @PathVariable("id") Integer id,
            @PageableDefault(sort={"timestamp"}, page=0, size=30) Pageable pageable) {
        
        RPiComponent component = rPiComponentRepository.findOne(id);
        if(component == null)
            throw new RPiComponentNotFoundException(componentNotFoundException);
        return temperatureDataRepository.findDailyLowByComponent(component, pageable);
        
    }
    
    
    
    @RequestMapping(value="/{id}/temperature/hourlyAverage", method=RequestMethod.GET)
    public Page<TemperatureDTO> getTemperatureDataHourlyAverage(
            @PathVariable("id") Integer id,
            @PageableDefault(sort={"timestamp"}, page=0, size=24) Pageable pageable) {
        
        RPiComponent component = rPiComponentRepository.findOne(id);
        if(component == null)
            throw new RPiComponentNotFoundException(componentNotFoundException);
        return temperatureDataRepository.findHourlyAverageByComponent(component, pageable);
    }
    
    
    
    @RequestMapping(value="/{id}/temperature/hourlyHigh", method=RequestMethod.GET)
    public Page<TemperatureDTO> getTemperatureDataHourlyHigh (
            @PathVariable("id") Integer id,
            @PageableDefault(sort={"timestamp"}, page=0, size=24) Pageable pageable) {
        
        RPiComponent component = rPiComponentRepository.findOne(id);
        if(component == null)
            throw new RPiComponentNotFoundException(componentNotFoundException);
        return temperatureDataRepository.findHourlyHighByComponent(component, pageable);
    }

    
    
    @RequestMapping(value="/{id}/temperature/hourlyLow", method=RequestMethod.GET)
    public Page<TemperatureDTO> getTemperatureDataHourlyLow(
            @PathVariable("id") Integer id,
            @PageableDefault(sort={"timestamp"}, page=0, size=24) Pageable pageable) {
        
        RPiComponent component = rPiComponentRepository.findOne(id);
        if(component == null)
            throw new RPiComponentNotFoundException(componentNotFoundException);
        return temperatureDataRepository.findHourlyLowByComponent(component, pageable);
        
    }
    
}
