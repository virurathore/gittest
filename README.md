# RetailManager
This is retailmanager rest service, which provide 2 API to expose 
1. get all near by shops based on longitude and latitude of customer
2. shop manager add shop to retailmanager service.


#How to build and execute
It's usages Maven to build and packaging.
use following commands
1. to build package for production.
``` mvn clean package ```  
2. run service 
``` java -jar target/rest-0.0.1-SNAPSHOT.jar ```

#Test using postman collection 
	use retailmanager postman collection added in github.
	
#Technical designs 
-This usages spring-boot to build rest service in MVC pattern. RestController will take care view of external user and interacting with bussiness service.
-Bussiness Service implements logic in terms of domain, which can further a common library or service based on project/domain. we have created a service class which interact with google geocode API and repository service(data layer).
-Data layer has implemented as component as we have small operation, so not fully complient with DTO/DAO pattern.
-Most of service/components has written as interface programming.

#Production Ready
we list technical items to takne care for prodction ready
1. Transactional semantics : This require for operation to be complient with commit/rollback/failover instead of putting data in half updated in system.
2. Caching : This require to handle based on LRU(least recently used) data should be cache as 20% of data reused 80% of time, so if we cache data, we don't require to refatch data from down layer.
3. Authantication : This require to handle legitimate user has access of service.
4. Monitoring : This require to build matrix to get to know performance of system, usages of system, bussiness value of system.
5. Error Handling : handle error and exception cases to provide logical HTTP status code and message to user of API.
6. Capacity and Performance : workout on bussiness case about call per day and service response time.
7. Unit Teting and functional testing : which should cover all kind scanrio to handle error and exceptions.	

	
