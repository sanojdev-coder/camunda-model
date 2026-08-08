use the camunda-modeler

&#x20;- camunda-modeler-4.12.0-win-x64.zip



send request using swagger:

&#x20;http://localhost:8081/swagger-ui/index.html#/



When running the spring-boot application, it itself will host the modeler, else need to run locally to model

&#x20;if using podman, use below:

&#x20; podman pull --tls-verify=false docker.io/camunda/camunda-bpm-platform:run-latest

&#x20; podman run -d --name camunda -p 8080:8080 camunda/camunda-bpm-platform:run-latest



The comunda task list is available as below:

&#x20;http://localhost:8081/camunda

&#x20;login using demo/demo



local modeller

https://weblogin.cloud.camunda.io/u/login

