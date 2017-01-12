# Continuous Integration Workshop
This repository contains the learning materials for the workshop on how to create a continuous integration workflow. The skills being taught apply to a variety of languages, tools and setups. But Gradle, Java, Git and Travis CI form the foundation of this workshop.

## What will be covered

- [What is Continuous Integration?](#what-is-continuous-integration-ci)
- What needs to be achieved to get a CI workflow?
- What else can be done with CI?
- CI example workflow
- Downsides to CI

## What is Continuous Integration (CI)?
[Wikipedia][1] tells us that:

>In software engineering, continuous integration (CI) is the practice of merging all developer working copies to a shared mainline several times a day

This however, although true, doesn't really tell us:
 - What CI is trying to achieve and solve
 - How to go about doing CI
 - What the natural consequences of a CI workflow are (both good and bad)
 
 So, the main the thing CI is trying to achieve, is the prevention of "integration problems". This is when one finds themselves working in a team of programmers on a shared code base, but the copy of code each programmer is working on is so different, that merging everything will be nightmare for oneself.
 
 The idea is that: if we have a CI workflow, that is one where the code is merged into a mainline several times day, any kind of merge problems that do arrise will be so small that they will be easy to resolve, and they will occur less often.
 
 However, achieving that workflow takes more than a top-down command demanding that it be done from the management, it requires many other problems to be solved before it can be achieved. It has to be easy to do, otherwise it will detract from other aspects from development.
 
 On the other hand, once it has been achieve, other neat bonuses spring out, like the ability to regularly test contributions to the shared mainline, test the mainline itself and of course, have newer features in the software tested more quickly by people, as the entire team will be using them sooner rather than later.


[1]:https://en.wikipedia.org/wiki/Continuous_integration
