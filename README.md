# ArkanoidGame

## Context of the project
This project was completed as part of the course "Introduction to Object Oriented Programming" using the Java programming language. 
The primary goal of this project was to learn key concepts in OOP, coding principles, design patterns, file hierarchy, and packages.
The project consists of four levels of an Arkanoid Game, which will be discussed in the following sections.

## Installation
I developed this project using the IntelliJ IDEA IDE (produced by JetBrains). If you use this IDE as well, you can clone this repository. Otherwise, you can create a new Java project in your preferred IDE and copy the source files from the src folder of this repository into the appropriate location in your project.

To use the GUI, you will need to add the biuoop-1.4.jar file to your project as a library. This file was provided to me by the university.

To compile the project, you can use the Ant build files that we used in this course. First, navigate to the project directory in the command line, and then run the following command:

```ant compile```

To run the game with the default level order (1 -> 2 -> 3 -> 4), use the following command:

```ant run```

If you want to play the levels in a different order, you can specify your desired order as command line arguments. For example, the following command will play the levels in the order 1, 3, 2, 1, 1, 3, 4, 3:

```ant -Dargs="1 3 2 1 1 3 4 3" run```

I hope you enjoy playing the game!


## About the game
There are four levels in this game (images in this order: 1, 2, 3, 4).

<img width="601" alt="image" src="https://user-images.githubusercontent.com/33784470/231135343-b71f6720-0ccd-4936-8cfa-3f5901974182.png">
<img width="600" alt="image" src="https://user-images.githubusercontent.com/33784470/231135439-14694104-8c11-4c87-95db-890b38a6eec3.png">
<img width="601" alt="image" src="https://user-images.githubusercontent.com/33784470/231135519-62b9dc03-dff6-4d34-9eca-bcdb01682d45.png">
<img width="602" alt="image" src="https://user-images.githubusercontent.com/33784470/231135595-84401a75-d13b-4533-bdfe-f3aa9a20fc65.png">

After finishing each level, you will move on to the next one until you either lose the game or win, in which case you will see the following screens:

If you lose:

<img width="601" alt="image" src="https://user-images.githubusercontent.com/33784470/231141171-6688f066-c88f-4916-beec-5a71b3722a83.png">

If you win:

<img width="600" alt="image" src="https://user-images.githubusercontent.com/33784470/231141354-af8cfbf7-5092-47e4-a76e-ceeccbc64575.png">

Additionally, you can pause the game at any point by pressing "p," which will bring up the following screen:

<img width="602" alt="image" src="https://user-images.githubusercontent.com/33784470/231141496-3ef97a4c-86d4-4a55-b841-c9c945f8257d.png">

I hope you enjoy playing the game. Despite it being only an introduction course, I really enjoyed it and learned a lot.

Thank you!





