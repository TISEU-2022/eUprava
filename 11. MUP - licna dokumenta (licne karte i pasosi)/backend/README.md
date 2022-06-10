# TISEU-2022
Projekat na nivou celog smera koji simulira elektronsku upravu.

## GIT PRAVILA
1. Zabranjeno je pushovanje na MAIN granu, samo tim MATICAR ima access ka MAIN grani
2. Kolaboratori moraju praviti svoje posebne grane za svoje projekte i praviti pull request za svaku promenu ka MAIN grani

## PRAVILNO PISANJE GIT KOMITOVA
  COMMIT-MESSAGE should:
    Start with capital letter (Implement login instead of implement login)
    Summarize changes in around 50 characters or less
 
  Use the imperative mode
    3.1. Fix bug and not Fixed bug or Fixes bug
    3.2. Implement login instead of Implementing login or implementation of login

  Remove unnecessary punctuation marks
  Do not end the subject line with a period (Implement login instead of Implement login.)
  
COMMIT-DESCRIPTION should:
  Explain what changes you have made and why you made them (explain what and why vs. how)
  1.1. Focus on why you are making this change as opposed to how (the code explains that)
    Do not assume the reviewer understands what the original problem was
    Do not assume the code is self-evident/self-documenting
  
To get this kind of git commit structure use the following command if you work via terminal:
git commit -m "COMMIT-MESSAGE" -m "COMMIT-DESCRIPTION"


## Mapiranje portova

Pozeljno je da se svi drze sledeceg mapiranja portova prilikom unosa svojih servisa
u docker-compose fajl u root direktorijumu radi lakseg snalazenja

Primer:
maticar - tim br 4 - **prvi broj porta je broj tima**

```port frontend: 4001, port backend: 4002, port baza: 4003```

Ukoliko imate jos opcionih servisa, slobodno inkrementirajte dalje(4004,4005...)
