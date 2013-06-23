$ECHO off
cd HomeBudgetWeb

echo "====== MAVEN CLEAN ======="
call mvn eclipse:clean
echo "====== MAVEN BUILD ======"
call mvn eclipse:eclipse -Dwtpversion=2.0
echo "====== MAVEN DOWNLOAD SOURCES JAVADOCS ======"
call mvn eclipse:eclipse -DdownloadSources=true -DdownloadJavadocs=true

echo "FINISH!"
cd ..