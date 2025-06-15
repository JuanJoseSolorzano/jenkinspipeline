// ==========================================================
// Jenkins Pipeline script to manage a dummy repostory
// Author: Juan Jose Solorzano
// Date: 12/10/2024
// ==========================================================

// The pipeline block defines the Jenkins Job configuration

pipeline {
    agent any
    stages {
        stage("Testing"){
            steps {
                echo "Running tests..."
            }
        }
    }
}