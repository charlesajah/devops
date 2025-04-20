println "DEBUG: Starting job definition for 'NodeJS Docker example'"

job('NodeJS Docker example') {
    println "DEBUG: Inside job block for 'NodeJS Docker example'"

    scm {
        println "DEBUG: Setting up Git SCM for 'NodeJS Docker example'"
        git('https://github.com/charlesajah/devops.git') { node ->
            node / gitConfigName('DSL User')
            node / gitConfigEmail('charles.ajah@gmail.com')
        }
    }

    triggers {
        println "DEBUG: Setting SCM polling trigger"
        scm('H/5 * * * *')
    }

    wrappers {
        println "DEBUG: Applying NodeJS wrapper"
        nodejs('nodejs')
    }

    steps {
        println "DEBUG: Starting dockerBuildAndPublish step"
        dockerBuildAndPublish {
            println "DEBUG: Inside dockerBuildAndPublish config block"
            repositoryName('dev1980/devcharles')
            tag('latest')  // Temporarily simplified tag
            registryCredentials('dockerhub')
            forcePull(false)
            forceTag(false)
            createFingerprints(false)
            skipDecorate()
        }
    }

    println "DEBUG: Finished defining job 'NodeJS Docker example'"
}
