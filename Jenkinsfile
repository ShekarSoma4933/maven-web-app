#!/usr/bin/env groovy
@Library('jenkins-shared-library')
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
              buildImage '143.198.43.144:8083/maven-web-app:2.2'
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