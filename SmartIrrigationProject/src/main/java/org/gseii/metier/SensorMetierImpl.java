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
			s.setValueOfHour(value, s.getActualHour());
			// Changing the value of min & max and avg of the day

			List<Double> values = s.getValuesOfDay();
			int day = s.getActualDay();
			int hour = s.getActualHour();

			double max = s.getMaxOfDay(day);
			double min = s.getMinOfDay(day);
			double sum = 0;

			if (hour != 0) {
				for (int i = 0; i < hour; i++) {
					sum += values.get(i);
					if (values.get(i) > max) {
						max = values.get(i);
					}
					if (values.get(i) < min) {
						min = values.get(i);
					}
				}
			} else {
				min = value;
				max = value;
			}

			s.setAvgOfDay((sum / hour), day);
			s.setMinOfDay(min, day);
			s.setMaxOfDay(max, day);

			// Increment the hour & day
			hour = (hour + 1) % 24;
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
