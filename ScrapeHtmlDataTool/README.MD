1. Public VCS github link : https://github.com/bvjavagithub/ConsoleApplication.git

2. Java version : 1.7

3. Development tool: Eclipse

4. Maven is used as a dependency management tool.

5. Libraries used in the project are:
	a) Jsoup : To scrape html data from web page.
	b) Jettison: To create JSON output from Java object.
	c) Spring core framework : Dependency injection and loosely coupled classes.
	d) log4j : Logger functionality
	e) Junit : Unit test cases
	POM.xml describes all the above dependencies.

6. How to run the application :
	a) com.sainsbury.App is the entry point for the application. It is a Java class with main() method which starts the application flow.
	b) To scrape the html data for the given Sainsbury product link in assignment, no need to pass any arguments to main() method and directly
		run the App.java class
	c) To scrape the html data for any link, need to pass main() method argument as a input link and need to make sure the web page has same 
		structure with same elements name, class and id provided as the application looks for specific elements with specific class or tag name.
		
7. How to test unit tests:
	a) Junit tests are available under package src/test/java. com.sainsbury.AppTest.java has all the test cases.
	b) Junit tests are attached in maven test scope. While running mvn test or packaging the application using mvn install to create jar file, the 
		tests are executed. If there is failure in any test case, maven will not create the project jar file. The project jar file will create only
		if all the tests are successfully completed.
	c) To run the Junit test case separately, need to go Junit perspective in eclipse and select the test to run.	 	
	
8. Logging functionality:
	a) Log messages will be available on console as well as in a file stored on hard drive.
	b) To log messages in file, need to specify file location in log4j.properties file in src/resources folder.
	c) The final JSON output will be available on console as well as file at specified location in log4j.properties file.
	
9. Output of the application is available as a JSON array of products along with total unit price value.
   After successful run of the application, output will get printed on a console as well as in a log file defined in log4j properties file. 	