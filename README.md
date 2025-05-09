# Versió 1
## Punts forts
- Codi ben estructurat.
- Aplicació bones pràctiques
    1. Separació de components en Main, Bottom, etc.
    2. Ús del Scaffold per a la disposició general.
    3. Estructura modular de Composable.
    4. Aplicació d’un Theme.
## Punts a millorar
- Els noms Main() i Bottom() són massa genèrics
    1. No escalable: a mesura que el projecte creixi, poden portar a confusió amb noms del sistema (main() és una funció típica d'entrada).
    2. Canviar a MainContent i BottomMenu
- No estàs usant el modifier que passes a Main() i Bottom()
    1. No escalable
    2. Components no reutilitzables.
| Sense `modifier` aplicat             | Amb `modifier` aplicat correctament        |
| ------------------------------------ | ------------------------------------------ |
| Rígid, difícil de reutilitzar        | Flexible, configurable des de fora         |
| No respon al context visual          | S’adapta fàcilment a diferents usos        |
| Fa que hagis de duplicar composables | Permet escalar mantenint composables únics |
