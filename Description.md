# TerraIncognita

### Note

Lines marked with *italics* describe secondary features, which implementation should come only after main part would be completed. *Maybe i will add dragons here?*

## General description

Game is run on a rectangular playing field. Field features some sort of a labryinth with walls, exit, players and objects and possibly, some intresting places to visit. Main goal is to learn about the labyrinth's internals, capture a **treasure** and to leave the labyrintg carrying it. Player get information about the labyrinth via text **mesages** sent to them in response to various actions. Locations, players, and other entities are located inside the tiles of a game map. Walls (border objects) are located between tiles.

## Running the game
Game is separated into rounds, which are separated into turns. There are as many turns in a round as there are players, new round starts after the last of the played did its turn. Player turn are sequential, not simultaneous.


Each player's turn is separated into three phases: pre-move, move, post-move.

In the pre-move phase, all non-move actions are allowed, such as shoothing or throwing a grenade.
In the move phase, player can make a move somewhere. 
After the move phase, only certain actions are allowed, such as *using concrete*.

Every player action, whether it was sucseccful or not, produces a **message** for everyone to read. This messages are the only way players get new information, just as their text commands are the only way for them to perform actions.

## Game objects

### Entities 

#### Players 
Player is controlled by players text commands, and is capable of:
* Shooting a bullet from a rifle in a certain direction. This is a pre-move phase action.
	Bullet travels in this direction until it hits a wall capable of stopping it. Should this wall be adjacent to the player's location during the shooting, player will be informed of that. On the way, bullet kills every player and *bear* it passes through. *Other entities might have different reactions to the shots*.
	
	*Player has a reserve of 20 bullets, replenishable in an Arsenal.*

* Throw a grenade in a certain direction. This is a pre-move phase action.
	Grenade is a tool used to destroy nearby walls. Player tries to destroy a nearby wall. Grenade can have no effects on other enities, its sole target is one border-object. Common walls are destroyed. *Other border objects, such as outer labyrinth walls, might have different reactions.* Whatever the outcome is, player is notified of the result.
	
	*Player has a reserve of 2 grenades, replenishable in an Arsenal.*
	
* *Use concrete to reinforce a nearby wall or create a new one. This is both pre-move and post-move action (you can do it before or after the move.*
	*Concrete is, just like a grenade, targeted at an adjacent border. By using it, player increases the wall's resistance to grenades or creates a new wall in the previously vacant spot.
	You can carry just one concrete unit at any time, and it is replenishable in a Concrete Factory*
	
* Move.
	Unsurprisingly, this is a move-phase action. Player moves one tile in a specified direction. Movement might be blocked by walls *or somehow affected by other entities*.

	
Being dead
	Should the player die for some reason, it will become a ghost and cannot perform non-move actions. *Ghosts can regain their life in Hospital*.
	
#### *Bears*
*Bear is an entity indirectly controlled by players. After the sucseccful move of any player, every bear on the map tries to move one tile in the same direction. Should the bear come closer than 3 moves to a player, players will be notified of that. Sholud the bear end up in the same tile as player, player will die. Bears die when being hit by a bullet.*

### Borders
"Border" is a general term for an object located between tiles. It might even be blank, it is still a border object. Border objects are a targets for grenades *or concrete*.
#### "Common" walls
Walls block the flythrogh of bullets. They are destroyable via grenades, and *by applying concrete to them*, you can increase their resistance, making 2 or more grenades necessary to demolish a wall. Technically, amount of grenades necessary to wreck a wall is defined by its "thickness": a grenade reduces thicness by 1, while *concrete* increases it.
#### Outer walls
Outer walls surround the labyrinth from outside and are undestructible. *You can, however, waste concrete on them, although this is pointless.*

### Items
Items represent something that can be picked up by a passing player. They include:
*Bullets
*Grenades
*Treasure
**Concrete*
Unlike other players, items are visible if they are in the same tile as the player.

### *Locations*
*Located in tiles, maximum one location per tile. Effects of the location apply on player immidiately after he arrives at the tile.*

* *Arsenal: Replenishes up to 20 bullets and 2 grenades upon arrival*

* *Concrete factory: Gives 1 concrete to an arriving player (if he has no concrete.)*

* *Swamp: Makes arriving player skip his next movement phase.*


