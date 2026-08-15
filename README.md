# Elemental Platformer

<img src="platformer%20ss1.png" alt="Elemental Platformer Gameplay" width="500">

**TLDR:** A physics-based 2D platformer where players switch between elemental forms to overcome environmental hazards, solve puzzles, and navigate challenging levels.

**Empathize:**
I wanted to create a platformer that combines responsive movement with problem-solving, requiring players to understand each element's strengths and weaknesses to progress through each level.

**Research / Inspiration:**

- **Fireboy and Watergirl:** Inspired by its elemental mechanics, environmental puzzles, and simple timing-based gameplay.
- **King of Thieves:** Inspired by its responsive movement and platforming mechanics.
- **Hollow Knight:** Inspired by its fluid movement, challenging environments, and combination of platforming and enemies.

**Target Audience:**
Players aged 12+ who enjoy platforming, puzzle-solving, and adventure games.

**Define:**
I wanted to create a game where players can switch between **Fire, Water, Air, and Earth** forms. Each element has unique abilities and weaknesses, meaning players must choose the appropriate form to overcome different obstacles and environmental hazards.

**Elemental Mechanics:**

- **Water:** Can swim through water but cannot touch lava.
- **Earth:** Can move heavy objects but is unable to jump and moves slowly.
- **Air:** Can double-jump and glide but can be pushed around by strong wind.
- **Fire:** Designed to interact with fire-based environments and hazards.

**Gameplay / Features:**

- Four elemental forms with unique abilities and weaknesses.
- Element switching to solve environmental puzzles.
- Physics-based movement and platforming.
- Environmental hazards that interact differently with each element.
- Challenging platforming sections requiring precise movement.
- Puzzle-solving based on elemental abilities.
- Collectible elemental orbs that unlock different abilities.
- Multiple environmental hazards, including spikes, poison darts, and rolling boulders.

**Controls:**

- `A / →D` — Move left / right
- `W` — Jump

**Level Objective:**

Players start at the **bottom-left door** and must navigate through the temple to reach the **top-right door** while using the correct elemental form to overcome obstacles and hazards.

**Setting:**

An ancient temple filled with hidden pathways, environmental puzzles, and dangerous traps. Players explore the temple and collect elemental orbs that allow them to access and switch between different abilities.

The temple contains hazards such as **poison dart traps, rolling boulders, and spikes**, requiring players to carefully plan their movements and choose the appropriate elemental form.
# libGDX
A [libGDX](https://libgdx.com/) project generated with [gdx-liftoff](https://github.com/libgdx/gdx-liftoff).

This project was generated with a template including simple application launchers and an empty `ApplicationListener` implementation.

## Platforms

- `core`: Main module with the application logic shared by all platforms.
- `lwjgl3`: Primary desktop platform using LWJGL3; was called 'desktop' in older docs.

## Gradle

This project uses [Gradle](https://gradle.org/) to manage dependencies.
The Gradle wrapper was included, so you can run Gradle tasks using `gradlew.bat` or `./gradlew` commands.
Useful Gradle tasks and flags:

- `--continue`: when using this flag, errors will not stop the tasks from running.
- `--daemon`: thanks to this flag, Gradle daemon will be used to run chosen tasks.
- `--offline`: when using this flag, cached dependency archives will be used.
- `--refresh-dependencies`: this flag forces validation of all dependencies. Useful for snapshot versions.
- `build`: builds sources and archives of every project.
- `cleanEclipse`: removes Eclipse project data.
- `cleanIdea`: removes IntelliJ project data.
- `clean`: removes `build` folders, which store compiled classes and built archives.
- `eclipse`: generates Eclipse project data.
- `idea`: generates IntelliJ project data.
- `lwjgl3:jar`: builds application's runnable jar, which can be found at `lwjgl3/build/libs`.
- `lwjgl3:run`: starts the application.
- `test`: runs unit tests (if any).

Note that most tasks that are not specific to a single project can be run with `name:` prefix, where the `name` should be replaced with the ID of a specific project.
For example, `core:clean` removes `build` folder only from the `core` project.
