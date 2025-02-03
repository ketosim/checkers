# Checkers Game Implementation

This repository contains the classic **Checkers Game** using the **Processing** library for graphics and **Gradle** as the dependency manager. It is part of my OOP course. 

## Key Features
- **Game Mechanics:**
  - White pieces move down, and black pieces move up. White goes first.
  - Pieces move diagonally (one space or two spaces when jumping).
  - Capture by jumping over opponent’s pieces.
  - Pieces reaching the opposite end are promoted to kings and can move in both directions.

- **Interactive Gameplay:**
  - Highlight available moves in **blue** when selecting a piece.
  - Highlight the selected piece's cell in **green**.
  - Smooth movement when a piece moves between cells.
  - Automatic opponent piece capture and removal.

## Tools & Technologies
- **Processing:** For handling the game graphics and rendering.
- **Gradle:** Dependency and build management.

## How to Run the Project
1. Clone the repository:
   ```bash
   git clone <repo_url>
   cd <repo_name>
   ```
2. Build and run using Gradle:
   ```bash
   gradle run
   ```

## Watch Video
[Check out the gameplay video on YouTube](https://youtu.be/ihh6JDwSRXk)

