# spring-cloud-microservice
Spring cloud microservices samples

### Applications:
1) config-service: This application is a config server which is backed by GIT.
2) eureka-server:    Service Discovery
3) example-service:  Microservice 1 (example for config server and spring cloud bus)
4) example-service2: Microservice 2 (example for config server and spring cloud bus)
5) example-service3: Microservice 3 (example for Eureka server)
6) example-service4: Microservice 4 (example for Feign and Circuit breaker)
7) example-service5: Microservice 5 (Microservice which is used for translating word from English to French)
8) api-gateway: API Gateway (example for Spring Cloud Gateway)

### Description

#### Spring Cloud config server testing

**config-service**: This application is a config server which is backed by GIT.
It connects to https://github.com/jgpreetham/config/tree/master/config-data to retrieve the configurations for all the microservices
The configuration yaml file name will be same as the microservice name (specified in application.yml of microservice)

**example-service**: This is microservice 1, which connects to config-server and retrieves the configurations of port number and value of someProperty
* Test it by hitting : http://localhost:8111/value  -> this returns the value retrieved from config-server ( specified in example-service.yml file config.someProperty)

**example-service2**: This is microservice 2, which connects to config-server and retrieves the configurations of port number and value of someProperty
* Test it by hitting : http://localhost:8112/value  -> this returns the value retrieved from config-server ( specified in example-service2.yml file config.someProperty)

*Now update the properties in example-service and example-service2 in GIT*

 * Click on http://localhost:8111/refresh which will refresh the example-service ConfigProperties.java bean because this bean is annotated with @RefreshScope.
 
*Now the example-service's someProperty would have the latest value. Test it by again hitting : http://localhost:8111/value*

But example-service2 wouldn't have the latest property values. Test it by hitting : http://localhost:8112/value

In real world scenario there would be 100-1000 microservices and it's not practical to go and hit /refresh for all the services. So we would use spring-cloud-bus with amqp

####  Points to be noted
Pre-requsite: Docker should be installed
Download RabbitMQ docker image: ```docker pull rabbitmq ```

Start the Rabbit MQ by running the following command:
```
 docker run -d --hostname my-rabbit --name some-rabbit -p 15762:15672 -p 5672:5672 rabbitmq:3-management
```
Test RabbitMQ by hitting URL http://localhost:15762/#/ . 
```
Username: guest
Password: guest
```
#### Spring cloud Bus testing for dynamic updates
Now again restart your config server, example-service and example-service2. 
* Test the value of example-service:http://localhost:8111/value
* Test the value of example-service2:http://localhost:8112/value 
* Update the properties and hit : http://localhost:8888/bus/refresh

Now again, test the example-service and example-service2
* Test the value of example-service:http://localhost:8111/value
* Test the value of example-service2:http://localhost:8112/value

*By hitting /refresh endpoint, all the microservices would be notified about the changes in GIT*

#### Eureka Server testing
**eureka-server**: Service Discovery

Run exmaple-service3, exmaple-service4 and exmaple-service5, api-gateway and notice that all the services are shown on eureka portal : http://localhost:8761/
 
#### Feign testing 
**example-service4** uses Feign annotations to interact with **example-service5**
* By hitting http://localhost:8114/hello, feign would call example-service5 @GetMapping(value = "/translate/{word}").
 
 #### Client Side load balancing testing
 * Start 2 profiles(default and test) of example-service5. Hit http://localhost:8114/hello, feign would call example-service5 @GetMapping(value = "/translate/{word}") of different instances(default and test) automatically and you can verify by looking at the port number returned in the response.
 * 
#### Circuit Breaker testing
 * Test circuit breaker by stopping example-service5 and hitting example-service4 http://localhost:8114/hello , this would go to fallback and give the result

#### API Gateway testing
* Start api-gateway service and hit http://localhost:8763/api-gateway/translate-service/hello which internally strips of the /api-gateway/translate-service/ and rewrites it as /example-service5/ and internally talks to eureka and goes to example-service5/hello to get the correct response and if you are running multiple instances of example-service5, it will load balance and you can verify by looking at the port number in the response.

