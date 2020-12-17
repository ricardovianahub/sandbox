docker images | awk '{if ($2 == "<none>") system("docker rmi -f " $3)}'
docker images
