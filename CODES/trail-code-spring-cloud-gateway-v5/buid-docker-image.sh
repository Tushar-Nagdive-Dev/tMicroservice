#!/bin/zsh

echo "configserver build ...." 
cd configserver/
./mvnw compile jib:dockerBuild

echo "\n"
cd ../eurekaserver/
./mvnw compile jib:dockerBuild

echo "\n"
echo "accounts"
cd ../accountwithpostgresql/
./mvnw compile jib:dockerBuild

echo "\n"
echo "cards"
cd ../cardswithprostgresql/
./mvnw compile jib:dockerBuild

echo "\n"
echo "loans"
cd ../loanswithprostgresql/
./mvnw compile jib:dockerBuild

echo "\n"
echo "gateway"
cd ../gatewayserver/
./mvnw compile jib:dockerBuild
