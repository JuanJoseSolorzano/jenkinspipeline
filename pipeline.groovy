// ==========================================================
// Jenkins Pipeline script to manage a dummy repostory
// Author: Juan Jose Solorzano
// Date: 12/10/2024
// ==========================================================

// The pipeline block defines the Jenkins Job configuration

pipeline {
    agents {
        label 'WindowsHostMachine'
    }
    stages {
        stage("Testing"){
            steps {
                powershell 'Write-Host "This is a test stage running on a Windows host machine."'
            }
        }
    }
}