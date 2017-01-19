# Continuous Integration Workshop

This repository contains the learning materials for the workshop on how to
create a continuous integration workflow. The skills being taught apply to a
variety of languages, tools and setups. But Gradle, Java, Git and Travis CI form
the foundation of this workshop.

The feedback form is available [here](https://goo.gl/forms/qyntiFjcL56Kbiq83)

Ask questions live [here](http://goo.gl/slides/mueqjk)

## Requirements and Preparation

If you want to follow along in the workshop, please make sure you have the
following installed on your machine:


 - Git
   - Windows:
     - https://git-scm.com/download/win
   - Mac:
     - https://git-scm.com/download/mac
   - Linux:
     - Use your favorite package manager!
 - Java JDK:
   - [Download Here](http://www.oracle.com/technetwork/java/javase/downloads/jdk8-downloads-2133151.html)
 - Gradle
   - Windows:
     - https://gradle.org/gradle-download/ (Download complete or binary-only
       distro)
     - Follow the following instructions given here:
       http://stackoverflow.com/a/37774085/5460161
   - Mac:
      - Use Homebrew: http://brewformulas.org/Gradle
   - Linux:
      - Ubuntu: `sudo apt-get install gradle`
 - Your favorite text editor or IDE!
   - We will be using [Atom](https://atom.io/), but what you use is not
      important as long as you are comfortable.

Also make sure you have an account on [this website](https://github.com/join),
GitHub!


## What will be covered

- [What is Continuous Integration?](#what-is-continuous-integration-ci)
- What needs to be achieved to get a CI workflow?
- What else can be done with CI?
- CI example workflow
- Downsides to CI

## What is Continuous Integration (CI)?

[Wikipedia][1] tells us that: In software engineering, continuous integration
(CI) is the practice of merging all developer working copies to a shared
mainline several times a day

This however, although true, doesn't really tell us:
 - What CI is trying to achieve and solve
 - How to go about doing CI
 - What the natural consequences of a CI workflow are (both good and bad)

 So, the main the thing CI is trying to achieve, is the prevention of
 "integration problems". This is when one finds themselves working in a team of
 programmers on a shared code base, but the copy of code each programmer is
 working on is so different, that merging everything will be nightmare for
 oneself.

 The idea is that: if we have a CI workflow, that is one where the code is
 merged into a mainline several times day, any kind of merge problems that do
 arrise will be so small that they will be easy to resolve, and they will occur
 less often.

 However, achieving that workflow takes more than a top-down command demanding
 that it be done from the management, it requires many other problems to be
 solved before it can be achieved. It has to be easy to do, otherwise it will
 detract from other aspects from development.

 On the other hand, once it has been achieve, other neat bonuses spring out,
 like the ability to regularly test contributions to the shared mainline, test
 the mainline itself and of course, have newer features in the software tested
 more quickly by people, as the entire team will be using them sooner rather
 than later.

## Getting started

This workshop can be completed in either pairs or individually.

As part of CI is working in a team, we are going to setup a project on GitHub
that can be collaborated on. To do this, sign in to GitHub and click on the "+"
button and select "New organisation". This essentially creates a new user, which
can be controlled by team. If you are in pair, one of you should do this and add
the other to the organisation.

Then click "Create new repository".


[1]:https://en.wikipedia.org/wiki/Continuous_integration
