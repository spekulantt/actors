# Filmová Databáza API 🚀

Vytvorené v rámci semestrálneho projektu. Autor: **Martin Polakovsky**

**Live Demo Aplikácie:** `https://actors-v7lc.onrender.com`

---

## O Projekte

Tento projekt je komplexné **RESTful API** vytvorené v **Java Spring Boot**, ktoré slúži na správu filmovej databázy. Aplikácia umožňuje plnohodnotne spravovať filmy, hercov a recenzie, vrátane komplexných vzťahov medzi nimi.

Dôraz bol kladený na čistú architektúru (Controller-Service-Repository), správne návrhové vzory (DTO pre vstupy aj výstupy), validáciu dát na strane servera a automaticky generovanú, prehľadnú dokumentáciu pomocou Swagger/OpenAPI.

---

## ✨ Kľúčové Vlastnosti

-   **Kompletné REST API:** Plná podpora CRUD operácií (Create, Read, Update, Delete) pre všetky kľúčové entity.
-   **Správa Filmov, Hercov a Recenzií:** Samostatné a logicky oddelené endpointy pre každú entitu.
-   **Komplexné Vzťahy:** Implementovaná M:N relácia medzi filmami a hercami s ukladaním konkrétnej role.
-   **Atomické Operácie:** Možnosť vytvoriť film a zároveň ho prepojiť s existujúcimi hercami v jedinej požiadavke.
-   **Validácia Dát:** API je zabezpečené proti neplatným vstupom vďaka Jakarta Bean Validation (napr. prázdny názov filmu).
-   **Profesionálna Dokumentácia:** Všetky endpointy sú prehľadne zdokumentované a interaktívne testovateľné cez Swagger UI.
-   **Development-Ready Databáza:** Pre jednoduchosť vývoja a testovania aplikácia využíva vstavanú H2 databázu, ktorá sa pri každom štarte automaticky resetuje a naplní testovacími dátami.
-   **Pripravené na Nasadenie:** Projekt obsahuje `Dockerfile` pre jednoduché nasadenie v kontajnerizovanom prostredí.

---

## 🔧 Použité Technológie

-   **Backend:** Java 17, Spring Boot 3
-   **Build & Dependencies:** Apache Maven
-   **Databáza & Perzistencia:** Spring Data JPA, Hibernate, H2 Database
-   **API & Dokumentácia:** Spring Web (RESTful), SpringDoc OpenAPI v3 (Swagger UI)
-   **Validácia:** Jakarta Bean Validation
-   **Nasadenie:** Docker

---

## 🚀 Spustenie Aplikácie Lokálne

### Požiadavky

-   Java Development Kit (JDK) verzia 17 alebo novšia.
-   Apache Maven.

### Inštrukcie

1.  **Naklonujte repozitár:**
    ```bash
    git clone [https://github.com/spekulantt/actors.git](https://github.com/spekulantt/actors.git)
    ```
2.  **Prejdite do priečinku projektu:**
    ```bash
    cd actors/film-api
    ```
3.  **Spustite aplikáciu pomocou Mavenu:**
    ```bash
    mvn spring-boot:run
    ```
4.  Aplikácia bude dostupná na adrese `http://localhost:8080`.

---

## 📚 Dokumentácia API (Swagger UI)

Interaktívna dokumentácia všetkých endpointov je automaticky generovaná a dostupná po spustení aplikácie.

-   **Lokálna adresa:** [http://localhost:8080/swagger-ui.html](http://localhost:8080/swagger-ui.html)
-   **Nasadená aplikácia:** `https://actors-v7lc.onrender.com/swagger-ui.html`

---

## ⚡️ Prehľad API Endpointov

### Filmy (`/api/movies`)

-   `GET /`: Získa zoznam všetkých filmov.
-   `GET /{id}`: Získa detail jedného filmu podľa ID.
-   `POST /`: Vytvorí nový film. Umožňuje zároveň prepojiť existujúcich hercov.
    -   **Príklad tela požiadavky:**
        ```json
        {
          "title": "Pacho, hybský zbojník",
          "releaseYear": 1975,
          "actors": [
            { "actorId": 1, "role": "Pacho Matrtaj" },
            { "actorId": 2, "role": "zeman Erdődy" }
          ]
        }
        ```
-   `PUT /{id}`: Upraví existujúci film podľa ID.
-   `DELETE /{id}`: Zmaže film podľa ID (vrátane všetkých prepojení a recenzií).
-   `POST /{movieId}/actors`: Prepojí existujúceho herca s existujúcim filmom a priradí mu rolu.

### Herci (`/api/actors`)

-   `GET /`: Získa zoznam všetkých hercov.
-   `GET /{id}`: Získa detail jedného herca podľa ID.
-   `POST /`: Vytvorí nového herca.
-   `PUT /{id}`: Upraví existujúceho herca podľa ID.
-   `DELETE /{id}`: Zmaže herca podľa ID.

### Recenzie (`/api/reviews`)

-   `GET`: Získa zoznam všetkých recenzií pre konkrétny film.
    -   **Použitie:** `/api/reviews?movieId=1`
-   `POST /`: Pridá novú recenziu k filmu.
    -   **Príklad tela požiadavky:**
        ```json
        {
          "author": "Ján Recenzent",
          "content": "Veľmi dobrý film!",
          "rating": 4.5,
          "movieId": 1
        }
        ```
-   `PUT /{id}`: Upraví existujúcu recenziu podľa jej ID.
-   `DELETE /{id}`: Zmaže recenziu podľa jej ID.

---

## 🗃️ Databáza

Aplikácia používa **H2 file-based databázu** pre jednoduchý lokálny vývoj. Pri každom spustení sa vďaka nastaveniu `spring.jpa.hibernate.ddl-auto=create` a `spring.sql.init.mode=always` databáza zmaže, vytvorí nanovo a naplní sa testovacími dátami zo súboru `src/main/resources/data.sql`. Tým je zaručené konzistentné prostredie pre testovanie.

Webovú konzolu pre priamy prístup k databáze nájdete na adrese [http://localhost:8080/h2-console](http://localhost:8080/h2-console).

-   **JDBC URL:** `jdbc:h2:file:./film-api-db`
-   **User Name:** `sa`
-   **Password:** `password`

---

## 👨‍💻 Autor

-   **Martin Polakovsky**
-   GitHub: [spekulantt](https://github.com/spekulantt)
