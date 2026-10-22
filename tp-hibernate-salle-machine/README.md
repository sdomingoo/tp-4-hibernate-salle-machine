# TP Hibernate : Persistance des Données avec MySQL / MariaDB

Ce projet est un laboratoire complet guidé mettant en œuvre l'ORM **Hibernate 5.6**, **JPA**, et **MySQL / MariaDB** avec une architecture en couches (**DAO**, **Service**, **Entités**).

---

## 🎯 Objectifs Réalisés

1. **Mapping Objet-Relationnel (ORM)** : Association `@OneToMany` (Salle -> Machines) et `@ManyToOne` (Machine -> Salle).
2. **Couplage faible via IDao générique** : Interface `IDao<T>` définissant les opérations CRUD standards.
3. **Architecture 3 Tiers** :
   - Entités : `entities.Salle`, `entities.Machine`
   - DAO : `dao.IDao<T>`, `dao.SalleDao`, `dao.MachineDao`
   - Services : `services.SalleService`, `services.MachineService`
   - Utilitaire : `util.HibernateUtil` (Singleton `SessionFactory`)
4. **Requêtes HQL (Hibernate Query Language)** : Recherche orientée objet `findByMarque(String marque)`.
5. **Gestion robuste des transactions** : `beginTransaction()`, `commit()`, et `rollback()` systématique en cas d'erreur.
6. **Tests Unitaires Automatisés JUnit 5** : Validation complète du cycle de vie CRUD et des requêtes HQL.

---

## 📂 Structure du Projet

```
tp-hibernate-salle-machine/
├── pom.xml
├── README.md
├── src/
│   ├── main/
│   │   ├── java/
│   │   │   ├── dao/
│   │   │   │   ├── IDao.java          <- Contrat d'interface générique
│   │   │   │   ├── SalleDao.java      <- Opérations CRUD Salle avec Hibernate
│   │   │   │   └── MachineDao.java    <- Opérations CRUD Machine + Requête HQL
│   │   │   ├── entities/
│   │   │   │   ├── Salle.java         <- Entité persistante (OneToMany)
│   │   │   │   └── Machine.java       <- Entité persistante (ManyToOne)
│   │   │   ├── services/
│   │   │   │   ├── SalleService.java  <- Couche métier Salle
│   │   │   │   └── MachineService.java<- Couche métier Machine
│   │   │   ├── util/
│   │   │   │   └── HibernateUtil.java <- Gestion centralisée de la SessionFactory
│   │   │   └── test/
│   │   │       └── MainTest.java      <- Programme exécutable de test et création des tables
│   │   └── resources/
│   │       └── hibernate.cfg.xml      <- Configuration de connexion MySQL & dialecte
│   └── test/
│       └── java/
│           └── services/
│               ├── SalleServiceTest.java    <- Tests unitaires CRUD JUnit 5 Salle
│               └── MachineServiceTest.java  <- Tests unitaires CRUD JUnit 5 Machine & HQL
```

---

## ⚙️ Configuration Base de Données

Le fichier `src/main/resources/hibernate.cfg.xml` est configuré pour se connecter à MySQL / MariaDB :
- **Hôte** : `localhost:3306`
- **Base de données** : `db_tp_hibernate` (créée automatiquement si absente)
- **Utilisateur** : `root`
- **Mot de passe** : `1234`
- **Génération automatique des tables** : `<property name="hbm2ddl.auto">update</property>`

---

## 🚀 Exécution

### 1. Lancer la démonstration MainTest (insertion et requêtes) :
```bash
mvn exec:java
```

### 2. Exécuter tous les tests JUnit 5 :
```bash
mvn test
```
