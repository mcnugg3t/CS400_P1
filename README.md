# CS400_P1
Group EB - Blue Team

Steps to clone to your machine and commit:

1. Make sure you have git installed on your local machine. You can run: `$ git --version`        in terminal to check if you have it.
2. Create github account at https://github.com/ 
3. In your terminal, navigate to the directory on your machine where you want to store our project work, then run: git clone https://github.com/mcnugg3t/CS400_P1.git
4. I’ve already made branches for each of us to work in, based on our role names. Please do not make any changes in main - it’s not the end of the world if you do, since we can revert back, but it will make extra work for us as a group. Recall that `$ git checkout BranchName` gets you working in branch BranchName.
5. Note that the git repo includes a .gitignore file already configured for java, so when you’re ready to commit the files in your branch, you can run:
 
  $ git pull (this shouldn’t be necessary for us, since we’re working in separate branches, but I think it’s a good habit to always pull before committing)

  $ git branch (to check you’re in the correct branch - should be your role name)
  
  $ git commit -am “descriptive message goes here” (adds and commits everything in your local repo)
  
  $ git push --set-upstream origin YourBranchName (pushes your changes to the shared repo stored on github.com)
