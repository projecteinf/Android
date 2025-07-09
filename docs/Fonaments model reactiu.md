# Fonaments del Model Reactiu – Explicació Elemental
Aquest document explica pas a pas els cinc pilars bàsics del model reactiu, amb exemples senzills i comentats en Kotlin.
## 1. Fluxos de dades declaratius
> Declares el *què* vols fer amb les dades, no el *com*.
```kotlin
getAiguaDelRiu() // retorna un Flow<Aigua>
    .filter { it.esNeta }
    .map { it.gel() }
    .collect { ampolla -> println("Ampolla plena: $ampolla") }
```
##  2. Propagació automàtica de canvis en cadena
> Quan una dada nova entra, els canvis es propaguen sols.
```kotlin
flowDeTemperatures()
    .filter { it > 30 }
    .map { "Fa calor: $it ºC" }
    .collect { println(it) }
```
## 3. Backpressure (gestió de sobrecàrrega)
> Protegeix el sistema si rep dades més ràpid del que pot processar.
```kotlin
flowDeSensor()
    .buffer(capacity = 10)          // Emmagatzema 10 valors
    .conflate()                     // Només l’últim si va molt ràpid
    .collect { processa(it) }       // Funció lenta
```
## 4. Tractament unificat d’errors i cancel·lació
> Els errors es gestionen dins el flux mateix.
```kotlin
getTemperatures()
    .map { 100 / it } // pot fallar si és 0!
    .catch { e -> println("Error: ${e.message}") }
    .collect { println(it) }
```
També pots cancel·lar el flux automàticament si canvia el context (per exemple, canvi de pantalla).
## 5. Composició funcional de fluxos
> Pots encadenar operadors com peces de Lego.
```kotlin
val temperatura = flowOf(20, 22, 25, 30, 35)

temperatura
    .filter { it >= 30 }
    .map { "ALERTA: $it ºC" }
    .onEach { avisaUsuari(it) }
    .collect { println(it) }
```
## Conclusió
La programació reactiva combina:
- Declarativitat
- Reacció automàtica
- Robustesa
- Escalabilitat
- Simplicitat composable
## Relació amb la Programació Declarativa
El model reactiu **es basa en la programació declarativa**.
En lloc d’indicar pas a pas què ha de fer el sistema (*estil imperatiu*), simplement **declares les operacions que vols aplicar sobre el flux de dades**.
Exemple:
```kotlin
temperatures.filter { it > 30 }.map { "Fa calor: $it ºC" }
```
Aquesta línia no processa cap dada immediatament. Només indica **què s’ha de fer** quan arribin noves dades. És el sistema qui s’encarrega de tot: observació, filtratge, transformació i emissió.
