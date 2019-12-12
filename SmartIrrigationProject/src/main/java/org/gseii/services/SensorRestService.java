package org.gseii.services;

import java.util.List;

import org.gseii.entities.Sensor;
import org.gseii.metier.SensorMetier;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class SensorRestService {

	@Autowired
	private SensorMetier sensorMetier;

	@RequestMapping(value = "/sensors", method = RequestMethod.POST)
	public Sensor addSensor(@RequestBody String nomSensor) {
		return sensorMetier.addSensor(nomSensor);
	}

	@DeleteMapping("/sensors/{idSensor}")
	public void deleteSensor(@PathVariable short idSensor) {
		sensorMetier.deleteSensor(idSensor);
	}

	@GetMapping("/sensors")
	public List<Sensor> listSensor() {
		return sensorMetier.listSensor();
	}

	@RequestMapping(value = "/sensors/{idSensor}", method = RequestMethod.GET)
	public Sensor findSensor(@PathVariable short idSensor) {
		return sensorMetier.findSensor(idSensor);
	}

	@PutMapping("/sensors/{idSensor}")
	public Sensor saveValue(@PathVariable short idSensor,@RequestBody double value) {
		return sensorMetier.saveValue(idSensor, value);
	}

	@GetMapping({"/sensors/values/{idSensor}"})
	public List<Double> findValues(@PathVariable short idSensor) {
		return sensorMetier.findValues(idSensor);
	}

	@GetMapping("/sensors/min/{idSensor}")
	public List<Double> findMins(@PathVariable short idSensor) {
		return sensorMetier.findMins(idSensor);
	}

	@GetMapping("/sensors/max/{idSensor}")
	public List<Double> findMaxs(@PathVariable short idSensor) {
		return sensorMetier.findMaxs(idSensor);
	}

	@GetMapping("/sensors/avg/{idSensor}")
	public List<Double> findAvgs(@PathVariable short idSensor) {
		return sensorMetier.findAvgs(idSensor);
	}

}
