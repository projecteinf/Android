# 🧩 Projecte: `MVVMComptadorFlow`

## ❓ Pregunta
> En un projecte Android amb el patró MVVM, tinc un `ViewModel` que manté un `StateFlow` amb el valor d’un comptador. Quan vull guardar aquest valor al model (persistència), és correcte passar el valor del comptador com a paràmetre a una funció `guardar()` del model?

## ✅ Resposta
No és recomanable que el `ViewModel` passi directament el valor del comptador al `Model`, ja que això crea una dependència de dades i trenca la separació de responsabilitats pròpia del patró MVVM.

**Estructura recomanada:**
- El `Model` hauria de contenir i exposar un `StateFlow` (o `Flow`) amb l’estat del comptador.
- El `ViewModel` es subscriu a aquest `Flow` i en manté una còpia observable per a la `View`.
- Quan cal modificar l’estat (incrementar, guardar, etc.), el `ViewModel` ha de cridar mètodes del `Model`, que actualitzaran el `StateFlow`.

Això permet:
- ✅ Reutilització del model  
- ✅ Separació clara entre capes  
- ✅ Persistència encapsulada en el model  
- ✅ Millor testabilitat i mantenibilitat  

---

## 🔁 Diagrama de flux de dades (MVVM + Flow)

```
+------------------+        recull i observa         +---------------------+
|     Model        | <----------------------------- |     ViewModel       |
| - comptadorFlow  |                                | - comptadorState    |
| - guardar()      |        crida a guardar()       | - incrementar()     |
+------------------+ -----------------------------> +---------------------+
                                                              |
                                                              | exposa el valor
                                                              v
                                                   +---------------------+
                                                   |        View         |
                                                   |  - mostra estat     |
                                                   |  - viewModel.guardar|
                                                   +---------------------+
```