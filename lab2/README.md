# Lab 2 - Mocking dependencies (for unit testing)

É convenção acabar o nome dos testes unitários com 'Test' e os testes de integração com 'IT'.

-> mvn test e mvn install correm os testes unitários

-> mvn install failsafe:integration-test correm todos (os testes unitários e de integração)



Maven commands:

mvn

​		compile

​		test

​		package

​		[integration-test]

​		install

```bash
$ mvn install -DskipTests=true
$ mvn integraton-test
```

---



```bash
mvn exec:java -Dexec.mainClass="geocoding.MainGeocode"
```

