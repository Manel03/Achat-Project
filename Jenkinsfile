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
    tools{
        maven 'M2_HOME'
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
                 sh """mvn compile -DskipTests"""
            }
        }
        stage('MAVEN PACKAGE') {
            steps {
                 sh """mvn package -DskipTests"""
            }
        }
        
        stage('BUILD DOCKER IMAGE') {
            steps {
                 sh """docker build -t maneeel/tp-achat ."""
            }
        }
        stage ("JUNIT / MOCKITO") {
                	    	steps {
                				script {
                					try {
                						sh 'mvn test';
                						sh 'mvn clean';
                					}catch (any) {
                						throw any
                					} finally {
                														
								mail to: "maneldevops@gmail.com",
                                                                subject: """ Jenkins stage Build ${currentBuild.currentResult}: Stage "${env.STAGE_NAME}" """ ,
                                                                body: """${currentBuild.currentResult}: stage "JUNIT / MOCKITO" build n°${env.BUILD_NUMBER}
                						More info at: ${env.BUILD_URL}"""
                                                                echo 'successful'
                					}
                				}
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
            
          
        }
    post {
    	always {
    		step([$class: 'Mailer', notifyEveryUnstableBuild: true, recipients: 'maneldevops@gmail.com', sendToIndividuals: true])
		}
	}
    
}

