# tetris-simple-swing

Throwback tetris in Swing, created just for fun :space_invader: and because I did a similar project a few years ago and lost the source code.

:warning::warning::warning: Project started March 7, 2018. Not functional but will update when basic tetris mechanics are done. :warning::warning::warning:


![Tetris](https://i.imgur.com/wliv17u.gif)

The game board is an array of custom `Piece` objects. 10 cols x 20 rows (22 if you count the top 2 rows above the view for staging pieces). Each piece is an array of (X,Y) `Point` Objects, so the Square block would be represented as: 



       y
       ^
       |_____ _____ _____ _____ _____ _____ _____ _____ _____ _____ _____ 
    22 |_____|_____|_____|_____|_____|_____|_____|_____|_____|_____|_____|
    21 |_____|_____|_____|_____|_____|_____|_____|_____|_____|_____|_____|
    20 |_____|_____|_____|_____|_____|_____|_____|_____|_____|_____|_____|
    19 |_____|_____|_____|_____|_____|_____|_____|_____|_____|_____|_____|
    18 |_____|_____|_____|_____|_____|_____|_____|_____|_____|_____|_____|
    17 |_____|_____|_____|_____|_____|_____|_____|_____|_____|_____|_____|
    16 |_____|_____|_____|_____|_____|_____|_____|_____|_____|_____|_____|
    15 |_____|_____|_____|_____|_____|_____|_____|_____|_____|_____|_____|
    14 |_____|_____|_____|_____|_____|_____|_____|_____|_____|_____|_____|
    13 |_____|_____|_____|_____|_____|_____|_____|_____|_____|_____|_____|
    12 |_____|_____|_____|_____|_____|_____|_____|_____|_____|_____|_____|
    11 |_____|_____|_____|_____|_____|_____|_____|_____|_____|_____|_____|
    10 |_____|_____|_____|_____|_____|_____|_____|_____|_____|_____|_____|
    9  |_____|_____|_____|_____|_____|_____|_____|_____|_____|_____|_____|
    8  |_____|_____|_____|_____|_____|_____|_____|_____|_____|_____|_____|
    7  |_____|_____|_____|_____|_____|_____|_____|_____|_____|_____|_____|
    6  |_____|_____|_____|_____|_____|_____|_____|_____|_____|_____|_____|
    5  |_____|_____|_____|_____|_____|_____|_____|_____|_____|_____|_____|
    4  |_____|_____|_____|_____|_____|_____|_____|_____|_____|_____|_____|
    3  |_____|_____|_____|_____|_____|_____|_____|_____|_____|_____|_____|
    2  |_____|_____|_____|_____|_____|_____|_____|_____|_____|_____|_____|
    1  |(0,1)|(1,1)|_____|_____|_____|_____|_____|_____|_____|_____|_____|
    0  |(0,0)|(1,0)|_____|_____|_____|_____|_____|_____|_____|_____|_____|
       --------------------------------------------------------------------> x
          0     1     2     3     4     5     6     7     8     9    10



To do list, updated March 8, 2018: 
- [X] Program skeleton and basic class structure
- [ ] Collision detection 
- [ ] Line clearing 
- [ ] Some kind of simple line clearing animation
- [ ] Different colors for pieces and visual tweaks
- [ ] Clean up code. For sure need to implement Iterable for custom objs, eliminate redundant variables, consolidate any code that is repeated. Hopefully around 500 lines total code
- [ ] Basic score keeping
- [ ] Levels and increasing speed. Want to increase speed rapidly for short games since this is just a demo and not practical
- [ ] Easter egg??

## Authors

* **Trent Fowler**
