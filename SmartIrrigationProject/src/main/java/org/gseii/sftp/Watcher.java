package org.gseii.sftp;

import java.io.File;
import java.io.IOException;
import java.nio.file.FileSystems;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardWatchEventKinds;
import java.nio.file.WatchEvent;
import java.nio.file.WatchKey;
import java.nio.file.WatchService;
import java.time.LocalDateTime;

import org.gseii.dao.SensorRepository;
import org.gseii.entities.Sensor;
import org.gseii.metier.SensorMetier;
import org.gseii.metier.SensorMetierImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

@Service
public class Watcher {

	public Watcher() {
		
	}
	
	public File getTheFileName(String PathOfTheFolder) {
		File folder=new File(PathOfTheFolder);
		File[] files=folder.listFiles();
		if(files[0].isFile()) {
			return files[0];
		}else {
			System.out.println("no file to show");
			return  null;
		}
	}
	
	public Path getPath(File f) {
		return f.toPath();
	}
	
	public Json ReadDataDronJsonFile(File TheFile) throws JsonParseException, JsonMappingException, IOException {
			ObjectMapper objectMapper = new ObjectMapper();
				 Json json =objectMapper.readValue(TheFile, Json.class);
				 return json;
	}
	
	@Autowired
	public SensorMetier s ;
	
	public void watch() {
		   try( WatchService watcher = FileSystems.getDefault().newWatchService()) {
	            // Creates a instance of WatchService.
	           
	            String chemin="C:\\Users\\NZT48\\Desktop\\hadak";
	            // Registers the logDir below with a watch service.
	            Path logDir = Paths.get(chemin);
	            logDir.register(watcher, StandardWatchEventKinds.ENTRY_CREATE);
	            
	            // Monitor the logDir at listen for change notification.
	            while (true) {
	            	//System.out.println("lllfl");
	                WatchKey key = watcher.take();
	                Thread.sleep(50);
	                
	                for (WatchEvent<?> event : key.pollEvents()) {
	                    WatchEvent.Kind<?> kind = event.kind();
	                    if ( StandardWatchEventKinds.ENTRY_CREATE.equals(kind)) {
	                    	Json j=this.ReadDataDronJsonFile(this.getTheFileName(chemin));
	                    	System.out.println(j.toString());

	                    	s.saveValue(j.getId(), j.getValue());
	                    	 Thread.sleep(500);
	                    	 MoveToHistory.move(this.getPath(this.getTheFileName(chemin)));
	                    }
	                }
	                key.reset();
	            }
	        } catch (IOException | InterruptedException e) {
	            e.printStackTrace();
	        }
	}
}
