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
      stage('commit git version to repo'){
        steps{
            script{
                withCredentials([usernamePassword('credentialsId': 'git_hub_credentials', 'usernameVariable': 'USER', 'passwordVariable': 'PASS')]){
                            sh 'git config user.email "jenkins@example.com"'
                            sh 'git config user.name "jenkins"'

                            sh 'git status'
                            sh 'git branch'
                            sh 'git config --list'

                            sh "git remote set-url origin https://${USER}:${PASS}@github.com/ShekarSoma4933/maven-web-app.git"
                            sh "git add ."
                            sh 'git commit -m "ci: version bump"'
                            sh 'git push origin HEAD:master'
                        }
            }
        }

      }
  }
}