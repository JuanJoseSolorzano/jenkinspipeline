// ==========================================================
// Jenkins Pipeline script to manage a dummy repostory
// Author: Juan Jose Solorzano
// Date: 12/10/2024
// ==========================================================

// The pipeline block defines the Jenkins Job configuration

pipeline {
    agent none //
    stages {
        stage("Configure Agents"){
            matrix {
                axes {
                    axis {
                        name 'Agents_Labels'
                        valus 'WindowsHostMachine','WindowsHostMachine1','WindowsHostMachine2'
                    }
                }
            }
        }
    }
}