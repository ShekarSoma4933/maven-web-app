pipeline {
  agent any
  environment{
     //global environment variables
     //NEW_VERSION='1.2.0'
     //SERVER_CREDENTIALS=credentials('')
  }
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
      stage('build') {
         steps {
            echo "this is build stage"
         }
      }
      stage('test') {
        when{
           expression{
             params.executeTests
           }
        }
        steps {
           echo "this is test stage"
        }
      }
      stage('push') {
        steps {
           echo "this is push stage"
        }
      }
      stage('deploy'){
        steps{
           withCredentials([usernamePassword(credentials: 'docker-hub-credentials',usernameVariable: USER,passwordVariable: PASS)]){
           }

           echo "this is deploy stage ${params.VERSION}"
        }
      }
  }
  /*post{
     always{
     }

  }*/
}