@evcharrier - @magaultier

# Gestion d’un Restaurant en Java

## Description du projet

Ce projet simule le fonctionnement d'un restaurant avec une file d'attente et une gestion concurrente des ressources. Nous utilisons des concepts tels que les sémaphores et les `Threads` pour modéliser les interactions entre les clients et les employés du restaurant.

## Implémentation

### Gestion des accès : le sémaphore

Nous avons choisi d’utiliser un **sémaphore** pour gérer l’accès des clients au restaurant. Cela permet de simuler une file d’attente à l’entrée en limitant le nombre de clients pouvant accéder simultanément aux ressources.

### Clients et employés en tant que Threads

Les clients et l'employé sont implémentés en tant que **Threads** pour permettre une gestion concurrente des actions. Cela nous permet d’endormir les Threads en cas de conflits d'accès à certaines ressources.

### Méthodes synchronisées

Les méthodes critiques (`seServir()`, `remplir()` et `cuire()`) sont marquées comme **synchronized** afin de garantir qu’un seul client ou employé y accède à la fois.

### Gestion du temps

Le temps nécessaire pour que les clients se servent, fassent cuire et mangent est proportionnel à la quantité désirée, simulant ainsi des délais réalistes.

### Modélisation du stand et des cuisiniers

Nous avons choisi de fusionner la logique du stand et du cuisinier en un seul objet. Les clients peuvent ainsi cuire leurs aliments de manière autonome, simplifiant l'implémentation sans sacrifier la logique métier.

