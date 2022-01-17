import jetbrains.buildServer.configs.kotlin.v2019_2.*
import jetbrains.buildServer.configs.kotlin.v2019_2.buildSteps.FTPUpload
import jetbrains.buildServer.configs.kotlin.v2019_2.buildSteps.ftpUpload
import jetbrains.buildServer.configs.kotlin.v2019_2.triggers.vcs
import jetbrains.buildServer.configs.kotlin.v2019_2.vcs.GitVcsRoot

/*
The settings script is an entry point for defining a TeamCity
project hierarchy. The script should contain a single call to the
project() function with a Project instance or an init function as
an argument.

VcsRoots, BuildTypes, Templates, and subprojects can be
registered inside the project using the vcsRoot(), buildType(),
template(), and subProject() methods respectively.

To debug settings scripts in command-line, run the

    mvnDebug org.jetbrains.teamcity:teamcity-configs-maven-plugin:generate

command and attach your debugger to the port 8000.

To debug in IntelliJ Idea, open the 'Maven Projects' tool window (View
-> Tool Windows -> Maven Projects), find the generate task node
(Plugins -> teamcity-configs -> teamcity-configs:generate), the
'Debug' option is available in the context menu for the task.
*/

version = "2021.2"

project {

    vcsRoot(HttpsGithubComIyankeBigdataRefsHeadsMaster)

    buildType(Build)
    buildType(Build1)
}

object Build : BuildType({
    name = "Build"

    artifactRules = """
        12.png
        android/** => android1
    """.trimIndent()

    vcs {
        root(HttpsGithubComIyankeBigdataRefsHeadsMaster)
    }

    steps {
        ftpUpload {
            targetUrl = "ftp://172.31.131.24:22"
            securityMode = FTPUpload.SecurityMode.NONE
            dataChannelProtection = FTPUpload.DataChannelProtectionMode.DISABLE
            authMethod = usernameAndPassword {
                username = "jetbrains"
                password = "credentialsJSON:3a18efca-ee26-4fc5-ab03-1ba28a703208"
            }
            transferMode = FTPUpload.TransferMode.AUTO
            sourcePath = """
                12.png
                adnroid1
            """.trimIndent()
        }
    }

    triggers {
        vcs {
        }
    }
})

object Build1 : BuildType({
    name = "Build1"

    artifactRules = "11.png"

    vcs {
        root(HttpsGithubComIyankeBigdataRefsHeadsMaster)
    }

    steps {
        ftpUpload {
            targetUrl = "ftp://172.31.131.24:22"
            securityMode = FTPUpload.SecurityMode.NONE
            authMethod = usernameAndPassword {
                username = "jetbrains"
                password = "credentialsJSON:3a18efca-ee26-4fc5-ab03-1ba28a703208"
            }
            transferMode = FTPUpload.TransferMode.ASCII
            sourcePath = "11.png"
        }
    }

    triggers {
        vcs {
        }
    }
})

object HttpsGithubComIyankeBigdataRefsHeadsMaster : GitVcsRoot({
    name = "https://github.com/iyanke/bigdata#refs/heads/master"
    url = "https://github.com/iyanke/bigdata"
    branch = "refs/heads/master"
    branchSpec = "refs/heads/*"
    authMethod = password {
        userName = "iyanke"
        password = "credentialsJSON:be3ba775-e2e3-4e7c-a419-93c1194d42f2"
    }
})
