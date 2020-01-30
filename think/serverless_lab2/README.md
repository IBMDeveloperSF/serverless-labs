# IBM Cloud Functions and Apache OpenWhisk - Lab 2

## Introduction

This lab walks you through the steps required to create, build, and run a Serverless application using IBM Cloud Functions. Serverless computing refers to a model where the existence of servers is entirely abstracted away. Even though servers exist, developers are relieved from the need to care about their operation. They are relieved from the need to worry about low-level infrastructural and operational details such as scalability, high-availability, infrastructure-security, and other details. Serverless computing is essentially about reducing maintenance efforts to allow developers to quickly focus on developing code that adds value.

Serverless computing simplifies developing cloud-native applications, especially microservice-oriented solutions that decompose complex applications into small and independent modules that can be easily exchanged. Some promising solutions like Apache OpenWhisk have recently emerged that ease development approaches used in the serverless model. IBM Cloud Functions is a Function-as-a-Service (FaaS) platform on IBM Cloud, built using the Apache OpenWhisk open source project, that allows you to execute code in response to an event.
It provides a serverless deployment and operations model. With a granular pricing model that works at any scale, you get exactly the resources you need – not more, not less – and you are charged for code that is really running.

IBM Cloud Functions provides:

- Support for multiple languages, including JavaScript, Python, Swift, and Java
- Support for running custom logic through Docker containers
- The ability to focus more on value-adding business logic, and less on low-level infrastructural and operational details.
- The ability to easily chain together microservices to form workflows via composition.

## Prerequisites

You will need an IBM Cloud Account. Either use your existing account, or create a new account by using the link provided in the workshop.

## Lab steps

In this lab, you’ll create an IBM Cloud Functions action that takes an image URL as input, and returns some tags describing the content of the image. To get the tags, the action will interact with the Visual Recognition Service on IBM Cloud. Follow the instructions in [this pdf file](quicklab2-ibmdevelopersf.pdf) to complete this lab.

[Lab PDF](quicklab2-ibmdevelopersf.pdf) 

## Command line labs

If you want to use the `ibmcloud cli` and create actions from the command line, you can follow the following labs

1. [Setting up the environment.](https://github.com/IBM-Cloud/openwhisk-workshops/tree/master/bootcamp/ex0%20-%20setting%20up%20development%20environment)
2. [Bundling NPM modules](https://github.com/IBM-Cloud/openwhisk-workshops/tree/master/bootcamp/ex1.3%20-%20bundling%20NPM%20modules)
