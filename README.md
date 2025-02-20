##Starting a new spring boot project

1. Go to start.spring.io for spring initializr
2. select setting
    Project maven
    Language - Java
    Spring boot - 2.2.6
3. Add dependencies 
    Thymeleaf
    h2 database
    spring-boot-starter-web
    spring-boot-starter-data-jpa
    
Download generated framework and open with IDE (made for IntelliJ IDEA)
    
    
4. Add test template 
in resources/templates/hellopage.html
```html
<html>

<title>
test page
</title>
	<header>
		<h1/>
		Hello World!
		</h1>
	</header>
</html>
```

5. Add controller

src/main/java/com/example/bergmanis/controllers/SimpleController.java

```java
package com.bergmanis.SimpleApplication.controllers;
   
   import org.springframework.stereotype.Controller;
   import org.springframework.web.bind.annotation.GetMapping;
   @Controller
   public class SimpleController {
       @GetMapping("/hello") // endpoint for localhost:8080
       public String showHelloPage() {
           System.out.println("showHelloPage");
           return "hellopage";// this should show html "Hello my dude"
       }
   }
```

Run SimpleApplication
And open browser
And navigate to localhost:8080/hello
Then 'Hello World' page is visible


check h2 database with
```java
http://localhost:8080/h2-console/
```

##MVC model

Controller classes require @Controller tag

Service classes require @Service tag

Models use tags @Entity 
additionally for data tables @Table


##Mapping 

One to One

One to Many

Many to Many

##Lombok

