**Kevin Portinga**
---
Since phase 1 I have extended a number of features of our software. I have added an inventory system, allowing decisions to give items which can later enable specific future decisions. Additionally, I made decisions able to rely on other decisions, allowing a player’s previous choices to be factored into what options they have available to them in the future. Finally, I added pop-up error boxes that appear if a user attempts to save or playtest an invalid game.

https://github.com/kdports/TextGameEngine/pull/40 <- This pull request was my addition of decision conditions to the project. This is our most significant feature extension in phase 2 and is extremely helpful for users. It built on pre-existing entity, use case, and GUI work.

**Kevin Li**
---
Refactored the code for game player from Swing to JavaFx alongside Jason you to keep the consistency of the GUI layer. Added an inventory button that allows you to check what items you have during the game. I also added the title screen for the game along with a settings button that allows you to choose which theme you want the game to use and what speed the animation for the text should have.

https://github.com/kdports/TextGameEngine/pull/46 <- This pull request was me and Jason You’s refactoring of the game player from swing to JavaFx and it was also my addition of the title screen to the game player. It was combined with Greg’s changes to the themes.

**Jason You**
---
Rewrote all of the code related to the game player to use JavaFx instead of Swing so that our code wasn’t using 2 packages for the GUI. Also made the main title screen that either opens the game player or editor. Fixed various bugs and made many small qol changes.

https://github.com/kdports/TextGameEngine/pull/12
This pull request was my addition of a fully working basic game player. This is a fundamental aspect of our program as there isn’t a point in making a game if you can’t play it.

**Jason Sastra**
---
Added scrollbar into editor game so that it is possible to make large games. Helped with implementing the theme editor. Implemented TestFx to test game editor. Fixed various small bugs regarding the editor game.

https://github.com/kdports/TextGameEngine/pull/20


https://github.com/kdports/TextGameEngine/pull/21
The overall JavaFx game editor that is integrated to be able to load, delete, edit, connect, and change slides and decisions in the editor game. Both of these pull request does that




**Greg Sherman**
---
Revamped the entire look of the editor. All slides, decisions, and window changes were made to follow a nice new look. Added the theme capabilities for the editor (with the help of Jason Sastra on the slides and decisions) and for the player. The themes can be infinite due to the open/closed principle. Made the program much easier to use, can type directly into a box to have it save, no more window for the slide, removed a lot of redundant buttons and dialogue. Implemented RDFSave and RDFLoad with kaspar to support the new decision and item conditionals. Wrote multiple sections of this design document as well.

Pull request 55: https://github.com/kdports/TextGameEngine/pull/55
This is the updated RDF saving and loading, it’s a crucial part of the game that was changed to support conditionals.

Pull request 46: https://github.com/kdports/TextGameEngine/pull/46
Added the theme ability and applied it to the new javaFX gameplayer and the editor.

**Kaspar Poland**
---
Worked on the RDF Ontology that outlines the metadata of the application, all stores in TGEO.java. Worked with Greg to implement the RDFSave and RDFLoad functionality with working inventory conditionals, working decision conditionals. Worked through creating new instances of an RDF list. Changed the storage of decisions from their in-memory toString() outputs to their RDF URIs, making our .trig output file entirely self-sufficient (could be imported anywhere and understood). 
