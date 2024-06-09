# Welcome to cl1p.net v4 Project!
I am excited to announce that I am working on a new version of cl1p.net, which will be completely open source. I believe in building software that is accessible to everyone and encourages community collaboration. I invite all developers and contributors from around the world to join me in this endeavor.
You can contribute to our GitHub repository or follow updates on Twitter:
- https://blog.cl1p.net
- https://github.com/robmayhew/cl1p_v4
- https://x.com/cl1p

### June 9, 2024

Python / Django prototype is complete. It can create / view cl1p URLS. 

#### Pros

1. Framework really tries to help, and I like the server based rendering
2. Feels very solid. 

#### Cons

1. Python (It's just not my cup of tea)
2. Strange folder layout
3. penv is really weird, and just feels wrong.

### May 11, 2024

#### Names

Now that I know Django can handle the project. (I don’t see any blockers) I’m going to write a very basic version of cl1p.

One problem I had in Version 3, 2 and 1 was naming. Naming is one of the hardest things to get right. The first thing I’m going to do is add a txt file where I list names and what they mean. The hope is if I keep them all in the same place I should made some better decisions. (I’ll call it vocabulary)

I need a key for each allowed path on the site. I want to just use the word Path, but I know that will cause conflicts with the python language and frameworks I will end up using. It may be ugly, but a simple prefix to all models might be the way to go.

I’m going with RM my initials.

RMPathKey: Contains a path and a unique db ID

RMPathContent: The text content of the page

### April 23, 2024

#### Cl1p.net v4 platform choice

What is the best platform for building an app keeping in mind.

-	Database compatibility
-	Upgradability


cl1p.net v3 is written in java spring. So I’m able to leverage my professional java experience. But it can also just feel really heavy. So first I want to check out other platforms.

Up first. Python/Django

There is a lot I like about Django. It’s everything in one. Front End, Back End, ORM, and admin!

Going to try to write a simple Django app to read and write to a db for a cl1p url. 