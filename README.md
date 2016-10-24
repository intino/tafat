# Tafat #

Tafat is a framework for building simulators based on a Model Driven Engineering (MDE) approach. This development is supported by [Tara](https://bitbucket.org/intino/tara), a framework for building software based on MDE.

Tafat has been developed by [SIANI](http://www.siani.es) and is released under [GPL v3.0](http://www.gnu.org/licenses/gpl-3.0.en.html).

## Get started ##

To use Tafat, you need to install [Intellij](https://www.jetbrains.com/idea/). Once you have installed, please, install plugins available in the download section of this webpage: Tara and Legio.

To install them, go to the settings/plugins in Intellij. Click on install plugins from disk and select the files downloaded before (one by one).

## Our first Tafat project ##

Once you have the IDE ready for using Tafat, you can go ahead with the creation of your own simulator. To do so, please create a new project and select the Tara facet.

![Captura de pantalla 2016-10-24 a las 17.48.16.png](https://bitbucket.org/repo/xyM64b/images/1531657500-Captura%20de%20pantalla%202016-10-24%20a%20las%2017.48.16.png)

Click on next twice and write the project name you prefer (make sure it is written with the first in uppercase as it will be the name of the simulator you're writing and the name of the DSL/Metamodel that will be created). Once you click on finish, you'll see a structure of the project like this:

![Captura de pantalla 2016-10-24 a las 17.49.12.png](https://bitbucket.org/repo/xyM64b/images/3564859854-Captura%20de%20pantalla%202016-10-24%20a%20las%2017.49.12.png)

Open the "configuration.legio" file and make sure it is filled with the next repositories:

```
#!maven

Release(url = "http://artifactory.siani.es/artifactory/libs-release", "siani-maven")
Release(url = "http://artifactory.siani.es/artifactory/libs-snapshot", "siani-maven-snapshot")
Distribution(url = "http://artifactory.siani.es/artifactory/libs-release-local", "siani-maven")
Language(url = "https://artifactory.siani.es/artifactory/languages-release", "siani-maven")
```
Finally, modify the part of the configuration of the factory to something like this:

```
#!python

	Factory as Application
		generationPackage = "this.is.my.package"
		Modeling(language = "Tafat", version = "2.1.6")
```

Once you're done with it, reload legio by clicking the button in the left-upper corner. Now, you can start to make your own simulator. For that, make a package you want in the src folder and create a Tara file.

## Model your domain ##

Now that you have your first Tara file created, be sure to fully use all the advantages of the auto-completion tool included in Tara. By pressing ctrl + space, you'll be able to see all the options you have.

In this tutorial, we will model a very simplistic domain in which we are going to simulate a Fridge from the point of view of the power grid. For that, this is how we are going to describe the domain:


```
#!python

dsl Tafat

Entity Fridge
	var double:{W} installedPower
	var double:{W} idlePower
	var double:{W} power = 0W
	var word:{On Off} mode


Behavior Electrical on Fridge
	Implementation
		StateChart
			State Off > EntryAction(@setFridgePowerTo0)
			State On
				State CycleOn > EntryAction(@setFridgePowerToInstalledPower)
				State CycleOff > EntryAction(@setFridgePowerToIdlePower)
				Transition(CycleOn, CycleOff) > After(10 minutes)
				Transition(CycleOff, CycleOn) > After(30 minutes)
			Transition(Off, On) > Condition(@whenModeIsOn)
			Transition(On, Off) > Condition(@whenModeIsOff)
```

We hope most of the things are easy to understand. That's one of the strongest points of Tafat. However, as it's your first read, let me help you a little bit. First we are declaring a new type of Entity called Fridge which includes some variables. All double variables must be defined using the W symbol (Watts). The word variable works like an enum and, in this case, defined the two modes that a Fridge can have.

On the behavior side, we have defined a Statechart that has two main states (Off and On) and two inner states of the On (CycleOn and CycleOff). These two last states will allow to simulate the transition from use energy to cool or not depending on the internal temperature of the fridge (in this case, temperature is not being simulated). Apart from it, there are transitions for the main states and for the inner states of the On state. Probably you are wondering what is the @ symbol for. This is the way in which you can insert Java code in your models. Here, we provide you with how those methods are implemented, but we are very sure that after some trainings more, you'll be able to come up with the idea. 

To know where these methods are located, just point the selector in one of the methods that are in red and press alt + enter. This is the way to fix something in Intellij. This way it will suggest you to create the method and will give you until this place so that you can fill it out.


```
#!java

    public static boolean whenModeIsOn(ElectricalFridge self, int advancedTime) {
        return self.mode() == Fridge.Mode.On;
    }

    public static boolean whenModeIsOff(ElectricalFridge self, int advancedTime) {
        return self.mode() == Fridge.Mode.Off;
    }

    public static void setFridgePowerTo0(ElectricalFridge self) {
        self.power(0);
    }

    public static void setFridgePowerToInstalledPower(ElectricalFridge self) {
        self.power(self.installedPower());
    }
    public static void setFridgePowerToIdlePower(ElectricalFridge self) {
        self.power(self.idlePower());
    }
```

Please, make sure you're compiled the previous model so that all generated classes are available for writing this code. If not, you can do it by pressing control + shift + F9 selecting the module in the project view. If not, make also sure that "gen" folder is marked as generated sources (right click on the folder inside intellij/mark as...).

So now, we have made our first simulator, let's go ahead and use it!

## Creating our first simulation ##

Once we have defined our first simulator, let's call it "Smartgrid", we can go ahead and create our first model. As we are still testing our simulator, you can create a test folder (like before with "gen" folder, mark it as test folder). Let's create a new package and a Tara file (e.g. "NYC"). This time we will be using dsl Smartgrid (or the one you selected). Please, don't forget to place this name in the header of your file.

As before, here we provide you with an small simulation that will be helpful for you to start executing a simulation:


```
#!python

dsl Smartgrid

Simulation(from = "01/01/2016 00:00:00", to = "02/01/2016 00:00:00")

Fridge f1 as Electrical
	installedPower = 120W
	idlePower = 10W
	mode = On

UserInterface(title = "Smartgrid", logo = "grid-icon.png")
	LineChart(title = "Fridges consumption")
		Line(label = "F1 consumption", value = @fridge1Consumption)
```

This is your second Tafat file and we are pretty sure you fell much more comfortable with it. However, let's review it with you. First, we are declaring that we are doing a simulation that takes one day long. Secondly, we are declaring a Fridge f1 that is going to behave as an Electrical component. Then, we fill the parameters that are need by the fridge. Note that the variable power is not needed to be filled as it was filled to 0 previously. Finally, we declare an user interface in which we will have a LineChart showing just one line: the consumption of the fridge. 

This grid-icon.png you see here can be any picture you find out on internet. Just make sure to place it in a folder called "test-res" (create it if it's not already). This folder must be marked as test-resources. Probably at this moment you have a very good idea of how to proceed to take the consumption of the fridge. However, as we don't have this time the scope of the fridge, we provide you here some help:


```
#!java

    public static double fridge1Consumption(io.intino.tafat.UserInterface.LineChart.Line self) {
        return self.graph().rootList(Fridge.class).get(0).power();
    }
```

Finally, create a Java class including this code for starting the simulation:


```
#!java

    public static void main(String[] args) {
        Graph.load("NYC").wrap(SmartgridApplication.class, TafatPlatform.class).platform().execute();
    }
```

Now run this executable class and you should have your simulation running on your localhost port 8080: http://localhost:8080. If everything is right, you should see something like this. In this screen we have already executed the simulation a bit to show you the output.

![Captura de pantalla 2016-10-24 a las 18.18.06.png](https://bitbucket.org/repo/xyM64b/images/379274976-Captura%20de%20pantalla%202016-10-24%20a%20las%2018.18.06.png)

## What's next? ##

We recommend you to play using the auto-completion code and to join us in the community opening discussions in the issue tracker. However, we are hardly working on creating a documentation to let you know what else you can do with Tafat. In the meantime, you can also see other examples in this [link](https://bitbucket.org/tafat/).

### Acknowledgment ###

The simulations of energy systems in cities and territories have been the most significant use cases that has been addressed using Tafat. A strong collaboration has been made with [EIFER](http://www.eifer.org/) and [EDF](http://www.edf.fr/) for developing use cases, defining requirements and supporting the evolution of this platform.