# payment

## Running in local development environment

```
mvn spring-boot:run
```
## payment test
```
0. 페이 목록조회
http GET localhost:8085/payments

1. 페이 등록
http POST localhost:8085/payments rentalId=1 toyRentalPrice=10000

2. 페이 취소
//직접적인 방법은 없고, rental에서 취소해야함
````

## Packaging and Running in docker environment

```
mvn package -B
docker build -t username/payment:v1 .
docker run username/payment:v1
```

## Push images and running in Kubernetes

```
docker login 
# in case of docker hub, enter your username and password

docker push username/payment:v1
```

Edit the deployment.yaml under the /kubernetes directory:
```
    spec:
      containers:
        - name: payment
          image: username/payment:latest   # change this image name
          ports:
            - containerPort: 8080

```

Apply the yaml to the Kubernetes:
```
kubectl apply -f kubernetes/deployment.yml
```

See the pod status:
```
kubectl get pods -l app=payment
```

If you have no problem, you can connect to the service by opening a proxy between your local and the kubernetes by using this command:
```
# new terminal
kubectl port-forward deploy/payment 8080:8080

# another terminal
http localhost:8080
```

If you have any problem on running the pod, you can find the reason by hitting this:
```
kubectl logs -l app=payment
```

Following problems may be occurred:

1. ImgPullBackOff:  Kubernetes failed to pull the image with the image name you've specified at the deployment.yaml. Please check your image name and ensure you have pushed the image properly.
1. CrashLoopBackOff: The spring application is not running properly. If you didn't provide the kafka installation on the kubernetes, the application may crash. Please install kafka firstly:

https://labs.msaez.io/#/courses/cna-full/full-course-cna/ops-utility

