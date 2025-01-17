import com.github.gradle.node.npm.task.NpmTask

plugins {
    id("com.github.node-gradle.node") version "7.0.2"
}

node {
    // Whether to download and install a specific Node.js version or not
    // If false, it will use the globally installed Node.js
    // If true, it will download node using above parameters
    // Note that npm is bundled with Node.js
    download = false

    // Version of node to download and install (only used if download is true)
    // It will be unpacked in the workDir
    version = "20.16.0"

    // Version of npm to use
    // If specified, installs it in the npmWorkDir
    // If empty, the plugin will use the npm command bundled with Node.js
    // npmVersion = ""

    // Version of Yarn to use
    // Any Yarn task first installs Yarn in the yarnWorkDir
    // It uses the specified version if defined and the latest version otherwise (by default)
    // yarnVersion = ""

    // Base URL for fetching node distributions
    // Only used if download is true
    // Change it if you want to use a mirror
    // Or set to null if you want to add the repository on your own.
    // distBaseUrl = "https://nodejs.org/dist"

    // Specifies whether it is acceptable to communicate with the Node.js repository over an insecure HTTP connection.
    // Only used if download is true
    // Change it to true if you use a mirror that uses HTTP rather than HTTPS
    // Or set to null if you want to use Gradle"s default behaviour.
    // allowInsecureProtocol = null

    // The npm command executed by the npmInstall task
    // By default it is install but it can be changed to ci
    // npmInstallCommand = "install"

    // The directory where Node.js is unpacked (when download is true)
    // workDir = file("${project.projectDir}/.gradle/nodejs")

    // The directory where npm is installed (when a specific version is defined)
    // npmWorkDir = file("${project.projectDir}/.gradle/npm")

    // The directory where yarn is installed (when a Yarn task is used)
    // yarnWorkDir = file("${project.projectDir}/.gradle/yarn")

    // The Node.js project directory location
    // This is where the package.json file and node_modules directory are located
    // By default it is at the root of the current project
    // nodeProjectDir = file("${project.projectDir}")

    // Whether the plugin automatically should add the proxy configuration to npm and yarn commands
    // according the proxy configuration defined for Gradle
    // Disable this option if you want to configure the proxy for npm or yarn on your own
    // (in the .npmrc file for instance)
    // nodeProxySettings = ProxySettings.SMART
}

tasks.register("build", NpmTask::class) {
    this.dependsOn("npmInstall", "openapiGenerate")
    this.args = listOf("run", "build")
}

tasks.register("openapiGenerate", NpmTask::class) {
    this.dependsOn("npmInstall")
    this.args = listOf("run", "generate-api")
}

tasks.findByPath("npmInstall")?.dependsOn(":Backend:generateOpenApiDocs")
tasks.findByPath("openapiGenerate")?.dependsOn(":Backend:generateOpenApiDocs")
