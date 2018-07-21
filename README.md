# spring-cloud-stream-rabbit
Spring Cloud Stream samples demonstrating various message exchange patterns.  This project is based on the SCS samples built by Baeldung and modified to work with the Elmhurst release or later.

*Examples include:*

1. MessageConverter:  Sample processor app that takes a message and formats it for logging and sends to an output channel.  Uses the default SCS Processor interface.

2. MultipleOutputsService: Custom processor using non-default named channels to conditionally route messages depending on the value of the input.

3.  MultipleOutputsWithConditionsService:  Another custom processor that conditional routes but uses annotation-based header filters for declarative routing instead of programmatic checking of conditions.  Note that payloads cannot be checked conditionally using this version of SCS.

4.  RabbitController:  REST controller that accepts HTTP input and publishes a message to the SCS transport (in this case, Rabbit) using REST input.  Can also accept input from an input channel.

## Running the application

TODO:  This project should be split out into separate Spring Boot apps to allow for running each SCS app independently and used in SCDF.  Since this project was based off the Baeldung sample, it kept the same approach of containing all SCS examples.  As such, you may need to use STS to easily test and run each of the samples.

Start up Rabbit using Docker:

`docker-compose up`

Start up your app of interest by executing the jar.

`java -jar target\spring-cloud-stream-rabbit-2.0.3.RELEASE.jar`

Note:  You will need to update the pom.xml's `mainClass` value with the name of the sample you'd like to execute.

### Testing

You can use the RabbitMQ UI at `http:localhost:15672` to send test messages to the input channels.  You will need to find the name of the channel in the application.yml which will identify the exchange to which you should publish messages.  If the sample sends the result to an output channel, you can bind the matching exchange to a test queue to see the output message.

Once you are done testing: `docker-compose down`
