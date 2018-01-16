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
