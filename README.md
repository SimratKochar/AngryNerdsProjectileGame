# Angry Nerds - Projectile Motion Game

## Project Description

**Welcome to Angry Nerds!** As the name (a parody on Angry Birds) suggests, this project is based on the physical phenomenon - projectile motion. Some **basic features** of the game are:
- There will be a "canon"-like object at the left of the window that shoots projectiles (circles), one at a time.
- The trajectory of the projectile will be based on two parameters - initial speed and angle relative to the ground - which will be input by the user. 
- The "target" circle will spawn in a random location in a region towards the right of the canon.
- The objective is to hit the target with the projectile by inputting the required parameters.
- The user will have unlimited tries to hit the target with their projectile.
- As soon as the target is hit, the game will end and all the parameters tried will be displayed in the order of inputs, with the correct parameters highlighted.

### Who will use this application?
This application would be very useful for anyone learning projectile motion, or for teachers teaching the same topic to students. It is a great way to help  visualize projectile motion. Being a game, it will make learning about the subject very interactive and engaging, while helping them understand how changing the parameters can affect the trajectory of the projectile.

### Why did I choose this application?
I first learned about projectile motion in high school. It was pretty difficult for me to understand the topic and I mostly just memorized the equations that would be required on an exam without giving it more thought. Having an application such as this not only would have helped me see what the equations actually mean , but also would have made learning this topic much more enjoyable. Pursuing a combined major in Computer Science and Physics also attracts me more towards this project, as it combines both fields.


## User Stories
**1. The X and Y classes (I want to be able to add one set of parameters to a collection of parameters):** In this project, the X class will be the Projectile object and the Y class will be the ProjectileList class. With each attempt, the most recent projectile will be added to the list of projectiles.
\
2. At any point during the game, I want to have the option to be able to access the parameters that have been input until now.
\
3. I want to see the Attempt Number at all times during the game.
\
4. I want to be able to see an approximate flight time for the projectile, so I can better understand the physics behind it.

### Added user stories for Phase 2:
\
5. When I quit the game, I want to have the option to be able to save the attempts made till now.
\
6. When I start the application, I want to have the option of either starting a new game, or loading a saved one.

## Instructions for end user
When you start the game, you will see three different panels. The top panel displays the number of attempts you have made till now and your previously entered parameters. You also have a lot of controls for the game here. The bottom projectile shows you the maximum height and total flight time of the last projectile you launched. In the main panel, which is black, you will see a red circle which is your target. The projectile is launched from the bottom left corner. 

Your mission, should you choose to accept it, is to enter the initial velocity (with a good starting point being 50) and the initial angle for the projectile and click the Launch buttom, to hit the red target. You will see the projectile (blue circle) on the screen if your parameters were valid. 

**Make sure that your angle (in degrees) is between 0 and 90, and that you are entering only numbers without spaces for both velocity and angle.**

- The first additional action related to "adding Projectiles to ProjectileList" is removing the last added projectile. In case you entered crazy parameters and don't want it to affect your score, you can click the "Remove Last Projectile" button to preserve your score (and pride!).

- The second additional action is to relaunch the last projectile. This can be done by clicking the "Replay Last Projectile Button". This is particularly useful if you come back to the game after a while, to see the trajectory of the last projectile again.

- To locate my visual component... you can play the game!!! There is a blue moving projectile, and also a (not so) hidden image that is only visible when you hit the target. 

- If you want to save your progress, you can click the "Save Game" option and you will have the state of the game ready to load whenever you want. Do remember that you can have only one saved state at a time. If you save the game in another state, the previous file will be overwritten.


- To load the saved state at a later point, you can click the "Load Game" button. 

- You can also start a new game whenever you want by clicking the "New Game" button. It will create a new window with a new target and no attempts made.


# Phase 4: Task 2

Actions completed: \
New projectile launched and added to Projectile List \
New projectile launched and added to Projectile List \
A projectile was removed. \
New projectile launched and added to Projectile List \
A projectile was replayed. \
New projectile launched and added to Projectile List

# Phase 4: Task 3

1. One way I would refactor my code is by applying the Observer-Observable pattern. Since the StatsPanel and ScorePanel is updated only when the projectile is launched, ANGame can be made an Observable and the StatsPanel and ScorePanel can be observers.

2. Another possible way of refactoring could be to remove the association between GameConsole and Projectile and ANGame and Projectile. This can be done such that the classes ANGame and GameConsole get the most recent projectile from the ProjectileList class itself. There would possibly still be a dependency to Projectile in those classes though.