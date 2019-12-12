package org.gseii.sftp;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Date;

public class MoveToHistory {

static public Boolean move(Path p) {
		Date d = new Date(); 
		String s = d.toString().replace(" ","_") ;
		s = s.replace(":","-") ;
		try {
			Files.move(p, Paths.get("C:\\Users\\NZT48\\Desktop\\historique\\"+s+".txt")) ;
			//Files.delete(p);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return true;
	}

}
