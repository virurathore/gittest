# RetailManager
This is retail manager rest service, which provide 2 API to expose 
* Get all nearby shops based on longitude and latitude of customer.
* Shop manager add shop to retail manager service.


#How to build and execute
Its usages Maven/gradle to build and packaging.
use following commands
* to build package for production.
``` mvn clean package ```  
``` gradle build ```  
* run service 
``` java -jar target/rest-0.0.1-SNAPSHOT.jar ```

#Test using postman collection 
	use retailmanager postman collection added in github.
	You can test service from cloud [click here](http://viru-retailmanager.cfapps.io/)

#Build Reports 
Project have build reports. These reports can access from build/reports/buildDashboard/index.html. These reports generated when you use gradle as build tool and run command ``` gradle build ```
* Build Reports to get dependency
* Test report to get result of unit-test run
* checkstyle report based on google check style
* pmd report for static analysis of code
	
#Technical designs 
* This uses spring-boot to build rest service in MVC pattern. Rest Controller will interact to external user and business service.
* Business Service implements logic in terms of domain, which can further a common library or service based on project/domain. We have created a service class which interacts with google geocode API and repository service (data layer).
* Data layer has implemented as component as we have small operation, so not fully complaint with DTO/DAO pattern.
* Most of service/components have written as interface programming.

#Production Ready
We list technical items to taken care for production ready
* Need to use profile based configuration(dev/qa/production), which help configure information like google API key used to access geocode data, or using DB connection or different rest-service(google geo code).
* Pagination required for search API, there might be long list of result-set. 
* Production build should have added Cobertura Code Coverage, Findbugs Reports.
* Transactional semantics: This require for operation to be complaint with commit/rollback/failover instead of putting data in half updated in system.
* Caching : This require to handle based on MRU(most recently used) data should be cache as 20% of data reused 80% of time, so if we cache data, we don't require to fetch data from down layer.
* Authentication: This requires handling legitimate user has access of service.
* Monitoring: This is building matrix which will provide insights like performance of system, usages of system, business value of system..
* Error Handling : handle error and exception cases to provide logical HTTP status code and message to user of API.
* Capacity and Performance : workout on business case about call per day and service response time.
* Unit testing and functional testing: This should cover all kind scenarios to handle error and exceptions.	

#TasksList
* [ ] use DB
* [ ] accutator for monitoring of service
* [ ] update comments and documentaion. 
	
