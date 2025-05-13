# Versió 3
## Ubicació elements
### **`Scaffold`** vs **`Column` amb `weight`**
| **Criteri**                                                       | **`Scaffold`**                                                                                                                      | **`Column` amb `weight`**                                                                                         |
| ----------------------------------------------------------------- | ----------------------------------------------------------------------------------------------------------------------------------- | ----------------------------------------------------------------------------------------------------------------- |
| **Semàntica**                                                     | Està pensat per estructurar una pantalla **completa**, amb parts com `topBar`, `bottomBar`, `drawer`, etc.                          | És més genèric; no expressa la intenció clara d'una pantalla amb estructura completa.                             |
| **Escalabilitat**                                                 | ✅ Fàcil afegir-hi funcionalitats globals (menú, snackbar, `FloatingActionButton`, topBar, etc.) sense tocar l’estructura principal. | ❌ Qualsevol afegit global implica modificar la jerarquia de la `Column`, cosa que pot dificultar l’escalabilitat. |
| **Reutilització de layout**                                       | Pots definir un `Scaffold` base reutilitzable per diverses pantalles, amb només el `content` canviant.                              | Menys flexible per reutilitzar com a base consistent de pantalles.                                                |
| **Gestió de padding automàtica (`innerPadding`)**                 | ✅ Té en compte automàticament el padding quan afegeixes barres.                                                                     | ❌ Has de gestionar-ho manualment.                                                                                 |
| **Comportament consistent amb les guidelines de Material Design** | ✅ Dissenyat per complir els estàndards.                                                                                             | ❌ És més lliure, però pots acabar amb inconsistències visuals.                                                    |
| **Complexitat inicial**                                           | Una mica més complex de llegir al principi.                                                                                         | Més simple i explícit per pantalles molt bàsiques.                                                                |
### `Surface`
Surface és el contenidor visual bàsic que aplica:
- Colors de fons del tema  
- Elevació (ombres)  
- Forma (com cantonades arrodonides)
- Canvis de tema clar/fosc
### `Scaffold` + `Surface`
De manera habitual s'inclou `Surface` dins `Scaffold` per aplicar fons i tema.
```Kotlin
Scaffold(
    bottomBar = { BottomMenu() }
) { innerPadding ->
    Surface(
        modifier = Modifier
            .fillMaxSize()
            .padding(innerPadding),
        color = MaterialTheme.colorScheme.background
    ) {
        MainContent()
    }
}
```
