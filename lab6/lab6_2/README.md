### LAB 6 Static Code analysis (with SonarQube)

##### Exercise 6.2

---

The project passed all conditions with:
 - 1 Bug (Reliability D)
 - 0 Vulnerabilities (Security A)
 - 1 Security Hotspot (0.0% reviewed, Security Review E)
 - 2h34min of Debt and 32 Code Smells (Maintainability A)
 - 72.0% of Coverage on 137 lines to cover
 - 10 unit tests
 - 0.0% of duplications on 283 lines
 - 0 duplicated blocks



A few sample issues:

​	*ISSUE*: Bug

​	*PROBLEM DESCRIPTION*: Creating a new Random object each time a random value is needed is inefficient and may produce numbers which are not random depending on the JDK 

​	*HOW TO SOLVE*: Create a single Random, then store, and reuse it.

---

​	*ISSUE*: Code Smell

​	PROBLEM DESCRIPTION*: JUnit5 is more tolerant regarding the visibilities of Test classes than JUnit4, which required everything to be public.*

​	HOW TO SOLVE*: Remove the public modifier. It is recommended to use the default package visibility, which improves readability of code.

---

​	*ISSUE*: Code Smell

​	*PROBLEM DESCRIPTION*: Unused parameters are misleading. Whatever the values passed to such parameters, the behavior will be the same.

​	*HOW TO SOLVE*: Remove this unused method parameter "subset".

