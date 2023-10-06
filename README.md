# Pokedex
Pokedex is a simple application written in Kotlin 100% has two main features, to show the Pokemon list and Pokemon details after clicking on 1 Pokemon from the list.

## Images
<div style="display: flex; justify-content: space-between; align-items: center;">
  <img src="https://github.com/braveheart3208/Pokedex/blob/development/screenshots/pokemon_list.png" alt="Poke List" width="200px" />
  <img src="https://github.com/braveheart3208/Pokedex/blob/development/screenshots/pokemon_detail.png" alt="Poke Detail" width="200px" />
</div>

## How to use
Simply clone the project and run it on the local machine with the help of Android Studio.

## Specification
- 100% Kotlin Usage
- MVI Design Pattern
- Dagger Hilt DI
- Coil Image Rendering
- Paging Compose
- Coroutine
- Retrofit

## Project Structure
The project includes four main layers:
- **`application`:** Holding extensions and shared logic/component through out the application
- **`data`:** Holding implementation and data model (repositories)
- **`domain`:** Holding blue-print logic of the application and mean of communication between data and presentation layer
- **`presentation`:** Holding primarily UI components

## Improvement
- The project is currently not being unit-tested yet.
- Local Database can be supported in the future version.

## Contributing 🤝
Feel free to open an issue or submit a pull request for any bugs/improvements.
