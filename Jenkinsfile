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
      stage('Build Version') {
                     steps {
                        script{
                           gv.buildVersion()
                        }
                     }
                  }
      stage('Build Jar') {
               steps {
                  script{
                     gv.buildJar()
                  }
               }
            }
      stage('commit version to git'){
           steps{
                script{
                gv.commitVersionToGitRepo()
                }
           }
      }
      stage('Build and Push Image') {
        steps {
            script{
              gv.buildAndPushImage()
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