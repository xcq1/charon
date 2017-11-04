# charon
Framework for testing eventually consistent solutions

## Approach

- **Goal**: Find out how much consistency loss due to BASE design actually becomes visible to the end user
- **Questions**: 
	- Does the user experience any more inconsistency with BASE than with ACID?
	- Does BASE improve latency of reponses compared to ACID?
	- Which percentage of users will actually be impaired by BASE?
	- How do the aforementioned qualities behave under different scales?
- **Metrics**:
	- Inconsistent responses from system
	- Latency of responses from system
	- Maximum time frame before system has reached a consistent state again
	- Analyze all of the above while changing the #requests/sec

## Modules

*NB* This is still WIP

- **API**: The definition of the REST interface between client and server. Currently written in Feign. The system is composed of a warehouse, cart, and account service that model some very basic web shop functionality.
- **Loadtester**: A loadtesting client that holds a volatile consistency model and can send arbitrarily many requests to the server. 
- **Server**: A general server implementation that allows for easy plugging of custom solutions
- **Implementations**
	- **impl-monolith-psql**: An ACID monolith that uses PostgreSQL to store its data.
	- **impl-monolith-mongo**: A BASE monolith that uses MongoDB to store its data.
	- **impl-micro-sync-psql**: A microservice implementation with an API gateway that uses PostgreSQL in each service and synchronous service communication.
	- **impl-micro-sync-mongo**: A microservice implementation with an API gateway that uses MongoDB in each service and synchronous service communication.
	- **impl-micro-rabbit-psql**: A microservice implementation with an API gateway that uses PostgreSQL in each service and a RabbitMQ message broker for inter-service communication.
	- **impl-micro-rabbit-mongo**: A microservice implementation with an API gateway that uses MongoDB in each service and a RabbitMQ message broker for inter-service communication.

## Results