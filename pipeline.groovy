// ==========================================================
// Jenkins Pipeline script to manage a dummy repostory
// Author: Juan Jose Solorzano
// Date: 12/10/2024
// ==========================================================

// The pipeline block defines the Jenkins Job configuration

pipeline {
    agent none
    stages {
        stage('Run on Multiple Agents') {
            matrix {
                axes {
                    axis {
                        name 'AGENT_LABEL'
                        values 'WindowsHostMachine', 'WindowsHostMachine1', 'WindowsHostMachine2'
                    }
                }
                agent { label "${AGENT_LABEL}" }
                stages {
                    stage('Main') {
                        steps {
                            echo "Running on ${AGENT_LABEL}"
                            // Add your pipeline steps here
                        }
                    }
                }
            }
        }
    }
}