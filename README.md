#Detaliere cerinte

Cerinta 1
Vor fi create relații între entități de toate tipurile: @OneToOne, @OneToMany, @ManyToOne, 
@ManyToMany. 

Avem:
-relatie one-to-one intre adresa si centru adoptie
-relatie one-to-many intre centru adoptie si pets
-relatie many to many intre user si role
-relatie many to many intre user si pet dar procesata ca 2 relatii one to many intre user si cerere adoptie si pet si cerere adoptie

Cerinta 2
Vor fi implementate toate tipurile de operații CRUD.  
S-au implementat in fiecare controller operatiile de: POST, PUT, DELETE, GET cu diferiti parametrii

Cerinta 3
Se va testa aplicația folosindu-se profiluri și două baze de date diferite, una dintre ele pentru etapa 
de testare. Se poate utiliza și o bază de date in-memory (H2). 
S-a folosit mysql si h2 pentru testare, observandu se cele trei fisiere de tip application.properties

Cerinta 4
Utilizare unit-tests/integration tests. 
S-au scris teste la nivel de service pentru fiecare fisier de acest tip in care au fost tratate toate exceptiile

Cerinta 5
Se vor valida datele din formulare, se vor trata excepțiile. 
Toate dto-urile contin validari pentru date

Cerinta 6
Se vor valida datele din formulare, se vor trata excepțiile. 

Cerinta 7
Se vor utiliza log-uri. Opțional aspecte. 
S-au adaugat loguri la nivel de controllere si service uri ce afiseaza diferite mesaje in consola in functie de statusul cererii

Cerinta 8
Vor fi utilizate opțiuni de paginare și sortare a datelor.  
S-a adaugat pentru pet optiune de sortare si filtrare in functie de fildurile date ca parametru

Cerinta 9
Se va include Spring Security (cerința minima autentificare jdbc).  
S-a implementat login ul si sign up ul ce returneaza un token ce se poate procesa mai apoi pe partea de front
