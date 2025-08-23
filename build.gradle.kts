plugins {
    java
    application
}

repositories {
    mavenCentral()
}

dependencies {
    implementation("mysql:mysql-connector-java:8.0.33")
}

application {
    mainClass.set("Main")
}
