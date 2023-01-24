def gv
pipeline {
  agent any
  /*environment{
     //global environment variables
     //NEW_VERSION='1.2.0'
     //SERVER_CREDENTIALS=credentials('')
  }*/
  tools{
      maven 'maven-3.6'
  }
  parameters{
        /*syntax to define parameters
        string(name: 'VERSION', defaultValue: '', description: 'version to deploy on prod')*/
        choice(name: 'VERSION',choices : ['1.1.0','1.1.1','1.2.1'], description:'')
        booleanParam(name: 'executeTests', defaultValue: true, description: '')
  }
  stages {
      stage('init') {
         steps {
            script{
               gv = load "script.groovy"
            }
         }
      }
      stage('build') {
               steps {
                  script{
                     gv.buildApp()
                  }

               }
            }
      stage('test') {
        when{
           expression{
             params.executeTests
           }
        }
        steps {
            script{
              gv.testStage()
            }
        }
      }
      stage('push') {
        steps {
           script{
             gv.pushStage()
           }
        }
      }
      stage('deploy'){
        steps{
           /*withCredentials([usernamePassword(credentials: 'docker-hub-credentials',usernameVariable: USER,passwordVariable: PASS)]){
           }*/
            script{
              gv.deployStage()
            }
        }
      }
  }
  /*post{
     always{
     }

  }*/
}