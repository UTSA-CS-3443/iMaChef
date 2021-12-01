# iMaChef
Repo for team: D MART
Members: Dylan Tran, Marcus Lopez, Roderick Simms, Thomas Herron


# Description
iMaChef is a virtual Cookbook manager and step-by-step recipe assistant, featuring auto-play 
step progression, built-in timers, and per-step media displays.


# Usage
Import the repository into your Java IDE, compile, and run.

The current repository contains a sample cookbook.dat file in the data folder, and media in the media folder. If 
you wish to remove these recipes, simply clear the cookbook.dat file in a text editor (or replace with an empty 
file of the same name).

Media stored in the media folder will be addressed with relative file paths when used. Linking to media elsewhere
on your computer will use absolute file paths, making your recipe non-sharable without editing.

If you wish to undo a change to your cookbook, close the app and replace the cookbook.dat file with the 
cookbook.dat.bak file to undo the last save.


# Known bugs
Console may occasionally display errors related to cancelled or defunct timers. This error 
does not appear to affect app function at this time, but if you see a timer not working, try
switching steps and coming back.

The TextFlow in the Cook view sometimes cuts off recipe step descriptions. This issue appears
to be JavaFX related.


# Requirements and Compatibility
This app was coded with Java 9 and Scenebuilder 2.0


# Copyright and Legal disclaimer
This app is a project in an educational environment. It is not being shared, offered, sold 
for profit, or provided for any purpose other than grading and evaluation.

Do not copy or distribute this project for any purpose other than official grading.

The students responsible for this app attempted to source uncopyrighted or creative commons 
material wherever possible, however some media (including but not necessarily limited to video 
clips) comes from copyrighted sources. We do not attempt to claim any rights or ownership to 
outside photographs or instructional video clips included in this project, and they are only being 
provided as examples of possible material the app might host. Please do not redistribute the 
example media contained in this project.