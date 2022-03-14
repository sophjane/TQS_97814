## TQS: Lab 1

### 1.1 

```bash
mvn archetype:generate -DgroupId=tqsstack -DartifactId=tqs-stack -DarchetypeArtifactId=maven-archetype-quickstart -DarchetypeVersion=1.4 -DinteractiveMode=false
```

1.2

#### 		JaCoCo - Java Code Coverage

​			Code coverage is a software metric used to measure how many lines of our code are executes during automated tests.

​			Running tests using JUnit will automatically set in motion the JaCoCo agent. It will create a coverage report in binary format in the target directory, target/jacoco.exec. We can use the jacoco:report goal in order to generate readable code coverage reports in several formats.

​			JaCoCo reports help us visually analyze code coverage by using diamonds with colors for branches, and backgrounds colors for lines:

- Red diamond - no branches have been exercised during the test phase
- Yellow diamond - the code is partially covered (some branches have not been exercised)
- Green diamond - all branches have been exercised during the test

​			The same color applies to the background color, but for lines coverage.

​			JaCoCo mainly provides three important metrics:

- Lines coverage - the amount of code that has been exercised based on the number of Java byte code instructions called by the tests.
- Branches coverage - the percent of exercised branches in the code, typically related to if/else and switch statements.
- Cyclomatic complexity - the complexity of code by giving the number of paths needed to cover all the possible paths in a code through linear combination. Generally, the cyclomatic complexity reflects the number of test cases we need to implement in order to cover the entire code.

JaCoCo runs as a Java agent. It's responsible for instrumenting the byte code while running the tests. JaCoCo drills into each instruction, and shows which lines are exercised during each test.

​		![](/home/sophie/Pictures/Screenshot from 2022-03-14 11-47-27.png)
