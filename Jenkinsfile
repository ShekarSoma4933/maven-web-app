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
              buildImage()
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