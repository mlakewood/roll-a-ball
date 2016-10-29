# Roll_a_Ball
The Unity3D initial tutorial done in Arcadia.

# Background

Unity3D is a game engine with a freely downloadable editor and IDE. The first tutorial is here https://unity3d.com/learn/tutorials/projects/roll-ball-tutorial and is 
a game where you roll a ball around an environment and pickup squares.

Arcadia is an extension to Unity3D that allows you to write your game in Clojure on the CLR.

This repo is the Roll a Ball tutorial in Unity written in Arcadia, with as much a one to one mapping so you can follow it along.

# How to get running

1. Download and install Unity -> https://store.unity.com/
   * This demo was created and tested with Unity 5.4.0f3 on Mac OS X
2. Clone this repo -> `git clone https://github.com/mlakewood/roll-a-ball roll-a-ball`
3. Install Arcadia -> `$ cd roll-a-ball; ./install_arcadia.sh`
4. Start Unity3D and open the above-cloned roll-a-ball project.
5. Navigate to the project view and open the `Roll a Ball` scene in the main Assets folder. 
   * You should see a number of game objects including a StartObject in the Hierarchy pane.
6. Once Arcadia has started connect the Arcadia REPL, e.g.:
```
~/src/unity/roll-a-ball$ ruby Assets/Arcadia/Editor/repl-client.rb
; Arcadia REPL
; Clojure 1.7.0-master-SNAPSHOT
; Unity 5.4.0f3 (a6d8d714de6f)
; Mono 2.6.5 (tarball)


user=> 
```
7. Import the `rollball.core` namespace and evaluate: `(use 'rollball.core)` then `(in-ns 'rollball.core)`
8. Run the following code:
```
rollball.core=> (require '[arcadia.core :refer [hook+]])
nil
rollball.core=> (def start-object (first (objects-named "StartObject")))
#'rollball.core/start-object
rollball.core=> (hook+ start-object :start #'start)
#unity/Object 9812
rollball.core=> 
```
9. Press the Play button in Unity and you should get the playfield up and be able to move the ball around with the arrows.


