package org.gseii.metier;

import java.util.List;

import org.gseii.entities.Sensor;

public interface SensorMetier {

	public Sensor addSensor(String nomSensor);
	
	public void deleteSensor(short idSensor);
	
	public List<Sensor> listSensor();
	
	//find one sensor
	public Sensor findSensor(short idSensor);
	
	//Save value from Raspberry
	public Sensor saveValue(short idSensor, double value);

	//find all values per day of of one sensor
	public List<Double> findValues(short idSensor);
	
	//find all values of min per week for one sensor
	public List<Double> findMins(short idSensor);
	
	//find all values of max per week for one sensor
	public List<Double> findMaxs(short idSensor);
	
	//find all values of average per week for one sensor
	public List<Double> findAvgs(short idSensor);
}
