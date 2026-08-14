# Elemental Platformer

**TLDR:** 
A physics-based platformer where players navigate challenging levels, avoid deadly hazards, use movement and environmental mechanics to survive.

**Empathize:**
To make a 2D platformer that challenges players to focus on problem-solving combined with platforming skills

**Research / Inspiration:**
- Fireboy and Watergirl: Love the elemental-style puzzles and simple timing mechanics.
- King of Thieves: Like the smooth platforming and responsive jumping controls.
- Hollow Knight: A Combination of enemies and overall movement fluidity.

**Target Audience:**
Players aged 12+ who enjoy puzzles and adventure games.

**Define:**
I want to create a game where the player can switch between Fire, Water, Air and Earth forms. Each element has strengths and weaknesses and different hazards are in effect based on the element you are:
- Water: Swim through water; can’t touch lava
- Earth: Move heavy objects; unable to jump, and is slow
- Air: Double-jump/glide; can be blown away

**Controls:**
- Arrow keys move left, move right, jump, duck
- Numbers 1–4 → switch elements
- Start at the bottom-left door, finish at the top-right door

**Setting:**
In an old, ancient temple, where you find/collect orbs that give you different abilities.
The obstacles in this setting could be poison dart traps, rolling boulders (est: Indiana Jones) and spikes

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
