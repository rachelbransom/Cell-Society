Refactoring
===========

A design issue we had was that the way we assigned neighbors was not flexible. They were assigned in our Grid class (which had other jobs as well) and in a method called setFullSquareNeighbors. Even if part of the project wasn't to create new shapes for the grid, it still is good design to have the code flexible enough that we could add that.

I discussed with George how we wanted to fix this issue (since he initially wrote it but I would be the one working with that code later in the project). We decided to try different ways of fixing if to see what would work best. I moved Grid to it's own package and then changed the setneighbors method to be abstract and moved setSquareNeighbors to a sub Rectangle class. I think this is better because Grid was now different enough from cells to warrant its own package and because this design alls for anyone to come in and easily add new shapes. However, George also made a deisgn which created a new class SetNeighbors and we deicded to go with that. 

[Here is my commit.](https://git.cs.duke.edu/CompSci308_2016Fall/cellsociety_team23/commit/511ffda7827c345b12b06c38eaede439135a0e8a)
