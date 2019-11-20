# Swift labs

Please follow the below steps to work with the labs.

## Swift based terminal example
**Add ibmcloud CLI**: https://cloud.ibm.com/functions/learn/cli - check the basic steps to log in, and setup environment. When you are ready to get all the existing actions with the following command follow steps below to create an action in Swift:

```
$ ibmcloud fn list
```

**Step 1. Clone the template repository**
```
$ git clone https://github.com/ibm-functions/template-hello-world.git
```

**Step 2. check the directory and the `manifest` file**
```
$ cd template-hello-world/runtimes/swift/
```

**Step 3. change the swift code**
In the `actions/helloworld.swift` file chenge the source code to the following

```swift
func main(args: [String:Any]) -> [String:Any] {
  return [ "message" : "Hello World!" ]
}
```

**Step 4. Create the action**

```
$PACKAGE_NAME=hello-world-serverless-swift-cli ibmcloud fn deploy -m manifest.yaml
```

**Step 5. Call the action**

```
$ ibmcloud fn action invoke --result hello-world-serverless-swift-cli/helloworld
```
You should see the following response: `{"message": "Hello World!"}`. The first call might take couple seconds to provision the container with the Swift based action. The following calls will render a very fast responses - if made under about 6 minutes from the first call - after that time the resources are going to be released by the OpenWhisk resource manager.

**Step 6. You are also able to update the action**

```
$ ibmcloud fn action update hello-world-serverless-swift-cli/helloworld actions/helloworld.swift 
```

You should receive the response: `ok: updated action hello-world-serverless-swift-cli/helloworld`
**Comment**
Also it is possible to **deploy warmed up Swift action** with a `Docker` based Swift action - more info would follow.
