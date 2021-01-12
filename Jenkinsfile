pipeline {
  agent {
    kubernetes {
      yaml """
apiVersion: v1
kind: Pod
metadata:
  labels:
    some-label: some-label-value
spec:
  containers:
  - name: maven
    image: maven:3.6.3-openjdk-11
	imagePullPolicy: "IfNotPresent"
    command:
    - cat
    tty: true
  - name: busybox
    image: busybox
    command:
    - cat
    tty: true
"""
    }
  }
  stages {
    stage('Run maven') {
      steps {
        container('maven') {
          sh 'mvn -version'
		  sh 'mvn clean install --settings settings.xml'
        }
        container('busybox') {
          
        }
      }
    }
  }
  
  post {
	always { 
            cleanWs()
        }
  }
}