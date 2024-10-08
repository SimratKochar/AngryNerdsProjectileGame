# Projectile Motion Game

## Project Description

As the name suggests, this project is based on the physical phenomenon - projectile motion. Some **basic features** of the game are:
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

