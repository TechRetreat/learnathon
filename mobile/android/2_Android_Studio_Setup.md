## Setup Android Studio
###Download/Install Android Studio
To build our Android app we'll be using an IDE called Android Studio. An IDE is a programming environment that allows you to edit files, compile and run your code, and has other features built into it like debugging. Lets start by [Downloading Android Studio](http://developer.android.com/training/basics/firstapp/index.html) To get it to work you'll also need to install [Java](TODO:link).

###Download SDK tools
TODO: SDK stuff here or after? test fresh install studio on windows.

###Setting up a New Project
Once Android studio is set up we'll need to start a [New Project](http://developer.android.com/training/basics/firstapp/creating-project.html). Open Android studio and select 'Start a new Android Studio Project', use a Minimum SDK of API 16 (Jelly Bean), and when prompted to choose starting Activity, choose 'Blank Activity with Fragment'
Minimum SDK is the minimum version of the Android Operating System required to run your app. We chose 16 because most phones run that or newer, and it allows us to use new Android features.
The starting Activity is the code that Android Studio will generate to start your project. Well explain what Activity and Fragment are in a bit.
Android studio should now generate your starting project, and drop you into the screen pictured below (TODO: Get screenshot from windows AS).

### Running project on Device/Emulator
To make sure it worked correctly, click the green 'Run' arrow at the top of the screen. This will compile the starting code and prompt you for a device to run it on. If you are using the emulator for testing, check Launch Emulator at the bottom and click OK, this will start a virtual device and run the app on it (may take while to start). If you are using a real device, plug in your phone and accept the prompt on the device, then it should appear under 'Choose a running device'. Select it and click OK to run the app on your phone. Your app should open with a white screen that says 'Hello World'.

### Problems?
Thats what mentors are here for, feel free to ask for help with any problems you have, if you feel up to the challenge you can also try searching your problem on [StackOverflow](http://stackoverflow.com/) or just Google.