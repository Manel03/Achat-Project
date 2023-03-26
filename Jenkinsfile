pipeline {
    agent any
     environment {
       DOCKERHUB_CREDENTIALS = credentials('dockerhub')
       NEXUS_VERSION = "nexus3"
       NEXUS_PROTOCOL = "http"
       NEXUS_URL = "172.10.0.140:8000/"
       NEXUS_REPOSITORY = "maven-nexus-repo"
       NEXUS_CREDENTIAL_ID = "nexus"
     }
     stages {
        stage('GIT CHECKOUT') {
            steps {
             echo 'Pulling ... ';
                git branch: 'Manel' ,
                url: 'https://github.com/Manel03/Achat-Project.git';
            }
        }
        stage('TESTING MAVEN') {
            steps {
                 sh """ mvn --version"""
                 
            }
        }
        stage('MAVEN COMPILE') {
            steps {
                 sh """mvn compile"""
            }
        }
        stage('MAVEN PACKAGE') {
            steps {
                 sh """mvn package"""
            }
        }
        
        stage('BUILD DOCKER IMAGE') {
            steps {
                 sh """docker build -t maneeel/tp-achat ."""
            }
        }
        stage('PUSH DOCKER IMAGE') {
            steps {
                 sh 'echo $DOCKERHUB_CREDENTIALS_PSW | docker login -u $DOCKERHUB_CREDENTIALS_USR -p $DOCKERHUB_CREDENTIALS_PSW  && docker push maneeel/tp-achat '
            }
        }
        stage(' MVN SONARQUBE') {
            steps {
                withSonarQubeEnv('Sonar') {
                    sh 'mvn clean -DskipTests package sonar:sonar'
                }
            }
        }
        stage('MVN NEXUS') {
            steps {
                script {
                    sh 'mvn clean deploy -DskipTests'
                }
            }
         
        }
        
           stage('DOCKER COMPOSE') {
            steps {
                script {
                    sh 'docker-compose up -d'
                }
            }
          
            }
            stage('Email Notification') {
                steps {
                emailext (attachLog: true, body: '''Hi  Welcome to Jenkins email alerts
                    Thanks 
                    Jenkins\' community ''', subject: 'Jenkins Job', to: 'maneldevops@gmail.com')
                    }
                
            }
          
        }
    
}

