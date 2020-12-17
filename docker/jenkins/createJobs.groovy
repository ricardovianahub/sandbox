pipelineJob('fakeresponseDeployToDocker') {
    definition {
        cpsScm {
            scm {
                git {
                    remote {
                        url 'https://github.com/ricardovianahub/sandbox.git'
                    }
                    branch 'adjust1'
                    scriptPath('docker/qaautomation/fakeresponse/Jenkinsfile')
                }
            }
        }
    }
}
