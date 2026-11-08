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

# UTF-8 : accents corrects dans les logs et dans les noms de fichiers
# (sinon Java lit les noms en ASCII et les photos accentuées posent problème)
ENV LANG=C.UTF-8 LC_ALL=C.UTF-8
COPY --from=build /app/target/Domes-*.war app.war

EXPOSE 8080
ENTRYPOINT ["java", "-jar", "app.war"]
