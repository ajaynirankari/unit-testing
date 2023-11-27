# unit-testing


Creating a Maven Project 
-------------------------

mvn archetype:generate -DgroupId=com.gl -DartifactId=junit-testing -DarchetypeArtifactId=maven-archetype-quickstart -DarchetypeVersion=1.4 -DinteractiveMode=false


How to run unit test with Maven
-------------------------------
```
# Run all the unit test classes.
$ mvn test

# Run a single test class.
$ mvn -Dtest=CalculatorTest test

# Run multiple test classes.
$ mvn -Dtest=CalculatorTest,AppTest test

# Run a single test method from a test class.
$ mvn -Dtest=CalculatorTest#addTestMethod test

# Run all test methods that match pattern 'testHello*' from a test class.
$ mvn -Dtest=CalculatorTest#testHello* test

# Run all test methods match pattern 'testHello*' and 'testMagic*' from a test class.
$ mvn -Dtest=CalculatorTest#testHello*+testMagic* test
```


Maven Reference
----------------
https://maven.apache.org/guides/getting-started/maven-in-five-minutes.html



Maven Getting Started Guide
--------------------------
https://maven.apache.org/guides/getting-started/index.html



JUnit 5 User Guide
-----------------
https://junit.org/junit5/docs/current/user-guide/



Writing Unit Test
-----------------
https://junit.org/junit5/docs/current/user-guide/#writing-tests




