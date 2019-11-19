# IBM Cloud Functions and Apache OpenWhisk - Lab 1

## Introduction
This lab walks you through the steps required to create, build, and run a Serverless application using IBM Cloud Functions. Serverless computing refers to a model where the existence of servers is entirely abstracted away. Even though servers exist, developers are relieved from the need to care about their operation. They are relieved from the need to worry about low-level infrastructural and operational details such as scalability, high-availability, infrastructure-security, and other details. Serverless computing is essentially about reducing maintenance efforts to allow developers to quickly focus on developing code that adds value.

Serverless computing simplifies developing cloud-native applications, especially microservice-oriented solutions that decompose complex applications into small and independent modules that can be easily exchanged. Some promising solutions like Apache OpenWhisk have recently emerged that ease development approaches used in the serverless model. IBM Cloud Functions is a Function-as-a-Service (FaaS) platform on IBM Cloud, built using the Apache OpenWhisk open source project, that allows you to execute code in response to an event.
It provides a serverless deployment and operations model. With a granular pricing model that works at any scale, you get exactly the resources you need – not more, not less – and you are charged for code that is really running.

IBM Cloud Functions provides:
* Support for multiple languages, including JavaScript, Python, Swift, and Java
* Support for running custom logic through Docker containers
* The ability to focus more on value-adding business logic, and less on low-level infrastructural and operational details.
* The ability to easily chain together microservices to form workflows via composition.

## Prerequisites
1. You will need an IBM Cloud Account. Either use your existing account, or create a new account by using the link provided in the workshop.
2. Install IBM Cloud CLI - https://cloud.ibm.com/docs/cli/reference/bluemix_cli?topic=cloud-cli-install-ibmcloud-cli#shell_install
3. Install the Functions plugin - https://cloud.ibm.com/functions/learn/cli

## Create an action
### Step 1 - Log into your IBM account using the CLI
1. Use the CLI to log into your account:
```
» ibmcloud login                                                         
API endpoint: https://cloud.ibm.com
Region: us-south

Email> upkar.ibm.watson.5@gmail.com

Password>
Authenticating...
OK

Targeted account Upkar Lidder's Account (a086ce7d00df4423ab024b123b587e76)


API endpoint:      https://cloud.ibm.com
Region:            us-south
User:              upkar.ibm.watson.5@gmail.com
Account:           Upkar Lidder's Account (a086ce7d00df4423ab024b123b587e76)
Resource group:    No resource group targeted, use 'ibmcloud target -g RESOURCE_GROUP'
CF API endpoint:
Org:
Space:
```

2. Target your Cloud Foundary space using the following command
```
» ibmcloud target --cf                                                    
Targeted Cloud Foundry (https://api.us-south.cf.cloud.ibm.com)

Targeted org upkar.ibm.watson.5@gmail.com

Targeted space dev



API endpoint:      https://cloud.ibm.com
Region:            us-south
User:              upkar.ibm.watson.5@gmail.com
Account:           Upkar Lidder's Account (a086ce7d00df4423ab024b123b587e76)
Resource group:    No resource group targeted, use 'ibmcloud target -g RESOURCE_GROUP'
CF API endpoint:   https://api.us-south.cf.cloud.ibm.com (API version: 2.142.0)
Org:               upkar.ibm.watson.5@gmail.com
Space:             dev
```

3. Ensure that you installed the [Cloud Functions plugin](https://cloud.ibm.com/functions/learn/cli) correctly. You might see an empty list if you don't have any actions deployed. You should not get any error at this point.

```
» ibmcloud fn action list                                                
actions
/upkar.ibm.watson.5@gmail.com_dev/att-hello                            private nodejs:10
/upkar.ibm.watson.5@gmail.com_dev/hello-debug                          private nodejs:10
/upkar.ibm.watson.5@gmail.com_dev/MYACTION                             private nodejs:10
/upkar.ibm.watson.5@gmail.com_dev/upkar-hello-debug                    private nodejs:10
/upkar.ibm.watson.5@gmail.com_dev/my-service-dev-hello                 private nodejs:10
/upkar.ibm.watson.5@gmail.com_dev/paypal-async                         private nodejs:10
/upkar.ibm.watson.5@gmail.com_dev/paypal-hello                         private nodejs:10
```
### Step 2 - Deploy your first action
1. Create index.js file with the following content
```
function main(params) {
  return { message: 'Hello, ' + params.name + ' from ' + params.place };
}
```

2. Deploy the function to IBM Cloud using the cli
```
» ibmcloud fn action create serverless-hello index.js                       
ok: created action serverless-hello
```

3. Let's try and invoke this fuction now!
```
ibmcloud fn action invoke serverless-hello                                
ok: invoked /_/serverless-hello with id 12d2c1256eaa49c792c1256eaa59c7a6
```
You don't get the result back. You get an activation id back! To see the result invoke the function again with the `-r` option. You can see all options using `ibmcloud fn action invoke --help`.

```
» ibmcloud fn action invoke serverless-hello -r                          
{
    "message": "Hello, undefined from undefined"
}
```

The `params.name` and `params.place` values are undefined as we did not pass them in. Let's pass them into the CLI this time.

```
» ibmcloud fn action invoke serverless-hello -r -p name "upkar lidder" -p place "San Francisco"
{
    "message": "Hello, upkar lidder from San Francisco"
}
```

## Create a trigger
### Step 1 - create an action
1. Create trigger.js file with the following content:
```
function main(params) {
  var date = new Date();
  var time = date.getHours() + ":" + date.getMinutes() + ":" + date.getSeconds();
  console.log("The time is " + time);
  return { message: "The time is " + time };
}
```

We are logging to `console.log` to see the message in the logs.

2. Deploy the action to IBM Cloud

```
» ibmcloud fn action create minute-action trigger.js                        
ok: created action minute-action

```

3. Let's create a new periodic trigger
```
» ibmcloud fn trigger create every-minute-trigger --feed /whisk.system/alarms/alarm -p cron "*/1 * * * *"
ok: invoked /whisk.system/alarms/alarm with id b8c811a1a1b142e78811a1a1b142e7cd
```

4. Connect the action to the trigger by creating a rule.
```
» ibmcloud fn rule create every-minute-rule every-minute-trigger minute-action      [8:40:56]
ok: created rule every-minute-rule
```

5. To see the action being called every minute, you can poll the logs using the following command
```
» ibmcloud fn activation poll
Enter Ctrl-c to exit.
Polling for activation logs

Activation: 'minute-action' (cd39ff96e5d64d74b9ff96e5d67d7423)
[
    "2019-11-19T16:44:01.159258Z    stdout: The time is 16:44:1"
]

Activation: 'every-minute-trigger' (183d7ffd5793468bbd7ffd5793e68b50)
[
    "{\"statusCode\":0,\"success\":true,\"activationId\":\"cd39ff96e5d64d74b9ff96e5d67d7423\",\"rule\":\"upkar.ibm.watson.5@gmail.com_dev/every-minute-rule\",\"action\":\"upkar.ibm.watson.5@gmail.com_dev/minute-action\"}"
]

Activation: 'minute-action' (01c12a3fa0054263812a3fa0057263f8)
[
    "2019-11-19T16:45:05.712643Z    stdout: The time is 16:45:5"
]

Activation: 'every-minute-trigger' (4527b4a124244c84a7b4a12424bc84ff)
[
    "{\"statusCode\":0,\"success\":true,\"activationId\":\"01c12a3fa0054263812a3fa0057263f8\",\"rule\":\"upkar.ibm.watson.5@gmail.com_dev/every-minute-rule\",\"action\":\"upkar.ibm.watson.5@gmail.com_dev/minute-action\"}"
]
```

## Create a web action
Read more about [web actions](https://cloud.ibm.com/docs/openwhisk?topic=cloud-functions-actions_web) in the [official documentation](https://cloud.ibm.com/docs/openwhisk?topic=cloud-functions-actions_web).

1. create web action
Let's convert our first `serverless-hello` action to a web action using the following command

```
» ibmcloud fn action update serverless-hello --web true                    
ok: updated action serverless-hello
```

2. confirm action is now a web action by using the `get` command. You should see `web-export` set to `true`.
```
» ibmcloud fn action get serverless-hello                                   
ok: got action serverless-hello
{
    "namespace": "upkar.ibm.watson.5@gmail.com_dev",
    "name": "serverless-hello",
    "version": "0.0.2",
    "exec": {
        "kind": "nodejs:10",
        "binary": false
    },
    "annotations": [
        {
            "key": "exec",
            "value": "nodejs:10"
        },
        {
            "key": "web-export",
            "value": true
        },
        {
            "key": "raw-http",
            "value": false
        },
        {
            "key": "final",
            "value": true
        }
    ],
    "limits": {
        "timeout": 60000,
        "memory": 256,
        "logs": 10,
        "concurrency": 1
    },
    "publish": false
}
```

3. get the public URL.
```
» ibmcloud fn action get serverless-hello --url                          
ok: got action serverless-hello
https://us-south.functions.cloud.ibm.com/api/v1/web/upkar.ibm.watson.5%40gmail.com_dev/default/serverless-hello
```

4. That's it! You can now curl the `<URL>` and pass in a `name` and `place` with any HTTP verb. Note the `.json` at the end of the URL. This is required if the action is returning json.
```
» curl -X GET <URL>.json\?name\=upkar+lidder\&place\=San+Francisco
{
  "message": "Hello, upkar lidder from San Francisco"
}
```

## Intermediate command line labs
If you want to practice more with the cli, you can follow the following labs
1. [Setting up the environment.](https://github.com/IBM-Cloud/openwhisk-workshops/tree/master/bootcamp/ex0%20-%20setting%20up%20development%20environment)
2. [Creating and invoking actions.](https://github.com/IBM-Cloud/openwhisk-workshops/tree/master/bootcamp/ex1%20-%20creating%20and%20invoking%20actions)