## What is Continuous Integration (CI)?

[Wikipedia][1] tells us that:
>In software engineering, continuous integration
>(CI) is the practice of merging all developer working copies to a shared
>mainline several times a day.

This, although true, doesn't really tell us:
 - What CI is trying to achieve and solve
 - How to go about doing CI
 - What the natural consequences of a CI workflow are (both good and bad)

### What is CI trying to solve

 The main the thing CI is trying to achieve, is the prevention of "integration problems". This generally describes the problem of trying to combine code that multiple people are working on at the same time. All of this should be done without breaking any of the pre-existing code.

### How does CI do this

 The idea is that: We have a CI workflow - one where the code is frequently merged into a central location. This means that any kind of merge problems that arise will be so small, and will be highlighted so quickly that they will be easy to resolve. This should also have the effect of making these issues occur less frequently.

### What do CI systems require

 While this sounds simple, achieving this workflow can require a lot of other problems to be solved first. For instance, you need to have a build system that compiles your entire project quickly and easily. You also need a similar system to run unit test, which in themselves, need to be kept up to date. 

 It is also very important that this entire workflow is easy and intuitive so that it doesn't detract from other aspects of development.

### What are the extra bonuses

 On the other hand, once it has been achieved, other neat bonuses spring out, like the ability to regularly test and deloy new features, as well as notifying team members who have broken your product. Some systems allow you to automatically deploy code when all of your unit tests pass.

 CI also makes it easier for new developers to get started with development, as it provides a standard environment which code is guaranteed to function on. This means that any features which only work locally on a developer's system, will be spotted early on and before they can break other developers' code.

## Getting started

Now we're going to get hands on with a build system called Gradle which allows us to get started with a java project very easily.

In a terminal, we can use gradle to start our project...

```bash
$ mkdir ci-workshop
$ cd ci-workshop
$ gradle init --type java-library
```

Gradle will now go away and set us up a new Java project, with some skeleton code. 

A lot of files which Gradle generates are not useful to us as programmers, so lets take a look at what we need to know about...

### build.gradle
This file gives Gradle some key information it needs in order to perform a build. Technically, this is a script is written in a language called `Groovy` but don't worry about the syntax.

#### Plugins
Plugins provide functionality that will help us as a developer. For instance, they add new tasks (e.g. JavaCompile), conventions (e.g. Java source is located at src/main/java), etc.

Gradle automatically adds the `java-library` plugin that allows us to do things like building and testing our project...
```groovy
plugins {
    id 'java-library'
}
```
#### Dependencies
Here we can declare the external code that our project needs in order to run. Gradle has generated some example dependencies which serve different purposes. The first is exposed to end users, the second to developers only, and the last is only used during testing.

```groovy
dependencies {
    // This dependency is exported to consumers, that is to say found on their compile classpath.
    api 'org.apache.commons:commons-math3:3.6.1'

    // This dependency is used internally, and not exposed to consumers on their own compile classpath.
    implementation 'com.google.guava:guava:23.0'

    // Use JUnit test framework
    testImplementation 'junit:junit:4.12'
}
```
#### Repositories
This tells Gradle how it can find dependencies. Here Gradle has defaulted to JCenter which is an online place to share and distribute packages.

```groovy
repositories {
    jcenter()
}
```

### gradlew and gradlew.bat

We can see that Gradle has generated `gradlew` and `gradlew.bat` which seem like gibberish at a glance. 

The contents of these files is not important, but they allow developers who don't have Gradle already installed to easily build the project. It will invoke a specific version of Gradle, downloading it first if necessary.

### settings.gradle

This file is another `Groovy` script which allows you to specify which projects to include in your build. It means that you can specify an arbitrary file structure for the projects in your build. The automatically generated file is pretty self explanatory...

```groovy
rootProject.name = 'examples'
```

### Source Code
You'll now notice that you have a `/src` directory in your workspace which contains a dummy Java library and a test class.

You can now continue to add classes to these directories and Gradle will know to compile them as parts of your projects.

## Building your Gradle project
You may be thinking "Well this is all well and good, but how do I actually build the darned thing!?".

At this point, you know everything you need to know to get started, so head to your terminal and type...

```
$ gradle build

BUILD SUCCESSFUL in 2s
4 actionable tasks: 2 executed, 2 up-to-date
```

...well that was fun. What just happened?

When we typed `gradle build`, Gradle went through all our files and compiled them. The resulting files were put in `/build` which you can look through in your own time.

To convince yourself that the build is actually doing something, try breaking the java in `Library.java` and then type `gradle build` again.

For the time being, we're going to try something a bit more interesting than what Gradle generated for us. Head to `Library.java` and swap out the skeleton code with the following method for calculating a fibonacci number...

```java
public int fib(int n) {
  if(n <= 1) {
    return n;
  }
  int fib = 1;
  int prevFib = 1;
  for(int i=2; i<n; i++) {
    int temp = fib;
    fib+= prevFib;
    prevFib = temp;
  }
  return fib;
}
```

If you now try to run `gradle build` then you'll notice that our generated test from earlier is failing. For the moment, we can skip running the tests and only execute a build by running...

```bash 
$ gradle build -x test
```

In general, it's not a good idea to avoid running tests unless they are going to be run elsewhere so lets go back to `LibraryTest.java` and fix the legacy test...

```java
@Test public void testFibonacci() {
  Library classUnderTest = new Library();
  assertEquals(55, classUnderTest.fib(10));
}
```

This should now work! Now let's take a look at what would happen if someone were to break the function `fib`. Let's imagine some big shot comes along and see's the following...

```java
int temp = fib;
fib+= prevFib;
prevFib = temp;
```

... and decides that we don't need this variable `temp` so instead writes...

```java
fib+= prevFib;
prevFib = fib;
```

Now if we build our project 

```bash
$ gradle build --info
``` 

(`--info` allows us to see our error) then we'll get something like the following...

```
LibraryTest > testFibonacci FAILED
    java.lang.AssertionError: expected:<55> but was:<256>
        at org.junit.Assert.fail(Assert.java:88)
        at org.junit.Assert.failNotEquals(Assert.java:834)
        at org.junit.Assert.assertEquals(Assert.java:645)
        at org.junit.Assert.assertEquals(Assert.java:631)
        at LibraryTest.testFibonacci(LibraryTest.java:10)
```

## Using a CI server
In most real scenarios, you'll want to do more than just building and testing a program locally. You'll want to be able to share code with your team mates and run builds and tests on central server. In a minute, we'll be showing you how to use Travis CI to do exactly that.

### Adding your project to GitHub
First, we need to host our code on GitHub. Initialise your directory as a git repository...

```bash
$ git init
```

If we now write...

```bash
$ git status
```

...you should see Git telling you that there's a lot of `Changes not staged for commit`. Gradle has generated/built a lot of files (i.e. in the `/build` directory) that aren't part of our source code, and that we do not want other users to have. There are many reasons for not wanting to check these items into source control, including...
 - Ideally, developers should generate any build files themselves so they aren't using old versions of the build
 - We do not want to take lots of time to download these files when we pull from a repository
 - They files will change whenever our code changes and if these changes are are part of the Git history then it can be hard to see what code was changed if you are sifting through lots of build files

In order to tell Git to ignore these files, we can add a file called `.gitignore`. This will contain a list of all the things we want git to ignore. 

Head to [gitignore.io](https://gitignore.io) and type in `Gradle` to generate a `.gitignore` with the right contents for a gradle project.

Now we can continue adding our code to GitHub.

```bash
$ git add .
$ git commit -m "Initial commit"
```

Now head over to [GitHub](https://github.com) and create yourself a new repository.

Lastly, add your new remote to your local repository and push your changes...

```bash
$ git remote add origin git@github.com:INSERT_USERNAME_HERE/ci-workshop.git
$ git push -u origin master
```

### Adding your project to Travis

Travis is a great system for running tests online and is free to use for open source projects. Luckily for us, it works natively with Gradle too!

First, let's head to [Travis CI](https://travis-ci.org). Log in with your GitHub account, click on your name in the top right hand corner, and head to your profile. There should be a list of your repositories and switches next to them. Activate our CI Workshop repository and lets head to the main page.

![Travis Repository Switch](images/travis-switch.png)

You should now have an empty section for the build of your project - there's only one step left to go now!

If we head to the [documentation](https://docs.travis-ci.com/user/languages/java), then we can see that in order for Travis to build our project, our project needs to contain a `.travis.yml` file.

On unix systems, this can be done easily...

```bash
$ echo "language: java" > .travis.yml
```

- Note: Windows users who aren't using 'Git Bash' may find it easier to create this file using Notepad

Now we can check this into source control...

```bash
$ git add .travis.yml
$ git commit -m "Add .travis.yml" 
$ git push
```
And voila! Just like that, you should now see a build starting (and passing too hopefully)!

### Adding a Build Status Badge
Ever been researching something online and seen this...?

![Travis Build Passing](images/travis-build-passing.png)

You'll also notice that this appears above your latest build on Travis. It's actually super easy to add this to your GitHub page. Firstly, click on the image on the Travis webpage and click 'Markdown' from the list you are presented. Copy and paste this text into a `README.md` file and there you have it! A badge on your GitHub repository which dipslays the current build status.

```bash
$ echo "[![Build Status](https://travis-ci.org/YOUR_USERNAME/YOUR_REPO_NAME.svg?branch=master)](https://travis-ci.org/YOUR_USERNAME/YOUR_REPO_NAME)" > README.md
$ git add README.md
$ git commit -m "Add README"
$ git push
```

[1]:https://en.wikipedia.org/wiki/Continuous_integration
