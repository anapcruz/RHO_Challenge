# RHO Challenge

### Description
Build a serive that will provide us all the necessary tools to be able to flat to a Business Ops when a customer, playing a game, stakes more than £n (n pounds) in a given interval x, which we refer to as a time window.
If a player goes over the threshold in the interval x, where x is the time window, a notification should be generated. That is, for each new stake the player creates (at instant t), the system must check if in t - time_window there were stakes that, in total, go over the threshold.

##### Specifications
Threshold: £100
Time window: 60s
Source messages can use a format like this: {account:123, stake:4}

### The application
This application consists of a webServer developed with [springBoot] and it uses two databases:
- PostgreSQL - database used to store all information related to notification
- H2 - database used only for tests

### Prerequisites
* [Maven]
* [Docker]
* [Docker-compose]

### How to run it?
Clone the repository to your local machine
```sh
$ git clone https://github.com/anapcruz/Challenge_RHO.git
```

An entire application can be ran with a single command in a terminal:
```sh
$ docker-compose up
```

If you want to stop it use following command:
```sh
$ docker-compose down
```

Run tests with the following command:

```sh
$ cd sportsbook/
$ mvn test
```

License
----

MIT


[//]: #
    
   [springBoot]: <https://spring.io/projects/spring-boot>
   [Maven]: <https://maven.apache.org/download.cgi>
   [Docker]: <https://www.docker.com/get-started>
   [Docker-compose]: <https://docs.docker.com/compose/install/>
