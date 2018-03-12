# tetris-simple-swing

Throwback tetris in Swing, created just for fun :space_invader: and because I did a similar project a few years ago and lost the source code.

:warning::warning::warning: Project started March 7, 2018. Not functional but will update when basic tetris mechanics are done. :warning::warning::warning:
:warning: March 11, 2018: Added basic mechanics, Still lacks collision detection on rotation. :warning: 

![Tetris](https://i.imgur.com/sOeYrcR.gif)

---

## ```Board```

The game board is a 2D grid of 10 cols x 20 rows. Each game piece occupies four squares and each element (```Color[row][col]```) of the board holds the ```Color``` of the square at index (row,col). The default color represents an unoccupied square. 

---

## ```Piece```

Each piece is an array of (X,Y) points and has 4 forms (one for each 90 degree rotation). So the "L" piece for example can be represented in these forms:

#### Graphically:

![L1](https://i.imgur.com/BwikjfQ.png)
![L2](https://i.imgur.com/GSTufYk.png)
![L3](https://i.imgur.com/8ALZqZI.png)
![L4](https://i.imgur.com/8wlKCek.png)



#### As an array of ```java.awt.Point``` elements, ```Point[]```:

    {
        { Point(0,0), Point(0,1), Point(0,2), Point(1,2) },
        { Point(0,0), Point(1,0), Point(2,0), Point(0,1) },
        { Point(0,0), Point(1,0), Point(1,1), Point(1,2) },
        { Point(0,1), Point(1,1), Point(1,1), Point(2,0) }
    }



#### (X,Y) indices in a 2d grid:

          0     1     2                 0     1     2      
       +------------------> x        +------------------> x
    0  |(0,0)|_____|_____|        0  |(0,0)|(1,0)|(2,0)|   
    1  |(0,1)|_____|_____|        1  |(0,1)|_____|_____|   
    2  |(0,2)|(1,2)|_____|        2  |_____|_____|_____|   
       v                             v                     
       y                             y                     
    
          0     1     2                 0     1     2      
       +------------------> x        +------------------> x
    0  |(0,0)|(1,0)|_____|        0  |_____|_____|(2,0)|   
    1  |_____|(1,1)|_____|        1  |(0,1)|(1,1)|(2,1)|   
    2  |_____|(1,2)|_____|        2  |_____|_____|_____|   
       v                             v                     
       y                             y                     

---

To do list, updated ~~March 8~~ March 11, 2018: 
- [X] Program skeleton and basic class structure
- [X] Collision detection 
- [ ] Line clearing 
- [ ] Some kind of simple line clearing animation and visual tweaks 
- [X] Different colors for pieces
- [ ] Clean up code. For sure need to implement Iterable for custom objs, eliminate redundant variables, consolidate any code that is repeated. Hopefully around 500 lines total code
- [ ] Basic score keeping
- [ ] Levels and increasing speed. Want to increase speed rapidly for short games since this is just a demo and not practical
- [ ] Easter egg??

## Authors

* **Trent Fowler**
