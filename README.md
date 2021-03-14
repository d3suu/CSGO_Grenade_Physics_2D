# CSGO_Grenade_Physics_2D
Physics emulation of thrown grenade in 2D space

## Example simulation
```
Angle: 80 degrees
Power: 1000
Max Bounces: 5
Surface Elasticity: 0.5
Gravity: 800 (CS:GO Default)
Frame Time: 0.1
```
> Image may need white background

![](/csgonade.svg)

## Output
Output data is raw gnuplot data. Check ![this](/gnuplot_file.txt) gnuplot file to build new images. Also make sure to define xrange and yrange in same size for angles to be correct.

## Talk
I wanted to know how exactly physics work in CS:GO, especially on objects like grenades, which can be thrown many ways (standing, jump boost, run boost...). This code was written after analysing ![this](https://github.com/perilouswithadollarsign/cstrike15_src/blob/f82112a2388b841d72cb62ca48ab1846dfcc11c8/game/shared/physics_main_shared.cpp) code. Many variables are unknown, so I had to guess. I think my code is easily modifiable to make what You need (for example: 3D space should be as easy as changing Y to Z space, and adding new Y, rotated 90 degrees horizontally.)
