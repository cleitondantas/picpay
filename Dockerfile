# Usar uma imagem base que inclui o JDK (Java Development Kit)
FROM openjdk:21-jdk as build

# Configura o diretório de trabalho dentro do container
WORKDIR /app

# Copia os arquivos de build do projeto (por exemplo, usando Maven ou Gradle)
# Aqui estamos assumindo que você está usando Maven
COPY mvnw .
COPY .mvn .mvn
COPY pom.xml .
COPY src src
# Adiciona permissão de execução ao mvnw
RUN chmod +x ./mvnw
# Compila o projeto
RUN ./mvnw package -DskipTests

# Usa outra imagem base para a fase de execução para manter o container final mais leve
FROM openjdk:21-jdk

WORKDIR /app

# Copia o jar compilado da fase de build para a fase de execução
COPY --from=build /app/target/*.jar app.jar

# Expõe a porta em que sua aplicação vai rodar
EXPOSE 8080

# Comando para executar a aplicação
CMD ["java", "-jar", "app.jar"]