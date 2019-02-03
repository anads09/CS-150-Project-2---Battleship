A command line battleship game in which the user plays against a computer opponent that takes random shots, not shooting the same space more than once.

The player's shot is received by printing the board after every turn and prompting the user for the row and column of the space they want to shoot at.

A near miss is when a shot lands next to, or digonal to a ship.
  ...
  !.! <-- There is a ship between these two near misses.
  !.. <-- That is a near miss because of the ship diagonal from it.
  
  .xo
  .o. <-- Those shots were regular misses because the ship on the x was destroyed before those shots were fired.
  ...
