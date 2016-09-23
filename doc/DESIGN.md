### Cell Society: Design ###

Introduction
===

Flexibility - Adding a new type of game should not involve changing core parts of our code. Size of the cell has to be flexible because the window dimensions are constant. Speed has to be flexible to user input.

Architecture - main class, UI, visualizer, logic, XML reader, rules with fire, life, water, seg extensions and cell class with enum state as a nested class.
Visualizer will call init in logic (rules, state, size). Logic uses the rules to make a second double array - next state- and pass the states of that array to the visualizer. 

Overview
===

# Classes:
1. Main: 
  1. Responsibility - starts up simulation, sets up stage and scene
  2. Collaborators - U/X

2. User Experience Module
  1. Responsibility - makes visualization window
  2. Collaborators - GridVisual

3. GridVisual
  1. Responsibility - initializes the grid, calls init in logic that returns an array of cell states
  2. Collaborators - Logic, U/X

4. Logic
  1. Responsibility - initializes / resets grid back-end (param: game being played and given configuration) [defines who is neighbors with whom depending on location of the cell in the grid ]. Returns state of grid. When told, advances state of grid by one step. 
  2. Collaborators - Gives grid state to visualizer. Gets initialization parameters by gridVisual. 

5. Cell
  1. Responsibility - getter for cell states, can interact with other cells and decide state upon interaction. Knows its neighbor cells to interact with.
  2. Collaborators - Logic, and other cells.

User Interface
===

As shown in the picture above, the grid will take up most of the visualization window, with buttons underneath. There will be buttons to start and stop the program, to go a single step into the program, and to reset the grid. There will also be a text field where the user will input the speed at which the program should run (there will be limits on the speed). In addition, there will be a drop down menu for the user to chose the XML file.

Erroneous situations:
* Bad input
* Empty data
* Outside our limits for speed


Design Details
===
Main extends the application class, and thus instantiates the user experience module and gives it inputs. The the U/X initializes as a blank slate with instructions but has the menu shown above. It’s given a filename which is handed off to XML reader, which returns a set of parameters ( which will likely be encapsulated in a wrapper object ). The U/X then instantiates the grid visualizer, which initializes the grid logic which initializes the actual grid of cells with the given starting states. The U/X tells the visualizer what speed it should be stepping. To initialize the grid, the logic creates new instances of all the cells, and tells them which neighbors they should interact with. The grid logic advances in state by having every cell interact with its neighbors and store that new state in a new grid, so at the end of the advance phase the old grid becomes the new grid and thus all logic is performed in parallel. The visualizer then pings the grid logic to get the current state of the grid as well as to advance to the next state. This continues until the U/X is given a new initialization to reset to.

#### Use Cases

* Middle Cell: Logic tells a cell who its neighbors are when it is initialized. Cell will iterate over all of its neighbors and counts how many are dead and how many are alive. If the parameter from the XML file that is now in cell (given by logic) is fulfilled, the cell will be given a new state in the new grid, which is passed to visualizer once all states are set.

* Edge Cell: Logic tells a cell who its neighbors are when it is initialized, so edge cells will have fewer neighbors. The cell will then behave in the same way as a middle cell.

* Move To Next Generation: U/X pings GridVisualizer which pings Logic to advance state. Logic advances by putting next logical state of each cell in newer grid, and the old grid becomes the new grid.

* Set Simulation Parameter: XML reader gives the parameter to Simulation, which passes rules down to cells, which (under the design considerations) may use one of several means to coordinate the rules of interaction

* Change Simulation: A new setting is chosen by the user, this is sent to the U/X module which reads XML once again. This info is sent down to the visualization module




Design Considerations
===
XML Formatting is unknown at this time, so implementation of the XML-parsing module is unkown.
Cell interaction modes
Using different subClasses of parent Cell class to define interaction for different simulation
Helpful to add cellFactory to parse name of simulation with rules
E.g. fireCell, gameOfLifeCell, waTorCell, segregationCell
Issue is that segregation cell needs information about the whole grid to move when unsatisfied 
Use actors (i.e. fish, fire, person) to move between cells upon interacting
Helpful to add actorFactory
Same issue with segregation actors
Unsolved: How does cell get information to move to random spot in segregation game

Team Responsibilities
===

* By Monday we should have the public method class infrastructure up to facilitate individual work
* By Thursday must be able to run but with bugs
* By Sunday must have fully featured code ready for refactor and debugging

#### George
- XML Read / Parsing
- Cell method Infrastucture 

#### Rachel
- U/X menus and design
- Main

#### Diane
- Logic
- Grid Visualization
