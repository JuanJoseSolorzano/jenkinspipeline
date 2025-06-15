// ==========================================================
// Jenkins Pipeline script to manage a dummy repostory
// Author: Juan Jose Solorzano
// Date: 12/10/2024
// ==========================================================

// The pipeline block defines the Jenkins Job configuration

/* groovylint-disable-next-line CompileStatic */
pipeline {
    agent {
        label 'WindowsHostMachine' // This should match the label of your Jenkins agent
    }
    stages {
        stage('Increase Counter'){
            steps{
                script {
                    echo "WORKSPACE ENVIRONMENT -> ${env.WORKSPACE}"
                    def counterFile = "${env.WORKSPACE}/counter.txt"
                    def counter = 0
                    if (fileExists(counterFile)) {
                        counter = readFile(counterFile).trim().toInteger()
                    }
                    counter++
                    echo "Run #${counter}"
                    writeFile file: counterFile, text: "${counter}"
                }
            }
        }
        stage('Check For Folders') {
            steps {
                // Powershell block to check if the folder exists
                powershell '''
                if(!(Test-Path "D:/Jenknins_Agent/BackUp")){
                    Write-Host "Folder: \"D:/Jenknins_Agent/BackUp\"" does not exists, creating it ...
                    New-Item -Type d "D:/Jenknins_Agent/BackUp"
                }
                '''
                // Powershell block to move the current repository if it exists
                powershell """
                \$counter = ${counter}
                Write-Host "Counter is \$counter"
                """
            }
        }
        stage('Clone Repository') {
            steps {
                // execute single powershell command
                powershell 'git clone https://github.com/JuanJoseSolorzano/DummyJenkinsRepo.git'
            }
        }
        stage('Modifying file and upload it') {
            steps {
                // execute single powershell command
                powershell """
                    Set-Location DummyJenkinsRepo
                    \$cnt = \$(Get-Content \"${env.WORKSPACE}/counter.txt\")
                    Set-Content fileOne.txt \"execution_\$cnt\"
                """
            }
        }
        stage('GitCommands') {
            steps {
                // execute single powershell command
                powershell """
                    $cnt = Get-Content "${env.WORKSPACE}/counter.txt"
                    git add .
                    git commit -m "execution number: $cnt"
                    git push origin master
                """
            }
        }
    }
}

