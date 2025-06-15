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
                python {
                    script: '''
                        import os
                        import sys
                        import unittest

                        class TestDummyRepository(unittest.TestCase):
                            def test_dummy(self):
                                self.assertTrue(True)

                        if __name__ == '__main__':
                            unittest.main()
                    '''
                }
            }
        }
    }
}