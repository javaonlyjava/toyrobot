/**
 * @author Nitin Aggarwal.
 * This coding test is developed by Nitin exclusively for Whispir on 22nd September 2018. 
 */

package au.com.whispir.app;


import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.beans.factory.annotation.Autowired;

import au.com.whispir.app.exception.RobotException;
import au.com.whispir.app.service.MakeRobotMoveService;
import org.springframework.boot.Banner;

@ComponentScan("au.com.whispir.app")
@SpringBootApplication
public class MainApp implements CommandLineRunner {


	@Autowired
	private MakeRobotMoveService makeRobotMoveService;
	
    public static void main(String[] args) throws Exception {
        SpringApplication app = new SpringApplication(MainApp.class);
        app.setBannerMode(Banner.Mode.OFF);
        app.run(args);
        
        
    }

      
    @Override
    public void run(String...args) throws RobotException {
    	makeRobotMoveService.makeAllTheMoves(args);
    }
    
    
}