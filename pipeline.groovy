// ==========================================================
// Jenkins Pipeline script to manage a dummy repostory
// Author: Juan Jose Solorzano
// Date: 12/10/2024
// ==========================================================

// The pipeline block defines the Jenkins Job configuration
def REPOSITORY_URL = 'https://github.com/JuanJoseSolorzano/Bash_Scripting.git'
def SEPARATOR = '$("="*75)'
pipeline {
    agent none // No default agent to be able to use matrix agents and execute the same job on multiple agents
    stages {
        stage('Running the same Job') { // One stage defined as a main stage, inside this stage we will run the same job.
            matrix { // Matrix block is needed to run the same job using different agents.
                axes { axis {
                        name 'AGENTS_LABEL' // Define an axis for the agent label
                        values 'WindowsHostMachine', 'WindowsHostMachine1', 'WindowsHostMachine2'
                    }
                }
                agent { label "${AGENTS_LABEL}" }
                stages {
                    stage('Main') {
                        steps {
                            powershell "Write-Output 'this is an example of log using the \"${AGENTS_LABEL}\"' >> log.txt"
                        } 
                    }
                    stage("Cloning a repository"){
                        powershell """
                            Write-Output 'Cloning the repository ${REPOSITORY_URL} using the agent ${AGENTS_LABEL}' >> log.txt
                            Write-Output ${SEPARATOR} >> log.txt
                            git clone ${REPOSITORY_URL} >> log.txt
                        """
                    }
                }
            }
        }
    }
}