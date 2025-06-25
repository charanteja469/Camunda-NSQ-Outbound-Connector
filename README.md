# Camunda 8 NSQ Custom Outbound Connector

Camunda 8 NSQ Outbound Connector is a custom-built connector (developed using the Camunda Connector SDK, typically in Java) that seamlessly integrates Camunda 8 process automation with NSQ. It allows Camunda workflows to publish messages to NSQ topics as part of a service task, enabling asynchronous communication and decoupling of processes. Developers can easily configure message content and NSQ topic details directly within their BPMN models.

![image](https://github.com/user-attachments/assets/f949a318-f36e-46ca-a8f9-344b3c30ca9b)

### NSQ

NSQ is an open-source, real-time, distributed messaging platform designed for high-throughput, fault-tolerant message delivery at scale. It acts as a lightweight, decentralized message queue system, enabling asynchronous communication between different components of a distributed application.

1. NSQ is a distributed, real-time messaging platform designed for high-throughput and fault tolerance.

2. It's decentralized, eliminating single points of failure through its nsqd, nsqlookupd, and nsqadmin components.

3. NSQ offers horizontal scalability, allowing easy addition of nodes to handle increasing message loads.

4. It provides low-latency, push-based message delivery and is agnostic to message data formats.

5. NSQ guarantees "at-least-once" message delivery, requiring consumers to be idempotent to handle potential duplicates.

you can download and host NSQ from here   👉🏻 https://nsq.io/deployment/installing.html

after downloding NSQ follow below commands to run

1. Terminal 1:   nsqlookupd.exe
2. Terminal 2:   nsqd --lookupd-tcp-address=127.0.0.1:4160
3. Terminal 3:   nsqadmin --lookupd-http-address=127.0.0.1:4161


## Test with SaaS and Self-Managed

#### Setting Up the Saas Environment:

1. Navigate to Camunda [SaaS](https://console.camunda.io).
2. Create a cluster using the latest version available.
3. Select your cluster, then go to the `API` section and click `Create new Client`.
4. Ensure the `zeebe` checkbox is selected, then click `Create`.
5. Copy the configuration details displayed under the `Spring Boot` tab.
6. Paste the copied configuration into your `application.properties` file within your project.

#### Setting Up the Self-Managed Environment:

1. Set up the Camunda 8 Self-Managed(https://docs.camunda.io/docs/self-managed/setup/deploy/local/docker-compose/).
2. Cluster endpoint is http://localhost:26500
3. uncomment the properties in test resource folder

   (camunda.client.zeebe.grpc-address=http://localhost:26500)
   
   (camunda.client.zeebe.rest-address=http://localhost:8088)
5. download desktop modeler if requires (https://camunda.com/download/modeler/)


### Launching Your Connector

1. Start your connector by executing `io.camunda.NSQ.LocalConnectorRuntime` in your development environment.
2. Access the Web Modeler or Desktop Modeler and create a new project.
3. Click on `Create new`, then select `Upload files`. Upload the connector template from the repository you have(https://github.com/charanteja469/Camunda-NSQ-Outbound-Connector/blob/master/element-templates/NSQ%20Outbound%20Connector.json)

 NOTE: if your using Desktop modeler--> go to modeler folder-->resources-->element-templates-->Past the above downloaded NSQ  
       Connector Template

4. In the same folder, create a new BPMN diagram.
5. Design and start a process that incorporates your new connector.

# STEP BY STEP Process to Configure and Use NSQ Outbound Connector

1. Create a workflow with Start event, Task, End Event
2. select the task and click on element change type and search for NSQ Outbound Connector

![image](https://github.com/user-attachments/assets/84c35057-d3c4-4c1d-b12a-9d1377e4c151)


3. Configure the Input like (Connection, Topic, Message)

   ![image](https://github.com/user-attachments/assets/caae5677-e5a1-4004-9ca3-692cbd833226)

   #### Input :

    NSQDAddress : localhost:4150
   
    Topic : Order
   
    Message : your order delivered successfully...

4. Configure the output Result Expression

   ![image](https://github.com/user-attachments/assets/12886372-f8ac-440b-a06b-b04dce4a8928)

   #### Result Expression :

   {"Status":result}

5. Deploy the process and Start the Process
6. Start the Connector Runtime

 ![image](https://github.com/user-attachments/assets/e7f9576e-860d-4ffa-b581-89f77bb771b2)

 ![image](https://github.com/user-attachments/assets/2e7f124a-a67a-43e7-be87-45195b9f3f2b)

 7. NSQ Outbound Connector successfully published the message.
    
    ![image](https://github.com/user-attachments/assets/11e95f78-f6d4-4f6d-a420-3469d40d4f30)

    ![image](https://github.com/user-attachments/assets/ea4b3e84-d254-450d-95ac-c40aed0c8b95)

    ![image](https://github.com/user-attachments/assets/eeb72a32-c086-4833-a9ac-00db2524a459)

8. Now you can open the NSQ consumer application and consume the message.

   here i'm using NSQ Consumer CLI. Run this Command and consume the message

   👉🏻  nsq_tail --topic=Order --channel=my_consumer_channels --lookupd-http-address=127.0.0.1:4161

   ![image](https://github.com/user-attachments/assets/17cfb465-ea7a-44d0-b8d2-d77b6af60d24)

## Refer Camunda BPMN File

you can refer the Camunda BPMN file here 👉🏻 https://github.com/charanteja469/Camunda-NSQ-Outbound-Connector/blob/master/src/main/resources/NSQ_Test.bpmn

    












