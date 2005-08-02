

*******************************************
*      JHoover, the WebSite hoover!       *
*                                         *
*  Romain PAPUCHON                        *
*  Université de Marne La Vallée          *
*  Diplome d'ingénieur par apprentissage  *
*  Informatique et Réseau - 2ème année    *
*                                         *
*  Projet d'interface graphique           *
*  aout 2005                              *
*******************************************





Arborescence de l'application:
------------------------------
readme.txt
	explique la structure des répertoires, comment compiler et exécuter le projet

build.xml
	fichier pour compiler le projet

./bin
	fichiers de lancement de l'application (.bat sous Windows et .sh sous Unix)
	contient le fichier params.xml, fichier de congifuration de l'application

./docs
	contient les documentation utilisateur et développeur

./docs/api
	 contient la javadoc

./lib
	contient l'application jHoover et les librairies annexes utilisées

./src
	contient les sources .java du projet

./classes
	contient les fichiers .class de l'application





Compilation de jHoover:
-----------------------
Il y a 4 targets possibles avec l'application Ant et le fichier build.xml:

ant compile
	compilation de l'application (seulement les .class)

ant release
	création d'un fichier jar executable (défaut)

ant clean
	suppression des fichiers générés

ant javadoc
	génération de la javadoc





Execution de jHoover:
---------------------
Executer le fichier ./bin/jHoover.bat sous Windows ou ./bin/jHoover.sh sous Unix