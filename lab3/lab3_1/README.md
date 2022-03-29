# Lab 3	Multi-layer application testing (with Spring Boot)   

## 3.1 Employee manager example

### a)

```java
- assertThat( found ).isEqualTo(alex);

- assertThat(allEmployees).hasSize(3).extracting(Employee::getName).containsOnly(alex.getName(), ron.getName(), bob.getName());
 
- assertThat(found).extracting(Employee::getName).containsOnly("bob");

```



### b)

```java
@Mock(lenient = true)
private EmployeeRepository employeeRepository;

@BeforeEach
public void setUp() {

    //these expectations provide an alternative to the use of the repository
    Employee john = new Employee("john", "john@deti.com");
    john.setId(111L);

    Employee bob = new Employee("bob", "bob@deti.com");
    Employee alex = new Employee("alex", "alex@deti.com");

    List<Employee> allEmployees = Arrays.asList(john, bob, alex);

    Mockito.when(employeeRepository.findByName(john.getName())).thenReturn(john);
    Mockito.when(employeeRepository.findByName(alex.getName())).thenReturn(alex);
    Mockito.when(employeeRepository.findByName("wrong_name")).thenReturn(null);
    Mockito.when(employeeRepository.findById(john.getId())).thenReturn(Optional.of(john));
    Mockito.when(employeeRepository.findAll()).thenReturn(allEmployees);
    Mockito.when(employeeRepository.findById(-99L)).thenReturn(Optional.empty());
}

```



### c) 

[What is the difference between Mock and MockBean?]: https://faq-all.com/en/Q%26A/page=d3ec1206f07b6be5ff73787c264b83d0#s0

Whereas the @Mock annotation is used for unit tests with JUnit and Mockito, @MockBean is used to write a test related to the Spring Boot Context, this way it can add mock objects to be used instead of beans (mock version).



### d) 

Just like in the "normal" application.properties we place properties that allow us to run the application in a different environmente, the application-integrationtest.properties allows us to test a function or class in a different environment (considering  a database, for example).



### e)

The three test strategies demonstrate different ways to access an API through Spring Boot. 

​	The first strategy uses the WebMvcTest annotation. In this case we're using MockMVC which allows us to run the tests in a simplified and light environment by starting a servlet container, this way the application context will only contain the beans needed for the testing of the Controller. By doing this, we can check if the Controller methods are being called and responded the right way. Also,  we're mocking the service with the MockBean annotation that replaces the bean of the Service in the application context with a Mockito mock.

​	In the second strategy, the SpringBootTest annotation is being used to create the application context used for the tests. MockMVC is still being used but we're no longer mocking the service, instead we're mocking the repository. Since we don't want to test eveything, just a specific part of the application, we can replace certain beans in the application context with the MockBean annotation as I have mentioned before. In this case, we want to specify the behavior of the the Repository mock in order to test the class that uses it. We are running the tests against a real database (AutoConfigureTestDatabase annotation).

​	The last strategy also uses the SpringBootTest annotation and the AutoConfigureTestDatabase. But in this case we aren't using the MockMvc, this time both the restTemplate and the repository are autowired. The TestRestTemplate simplifies  integration testing and facilitates the authentication during tests.