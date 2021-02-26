# CS400_P1
Group EB - Blue Team

Steps to clone to your machine and commit:

1. Make sure you have git installed on your local machine. You can run: `$ git --version` in terminal to check if you have it.

2. Create github account at https://github.com/ 

3. In your terminal, navigate to the directory on your machine where you want to store our project work, then run: git clone https://github.com/mcnugg3t/CS400_P1.git

4. I’ve already made branches for each of us to work in, based on our role names. Please do not make any changes in main. Once you have the repo downloaded, run one of the following:
  `$ git checkout FrontEnd`
  `$ git checkout BackEnd` or
  `$ git checkout DataWrangler` (depending on your role).

You should get the following message: `Branch <<BranchName>> set up to track remote branch <<BranchName>> from origin. Switched to a new branch <<BranchName>>.`

5. Note that the git repo includes a .gitignore file already configured for java, so when you’re ready to commit the files in your branch, you can run:
 
  `$ git pull` (this shouldn’t be necessary for us, since we’re working in separate branches, but I think it’s a good habit to always pull before committing)

  `$ git branch` (to check you’re in the correct branch - should be your role name)

  `$ git add .` adds everything in current branch, ignoring according to .gitignore file
  
  `$ git commit -m “descriptive message goes here”` (commits everything in your local repo that's not being ignored)
  
  `$ git push -u origin YourBranchName` (pushes your changes to the shared repo stored on github.com)
