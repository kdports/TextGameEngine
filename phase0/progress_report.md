Brief Summary:

Specification: A graphical game studio for quickly creating a text-based adventure game with decisions at each scene.

CRC Model: Classes are divided into two broad groups: GUI classes and non-GUI classes. While both of these follow SOLID design principles, the split is focused primarily on the fact that our specification includes two separate parts-an engine and a game player. Therefore, we need different entities and slightly different logic for these two parts, but we've managed to maintain some overlap through creating a shared Game entity class. Additionally, we have a subsection of classes dedicated to saving and loading games created in our engine using the RDF format.

Scenario Walkthrough: Our scenario walkthrough shows a user working with both the engine and game player to create a text-based adventure game. It shows how the user might interact with the GUI classes to create the game, use the RDFSave and RDFLoad to transfer the game into the Player, and play through their game using the Player.

What has worked well: The separation of classes between those used by the engine and those used by the game has allowed different groups to work independently on the game and engine GUI. Additionally, they've been able to make progress without the RDFSave and RDFLoad classes being entirely complete, since test games can be created independently of a working engine or working game.

Struggles: There's been great progress on the engine's GUI, but it's still slow going. No one was familiar with javaFX (or other java GUI) before this project, so there's been a learning curve for it.

Group Work:

Kaspar P:
Has been working on RDFSave and RDFLoad classes, as well as general implementation of .trig files. Will continue to work on this.

Kevin P:
Has been working on CRC Design and writing specifications/progress report. Will be switching to helping code, most likely assisting the GUI group.

Kevin L:
Has been working on GameRenderer to display the Player class' logic and a Game instance's slides and decisions. Will work on additional code on the player side.

Greg S:
Has been working on the GUI for the engine and having it properly interact with lower level code. Will continue to work on this.

Jason Y:
Has been working on GameRenderer to display the Player class' logic and a Game instance's slides and decisions. Will work on additional code on the player side.

Jason S:
Has been working on the GUI for the engine and having it properly interact with lower level code. Will continue to work on this.