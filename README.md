# Software Design and Architecture
This repository contains the project developed for the course Software Construction and Software Architecture at Aarhus University. The project showcases various software design patterns and architectural principles applied in a Java-based game called HotCiv. This project was developed in collaboration with another student, primarily through pair programming.

## Project Overview
HotCiv is a turn-based strategy game where players control units and cities on a grid-based map. The game includes different variants, each with unique rules and strategies. This project demonstrates the use of design patterns and architectural principles to create a flexible and extensible game framework.

## Features
- **Multiple Game Variants**: Implementations of different game rules and strategies, such as AlphaCiv, BetaCiv, and DeltaCiv.
- **Design Patterns**: Utilization of design patterns like Factory, Strategy, Observer, and Decorator to achieve modularity and flexibility.
- **Graphical User Interface**: A GUI built using the MiniDraw framework to visualize the game state and interact with the game.
- **Unit Testing**: Comprehensive unit tests to ensure the correctness of the game logic and design patterns.
**Clean Code Principles**: The codebase follows clean code principles to ensure readability, maintainability, and simplicity.
## Key Files and Directories

- **Main Game Logic**:
  - [`src/main/java/hotciv/standard/GameImpl.java`](src/main/java/hotciv/standard/GameImpl.java): Core game logic and rules implementation.

- **Design Patterns**:
  - For examples of design patterns used in this project, refer to the [Examples of Used Design Patterns](#examples-of-used-design-patterns) section.

- **User Interface**:
  - [`src/main/java/hotciv/view/CivDrawing.java`](src/main/java/hotciv/view/CivDrawing.java): Responsible for rendering the game state and updating the graphical user interface based on changes in the game.

- **Unit Tests**:
  - [`src/test/java/hotciv/standard/TestAlphaCiv.java `](src/test/java/hotciv/standard/TestAlphaCiv.java): Unit tests for the game logic.

## Examples of Used Design Patterns

- **Factory Pattern**:
  - Used to create different game variants.
  - Example: [`AlphaCivFactory.java`](src/main/java/hotciv/standard/variants/factories/AlphaCivFactory.java)
  - This class implements the `GameFactory` interface to instantiate the specific components needed for the BetaCiv variant of the game. By using the factory pattern, the game can easily switch between different variants by changing the factory implementation.

- **Strategy Pattern**:
  - Used to define different strategies for game rules, time progression, unit actions, etc.
  - Example: [`CityConquestThenThreeStrikesWinnerStrategy.java`](src/main/java/hotciv/standard/variants/strategies/CityConquestThenThreeStrikesWinnerStrategy.java)
  - This class implements the `WinnerStrategy` interface to determine the winner based on a defined ruleset (city conquest and the three strikes rule). By using the strategy pattern, different winning strategies can be easily swapped and tested.

- **Observer Pattern**:
  - Used to update the game state and GUI components.
  - Example: [`CivDrawing.java`](src/main/java/hotciv/view/CivDrawing.java)
  - This class implements the `GameObserver` interface to observe changes in the game state and update the graphical user interface accordingly. By using the observer pattern, the GUI remains in sync with the game state without tight coupling.

- **Decorator Pattern**:
  - Used to add additional behavior to game components.
  - Example: [`GameLogDecorator.java`](src/main/java/hotciv/standard/variants/GameLogDecorator.java)
  - This class implements the `Game` interface and decorates the game with logging functionality, recording actions such as unit movements and end of turns. By using the decorator pattern, additional behavior can be added to the game without modifying the original game class.

- **Adapter Pattern**:
  - Used to adapt third-party code to fit the game's interfaces.
  - Example: [`FractalWorldLayoutStrategyAdapter.java`](src/main/java/hotciv/standard/variants/FractalWorldLayoutStrategyAdapter.java)
  - This class implements the `WorldLayoutStrategy` interface and adapts the `ThirdPartyFractalGenerator` to generate the world layout. By using the adapter pattern, the game can utilize third-party code without modifying it.

## Repository Structure
The repository is organized as follows:

```
.
├── .gitignore
├── .gitlab-ci.yml
├── build.gradle
├── gradle.properties
├── README.md
├── src/
│   ├── backlog.txt
│   ├── main/
│   │   ├── java/
│   │   │   ├── hotciv/
│   │   │   │   ├── client/
│   │   │   │   ├── common/
│   │   │   │   ├── framework/
│   │   │   │   ├── server/
│   │   │   │   ├── standard/
│   │   │   │   ├── stub/
│   │   │   │   ├── utility/
│   │   │   │   ├── view/
│   │   │   │   ├── visual/
│   │   │   │   └── variants/
│   │   ├── resources/
│   │   │   └── minidraw-images/
│   ├── specification.md
│   └── test/
│       ├── java/
│       │   └── hotciv/
│       │       └── standard/
```

## Acknowledgements
Henrik Bærbak Christensen, Department of Computer Science, Aarhus University

The course Software Construction and Software Architecture at Aarhus University

## License
This project is licensed under the MIT License.