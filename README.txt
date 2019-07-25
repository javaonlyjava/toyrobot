This file is the instructions file that explains about how to build, run and test the Toy Robot application.

1. If you want to build the source files, then it can be done through your favourite IDE usin maven plugin.

2. First extract the given Toy_Robot_Whispir zip file into a folder. 

3. Then, import the project into your ide if you are building the project from the ide itself.

4. We can build the Toy Robot project from within eclipse IDE (if you have maven plugin) 
   by right clicking on Toy_Robot_Whispir project and then click on "Run As" --> "Maven install".
   This will create a Toy_Robot_Whispir-0.0.1-SNAPSHOT.jar file in the target folder. 
   
5. To run the toy robot application, can do it in two ways, either from the command line or through the ide itself.

6. To run from command line, go to the target folder and run: 
   java -jar Toy_Robot_Whispir-0.0.1-SNAPSHOT.jar "commands.txt"
   
   where "commands.txt" is an input file in the classpath that contains list of commands. It'd give an output of 
    0,1,NORTH
	0,0,WEST
    3,3,NORTH
    
 7. You can run the application from the ide itself. 
    It's a SpringBoot app, so, just go to the MainApp class and "Run As" Java Application. 
    Also, need to specify the program arguments as "commands.txt" in the run configurations within ide. 
    
 8. Also, there are few test data files supplied within src/test/resources folder, that captures 
    the different scenarios and expected outputs. 
  
 9. You can run the junit tests that have been created in src/test/java folder. 
    
 9. For design, I have used Spring Boot application and the Command Design Pattern. 
 
 10. I have made sure, I have tested the whole application and it's working end-to-end and covered all the constraints.  