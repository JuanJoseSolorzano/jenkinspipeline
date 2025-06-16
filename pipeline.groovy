// ==========================================================
// Jenkins Pipeline script to manage a dummy repostory
// Author: Juan Jose Solorzano
// Date: 12/10/2024
// ==========================================================
//
// The pipeline block defines the Jenkins Job configuration
def REPOSITORY_URL = 'https://github.com/JuanJoseSolorzano/Bash_Scripting.git'
def SEPARATOR = '$("="*75)'
pipeline {
    agent {
        label 'AgentNumberOne'
    }
    triggers {
    githubPush()
  }
  stages{
    stage("Cloning"){
        steps{
            echo "Cloning the repository from ${REPOSITORY_URL}"
        }
    }
  }
}