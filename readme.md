# MAZE ESCAPE: Jerry v.s Tom
<img src = "https://github.com/VincentComp/Comp3111F23G18/blob/Master/Team%20Formation.png">


## Introduction
This is a 2D mazes chasing game featuring the theme Tom and Jerry. This game is designed by the Comp3111 2023Fall Group 18. 

## User Guide
1. Run the MainGame.java
2. You will see the main menu with three buttons
<br>Reminders:**Please close the new window before opening another new window (See Important Notes)**



   * 2.1 "Function A: Maze Generator"
     * 2.1.1 After clicking this button, you will see a 30*30 2D maze (stored in  **"maze.csv"**)is generated
     * 2.1.2 The maze would always contain more than one path connecting the entry point and the exit point
     * 2.1.3 {Grey: Wall , White: Path}
     * 2.1.4  Close the window to continue.


   * 2.2 "Function B: Shortest Path"
     * 2.2.1 Similar to "Function A",  after clicking this button, you will see a 30*30 2D maze (stored in  **"shortest path.csv"** (row,col)) is generated and 
     * 2.2.2 There is a yellow path connecting the entry point and the exit point, which indicates the shortest path from the entry to the exit
     * 2.2.3 Close the window to continue


  * 2.3 "Function C: Has fun and play"
    * 2.3.1 After Clicking this button, you will see the 30*30 2D maze is generated together with 2 chracters <br> i.e. {Blue square: Tom, Orange square: Jerry}
    * 2.3.2 After 1 Second, Tom(Blue) would start chasing Jerry(Orange)
    * 2.3.3 You have to control Jerry(Orange) with **arrow keys** to escape from Tom and arrive the exit
    * 2.3.4 If you arrived the exit, a win screen will be shown
    <br> &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; 
    If you are caught by Tom, a lose screen will be shown
    * 2.3.5 Close the window to continue


## Important Notes
1. To prevent interference between different windows, we has restricted **at most 2 windows** (including the menu) can exist at a time. In other words, **please close the new window before opening another new window**. 
2. We assume that the leftmost Vertex is always the entry point, while righmost Vertex is always the exit point**
3. The size of the window has been fixed
4. If the files doesn't exist after clone, please rebuild the project with the Maven
