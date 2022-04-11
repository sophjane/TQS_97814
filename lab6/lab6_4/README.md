### LAB 6 Static Code analysis (with SonarQube)

##### Exercise 6.4

---

![](/home/sophie/Pictures/Screenshot from 2022-04-11 23-08-44.png)

I realize that these conditions are quite restrictive, however I made these choices a little based on the fact that it's important for projects to not have bugs in order to be more robust and, in a perfect scenario, they also should not have vulnerabilities. A small amount of code smells is also good as they might contain bigger underlying issues and make the system less reliable. Finally a little bit of duplicated code is possible but that is also something to be cautious, the code must be clean and concise, a lot of duplicated code not only is that for the programmer but for the project itself. Coverage is also very important as the code should be tested as much as possible.



Tried adding more code smells (private fields public, unnecessary and redudant variables, etc) and a vulnerability using a vulnerable version of log4j (not detected by Sonarqube).

![](/home/sophie/Pictures/Screenshot from 2022-04-11 23-08-11.png)

