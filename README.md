# BARBER SHOP

###### Epicodus | Java Week Three Independent Project, 03.31.2017

##### By _**Chris Finney**_

## Description
Management software with dashboard for a hypothetical barber shop to allow employees to do the following:
* VIEW all barbers and each barber's clients;
* ADD a barber and add client to particular barber;
* UPDATE barber details and client details;
* DELETE barber if no longer employed and delete a client if they no longer visit the shop.


## Specifications

| Behavior                   | Input Example     | Output Example    |
| -------------------------- | -----------------:| -----------------:|



## Setup/Installation Requirements

* _Clone the repository_
* In PSQL:
  * CREATE DATABASE hair_salon;
  * \c hair_salon;
  * CREATE TABLE stylists (id serial PRIMARY KEY, name varchar, hire_date varchar, base_salary varchar, work_schedule varchar);
  * CREATE TABLE clients (id serial PRIMARY KEY, name varchar, appt_date varchar, cut_request varchar, stylist_id int);
* _In the cloned repository root directory, run the command 'gradle run'_
* _Open browser and go to localhost:4567_


### License

Copyright (c) 2017 **_Chris Finney_**

This software is licensed under the MIT license.
