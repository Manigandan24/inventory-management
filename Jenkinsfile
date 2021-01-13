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
    imagePullPolicy: IfNotPresent
    command:
    - cat
    tty: true
  - name: busybox
    image: busybox
    command:
    - cat
    tty: true
  - name: kaniko
    image: gcr.io/kaniko-project/executor:debug-539ddefcae3fd6b411a95982a830d987f4214251
    imagePullPolicy: Always
    command:
    - /busybox/cat
    tty: true
    volumeMounts:
      - name: jenkins-docker-cfg
        mountPath: /kaniko/.docker
  volumes:
  - name: jenkins-docker-cfg
    projected:
      sources:
      - secret:
          name: my-secret
          items:
            - key: .dockerconfigjson
              path: config.json
"""
    }
  }
  stages {
    /*stage('Run maven') {
      steps {
        container('maven') {
          sh 'mvn -version'
		  sh 'mvn clean install --settings settings.xml'
        }
      }
    }*/
	
	stage('Build with Kaniko') {
		environment {
                PATH = "/busybox:$PATH"
        }
     steps {
      container(name:'kaniko', shell: '/busybox/sh') {
		sh '''#!/busybox/sh
        /kaniko/executor -f `pwd`/Dockerfile -c `pwd` --insecure --skip-tls-verify --cache=true --destination=skmani2/inventory-management:v2
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