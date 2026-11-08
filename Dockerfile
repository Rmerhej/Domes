# ---------- Étape 1 : compilation avec Maven ----------
FROM maven:3.9-eclipse-temurin-11 AS build
WORKDIR /app

# On copie d'abord le pom seul : tant qu'il ne change pas, Docker garde
# les dépendances téléchargées en cache et le build suivant est rapide.
COPY pom.xml .
RUN mvn -q -B dependency:go-offline

COPY src ./src
RUN mvn -q -B package -DskipTests

# ---------- Étape 2 : image d'exécution, légère (Java seul, sans Maven) ----------
FROM eclipse-temurin:11-jre
WORKDIR /app
COPY --from=build /app/target/Domes-*.war app.war

EXPOSE 8080
ENTRYPOINT ["java", "-jar", "app.war"]
