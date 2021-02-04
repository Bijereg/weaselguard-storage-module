# WeaselGuard Storage Module

## 1. Configuration
Open file `src/main/resources/application.properties` and edit settings.

### 1.1 Database configuration
Credentials used in examples:
- Server address: localhost:5432
- DB name: weaselguard_storagedb
- Username: weaselguard_storage
- Password: 123456Qq

There is example for PostgreSQL DBMS:
```yaml
# Database settings
spring.datasource.platform=postgres
spring.datasource.driver-class-name=org.postgresql.Driver
spring.datasource.url=jdbc:postgresql://localhost:5432/weaselguard_storagedb
spring.datasource.username=weaselguard_storage
spring.datasource.password=123456Qq
spring.datasource.initialization-mode=always
spring.jpa.hibernate.ddl-auto=update
```

If you're using a DBMS other than PostgreSQL, you should download a driver for it.

### 1.2 Application configuration
In progress.



## 2. How to run

### 2.1 Running with mvnw

Run:
```shell
mvnw spring-boot:run
```
After that you have got the application running on http://localhost:8080.

### 2.2 Running with third-party server (Apache Tomcat)
First, you should build this module:
```shell
mvnw clean
mvnw package
```
Second command creates a .war file in `/target` directory.

... (work in progress) ...



## 3. Using WeaselGuard Storage Module

### 3.1 Model Structure
There are 3 models: Event, Incident and IncidentType.

#### 3.1.1 Event Structure:
| Field | Type | Description |
|-------|------|-------------|
| id | Long | Primary key |
| sourceAddress | String | IPv4 address of device which generated that event|
| sourceApplication | String | Application name (maybe also process id on a device OS or just a filename) of event source |
| protocol | String | Protocol by which event has been delivered into WeaselGuard Collection Module (syslog, SNMP, WGAgent, ...) |
| registrationDatetime | LocalDateTime | Date and time in format `yyyy-MM-dd HH:mm:ss` when event has been delivered into WeaselGuard Collection Module |
| priority | PRIORITY (enum) | Priority of event. Allowed values: `EMERG, ALERT, CRIT, ERR, WARNING, NOTICE, INFO, DEBUG, OTHER` |
| message | String | Message (explanation) of event |
| comment | String | Comment of security administrator (just for humans) |

#### 3.1.2 Incident Structure:
| Field | Type | Description |
|-------|------|-------------|
| id | Long | Primary key |
| registrationDatetime | LocalDateTime | Date and time in format `yyyy-MM-dd HH:mm:ss` when incident has been created |
| modifyingDatetime | LocalDateTime | Date and time in format `yyyy-MM-dd HH:mm:ss` of last incident modifying |
| responseDatetime | LocalDateTime | Date and time in format `yyyy-MM-dd HH:mm:ss` when security administrator respond on incident |
| author | String | Name of security administrator who created this incident or name of a correlation engine |
| accuracy | ACCURACY | Confidence level. Allowed values: `TRUE, ATTEMPT, DOUBT` |
| comment | String | Comment of security administrator (just for humans) |
| type | IncidentType | Class (type) of an incident. For example: `Malware`, `Unauthorized Access` |
| events | List\<Event\> | List of events which constructed this incident |

#### 3.1.3 IncidentType Structure:
| Field | Type | Description |
|-------|------|-------------|
| id | Long | Primary key |
| type | String | String representation of incident type. For example: `Malware`, `Unauthorized Access` |
| incidents | List\<Incident\> | List of incidents for current incident type |

### 3.2 API endpoints
... (work in progress) ...