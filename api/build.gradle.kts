plugins {}

version = "0.0.1"

dependencies {
    // spring-boot-starter-web
    implementation("org.springframework.boot:spring-boot-starter-web")

    implementation("org.springframework.boot:spring-boot-starter-data-jpa:2.7.6")

    implementation(project(":domain")) // 도메인 모듈

}
