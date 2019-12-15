package org.gseii.entities;

import java.io.Serializable;
import java.util.LinkedList;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Lob;

import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;

@Entity
public class Sensor implements Serializable {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private short id;
	private String nomSensor;
	@Column(length = 2)
	private int actualHour;
	@Column(length = 1)
	private int actualDay;
	@Lob
	@LazyCollection(LazyCollectionOption.FALSE)
	private LinkedList<Double> valuesOfDay = new LinkedList<Double>();
	@LazyCollection(LazyCollectionOption.FALSE)
	private LinkedList<Double> minOfWeek = new LinkedList<Double>();
	@LazyCollection(LazyCollectionOption.FALSE)
	private LinkedList<Double> maxOfWeek = new LinkedList<Double>();
	@LazyCollection(LazyCollectionOption.FALSE)
	private LinkedList<Double> avgOfWeek = new LinkedList<Double>();

	public Sensor() {
		super();
	}

	public Sensor(String nomSensor) {
		super();
		this.nomSensor = nomSensor;
		this.valuesOfDay.add(0.0);
		for(int i = 0;i<7;i++) {
			this.valuesOfDay.add(0.0);
			this.minOfWeek.add(0.0);
			this.maxOfWeek.add(0.0);
			this.avgOfWeek.add(0.0);
		}
		this.actualHour = 0;
		this.actualDay = 0;
	}

	public short getId() {
		return id;
	}

	public void setId(short id) {
		this.id = id;
	}

	public String getNomSensor() {
		return nomSensor;
	}

	public void setNomSensor(String nomSensor) {
		this.nomSensor = nomSensor;
	}

	public int getActualHour() {
		return actualHour;
	}

	public void setActualHour(int actualHour) {
		this.actualHour = actualHour;
	}

	public int getActualDay() {
		return actualDay;
	}

	public void setActualDay(int actualDay) {
		this.actualDay = actualDay;
	}

	public LinkedList<Double> getValuesOfDay() {
		return valuesOfDay;
	}

	public void setValuesOfDay(LinkedList<Double> valuesOfDay) {
		this.valuesOfDay = valuesOfDay;
	}

	public void setValueOfHour(double value, int hour) {
		this.valuesOfDay.set(hour, value);
	}

	public LinkedList<Double> getMinOfWeek() {
		return minOfWeek;
	}

	public double getMinOfDay(int day) {
		return minOfWeek.get(day);
	}

	public void setMinOfWeek(LinkedList<Double> minOfWeek) {
		this.minOfWeek = minOfWeek;
	}

	public void setMinOfDay(double min, int day) {
		this.minOfWeek.set(day, min);
	}

	public LinkedList<Double> getMaxOfWeek() {
		return maxOfWeek;
	}

	public double getMaxOfDay(int day) {
		return maxOfWeek.get(day);
	}

	public void setMaxOfWeek(LinkedList<Double> maxOfWeek) {
		this.maxOfWeek = maxOfWeek;
	}

	public void setMaxOfDay(double max, int day) {
		this.maxOfWeek.set(day, max);
	}

	public LinkedList<Double> getAvgOfWeek() {
		return avgOfWeek;
	}

	public double getAvgOfDay(int day) {
		return avgOfWeek.get(day);
	}

	public void setAvgOfWeek(LinkedList<Double> avgOfWeek) {
		this.avgOfWeek = avgOfWeek;
	}

	public void setAvgOfDay(double avg, int day) {
		this.avgOfWeek.set(day, avg);
	}
}
