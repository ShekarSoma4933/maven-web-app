#!/usr/bin/env groovy
//@Library('jenkins-shared-library') //use this when global setting is defined
library identifier: 'jenkins-shared-library@master' , retriever: modernSCM(
        [$class: 'GitSCMSource',
         remote: 'https://github.com/ShekarSoma4933/jenkins-shared-library.git',
         credentialsId: 'git_hub_credentials'
        ]
)
def gv

pipeline {
  agent any
  tools{
      maven 'maven-3.6'
  }
  stages {
      stage('init') {
         steps {
            script{
               gv = load "script.groovy"
            }
         }
      }
      stage('Build Jar') {
               steps {
                  script{
                     buildJar()
                  }
               }
            }
      stage('Build and Push Image') {
        steps {
            script{
              buildImage '143.198.43.144:8083/maven-web-app:2.3'
            }
        }
      }
      stage('deploy'){
        steps{
            script{
              gv.deployJar()
            }
        }
      }
  }
}