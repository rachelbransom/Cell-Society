### Cell Society: Refactoring Assignment ###

Design Issue
===
I noticed an inconsistency throughout the simulations where each one's updateGrid method was similar, but not identical to the others. Therefore, I pulled part of this method up into the AbstractSimulation class, so that each simulation called the super method and then called the methods specific to the simulation afterwards. Since there were calls to methods in different places within the common update grid instructions, I created two different updateGrid and updateAndChangeGrid methods in the abstract class, that work together.

Explanation
===
I believe this version of code is better because it increases the level of consistency across the simulations. This is part of a much larger overhaul to make the simulations act more alike and thus work better together throughout the third sprint.

The tradeoff with this change is that the code is better designed (or at least is moving in the right direction) and there is greater consistency across the simulations, but it increase the complexity of the program and requires multiple different calls instead of a single updateGrid call.


Link to Commit
===

https://git.cs.duke.edu/CompSci308_2016Fall/cellsociety_team23/commit/4b7223d0626e7f1ab75b57bb1f5ab91098152d0c