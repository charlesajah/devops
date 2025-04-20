println "DEBUG: Starting job definition for 'NodeJS example'"

job('NodeJS example') {
    println "DEBUG: Inside job block for 'NodeJS example'"

    scm {
        println "DEBUG: Setting up Git SCM for 'NodeJS example'"
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
        println "DEBUG: Adding shell step to run 'npm install'"
        shell("npm install")
    }

    println "DEBUG: Finished defining job 'NodeJS example'"
}
