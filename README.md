# KapCoSumSonInc-app_project_355
app_project_355 created for KapCoSumSonInc

**Iteration #1 Summary:**

Our goal for this iteration was to create a proof of concept app where you could select a puzzle type and the app would generatea random scramble, as well as run a timer and save your times.  One of the biggest difficulties we had faced was utilizing the SQLite database as we didn't really have a lot of database experience.  The only user story we didn't entirely on was a full stats page, but we changed that into displaying stats on the bottom of the Timer display.  We also couldn't fully test a proper inspection for the happy path in the timer fragment due to limitations with runnables in espresso.

For the next iteration, we plan to refactor and clean up much of our existing codebase, as well as aesthetic UI changes.  We want to put in the Rubik's cube notation guide, as well as varying levels of difficulty, and the plain english step by step scramble guide.


**Iteration #2 summary**

Our goal for this iteration was to provide more functionality and information to the user, where they would have access to a history of their times, a way to translate the scramble notation into a plain english text guide, and a detailed guide for teaching notations for scrambles.  One of our largest challenges this iteration was communication and time management.  Other than that the database continued to give us challenges.  The thing that we couldn't fully deliver on was having different scramle generation algorithms as the algorithms were more complex than anticipated.  Our main goal for next iteration is to continue refactor code and make UI changes.  We may also implement a basic solving function.

Notes for TAs.  Right now the way you navigate to the tools and stats menu is swiping to the right.  We intend to add tabs to the top for the sake of clarity.  The notation guide is relatively basic, we intend to add a more detailed guide in the future.
