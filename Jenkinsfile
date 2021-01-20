pipeline {
  agent {
    kubernetes {
      yaml """
apiVersion: v1
kind: Pod
metadata:
  labels:
    jenkins-poc: 1-jenkins-poc
spec:
  containers:
  - name: maven
    image: maven:3.6.3-openjdk-11
    imagePullPolicy: IfNotPresent
    command:
    - cat
    tty: true
    volumeMounts:
      - name: maven-cache
        mountPath: /root/.m2/repository
  - name: busybox
    image: busybox
    command:
    - cat
    tty: true
  - name: kaniko
    image: gcr.io/kaniko-project/executor:debug-539ddefcae3fd6b411a95982a830d987f4214251
    imagePullPolicy: IfNotPresent
    command:
    - /busybox/cat
    tty: true
    volumeMounts:
      - name: jenkins-docker-cfg
        mountPath: /root
      - name: docker-cache
        mountPath: /cache    
  volumes:
  - name: jenkins-docker-cfg
    projected:
      sources:
      - secret:
          name: docker-secret
          items:
            - key: .dockerconfigjson
              path: .docker/config.json
  - name: maven-cache
    persistentVolumeClaim:
      claimName: maven-cache-pvc
  - name: docker-cache
    persistentVolumeClaim:
      claimName: docker-cache-pvc
"""
    }
  }
  stages {
    stage('maven build') {
      steps {
        container('maven') {
          sh 'mvn -version'
		  sh 'mvn clean install --settings settings.xml'
        }
      }
    }
	
	stage('Build Image') {
		environment {
                PATH = "/busybox:$PATH"
        }
     steps {
      container(name:'kaniko', shell: '/busybox/sh') {
		sh 'pwd'
		sh 'ls -ltr'
		sh '''#!/busybox/sh
        /kaniko/executor -f `pwd`/Dockerfile -c `pwd` --insecure --skip-tls-verify --cache=true --destination=skmani2/inventory-management:v3
		'''
      }
	}
  }
 }
 
  
  post {
	always {
			echo 'build success'
            /*cleanWs()*/
        }
  }
}