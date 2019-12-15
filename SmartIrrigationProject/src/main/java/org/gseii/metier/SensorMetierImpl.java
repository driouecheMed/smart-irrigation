package org.gseii.metier;

import java.util.List;

import org.gseii.dao.SensorRepository;
import org.gseii.entities.Sensor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SensorMetierImpl implements SensorMetier {

	@Autowired
	private SensorRepository sensorRepository;

	@Override
	public Sensor addSensor(String nomSensor) {
		Sensor s = new Sensor(nomSensor);
		return sensorRepository.save(s);
	}

	@Override
	public void deleteSensor(short idSensor) {
		sensorRepository.deleteById(idSensor);
	}

	@Override
	public List<Sensor> listSensor() {
		return sensorRepository.findAll();
	}

	@Override
	public Sensor findSensor(short idSensor) {
		return sensorRepository.findById(idSensor).orElse(null);
	}

	@Override
	public Sensor saveValue(short idSensor, double value) {
		return sensorRepository.findById(idSensor).map(s -> {

			// Changing the value of min & max and avg of the day

			int day = s.getActualDay();
			int hour = s.getActualHour();
			if (hour == 7) {
				double max = value;
				double min = value;
				double sum = value;
				for (int i = 0; i < 7; i++) {
					sum += s.getValuesOfDay().get(i);
					if (s.getValuesOfDay().get(i) > max) {
						max = s.getValuesOfDay().get(i);
					}
					if (s.getValuesOfDay().get(i) < min) {
						min = s.getValuesOfDay().get(i);
					}
				}
				s.getMinOfWeek().removeFirst();
				s.getMinOfWeek().add(min);
				s.getMaxOfWeek().removeFirst();
				s.getMaxOfWeek().add(max);
				s.getAvgOfWeek().removeFirst();
				s.getAvgOfWeek().add(sum / 8);
			}
			
			if(hour==0) {
				for(int i = 1;i<8;i++) {
					s.setValueOfHour(0.0, i);
				}
			}
			
			s.setValueOfHour(value, hour);
			// Increment the hour & day
			hour = (hour + 1) % 8;
			s.setActualHour(hour);
			if (hour == 0) {
				// new day
				day = (day + 1) % 7;
				s.setActualDay(day);
			}
			return sensorRepository.save(s);
		}).orElseGet(() -> {
			return null;
		});
	}

	@Override
	public List<Double> findValues(short idSensor) {
		return sensorRepository.findById(idSensor).orElse(null).getValuesOfDay();
	}

	@Override
	public List<Double> findMins(short idSensor) {
		return sensorRepository.findById(idSensor).orElse(null).getMinOfWeek();
	}

	@Override
	public List<Double> findMaxs(short idSensor) {
		return sensorRepository.findById(idSensor).orElse(null).getMaxOfWeek();
	}

	@Override
	public List<Double> findAvgs(short idSensor) {
		return sensorRepository.findById(idSensor).orElse(null).getAvgOfWeek();
	}

}
