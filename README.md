# Elemental Platformer

<p align="center">
  <img src="platformer%20ss1.png" alt="Gameplay screenshot from Elemental Platformer" width="700">
</p>

A physics-based platformer built with **Java** and **libGDX**. Navigate and avoid environmental hazards and use precise movement and timing to progress.

## Overview

The game challenges players to navigate the map using responsive movement and jumping mechanics while avoiding environmental hazards. Levels become progressively more challenging and require players to adapt their movement and timing.

## Features

- Physics-based player movement
- Platforming and jumping mechanics
- Environmental hazards
- Multi-level gameplay
- Progressively challenging level design
- Collision detection

## Design Decisions

- **Responsive movement**: Focused on responsive player controls so movement and jumping feel consistent and give the player precise control.
- **Environmental hazards**: Added hazards to make players consider their movement and timing rather than simply moving through each level.
- **Collision detection**: Used collision detection to determine interactions between the player, platforms and hazards and create consistent gameplay behaviour.

## Controls

| Key | Action |
|---|---|
| `A / D` | Move left / right |
| `W / Space` | Jump |
| `R` | Restart level |
| `Esc` | Pause / Menu |

## Technologies

- [Java](https://www.java.com/)
- [libGDX](https://libgdx.com/)
- [Gradle](https://gradle.org/)
- [LWJGL3](https://www.lwjgl.org/)

## What I Learned

- Building player movement and jumping mechanics
- Implementing collision detection
- Designing platforming levels
- Managing game objects with object-oriented programming
- Debugging movement and gameplay interactions

## Running the Project

### Requirements

- Java Development Kit (JDK)
- Git
- No separate Gradle installation is required because the project includes the Gradle wrapper.

### Run on desktop

From the project root:

    # macOS / Linux
    ./gradlew lwjgl3:run

    # Windows
    gradlew.bat lwjgl3:run

### Build a runnable JAR

    # macOS / Linux
    ./gradlew lwjgl3:jar

    # Windows
    gradlew.bat lwjgl3:jar

The generated JAR will be placed in:

    lwjgl3/build/libs

## Project Structure

- `core` - Shared game logic, entities, screens and gameplay systems
- `lwjgl3` - Desktop launcher built with LWJGL3
- `assets` - Game assets such as sprites and other resources

## Inspiration

The project draws inspiration from:

- **Celeste** - Precise platforming movement, timing-based challenges and level progression
- **Super Mario Bros.** - Accessible platforming mechanics and clear level navigation
- **Hollow Knight** - Environmental atmosphere, hazards and exploration-focused level design
