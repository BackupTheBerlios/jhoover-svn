

*******************************************
*      JHoover, the WebSite hoover!       *
*                                         *
*  Romain PAPUCHON                        *
*  Universit� de Marne La Vall�e          *
*  Diplome d'ing�nieur par apprentissage  *
*  Informatique et R�seau - 2�me ann�e    *
*                                         *
*  Projet d'interface graphique           *
*  aout 2005                              *
*******************************************





Arborescence de l'application:
------------------------------
readme.txt
	explique la structure des r�pertoires, comment compiler et ex�cuter le projet

build.xml
	fichier pour compiler le projet

./bin
	fichiers de lancement de l'application (.bat sous Windows et .sh sous Unix)
	contient le fichier params.xml, fichier de congifuration de l'application

./docs
	contient les documentation utilisateur et d�veloppeur

./docs/api
	 contient la javadoc

./lib
	contient l'application jHoover et les librairies annexes utilis�es

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
	cr�ation d'un fichier jar executable (d�faut)

ant clean
	suppression des fichiers g�n�r�s

ant javadoc
	g�n�ration de la javadoc





Execution de jHoover:
---------------------
Executer le fichier ./bin/jHoover.bat sous Windows ou ./bin/jHoover.sh sous Unix